---
layout: index
title: Chapter 2 - Concepts and Notation
permalink: /chapter2/
sidebar_link: true
sidebar_sort_order: 2
---

<div id="toc-wrapper" markdown="1">
* Table of contents
{:toc}
</div>

Like sculpting, theoretical modeling requires its own set of dedicated tools. The theoretical modeler's tools are mathematical concepts, formal expressions, and notational conventions. One can already get quite far with the basics in _set theory_, _functions_ and _logic_. Below we present a brief primer. Readers who have taken introductory classes on these topics can skip this section without loss of continuity.

# Set theory
A set is a collection of distinct objects. For example, a set of people, animals or numbers. Sets are usually denoted by a capital letter and their elements listed between curly brackets. They can also be visualized as circles.

{% scalafiddle template="Template", minheight="150" %}
```scala
val persons = Set("Ramiro", "Brenda", "Molly")
val animals = Set("cat", "turtle", "blue whale", "cuttlefish")
val numbers = Set(1,5,7,12)
```
{% endscalafiddle %}

When we want to write that an object $$x$$ is (or is not) part of a set $$X$$, we use _set membership_ notation:

{% scalafiddle template="Template", minheight="150" %}
```scala
val persons = Set("Ramiro", "Brenda", "Molly")
println(persons.contains("Ramiro"))
```
{% endscalafiddle %}

{% scalafiddle template="Template", minheight="150" %}
```scala
val numbers = Set(1,5,7,12)
println(numbers.contains(17))
```
{% endscalafiddle %}

Often, we want to express things like 'the set of mammals $$M$$ is part of the set of all animals $$A$$'. We then use _subset_ notation: $$M\subseteq A$$ or $$M\subset A$$. The latter means that $$M$$ is smaller than $$A$$.

{% scalafiddle template="Template", minheight="150" %}
```scala
val animals = Set("cat", "turtle", "blue whale", "cuttlefish")
val mammals = Set("cat", "blue whale")

println(mammals isSubsetOf animals)
println(mammals isSubsetEqTo animals)
```
{% endscalafiddle %}

Vice versa, we can express that 'the set of all things on earth $$T$$ contains all animals $$A$$' using _superset_ notation: $$T\supseteq A$$ or $$T\supset A$$. The latter means that $$T$$ is bigger than $$A$$.

{% scalafiddle template="Template", minheight="150" %}
```scala
val manyAnimals = Set("cat", "dog", "goldfish", "turtle", "blue whale",
  "cuttlefish", "shark")
val animals = Set("cat", "turtle", "blue whale", "cuttlefish")


println(manyAnimals isSupersetOf animals)
println(manyAnimals isSupersetEqTo animals)
```
{% endscalafiddle %}
