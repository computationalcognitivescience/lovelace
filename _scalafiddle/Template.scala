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

////
