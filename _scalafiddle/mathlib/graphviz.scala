import scalatags.JsDom.all._

case object Viz {

  var vizCounter = 0

  def render(dot: String): Unit = {
    Fiddle.print(
	  div(id:=s"plot$vizCounter"),
	  script(s"""
  	    var script = document.createElement('script');
	    script.onload = function () {
		  requirejs.config({
		    baseUrl: 'http://webgraphviz.com/',
		    paths: {
			  "viz": "viz"
		    }
		  });

	    require(["viz"], function(viz) {
  		  const figure = '${dot.filter(_ >= ' ')}';
  		  var svg = Viz(figure, "svg");
  		  document.getElementById('plot$vizCounter').innerHTML = svg;
	    });
	  };

	  script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
	  document.head.appendChild(script);
	  """)
    )
    vizCounter = vizCounter + 1
  }
}


