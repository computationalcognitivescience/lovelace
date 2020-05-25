import scala.util.Random
import scalatags.JsDom.all._

/**
  * This renderer allows the user to render a Vega plot specification. The specifying
  * must be passed as a String and will be rendered in a div.
  */
case object VegaRenderer {
  case object PlotType extends Enumeration {
    type PlotType = Value
    val Line, Bar, Point = Value
  }

  case class Trace(label: String, data: List[Map[String, Any]]) {
    def toVegaString: String = data.map(convertValues(label, _)).mkString(", ")
  }

  def convertValues(label: String, values: Map[String, Any]): String =
    values.toSeq.map(v => {
      if(v._2.isInstanceOf[String])
        s""""${v._1}": "${v._2}""""
      else
        s""""${v._1}": ${v._2}"""
    }).
    mkString(s"""{"label": "$label",""",",","}")

  import PlotType._

  var plotCounter = 0

  def render(vegaSpec: String): Unit = {
    Fiddle.print(
      div(id:=s"plot$plotCounter", s"Rendering plot $plotCounter..."),
      script(s"""
        var script = document.createElement('script');
        script.onload = function () {
          requirejs.config({
            baseUrl: 'https://cdn.jsdelivr.net/npm/',
            paths: {
              "vega-embed":  "vega-embed@3?noext",
              "vega-lib": "vega-lib?noext",
              "vega-lite": "vega-lite@2?noext",
              "vega": "vega@3?noext"
            }
          });

        require(["vega-embed"], function(vegaEmbed) {
          const spec = {
            $vegaSpec
          };
          vegaEmbed('#plot$plotCounter', spec, {defaultStyle: true, renderer: "svg"}).catch(console.warn);
        });
      };

      script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
      document.head.appendChild(script);
      """)
    )
    plotCounter = plotCounter + 1
  }

  def render(traces: List[Trace],
    xValue: String,
    xLabel: String,
    yValue: String,
    yLabel: String,
    title: String,
    plotType: PlotType = PlotType.Line): Unit =
      render(
        genSpec(
          traces,
          xValue,
          xLabel,
          yValue,
          yLabel,
          title,
          plotType
        )
      )

  def genSpec(traces: List[Trace],
    xValue: String,
    xLabel: String,
    yValue: String,
    yLabel: String,
    title: String,
    plotType: PlotType = PlotType.Line): String = {
      s"""
      "$$schema": "https://vega.github.io/schema/vega-lite/v4.json",
      "data": { "values": [
          ${traces.map(_.toVegaString).mkString(",\n")}
      ]},
      """ + {
        if(plotType==PlotType.Bar && traces.length==1) {
          s"""
          "mark": "bar",
          "encoding": {
            "x": {"field": "$xValue", "type": "ordinal"},
            "y": {"field": "$yValue", "type": "quantitative"},
            "color": {"field": "label", "type": "nominal"}
          }
          """
        } else if(plotType==PlotType.Bar && traces.length>1) {
          s"""
          "mark": "bar",
          "encoding": {
            "column": {
              "field": "$xValue", "type": "nominal", "spacing": 10
            },
            "x": {"field": "label", "type": "ordinal", "axis": {"title": ""}},
            "y": {"field": "$yValue", "type": "quantitative"},
            "color": {"field": "label", "type": "nominal"}
          }
          """
        } else if(plotType==PlotType.Line || plotType==PlotType.Point) {
          s"""
          "mark": "${plotType.toString.toLowerCase}",
          "encoding": {
            "x": {"field": "$xValue", "type": "ordinal"},
            "y": {"field": "$yValue", "type": "quantitative"},
            "color": {"field": "label", "type": "nominal"}
          }
          """
        }
      }
  }.replace(" ","").replace("\n","")
}

/**
  * Implementation of basic set theory as implicits
  */
  object Math {
    implicit class ImplSet[A](set: Set[A]) {
      // for set membership, use set.contains(element)

      def isSubsetOf(set2: Set[A]): Boolean = set != set2 && set.subsetOf(set2)

      def isSubsetEqTo(set2: Set[A]): Boolean = set.subsetOf(set2)

      def isSupersetOf(set2: Set[A]): Boolean = set2 isSubsetOf set

      def isSupersetEqTo(set2: Set[A]): Boolean = set2 isSubsetEqTo set

      // for intersection use set.intersection(set2)

      // for union use set.union(set2)

      // for difference use set.diff(set2)

      def build(f: A => Boolean): Set[A] = set.filter(f(_))

      def diff(set2: Set[A]): Set[A] = (set diff set2) union (set2 diff set)

      def cardinalProduct[B](set2: Set[B]): Set[(A,B)] =
        for(x <- set; y <- set2) yield (x,y)

      def cardinalProduct[B](set2: Set[B], condition: (A, B) => Boolean): Set[(A,B)] =
        for(x <- set; y <- set2 if condition(x,y)) yield (x,y)

      def pairs: Set[(A,A)] = for(x <- set; y <- set) yield (x,y)

      def uniquepairs: Set[(A,A)] = for(x <- set; y <- set if x!=y) yield (x,y)

      def powerset: Set[Set[A]] = set.subsets.toSet

      def argmax(f: A => Double): Option[A] = {
        val seq = set.toSeq // convert to sequence to preserve ordering in zip function
        val valSeq = seq map f
        val maxValue = valSeq.max
        val maxValSet = seq zip valSeq filter (_._2 == maxValue)
        if(maxValSet.nonEmpty) Some(maxValSet(new Random().nextInt(maxValSet.length))._1) // if one or more maxima exist return random
        else None
      }
    }

    implicit class Impl2Set[A,B](sets: Tuple2[Set[A],Set[B]]) {
      // Example (set, set2) build((a: Int, b: Int) => a/2==0 && b%2==0)
      def build(f: (A, B) => Boolean): Set[(A,B)] =
        (sets._1 cardinalProduct sets._2) build Function.tupled(f)
    }

    implicit class ImplSetSet[A](setOfSets: Set[Set[A]]) {
      def bigUnion: Set[A] =
        if(setOfSets.nonEmpty) setOfSets.reduce(_ union _) else Set.empty

      def bigIntersection: Set[A] =
        if(setOfSets.nonEmpty) setOfSets.reduce(_ intersect _) else Set.empty
    }
  }

import Math._
import VegaRenderer._

////
