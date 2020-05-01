---
layout: index
title: Home
permalink: /
sidebar_link: true
sidebar_sort_order: 1
---

<div id="toc-wrapper" markdown="1">
* Table of contents
{:toc}
</div>

# Introduction
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.


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

render(plotSeries(List(Trace("data1", data1), Trace("data2", data2)),
  "a", "xAxis", "b", "yAxis", "myTitle", PlotType.Line))
```
{% endscalafiddle %}

## Using $$\LaTeX$$

Does it work by default? $$x^2$$

Nope, but I got it working anyway.

# Third unit

Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

# stuff
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

# more stuff
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

# even more stuff
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
