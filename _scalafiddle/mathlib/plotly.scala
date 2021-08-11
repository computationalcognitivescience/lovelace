import scalatags.JsDom.all._

case object Plotly {

  var plotCounter = 0

  case object PlotType extends Enumeration {
    type PlotType = Value
    val Line, Bar, Scatter = Value
  }

  case class Trace(data: List[(Double, Double)], name: String, plotType: PlotType.PlotType = PlotType.Scatter) {
    val markers = if(plotType == PlotType.Scatter) "\"mode\": \"markers\"," else ""

    def mean: Trace = {
      def calcMean(xs: Iterable[Double]) = xs.sum / xs.size

      val meanData = data.toMap.groupBy(_._1)
        .mapValues(xs => calcMean(xs.map(_._2)))
        .toList

      Trace(meanData, name, plotType)
    }

    def toJSON: String = {
      s"""
      {
        "name": "$name",
        "type": "${plotType.toString.toLowerCase}",
        $markers
        "x": ${data.sortBy(_._1).map(_._1).mkString("[",",","]")},
        "y": ${data.sortBy(_._1).map(_._2).mkString("[",",","]")}
      }
      """
    }
  }

  case class Plot(traces: List[Trace], xAxisTitle: String = "", yAxisTitle: String = "") {
    val xAxis = if(!xAxisTitle.isEmpty)
      s"""
      ,"xaxis": {
        "title": {
          "text": "$xAxisTitle"
        }
      }
      """
    else ""
    val yAxis = if(!yAxisTitle.isEmpty)
      s"""
      ,"yaxis": {
        "title": {
          "text": "$yAxisTitle"
        }
      }
      """
    else ""

    def toJSON: String = {
      s"""
      {
        "data": ${traces.map(_.toJSON).mkString("[",",","]")},
        "layout": {
          "showlegend": "true",
          "legend": {
            "x": 1,
            "xanchor": "right",
            "y": 1
          }
          $xAxis
          $yAxis
        }
      }
      """
    }

    def render: Unit = Plotly.render(this.toJSON)
  }

  def render(plotJson: String): Unit = {
    Fiddle.print(
	  div(id:=s"plot$plotCounter"),
	  script(s"""
  	    var script = document.createElement('script');
	    script.onload = function () {
		  requirejs.config({
		    baseUrl: 'https://cdn.jsdelivr.net/npm/',
		    paths: {
			  "plotly": "plotly.js@1.58.4/dist/plotly.min.js?noext"
		    }
		  });

	    require(["plotly"], function(plotly) {
		  const figure = JSON.parse('${plotJson.filter(_ >= ' ')}');
		  plotly.newPlot('plot$plotCounter', figure.data, figure.layout).catch(console.warn);
	    });
	  };

	  script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
	  document.head.appendChild(script);
	  """)
    )
    plotCounter = plotCounter + 1
  }
}

import Plotly._
