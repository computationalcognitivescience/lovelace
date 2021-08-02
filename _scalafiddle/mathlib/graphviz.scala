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
  		    baseUrl: 'https://unpkg.com/',
  		    paths: {
            "d3-array": "d3-array@latest/dist/d3-array.min",
            "d3-axis": "d3-axis@latest/dist/d3-axis.min",
            "d3-brush": "d3-brush@latest/dist/d3-brush.min",
            "d3-chord": "d3-chord@latest/dist/d3-chord.min",
            "d3-color": "d3-color@latest/dist/d3-color.min",
            "d3-contour": "d3-contour@latest/dist/d3-contour.min",
            "d3-delaunay": "d3-delaunay@latest/dist/d3-delaunay.min",
            "d3-dispatch": "d3-dispatch@latest/dist/d3-dispatch.min",
            "d3-drag": "d3-drag@latest/dist/d3-drag.min",
            "d3-dsv": "d3-dsv@latest/dist/d3-dsv.min",
            "d3-ease": "d3-ease@latest/dist/d3-ease.min",
            "d3-fetch": "d3-fetch@latest/dist/d3-fetch.min",
            "d3-force": "d3-force@latest/dist/d3-force.min",
            "d3-format": "d3-format@latest/dist/d3-format.min",
            "d3-geo": "d3-geo@latest/dist/d3-geo.min",
            "d3-hierarchy": "d3-hierarchy@latest/dist/d3-hierarchy.min",
            "d3-interpolate": "d3-interpolate@latest/dist/d3-interpolate.min",
            "d3-path": "d3-path@latest/dist/d3-path.min",
            "d3-polygon": "d3-polygon@latest/dist/d3-polygon.min",
            "d3-quadtree": "d3-quadtree@latest/dist/d3-quadtree.min",
            "d3-random": "d3-random@latest/dist/d3-random.min",
            "d3-scale": "d3-scale@latest/dist/d3-scale.min",
            "d3-scale-chromatic": "d3-scale-chromatic@latest/dist/d3-scale-chromatic.min",
            "d3-selection": "d3-selection@latest/dist/d3-selection.min",
            "d3-shape": "d3-shape@latest/dist/d3-shape.min",
            "d3-time": "d3-time@latest/dist/d3-time.min",
            "d3-time-format": "d3-time-format@latest/dist/d3-time-format.min",
            "d3-timer": "d3-timer@latest/dist/d3-timer.min",
            "d3-transition": "d3-transition@latest/dist/d3-transition.min",
            "d3-zoom": "d3-zoom@latest/dist/d3-zoom.min",
            "d3": "d3@latest/dist/d3.min",
            "@hpcc-js/wasm": "@hpcc-js/wasm@latest/dist/index.min",
    			  "graphviz": "d3-graphviz@latest/build/d3-graphviz"
  		    }
  		  });

  	    require(["d3", "graphviz"], function(d3, viz) {
    		  const dotString = '${dot}';
          viz.graphviz('#plot$vizCounter')
            .renderDot(dotString);
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
