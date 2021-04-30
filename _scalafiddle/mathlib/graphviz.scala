import scalatags.JsDom.all._

case object Viz {

  var vizCounter = 0

  def renderAlt(dot: String): Unit = {
    Fiddle.print(
	  div(id:=s"plot$vizCounter"),
	  script(s"""
  	    var script = document.createElement('script');
	    script.onload = function () {
		  requirejs.config({
		    baseUrl: 'https://unpkg.com/vis-network/standalone/umd/',
		    paths: {
			  "vis": "vis-network.min"
		    }
		  });

	    require(["vis"], function(vis) {
  		  const dotString = '${dot}';
		  var parsedData = vis.parseDOTNetwork(dotString);
		  var data = {
			  nodes: parsedData.nodes,
			  edges: parsedData.edges
			}
		  var options = parsedData.options;
  		  var container = document.getElementById('plot$vizCounter');
		  var network = new vis.Network(container, data, options);
	    });
	  };

	  script.src = "https://requirejs.org/docs/release/2.3.6/minified/require.js";
	  document.head.appendChild(script);
	  """)
    )
    vizCounter = vizCounter + 1
  }

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
  		  const dotString = '${dot}';
  		  var svg = Viz(dotString, "svg");
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


