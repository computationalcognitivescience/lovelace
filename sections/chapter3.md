---
layout: index
title: Chapter 3 - Formalizing
permalink: /chapter3/
sidebar_link: true
sidebar_sort_order: 3
---

<div id="toc-wrapper" markdown="1">
* Table of contents
{:toc}
</div>

# Introduction

{% scalafiddle template="Persons", minheight="200", layout="v70" %}
```scala
def si4(
  p: Set[Person],
  l: Set[Person],
  d: Set[Person],
  like: (Person, Person) => Boolean,
  k: Int
  ): Set[Person] = {
  requirement(l subsetOf p, "l must be a subset of p")
  requirement(d subsetOf p, "d must be a subset of p")
  requirement((l intersect d).isEmpty, "intersection between l and d must be emtpy")
  requirement((l union d) == p, "union of l and d must equal p")


  p.subsets.toSet // G \subseteq P
   .filter(g => (g intersect d).size <= k) // such that |G \cap D| <= k
   .argMax(g => g.size + g.pairs.build(Function.tupled(like)).size)
   .get
}

def si5(
  p: Set[Person],
  l: Set[Person],
  d: Set[Person],
  like: (Person, Person) => Boolean
  ): Set[Person] = {
  requirement(l subsetOf p, "l must be a subset of p")
  requirement(d subsetOf p, "d must be a subset of p")
  requirement((l intersect d).isEmpty, "intersection between l and d must be emtpy")
  requirement((l union d) == p, "union of l and d must equal p")


  p.subsets.toSet // G \subseteq P
   .argMax(g => {
     (g intersect l).size // |G \cap L|
     + g.size // |G|
     + g.pairs.build(pair => like(pair._1, pair._2)).size // |X|
   })
   .get
}

def si6(
  p: Set[Person],
  l: Set[Person],
  d: Set[Person],
  like: (Person, Person) => Boolean,
  k: Int
  ): Set[Person] = {
  requirement(l subsetOf p, "l must be a subset of p")
  requirement(d subsetOf p, "d must be a subset of p")
  requirement((l intersect d).isEmpty, "intersection between l and d must be emtpy")
  requirement((l union d) == p, "union of l and d must equal p")


  p.subsets.toSet // G \subseteq P
   .filter(g => g.uniquepairs.build(pair => !like(pair._1, pair._2)).size <= k)
   .argMax(g => (g intersect l).size + g.size)
   .get
}

val a = Person("Ada")
val b = Person("Bob")
val c = Person("Cal")
val d = Person("Deb")
val e = Person("Edo")
val f = Person("Fae")

// val relations = Set(
//   a likes b, b likes c, c likes d, c likes f, d likes e, d likes f, e likes f
// )

val P = Set[Person](a, b, c, d, e, f)
// val L = Set[Person](d, e, f)
// val D = Set[Person](a, b, c)

val ld = P.toList.splitAt(Random.nextInt(P.size))
val L = ld._1.toSet
val D = ld._2.toSet

val relations = P.uniquepairs
  .map(pair => if(Random.nextBoolean) pair._1 likes pair._2 else pair._1 dislikes pair._2)

val outputSI4 = si4(P, L, D, relations.deriveFun, 1)
val outputSI5 = si5(P, L, D, relations.deriveFun)
val outputSI6 = si6(P, L, D, relations.deriveFun, 2)

VegaRenderer.render(relations.deriveGraph(P))

val rel4 = relations.filter(r => outputSI4.contains(r.a) || outputSI4.contains(r.b))
val rel5 = relations.filter(r => outputSI5.contains(r.a) || outputSI5.contains(r.b))
val rel6 = relations.filter(r => outputSI6.contains(r.a) || outputSI6.contains(r.b))



val res4 = (
  for(g1 <- outputSI4.toList) yield {
    (for(g2 <- outputSI4.toList if(g1!=g2)) yield relations.contains(g1 likes g2)).count(_ == true)
  })
val res5 = (
  for(g1 <- outputSI5.toList) yield {
    (for(g2 <- outputSI5.toList if(g1!=g2)) yield relations.contains(g1 likes g2)).count(_ == true)
  })
val res6 = (
  for(g1 <- outputSI6.toList) yield {
    (for(g2 <- outputSI6.toList if(g1!=g2)) yield relations.contains(g1 likes g2)).count(_ == true)
  })

VegaRenderer.render(rel4.deriveGraph(outputSI4))
println("SI4: " + outputSI4)
println(res4.sum.toDouble / res4.length)

VegaRenderer.render(rel5.deriveGraph(outputSI5))
println("SI5: " + outputSI5)
println(res5.sum.toDouble / res5.length)

VegaRenderer.render(rel6.deriveGraph(outputSI6))
println("SI6: " + outputSI6)
println(res6.sum.toDouble / res6.length)
```
{% endscalafiddle %}
