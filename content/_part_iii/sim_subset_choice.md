---
layout: page
title: Subset choice
chapter: 10
nav_exclude: true
---

We pick up the conversation between Verbal and Formal from [Chapter 4 - Subset Choice](/lovelace/part_ii/subset#dialogue-1-formalizing-inviting-guests). Formal is very excited to share the computer simulations they implemented of the theoretical models Formal and Verbal created. Formal has some suggestions on how to use the simulations, which they explain to Verbal.

{% indent 4 %}
**Formal:** Welcome dr. Verbal! As promised, I have implemented computer simulations for three of our computational-level models.
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
**Formal:** ...wait! Before you leave me alone again for a few months, let's do a bit more theory before the test (van Rooij & Baggio, 2021). Do we even know if the models are different in important and meaningful ways? Even if they are different, are they so under sensible conditions?
{% endindent %}

{% indent 0 %}
**Verbal:** But the formalizations are different, so the models must behave differently, right?
{% endindent %}

{% indent 4 %}
**Formal:**
Not necessarily. Formalizations that are different may behave the same or very similarly. Sometimes we can analytically derive such equivalence{% sidenote 'sn-id-equivalence' 'You can read about mathematically proving model equivalence in [Chapter 5 - Coherence](/lovelace/part_ii/coherence#Equivalence).' %} but this is not always possible. Then computer simulations can come in handy.
{% endindent %}

{% indent 0 %}
**Verbal:** Ah, I see. I would like to know if there are important differences between the theories. That way we can possibly rule out theories that cannot explain the phenomenon or find ways to update them, just like when we were formalizing my verbal theories.
{% endindent %}

{% indent 4 %}
**Formal:** Indeed, that is the idea.
{% endindent %}

If you jumped here directly from [Chapter 4 - Subset choice](/lovelace/part_ii/subset)
you may find it helpful to first read [Chapter 9 - Scala and mathlib](/lovelace/part_ii/mathlib) to learn how to read (and write) Scala code using the ```mathlib``` library. In addition to the default ```mathlib``` library, the simulation code on this page includes supporting code which we explain first.

## Supporting code
Running simulations requires input instances as specified by the theoretical model. While we could code input by hand, that is a lot of work. The beauty of using computer simulations is that it can do the hard work for us by *automatically* generating input. To that end, Formal has written supporting code. {% sidenote 'sn-id-helper' 'Supporting code is often written specifically for a domain. For example, [simulating Coherence](/lovelace/part_iii/sim_coherence) uses different support code.' %}

For now, it is not important that you know how to write support code. However, in order to explore and adapt the code that Formal has provided, being able to *use* support code is recommended. Let's explore some examples. Remember that you can run (and adapt) the code in your browser using the <button style="background: rgba(255,255,255,0.6) !important;color: rgba(0, 0, 0, 0.6) !important;border-radius: 5px;border: 1px solid #ddd;font-family: Lato,'Helvetica Neue',Arial,Helvetica,sans-serif;font-size: 14px;
padding: 3px 8px;transition: all 350ms ease;"><img src="https://embed.scalafiddle.io/runicon.png" style="padding: 0;margin: 0 0 4px 0;vertical-align: middle;width: 16px;height: 16px;display: inline;">Run</button> button.

The theoretical models for selecting invitees (subset choice) take as input sets of persons and a function that for pairs of persons returns if they like eachother or not. The support code helps us generate these parts of the input.

### Persons
A particular person is identified by their name, and can be defined by using ```Person(name: String)```. This function takes a string as input and returns a Person object with the given name:

{% scalafiddle template="mathlib" %}
```scala
Person("Jamie")
```
{% endscalafiddle %}

Persons with the same name are considered to refer to the same individual, since the computer cannot distinguish between them.

{% scalafiddle template="mathlib" %}
```scala
val person1 = Person("Jamie")
val person2 = Person("Jamie")

person1 == person2
```
{% endscalafiddle %}

We can also create random persons. Their names are randomly selected from a predefined list with 100 names. Running the code below multiple times will create different persons.

{% scalafiddle template="mathlib" %}
```scala
Person.random
```
{% endscalafiddle %}

We can also generate groups of ```n``` random individuals.

{% scalafiddle template="mathlib" %}
```scala
Person.randomGroup(5)
```
{% endscalafiddle %}

These functions will help us create sets of persons. We can then use ```mathlib``` to work with these sets as expected. For example, we can create a set of random persons $$P$$, randomly take 2 persons who are liked $$L$$, and create a set of persons who are disliked $$D=P \setminus L$$:  

{% scalafiddle template="mathlib" %}
```scala
val persons = Person.randomGroup(5)
val personsLiked = persons.take(2)            // Take 2 people from persons.
val personsDisliked = persons \ personsLiked

println(personsLiked)
println(personsDisliked)
println(persons)
```
{% endscalafiddle %}

### Like-function
The final support code Formal provided is used to create like relationships between persons. In the formal model this function is defined as $$like: P\times P \rightarrow \{true,false\}$$. After discussing with a colleague (see [Exercise X in Chapter 4](/lovelace/part_ii/subset#try-again)), Formal recognized that the like function was intended to exclude reflection (i.e., self-liking) and is symmetrical $$like(a,b)=like(b,a)$$ (i.e., it formalizes like or dislike *eachother*).{% sidenote 'sn-id-helper' 'The formalizations in this chapter are updated with these properties.' %}

One could specify a like relationship manually. Simply create persons, store them in values so we can refer to them and then use ```likes``` or ```dislikes``` to create like relationships.

{% scalafiddle template="mathlib" %}
```scala
val lela = Person("Lela")
val carlos = Person("Carlos")
val ervin = Person("Ervin")

println(lela likes carlos)
println(carlos dislikes ervin)
println(carlos dislikes lela)
```
{% endscalafiddle %}

Specifying a *complete* like function for a set of persons, however, will be quite a chore: for each pair you need to explicate if $$a$$ likes $$b$$ and vice versa. For $$10$$ persons, that is a list of $$10 \cdot 10=100$$ likes. Support functions help us reduce this chore.

When given a partial specification of the like function, we can complete it by assuming that any non-specified relationship is a dislike. Use the support function ```.deriveLikeFunction(partialLikes: Set[Likes])``` on a set of persons to create a like function for which the domain consists of all pairs of persons (including $$(a,b)$$, $$(b,a)$$ and $$a,a$$). It will complete ```partialLikes``` by assuming non-specified relationships are dislikes.

{% scalafiddle template="mathlib" layout="v50" %}
```scala
val lela = Person("Lela")
val carlos = Person("Carlos")
val ervin = Person("Ervin")

val persons = Set(lela, carlos, ervin)
val partialLikings = Set(lela likes carlos, carlos likes ervin,carlos dislikes lela)

def like = persons.deriveLikeFunction(partialLikings)
```
The ```Viz.render()``` function can draw graphs specified in the [DOT language](https://graphviz.org/doc/info/lang.html). The ```.toDotString(like)``` helper function transforms persons and a like function to graph figures.
```scala
Viz.render(persons.toDotString(like))
```
And we can view the truth values associated with all pairs of persons.
```scala
List(
  like(lela, carlos),
  like(lela, ervin),
  like(carlos, ervin)
)
```
{% endscalafiddle %}

While this approach is useful to manually explore small examples, it still is a lot of manual work. Wouldn't it be nice if we can generate a complete like function randomly? Use the support function ```.randomLikeFunction(probability: Double)``` on a set of persons to create a random like function. For each pair (including $$(a,b)$$, $$(b,a)$$ and $$a,a$$), it generates ```true``` with probability equal to the ratio or false otherwise.

{% scalafiddle template="mathlib" layout="v50" minheight="700"%}
```scala
val lela = Person("Lela")
val carlos = Person("Carlos")
val ervin = Person("Ervin")

val persons = Set(lela, carlos, ervin)

def like = persons.randomLikeFunction(0.7)

Viz.render(persons.toDotString(like))

List(
  like(lela, carlos),
  like(lela, ervin),
  like(carlos, ervin)
)
```
{% endscalafiddle %}

{% question %}
What happens to the output of the like function when you change the probability?
{% hidden Hint? %}
Try changing the probability value (the input of the function ```randomLikeFunction```) and see what changes in the output.
{% endhidden %}
{% endquestion %}

A final example to illustrate how to generate a random input instance. An alternative visualization is used to indicate which persons are liked by the host or not. Note that generating a visualization graph with many persons will not display properly or potentially crash your browser due to the many relationships.

{% scalafiddle template="mathlib" layout="v20" minheight="700"%}
```scala
val persons = Person.randomGroup(5)
val personsLiked = persons.take(2)
val personsDisliked = persons \ personsLiked

def like = persons.randomLikeFunction(0.7)

Viz.render(persons.toDotString(personsLiked, personsDisliked, like))
```
{% endscalafiddle %}


{% question %}
With these support functions, we can randomly create instances for the formal models of selecting invitees. Why is this helpful?
{% hidden Hint? %}
It save a lot of manual work.

{% question %}
Can you think of another use?
{% hidden Hint? %}
You can compare model behaviour for the same input.

{% question %}
Can you think of another use?
{% hidden Hint? %}
You can generate different inputs at random and see if (and how) model behaviour changes as a function of the input.

{% question %}
Can you think of another use?
{% endquestion %}
{% endhidden %}
{% endquestion %}
{% endhidden %}
{% endquestion %}
{% endhidden %}
{% endquestion %}

## Simulating <span style="font-variant: small-caps; font-style: normal;">Selecting Invitees</span>

In this section we cover how to simulate {% problem Selecting invitees (version
4, 5 and 6) %}. You will learn how to read Scala ```mathlib``` simulation code
and how it relates to the formalization. We go through {% problem Selecting
invitees (version 4) %} step by step, after which you can explore versions 5 and
6 yourself. To make the code more readable, we use names in the code that are
more descriptive than the single letters used in math (see Table 1).

{% marginnote 'Table-ID1' 'Table 1: the mapping from math notation to Scala code.'  %}
<div class="table-wrapper" markdown="block" style="margin-top:3rem;">

| Math | Scala | Description |
| :--- | :--- | :--- |
| $$P$$ | ```persons``` | Set of persons from which to select invitees. |
| $$L$$ | ```personsLiked``` | Subset of persons that is liked. |
| $$D$$ | ```personsDisliked``` | Subset of persons that is disliked. |
| $$like$$ | ```like``` | Function that captures if two persons like each other or not. |
| $$k$$ | ```k``` | Value that states how many of the invited persons at most can be disliked. |
| $$G$$ | ```invitees``` | Set of invited persons. |
| $$X$$ | ```x``` | Set of all unique pairs of persons that like each other. |
| $$Y$$ | ```y``` | Set of all unique pairs of persons that dislike each other. |

</div>

{% stopandthink %}
Take a moment to familiarize yourself again with the
formalization. If you need more context, you can go back to [Chapter 4 - Subset
choice](/lovelace/part_ii/subset#) where the formalization was introduced.
{% endstopandthink %}

{% fproblem Selecting invitees (version 4) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|G\cap D| \leq k$$ and $$|X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}

Let's see how this formalization translated to simulation code. Once you press
<button style="background: rgba(255,255,255,0.6) !important;color: rgba(0, 0, 0,
0.6) !important;border-radius: 5px;border: 1px solid #ddd;font-family:
Lato,'Helvetica Neue',Arial,Helvetica,sans-serif;font-size: 14px; padding: 3px
8px;transition: all 350ms ease;"><img
src="https://embed.scalafiddle.io/runicon.png" style="padding: 0;margin: 0 0 4px
0;vertical-align: middle;width: 16px;height: 16px;display: inline;">Run</button>
to run the code, the explanation text will disappear. You can get it back by
reloading this webpage.

The formalization is implemented in the ```si4``` function, all of the input
($$P$$, $$L$$, $$D$$, $$like$$ and $$k$$) is listed as an argument of the
function. The type of the output also needs to be defined, which in this case is
a subset $$G\subseteq P$$ of persons, i.e., ```Set[Person]```.

{% scalafiddle template="mathlib" %}
```scala
def si4(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean,
        k: Int): Set[Person] = {
```

The input in the formalization is subject to a few constraints. We check those
constraints in the code and stop the program of the constraints are not met with
an informative error message.

```scala
    // Input must satisfy these constraints, or program halts.
    require(personsLiked <= persons, "personsLiked must be a subset of persons")
    require(personsDisliked <= persons, "personsDisliked must be a subset of persons")
    require(personsLiked /\ personsDisliked == Set.empty, "intersection between personsLiked and personsDisliked must be emtpy")
    require(personsLiked \/ personsDisliked == persons, "union of personsLiked and personsLiked must equal persons")
```
bla
```scala
    // Specify that invitees is valid if |G /\ D| <= k.
    def atMostKDislikes(invitees: Set[Person]): Boolean =
        (invitees /\ personsDisliked).size <= k
```
bla
```scala
    // Specify the optimality condition.
    def xg(invitees: Set[Person]): Int = {
        val x = invitees.uniquePairs // From all pairs of invitees,
                .build(like.tupled)  // select all pairs that like each other,
                .size                // and count them.
        val g = invitees.size        // Count the number of total invitees.
        x + g
    }
```
bla
```scala
    val invitees = powerset(persons)  // From all possible subsets of persons,
      .build(atMostKDislikes)       // select subsets that contain at most k disliked persons,
      .argMax(xg)                   // and select the subsets that maximize the optimality condition.
```
bla
```scala
    // If more than one solution exists, return one at random. Always 1 solution must exist,
    // because the empty set is a valid solution. Hence, we can assume random does not
    // return None and 'get' the value.
    invitees.random.get
}
```
bla
```scala
val group = Person.randomGroup(10)    // Generate random group
val personsLiked = group.take(5)      // The first 5 are liked
val personsDisliked = group.drop(5)   // The rest is disliked

def like = group.randomLikeFunction(.7) // Autogenerate random like relations

Viz.render(group.toDotString(personsLiked, personsDisliked, like))
```
bla
```scala
si4(group, personsLiked, personsDisliked, like, k = 2)
```
{% endscalafiddle %}


From here on, you are free to explore the simulations at your own. Try to get a feeling for how the three formalizations behave. You can even change the simulation code if you want. After simulating the three models individually, we provide a sandbox for you to compare their behaviour directly.

{% question %}
In the simulations below you can generate groups of any size. The simulation,
however, considers all possible subsets of people. How many possible subsets
exist given 3 people? The first person can be *in* or *out*, that's two
options. The second person can also be *in* or *out*, that's again two options,
but combined with the first thats $$2 \times 2$$ options. The third person can
be *in* or *out* making $$2\times 2\times 2=8$$ options. How many possible
subsets exist for 4 people? And for 8? and 15?
{% endquestion %}

Keep in mind that the search space grows exponentially with the size of $$P$$.
If your computer crashes or is taking a long time, you are probably trying to
simulate for large ($$>10$$) groups.

{% fproblem Selecting invitees (version 5) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
$$G \subseteq P$$ such that $$|G\cap L| + |X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G\}~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}


{% scalafiddle template="mathlib", minheight="1000", layout="v45" %}
```scala
def si5(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean): Set[Person] = {

    // Input must satisfy these constraints, or program halts.
    require(personsLiked <= persons, "personsLiked must be a subset of persons")
    require(personsDisliked <= persons, "personsDisliked must be a subset of persons")
    require(personsLiked /\ personsDisliked == Set.empty, "intersection between personsLiked and personsDisliked must be emtpy")
    require(personsLiked \/ personsDisliked == persons, "union of personsLiked and personsLiked")

    // Specify the optimality condition.
    def gl_x_g(invitees: Set[Person]): Int = {
        val gl = (invitees /\ personsLiked)
    	         .size                // Count the invitees the host likes.
        val x  = invitees.uniquePairs // From all pairs of invitees,
                 .build(like.tupled)  // select all pairs that like each other,
                 .size                // and count them.
        val g  = invitees.size        // Count the number of total invitees.
        gl + x + g
    }

    val invitees = powerset(persons)  // From all possible subsets of persons,
        .argMax(gl_x_g)               // select those that maximize |G/\L| + |X| + |G|

    // If more than one solution exists, return one at random. Always 1 solution must exist,
    // because the empty set is a valid solution. Hence, we can assume random does not
    // return None and 'get' the value.
    invitees.random.get
}

val group = Person.randomGroup(10)    // Generate random group
val personsLiked = group.take(5)      // The first 5 are liked
val personsDisliked = group.drop(5)   // The rest is disliked

def like = group.randomLikeFunction(.7) // Autogenerate random like relations

Viz.render(group.toDotString(personsLiked, personsDisliked, like))

si5(group, personsLiked, personsDisliked, like)
```
{% endscalafiddle %}

{% fproblem Selecting invitees (version 6) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|Y| \leq k$$ and  $$|G\cap L|+|G|$$ is maximized (where $$Y = \{p_i,p_j \in G\}~|~like(p_i,p_j) = false \wedge i\neq j \}$$).
{% endfproblem %}

{% scalafiddle template="mathlib", minheight="1000", layout="v45" %}
```scala
def si6(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean,
        k: Int): Set[Person] = {

    // Input must satisfy these constraints, or program halts.
    require(personsLiked <= persons, "personsLiked must be a subset of persons")
    require(personsDisliked <= persons, "personsDisliked must be a subset of persons")
    require(personsLiked /\ personsDisliked == Set.empty, "intersection between personsLiked and personsDisliked must be emtpy")
    require(personsLiked \/ personsDisliked == persons, "union of personsLiked and personsLiked")

	// Specify that invitees is valid if |Y| <= k.
    def atMostKPairDislikes(invitees: Set[Person]): Boolean =
      { invitees.uniquePairs | like.tupled }.size <= k

    // Specify the optimality condition.
    def gl_g(invitees: Set[Person]): Int = {
        val gl = (invitees /\ personsLiked)
    	         .size                // Count the invitees the host likes.
        val g  = invitees.size        // Count the number of total invitees.
        gl + g
    }

    val invitees = { powerset(persons) | atMostKPairDislikes _ }
                   .argMax(gl_g)

    // If more than one solution exists, return one at random. Always 1 solution must exist,
    // because the empty set is a valid solution. Hence, we can assume random does not
    // return None and 'get' the value.
    invitees.random.get
}

val group = Person.randomGroup(10)    // Generate random group
val personsLiked = group.take(5)      // The first 5 are liked
val personsDisliked = group.drop(5)   // The rest is disliked

def like = group.randomLikeFunction(.7) // Autogenerate random like relations

Viz.render(group.toDotString(personsLiked, personsDisliked, like))

si6(group, personsLiked, personsDisliked, like, k = 2)
```
{% endscalafiddle %}

### Comparing model behaviour

We can run the simulation for all three versions of {% problem Selecting
Invitees %} on the same input to compare their behaviour.

{% question %}
Can you find input where two or more of the models give the same output?
{% hidden Hint? %}
Try defining input by hand instead of using the random generation first. Then
try finding variations of that input for which two or more models are
equivalent.
{% endhidden %}
{% endquestion %}



{% scalafiddle template="mathlib", minheight="1000", layout="v30" %}
```scala
val group = Person.randomGroup(10)    // Generate random group
val personsLiked = group.take(5)      // The first 5 are liked
val personsDisliked = group.drop(5)   // The rest is disliked

def like = group.randomLikeFunction(.7) // Autogenerate random like relations

val k = 2

println("Output SI4: " + SelectingInvitees.si4(group, personsLiked, personsDisliked, like, k))
println("Output SI5: " + SelectingInvitees.si5(group, personsLiked, personsDisliked, like))
println("Output SI6: " + SelectingInvitees.si6(group, personsLiked, personsDisliked, like, k))

Viz.render(group.toDotString(personsLiked, personsDisliked, like))

```
{% endscalafiddle %}




{% scalafiddle template="mathlib", minheight="1000", layout="v30" %}
```scala
val inputData: List[SelectingInvitees.Input] =
  SelectingInvitees.inputGenerator(groupSize = 5,
                                   likeDislikeRatio = .2,
                                   pairLikeRatio = .4,
                                   k = 2,
                                   sampleSize = 10)

val outputData: List[Set[Person]] = inputData.map(input =>
  SelectingInvitees.si4(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like,
                        input.k))




Plotly.render("""
{
    "data": [
        {
            "x": [
                "giraffes",
                "orangutans",
                "monkeys"
            ],
            "y": [
                20,
                14,
                23
            ],
            "type": "bar"
        }
    ]
}
""")

```
{% endscalafiddle %}


{% scalafiddle template="mathlib", minheight="1000", layout="v30" %}
```scala

val groupSizes = Set(5, 10, 15)
val likeDislikeRatios = Set(0, 0.5, 1.0)
val pairLikeRatios = Set(0, 0.5, 1.0)
val ks = Set(0, 0.5, 1.0)
val sampleSize = 1

var i = 1
val inputData =
  (for(groupSize <- groupSizes;
      likeDislikeRatio <- likeDislikeRatios;
      pairLikeRatio <- pairLikeRatios;
      k <- ks) yield {
        println(s"$i")
        i += 1
        SelectingInvitees.inputGenerator(
          groupSize,
          likeDislikeRatio,
          pairLikeRatio,
          (k * groupSize).intValue,
          sampleSize
          )
      }).toList.flatten

var n = 1
val outputData: List[Set[Person]] = inputData.map(input => {
  println(s"$n / ${inputData.length}")
  n += 1
  SelectingInvitees.si4(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like,
                        input.k)
                        })

(inputData zip outputData).foreach(println)
```
{% endscalafiddle %}

### References

van Rooij, I., & Baggio, G. (2021). [Theory before the test: How to build high-verisimilitude explanatory theories in psychological science.](https://journals.sagepub.com/doi/full/10.1177/1745691620970604) *Perspectives on Psychological Science, 16*(4) 682–697.
