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

  case class Graph(nodes: List[Any], edges: List[(Any, Any, String)]) {
    private def toVegaData: (Trace, Trace) = {
      val nDat: List[Map[String, Any]] = (nodes zip nodes.indices).map(ni => Map("lab" -> ni._1.toString, "id" -> ni._2, "maxId" -> nodes.length))
      val nodesTrace = Trace("node", nDat)
      val eDat: List[Map[String, Any]] = edges.map(e => Map("id1" -> nodes.indexOf(e._1), "id2" -> nodes.indexOf(e._2), "lab" -> e._3, "maxId" -> nodes.length))
      val edgesTrace = Trace("edge", eDat)
      (nodesTrace, edgesTrace)
    }

    def toVegaString: String = toVegaData._1.toVegaString + ",\n" + toVegaData._2.toVegaString
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

  def render(graph: Graph): Unit = render(genGraphSpec(graph))

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
      "height": 340,
      "autosize": {
        "type": "fit",
        "contains": "padding"
      },
      "data": { "values": [
          ${traces.map(_.toVegaString).mkString(",\n")}
      ]},
      """ + {
        if(plotType==PlotType.Bar && traces.length==1) {
          s"""
          "width": 300,
          "mark": "bar",
          "encoding": {
            "x": {"field": "$xValue", "type": "ordinal"},
            "y": {"field": "$yValue", "type": "quantitative"},
            "color": {
              "field": "label",
              "type": "nominal",
              "legend": {"orient": "bottom", "title": null}
            }
          }
          """
        } else if(plotType==PlotType.Bar && traces.length>1) {
          s"""
          "width": 10,
          "mark": "bar",
          "encoding": {
            "column": {
              "field": "$xValue", "type": "nominal", "spacing": 0
            },
            "x": {"field": "label", "type": "ordinal", "axis": {"title": ""}},
            "y": {"field": "$yValue", "type": "quantitative"},
            "color": {
              "field": "label",
              "type": "nominal",
              "legend": null
            }
          }
          """
        } else if(plotType==PlotType.Line || plotType==PlotType.Point) {
          s"""
          "width": 300,
          "mark": "${plotType.toString.toLowerCase}",
          "encoding": {
            "x": {"field": "$xValue", "type": "ordinal"},
            "y": {"field": "$yValue", "aggregate": "mean", "type": "quantitative"},
            "color": {
              "field": "label",
              "type": "nominal",
              "legend": {"orient": "bottom", "title": null}
            }
          }
          """
        }
      }
  }.replace(" ","").replace("\n","")

  def genGraphSpec(graph: Graph): String = {
    s"""
      "$$schema": "https://vega.github.io/schema/vega-lite/v4.json",
      "width": 300,
      "height": 340,
      "autosize": {
        "type": "fit",
        "contains": "padding"
      },
      "data": {
        "values": [
              ${graph.toVegaString}
            ]
      },
      "layer": [
            {
            "transform": [
                  {"filter": "datum.label == 'edge'"},
                  {"calculate": "sin(datum.id1 / datum.maxId * 2 * PI)+1.5", "as": "x"},
                  {"calculate": "cos(datum.id1 / datum.maxId * 2 * PI)+1.5", "as": "y"},
                  {"calculate": "sin(datum.id2 / datum.maxId * 2 * PI)+1.5", "as": "x2"},
                  {"calculate": "cos(datum.id2 / datum.maxId * 2 * PI)+1.5", "as": "y2"}
                ],
            "mark": {
                "type": "rule",
                "size": 3
            },
            "encoding": {
                "x": {"field": "x", "type": "quantitative", "axis": null, "scale": {"domain": [0, 3]}},
                "y": {"field": "y", "type": "quantitative", "axis": null, "scale": {"domain": [0, 3]}},
                "x2": {"field": "x2", "type": "quantitative"},
                "y2": {"field": "y2", "type": "quantitative"},
                "color": {
                  "field": "lab",
                  "type": "nominal",
                  "scale": {"scheme": "set1"},
                  "legend": {"orient": "bottom", "title": null}
                }
            }
          },
          {
            "transform": [
                {"filter": "datum.label == 'node'"},
                {"calculate": "sin(datum.id / datum.maxId * 2 * PI)+1.5", "as": "x"},
                {"calculate": "1.2*sin(datum.id / datum.maxId * 2 * PI + 0.1)+1.5", "as": "dx"},
                {"calculate": "cos(datum.id / datum.maxId * 2 * PI)+1.5", "as": "y"},
                {"calculate": "1.2*cos(datum.id / datum.maxId * 2 * PI + 0.1)+1.5", "as": "dy"}
            ],
            "layer": [
                {
                    "encoding": {
                        "x": {"field": "x", "type": "quantitative", "axis": null},
                        "y": {"field": "y", "type": "quantitative", "axis": null}
                    },
                    "mark": {
                        "type": "circle",
                        "opacity": 1,
                        "size": 200
                    }
                },
                {
                    "mark": {
                        "type": "text",
                        "baseline": "middle"
                    },
                    "encoding": {
                        "x": {"field": "dx", "type": "quantitative", "axis": null},
                        "y": {"field": "dy", "type": "quantitative", "axis": null},
                        "text": {"field": "lab", "type": "nominal"}
                    }
                }
            ]
          }
      ]
    """
  }
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

      def argMax(f: A => Double): Option[A] = {
        val seq = set.toSeq // convert to sequence to preserve ordering in zip function
        val valSeq = seq map f
        val maxValue = valSeq.max
        val maxValSet = seq zip valSeq filter (_._2 == maxValue)
        if(maxValSet.nonEmpty) Some(maxValSet(new Random().nextInt(maxValSet.length))._1) // if one or more maxima exist return random
        else None
      }

      def argMaxOrElse(f: A => Double)(fallback: A): A = {
        val seq = set.toSeq // convert to sequence to preserve ordering in zip function
        val valSeq = seq map f
        val maxValue = valSeq.max
        val maxValSet = seq zip valSeq filter (_._2 == maxValue)
        if(maxValSet.nonEmpty) maxValSet(new Random().nextInt(maxValSet.length))._1 // if one or more maxima exist return random
        else fallback
      }

      def random: A = set.toList(Random.nextInt(set.size))
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

    def requirement(b: Boolean, msg: String): Unit =
      if(!b) {
        println(s"Requirement not met: $msg")
        assert(false)
      }
  }

import Math._
import VegaRenderer._


case class Person(name: String) {
  def likes(other: Person): Relation = Relation(this, other, true)
  def dislikes(other: Person): Relation = Relation(this, other, false)

  override def toString: String = name
}

case object Person {
    val names = List("Nettie","Lester","Brian","Cody","Erik","William","Molly","Joey","Thelma","Edgar","Emanuel","Sergio","Herman","Kelley","Wilfred","Guadalupe","Paula","Sheila","Javier","Kelly","Jason","Gilbert","Harriet","Meghan","Kenneth","Holly","Rose","Lela","Brenda","Constance","Vera","Ramiro","Diana","Charlene","Betty","Michelle","Frederick","Elmer","Byron","Randal","Roderick","Clark","Mathew","Sammy","Colleen","Marian","Tyrone","Keith","Tonya","John","Kayla","Johanna","Dwayne","Antonia","Kerry","Fannie","Nichole","Jeanne","Roberto","Vicky","Jesus","Angela","Fredrick","Fernando","Vivian","Natalie","Johnnie","Monica","Angelica","Anna","Carlos","Marion","Henry","Lawrence","Alexis","Garry","Bernard","Jana","Ernestine","Deborah","Willard","Eileen","Erica","Elvira","Myron","Elena","Ervin","Jeannette","Veronica","Abraham","Lamar","Wanda","Lorraine","Doris","Leigh","Devin","Lindsay","Isabel","Marlene","Betsy")
    def random: Person = Person(names(Random.nextInt(names.length)))
    def randomSet(size: Int): Set[Person] = List.tabulate(size)(_ => Person.random).toSet
}

case class Relation(a: Person, b: Person, liking: Boolean) {
    def canEqual(a: Any) = a.isInstanceOf[Relation]

    override def equals(that: Any): Boolean = that match {
        case that: Relation => {
            this.liking == that.liking && (this.a == that.a && this.b == that.b || this.a == that.b && this.b == that.a)
        }
        case _ => false
    }
}

object Helpers {
  import Math._

  implicit class ImplRelation(personA: String) {
    def likes(personB: String): Relation = Relation(Person(personA), Person(personB), true)

    def dislikes(personB: String): Relation = Relation(Person(personA), Person(personB), false)
  }

  implicit class ImplRelFun(relations: Set[Relation]) {
    def deriveFun: ((Person, Person) => Boolean) = {
      (a: Person, b: Person) => {
        val rel = relations.find(p => p.a == a && p.b == b || p.a == b && p.b == a)
        if(rel.isDefined) rel.get.liking
        else false
      }
    }

    def deriveGraph(persons: Set[Person]): Graph = {
      val edges = for(p1 <- persons; p2 <- persons if p1 != p2) yield {
        val col = if(deriveFun(p1, p2)) "likes" else "dislikes"
        (p1, p2, col)
      }
      Graph(persons.toList, edges.toList)
    }
  }
}

import Math._
import Helpers._

////
