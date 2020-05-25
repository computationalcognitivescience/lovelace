---
layout: index
title: Chapter 1 - Scala
permalink: /chapter1/
sidebar_link: true
sidebar_sort_order: 1
---

<div id="toc-wrapper" markdown="1">
* Table of contents
{:toc}
</div>

# Introduction
You can embed scala using scalafiddle:

{% scalafiddle template="Template", layout="v50" %}
```scala
println("Hello world!")
```
{% endscalafiddle %}


# Simple bar chart
We can do plots via Vega-lite by passing any spec string:

{% scalafiddle template="Template", minheight="800", layout="v65" %}
```scala
VegaRenderer.render(
"""
    $schema: 'https://vega.github.io/schema/vega-lite/v2.0.json',
    description: 'A simple bar chart with embedded data.',
    data: {
      values: [
        {a: 'A', b: 28},
        {a: 'B', b: 55},
        {a: 'C', b: 43},
        {a: 'D', b: 91},
        {a: 'E', b: 81},
        {a: 'F', b: 53},
        {a: 'G', b: 19},
        {a: 'H', b: 87},
        {a: 'I', b: 52}
      ]
    },
    mark: 'bar',
    encoding: {
      x: {field: 'a', type: 'ordinal'},
      y: {field: 'b', type: 'quantitative'}
    }
"""
)
```
{% endscalafiddle %}

# Simple line plot
We can do more advanced plotting using the scala functionality:

{% scalafiddle template="Template", minheight="800", layout="v50" %}
```scala

import VegaRenderer._

val data1: List[Map[String, Any]] = List(
  Map("a" -> "A", "b" -> 28), Map("a" -> "B", "b" -> 55), Map("a" -> "C", "b" -> 43),
  Map("a" -> "D", "b" -> 91.4), Map("a" -> "E", "b" -> 81), Map("a" -> "F", "b" -> 53),
  Map("a" -> "G", "b" -> 19), Map("a" -> "H", "b" -> 87), Map("a" -> "I", "b" -> 52)
  )

val data2: List[Map[String, Any]] = List(
  Map("a" -> "A", "b" -> 51), Map("a" -> "B", "b" -> 75), Map("a" -> "C", "b" -> 14),
  Map("a" -> "D", "b" -> 11), Map("a" -> "E", "b" -> 43), Map("a" -> "F", "b" -> 33),
  Map("a" -> "G", "b" -> 9), Map("a" -> "H", "b" -> 78), Map("a" -> "I", "b" -> 72)
)

render(traces = List(Trace("data1", data1), Trace("data2", data2)),
       xValue = "a",
       xLabel = "xAxis",
       yValue = "b",
       yLabel = "yAxis",
       title = "myTitle",
       plotType = PlotType.Line)
```
{% endscalafiddle %}
