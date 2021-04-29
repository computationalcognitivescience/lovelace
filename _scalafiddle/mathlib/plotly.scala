import scalatags.JsDom.all._

case object Plotly {

  var plotCounter = 0

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