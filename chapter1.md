---
layout: index
title: Introduction to theory
permalink: /chapter1/
sidebar_link: true
sidebar_sort_order: 2
---

<div id="toc-wrapper" markdown="1">
* Table of contents
{:toc}
</div>

# Theory version 1
<span style="font-variant:small-caps;">Theory version 1</span><br/>
*Input:* A set of people $$P$$, some of whom you like ($$L \subseteq P$$) and some of whom you dislike ($$D \subseteq P$$), and a relationship $$r: P \times P \rightarrow \{like, dislike\}$$ specifying for each pair of persons $$(p_i, p_j) \in P$$ whether or not they like each other.<br/>
*Output:* A set of invited guests $$G \subseteq L$$ that all like each other (i.e., for each pair $$(p_i, p_j)$$ with $$p_i,p_j \in G$$, $$r(p_i,p_j) = like$$).

Note that there may be multiple possible outputs consistent for any given input, our simulation below generates all of them.

{% scalafiddle template="Persons" %}
```scala
def theoryV1(p: Set[Person],
             l: Set[Person],
             d: Set[Person],
             r: (Person, Person) => Boolean): Set[Person] = {
    assert(l.subsetOf(p), "l is not a subset of p")
    assert(d.subsetOf(p), "d is not a subset of p")

    val solutions = (for(subset <- l.subsets if forallUniquePairs(subset, r)) yield subset).toVector
    solutions(Random.nextInt(solutions.size))
}
```
{% endscalafiddle %}

# Another section
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.


## Subsections aren't listed on topics
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

{% scalafiddle template="Persons" %}
```scala
import scalatags.JsDom.all._

Fiddle.loadJS("https://cdn.jsdelivr.net/npm/vega@5")
Fiddle.loadJS("https://cdn.jsdelivr.net/npm/vega-lite@4")
Fiddle.loadJS("https://cdn.jsdelivr.net/npm/vega-embed@5")

val ht = div("Results:",tag("iframe")(
    html(
    head(
      script(src := "https://cdn.jsdelivr.net/npm/vega@5"),
      script(src := "https://cdn.jsdelivr.net/npm/vega-lite@4"),
      script(src := "https://cdn.jsdelivr.net/npm/vega-embed@6")
      ),
      body(
      h1("I'm a title."),
      div(id := "link-wrapper")(
        a(href := "#", target := "_blank")(
          "I'm a link."
        )
      )
    )
    ))
  )

val visdiv = div(id := "vis")
val visscript = script(`type` := "text/javascript",
"""
  var yourVlSpec = {
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
  };
  vg.embed('#vis', yourVlSpec, function(error, result) {
  // Callback receiving the View instance and parsed Vega spec
  // result.view is the View, which resides under the '#vis' element
  // result.spec is the parsed spec
});
"""
)
val wrapper = div(visdiv, visscript)
Fiddle.print(wrapper.render)


```
{% endscalafiddle %}
