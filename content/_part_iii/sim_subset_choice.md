---
layout: page
title: Subset choice
chapter: 9
nav_exclude: true
---

We pick up the conversation between Verbal and Formal from [Chapter 4 - Subset Choice](/lovelace/part_ii/subset#dialogue-1-formalizing-inviting-guests). Formal is very excited to share the computer simulations they implemented of the theoretical models Formal and Verbal created. Formal has some suggestions on how to use the simulations, which they explain to Verbal.

{% indent 4 %}
**Formal:** Welcome dr. Verbal! As promised, I have implemented three of our computational-level models in computer simulations.
{% endindent %}

{% indent 0 %}
**Verbal:** That's great. You said we can use the simulations to explore the models' empirical implications. How does that work?
{% endindent %}

{% indent 4 %}
**Formal:** The three formal models each make different tradeofs in optimizing selecting guests...
{% endindent %}

{% indent 0 %}
**Verbal:** Yes, I remember. Shall I run some experiments to see which one is best?
{% endindent %}

{% indent 4 %}
**Formal:** ...wait! Before you leave me alone again for a few months, let's do a bit more theory before the test. Do we even know if the models are different in important and meaningful ways? Even if they are different, are they so under sensible conditions?
{% endindent %}

{% indent 0 %}
**Verbal:** But the formalisations are different, so the models must behave differently, right?
{% endindent %}

{% indent 4 %}
**Formal:**
While the formalizations are different, it might be actually be possible that they behave the same or very similarly. Sometimes we can formally derive equivalence, but this is not always possible.{% sidenote 'sn-id-equivalence' 'You can read about mathematically proving model equivalence in [Chapter 5 - Coherence](/lovelace/part_ii/coherence#Equivalence).' %} In those cases, we can use computer simulations to explore the qualitative differences between theories.


{% endindent %}

{% indent 0 %}
**Verbal:** 
{% endindent %}

{% indent 4 %}
**Formal:** 
{% endindent %}

## Selecting Invitees V4


{% fproblem Selecting invitees (version 4) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|G\cap D| \leq k$$ and $$|X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}

To make the code more readable, we use variable names that are more descriptive. The table below gives an overview of the mapping between formal model and Scala code labels.


| Math | Scala | Description |
| --- | --- | --- |
| $$P$$ | ```persons``` | Set of persons from which to<br/> select invitees. |
| $$L$$ | ```personsLiked``` | Subset of persons that is liked. |
| $$D$$ | ```personsDisliked``` | Subset of persons that is <br/>disliked. |
| $$like$$ | ```like``` | Function that captures if two<br/> persons like each other or not. |
| $$k$$ | ```k``` | Value that states how many of<br/> the invited persons at most<br/> can be disliked. |
| $$G$$ | n.a. | Set of invited persons. |
| $$X$$ | n.a. | Set of all pairs of persons that<br/> like each other. |


{% scalafiddle template="SetTheory", minheight="1000", layout="v45" %}
```scala
def si4(persons: Set[String],
        personsLiked: Set[String],
        personsDisliked: Set[String],
        like: (String, String) => Boolean,
        k: Int): Set[String] = {
  
	// Specify requirements. Selecting Invitees V4 is undefined if requirements are not met.
    requirement(personsLiked subsetOf persons, "L must be a subset of P")
    requirement(personsDisliked subsetOf persons, "D must be a subset of P")
    requirement((personsLiked intersect personsDisliked).isEmpty, "intersection between L and D must be emtpy")
    requirement((personsLiked union personsDisliked) == persons, "union of L and D must equal P")
    
    // Specify what makes a valid (sub)set of invitees:
    def atMostKDislikes(invitees: Set[String]): Boolean = {
        // |G /\ D| <= k
        (invitees /\ personsDisliked).size <= k
    }
    
    // Specify the optimality condition:
    def xg(invitees: Set[String]): Int = {
        // |X|
        val x = invitees.uniquePairs // From all pairs of invitees,
                .build(like.tupled)  // select all pairs that like each other,
                .size                // and count them.
        // |G|
        val g = invitees.size        // Count the number of total invitees.
        
        x + g
    }
    
    val invitees = powerset(persons)  // From all possible subsets of persons,
        .build(atMostKDislikes)       // select subsets that contain at most k disliked persons,
        .argMax(xg)                   // then select the subsets that maximize the optimality condition.
    
    // If more than one solution exists, return one at random. Always 1 solution must exist,
    // because the empty set is a valid solution. Hence, we can assume random does not
    // return None and 'get' the value.
    invitees.random.get 
}

```
{% endscalafiddle %}

## Selecting Invitees V5

{% fproblem Selecting invitees (version 5) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
$$G \subseteq P$$ such that $$|G\cap L| + |X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G\}~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}


{% scalafiddle template="SetTheory", minheight="1000", layout="v45" %}
```scala
def si5(P: Set[Person],
        L: Set[Person],
        D: Set[Person],
        like: (Person, Person) => Boolean): Set[Person] = {
  requirement(L subsetOf P, "l must be a subset of p")
  requirement(D subsetOf P, "d must be a subset of p")
  requirement((L intersect D).isEmpty, "intersection between l and d must be emtpy")
  requirement((L union D) == P, "union of l and d must equal p")


  P.subsets.toSet // G \subseteq P
   .argMax(G => {
     (G intersect L).size // |G \cap L|
     + G.size // |G|
     + G.uniquepairs.build(pair => like(pair._1, pair._2)).size // |X|
   })
   .get
}

val (p1, p2, p3, p4) = (Person("p1"), Person("p2"), Person("p3"), Person("p4"))

val P = Set(p1, p2, p3, p4)
val L = Set(p1, p2, p3)
val D = Set(p4)
val relations = Set(
  p1 like p2,
  p1 like p3,
  p2 like p3,
  p3 like p4
)
def like = relations.deriveFun
val k = 3

val out = si5(P, L, D, like)

println(h2("Input:"))
VegaRenderer.render(relations.deriveGraph(P))
println(s"k=$k")

println(h2("Output:"))
println(out)
```
{% endscalafiddle %}

## Selecting Invitees V6

{% fproblem Selecting invitees (version 6) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|Y| \leq k$$ and  $$|G\cap L|+|G|$$ is maximized (where $$Y = \{p_i,p_j \in G\}~|~like(p_i,p_j) = false \wedge i\neq j \}$$).
{% endfproblem %}

{% scalafiddle template="SetTheory", minheight="1000", layout="v45" %}
```scala
def si6(P: Set[Person],
        L: Set[Person],
        D: Set[Person],
        like: (Person, Person) => Boolean,
        k: Int): Set[Person] = {
    requirement(L subsetOf P, "l must be a subset of p")
    requirement(D subsetOf P, "d must be a subset of p")
    requirement((L intersect D).isEmpty, "intersection between l and d must be emtpy")
    requirement((L union D) == P, "union of l and d must equal p")


  P.subsets.toSet // G \subseteq P
   .filter(G => G.uniquepairs.build(pair => !like(pair._1, pair._2)).size <= k)
   .argMax(G => (G intersect L).size + G.size)
   .get
}

val (p1, p2, p3, p4) = (Person("p1"), Person("p2"), Person("p3"), Person("p4"))

val P = Set(p1, p2, p3, p4)
val L = Set(p1, p2, p3)
val D = Set(p4)
val relations = Set(
  p1 like p2,
  p1 like p3,
  p2 like p3,
  p3 like p4
)
def like = relations.deriveFun
val k = 3

val out = si6(P, L, D, like, k)

println(h2("Input:"))
VegaRenderer.render(relations.deriveGraph(P))
println(s"k=$k")

println(h2("Output:"))
println(out)
```
{% endscalafiddle %}