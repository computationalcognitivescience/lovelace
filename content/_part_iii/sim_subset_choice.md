---
layout: page
title: Subset choice
chapter: 9
nav_exclude: true
---

{% marginnote 'sn-id-binder' '[![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/markblokpoel/scala-mybinder-template/HEAD?filepath=notebooks%2Fselecting-invitees.ipynb)' %}
Some text.

{% scalafiddle template="SetTheory" %}
```scala
type Item = String

def subsetChoice(
  stuff: Set[Item],
  f: (Item => Double),
  f2: ((Item, Item) => Double)): Set[Set[Item]] = {

  def value(subset: Set[Item]): Double = 
    sum(subset, f) + sum(subset.uniquePairs, f2)

  argMax(powerset(stuff), value)
}

val objects = Set("coffee", "sugar", "salt", "cookie", "milk")
def value(item: Item): Double = item match {
  case "coffee" => 4.0
  case "sugar" => 2.0
  case "salt" => 1.0
  case "cookie" => 8.0
  case "milk" => 3.0
  case _ => 0.0
}
def value2(item1: Item, item2: Item): Double = (item2, item1) match {
  case ("coffee", "sugar") => -3.0
  case ("sugar", "coffee") => -3.0
  case ("coffee", "salt") => -6.0
  case ("salt", "coffee") => -6.0
  case ("coffee", "cookie") => 6.0
  case ("cookie", "coffee") => 6.0
  case ("coffee", "milk") => 3.0
  case ("milk", "coffee") => 3.0
  case ("sugar", "salt") => -2.0
  case ("salt", "sugar") => -2.0
  case ("sugar", "cookie") => 5.0
  case ("cookie", "sugar") => 5.0
  case ("sugar", "milk") => 2.0
  case ("milk", "sugar") => 2.0
  case ("salt", "cookie") => 4.0
  case ("cookie", "salt") => 4.0
  case ("salt", "milk") => -7.0
  case ("milk", "salt") => -7.0
  case ("cookie", "milk") => 5
  case ("milk", "cookie") => 5
  case _ => 0
}

println(subsetChoice(objects, value, value2))
```
{% endscalafiddle %}

{% fullwidth 'assets/img/cheatsheet/napoleons-march.png' 'Napoleons March *(Edward Tufte’s English translation)*' %}