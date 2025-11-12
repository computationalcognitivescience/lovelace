---
layout: page
title: Subset choice
chapter: 10
nav_exclude: true
---

<div class="warning" style='max-width: 55%;background-color:#DF7777; color: #000; border-left: solid #a00000 4px; border-radius: 4px; padding-right: 2em;'>
<span>
<p style='width: 100%;margin-top:1em; text-align:center'>
<b>Interactive code offline</b></p>
<p style='width: calc(100% - 1em);margin-left: 1em;'>
Due to the discontinuation of <a href="https://www.scalafiddle.com">www.scalafiddle.com</a>, the code blocks in the book are currently not interactive. We regret the limitations this imposes and are working on a solution.
</p></span>
</div>

In this chapter you will learn how to use computer simulations as a theoretical
tool, namely to analyze the consequences different formalizations of verbal
theories. To reach that goal, you will also learn how to read an implementation
of formal theory in Scala ```mathlib```. At the end of this chapter, you will be
able to use (adapt and run) the provided simulation code to compare three formal
models. You will be able to test your intuitions about the theory and derive qualitative differences between them.

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

For now, it is not important that you know how to write support code. However, in order to explore and adapt the code that Formal has provided, being able to *use* support code is recommended. Let's explore some examples.

The theoretical models for selecting invitees (subset choice) take as input sets of persons and a function that for pairs of persons returns if they like eachother or not. The support code helps us generate these parts of the input.

### Persons
A particular person is identified by their name, and can be defined by using ```Person(name: String)```. This function takes a string as input and returns a Person object with the given name:

<pre class="mathlib">
import mathlibrepo.selectinginvitees._

Person("Jamie")
</pre>

Persons with the same name are considered to refer to the same individual, since the computer cannot distinguish between them.

<pre class="mathlib">
import mathlibrepo.selectinginvitees._

val person1 = Person("Jamie")
val person2 = Person("Jamie")

person1 == person2
</pre>

We can also create random persons. Their names are randomly selected from a predefined list with 100 names. Running the code below multiple times will create different persons.

<pre class="mathlib">
import mathlibrepo.selectinginvitees._

Person.random
</pre>

We can also generate groups of ```n``` random individuals.

<pre class="mathlib">
import mathlibrepo.selectinginvitees._

Person.randomGroup(5)
</pre>

These functions will help us create sets of persons. We can then use ```mathlib``` to work with these sets as expected. For example, we can create a set of random persons $$P$$, randomly take 2 persons who are liked $$L$$, and create a set of persons who are disliked $$D=P \setminus L$$:

<pre class="mathlib">
import mathlibrepo.selectinginvitees._
import mathlib.set.SetTheory._

val persons = Person.randomGroup(5)
val personsLiked = persons.take(2)            // Take 2 people from persons.
val personsDisliked = persons \ personsLiked

personsLiked
personsDisliked
persons
</pre>

### Like-function
The final support code Formal provided is used to create like relationships between persons. In the formal model this function is defined as $$like: P\times P \rightarrow \{true,false\}$$. After discussing with a colleague (see [Exercise X in Chapter 4](/lovelace/part_ii/subset#try-again)), Formal recognized that the like function was intended to exclude reflection (i.e., self-liking) and is symmetrical $$like(a,b)=like(b,a)$$ (i.e., it formalizes like or dislike *eachother*).{% sidenote 'sn-id-helper' 'The formalizations in this chapter are updated with these properties.' %}

One could specify a like relationship manually. Simply create persons, store them in values so we can refer to them and then use ```likes``` or ```dislikes``` to create like relationships.

<pre class="mathlib">
import mathlibrepo.selectinginvitees._

val lela = Person("Lela")
val carlos = Person("Carlos")
val ervin = Person("Ervin")

lela likes carlos
carlos dislikes ervin
carlos dislikes lela
</pre>

Specifying a *complete* like function for a set of persons, however, will be quite a chore: for each pair you need to explicate if $$a$$ likes $$b$$ and vice versa. For $$10$$ persons, that is a list of $$10 \cdot 10=100$$ likes. Support functions help us reduce this chore.

[//]: # ({% marginnote 'mn-id-runbutton' 'The code snippet below is interleaved with explanation text. Pressing <button style="background: rgba(255,255,255,0.6) !important;color: rgba(0, 0, 0, 0.6) !important;border-radius: 5px;border: 1px solid #ddd;font-family: Lato,,Arial,Helvetica,sans-serif;font-size: 14px; padding: 3px 8px;transition: all 350ms ease;"><img src="https://embed.scalafiddle.io/runicon.png" style="padding: 0;margin: 0 0 4px 0;vertical-align: middle;width: 16px;height: 16px;display: inline;">Run</button> removes the explanation text to run the code. You can get the explanation back by reloading this webpage.' %})

When given a partial specification of the like function, we can complete it by assuming that any non-specified relationship is a dislike. Use the support function ```.deriveLikeFunction(partialLikes: Set[Likes])``` on a set of persons to create a like function for which the domain consists of all pairs of persons (including $$(a,b)$$, $$(b,a)$$ and $$a,a$$). It will complete ```partialLikes``` by assuming non-specified relationships are dislikes.

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

While this approach is useful to manually explore small examples, it still is a lot of manual work. Wouldn't it be nice if we can generate a complete like function randomly? Use the support function ```.randomLikeFunction(probability: Double)``` on a set of persons to create a random like function. For each pair (including $$(a,b)$$, $$(b,a)$$ and $$a,a$$), it generates ```true``` with probability equal to the ratio or false otherwise.

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

{% question %}
What happens to the output of the like function when you change the probability?
{% hidden Hint? %}
Try changing the probability value (the input of the function ```randomLikeFunction```) and see what changes in the output.
{% endhidden %}
{% endquestion %}

A final example to illustrate how to generate a random input instance. An alternative visualization is used to indicate which persons are liked by the host or not. Note that generating a visualization graph with many persons will not display properly or potentially crash your browser due to the many relationships.

```scala
val persons = Person.randomGroup(5)
val personsLiked = persons.take(2)
val personsDisliked = persons \ personsLiked

def like = persons.randomLikeFunction(0.7)

Viz.render(persons.toDotString(personsLiked, personsDisliked, like))
```


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

Let's see how this formalization translates to simulation code. The
formalization is implemented in the ```si4``` function, all of the input ($$P$$,
$$L$$, $$D$$, $$like$$ and $$k$$) is listed as an argument of the function. The
type of the output also needs to be defined. In this case the output is a subset
$$G\subseteq P$$ of persons, translating to type ```Set[Person]```.

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
  // Input must satisfy these constraints, otherwise error.
  require(personsLiked <= persons,
          "personsLiked must be a subset of persons")
  require(personsDisliked <= persons,
          "personsDisliked must be a subset of persons")
  require(personsLiked /\ personsDisliked == Set.empty,
          "personsLiked intersect personsDisliked must be emtpy")
  require(personsLiked \/ personsDisliked == persons,
          "personsLiked union personsLiked must equal persons")
```

The output is defined using two properties. To define output using the set
builder we write two functions that compute these properties. First, the host
wants to invite at most $$k$$ people they dislike <span>$$|G \cap D|\leq
k$$</span>. This function returns a Boolean if a given (sub)set of people does
not have this property.

```scala
  // Specify that invitees is valid if |G /\ D| <= k.
  def atMostKDislikes(invitees: Set[Person]): Boolean = {
    (invitees /\ personsDisliked).size <= k
  }
```

Second, the formalization
states that the number of invited pairs that like each other plus the number of
invited people $$|X| + |G|$$ is maximal. This is an optimality condition. This
function computes for a given (sub)set of people, the set $$X$$ and returns an
integer corresponding to $$|X| + |G|$$.

{% marginnote 'mn-id-tupled' 'The ```.tupled``` function transforms a function
with $$n$$ arguments into a function with 1 argument, where that argument is an
$$n$$-tuple. This is needed when applying a function on a set of tuples that
correspond to the arguments of that function.' %}
```scala
  // Specify the optimality condition.
  def xg(invitees: Set[Person]): Int = {
    // The number of unique pairs that like eachother.
    val x = { invitees.uniquePairs | like.tupled }.size
    // The number of total invitees.
    val g = invitees.size
    x + g
  }
```

Here we specify the set of possible valid outputs. Remember that for any given
formalization multiple possible outputs may exist that satisfy the output
conditions. Below, we consider all possible subsets of people (the powerset
$$\mathcal{P}(P)$$). Any $$G\in\mathcal{P}(P)$$ is a subset of people
$$G\subseteq P$$. From this set of sets we build a set of sets of people that
satisfy <span>$$|G \cap D|\leq k$$</span> using ```atMostKDislikes``` and the
optimality condition $$\arg\max_{|X|+|G|}$$ using ```argMax(xg)```.

```scala
  val invitees = { powerset(persons) | atMostKDislikes _ }.argMax(xg)
```

To complete the implementation, we need to output one valid solution if any
exist. If multiple possible solutions exist, we return one at random. Minimally
one solution will always exist, namely the empty set, so we can safily ask for
a random one.

```scala
  // Return a (valid) set of invitees at random.
  invitees.random.get
}
```

This conclused the implementation of {% problem Selecting Invitees (version 4)
%}. Now we need to create some input on which it can run and to do that we use
the helper functions.

```scala
val group = Person.randomGroup(10)    // Generate random group
val personsLiked = group.take(5)      // The first 5 are liked
val personsDisliked = group.drop(5)   // The rest is disliked

def like = group.randomLikeFunction(.7) // Autogenerate random like relations

Viz.render(group.toDotString(personsLiked, personsDisliked, like))
```

Then we simply evaluate ```si4(.)``` on this input.

```scala
si4(group, personsLiked, personsDisliked, like, k = 2)
```

{% question %}
Try to play around with the ratios of people that are liked by the host and the
ratio of pairs that like eachother. Look at the visualization of the input and
see if you can find some interesting observations on the output.
{% hidden Hint? %}
Sorry, this is a trick question. It is very hard to find interesting patterns in
single observations, unless you are exploring edge cases. After we cover {%
problem Selecting Invitees (versions 5 and 6) %}, will show how to analyze the
formalizations' behaviour across many (different) inputs.
{% endhidden %}
{% endquestion %}

{% question %}
In these simulations you can generate groups of any size. The simulation,
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

From here on, you are free to explore the implementations of {% problem
Selecting Invitees (versions 5 and 6) %} on your own. Try simulating some inputs
to get a feeling for the differences between the three formalizations. You can
even change the simulation code if you want. Perhaps try implementing any of the
other versions? After simulating the three models individually, we provide a
sandbox for you to compare their behaviour directly.

{% fproblem Selecting invitees (version 5) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
$$G \subseteq P$$ such that $$|G\cap L| + |X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G\}~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}


```scala
def si5(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean): Set[Person] = {

  // Input must satisfy these constraints, otherwise error.
  require(personsLiked <= persons,
          "personsLiked must be a subset of persons")
  require(personsDisliked <= persons,
          "personsDisliked must be a subset of persons")
  require(personsLiked /\ personsDisliked == Set.empty,
          "personsLiked intersect personsDisliked must be emtpy")
  require(personsLiked \/ personsDisliked == persons,
          "personsLiked union personsLiked must equal persons")

  // Specify the optimality condition.
  def gl_x_g(invitees: Set[Person]): Int = {
    // The number of invitees the host likes.
    val gl = (invitees /\ personsLiked).size
    // The number of unique pairs that like eachother.
    val x = { invitees.uniquePairs | like.tupled }.size
    // The number of total invitees.
    val g  = invitees.size
    gl + x + g
  }

  val invitees = powerset(persons).argMax(gl_x_g)

  // Return a (valid) set of invitees at random.
  invitees.random.get
}

val group = Person.randomGroup(10)    // Generate random group
val personsLiked = group.take(5)      // The first 5 are liked
val personsDisliked = group.drop(5)   // The rest is disliked

def like = group.randomLikeFunction(.7) // Autogenerate random like relations

Viz.render(group.toDotString(personsLiked, personsDisliked, like))

si5(group, personsLiked, personsDisliked, like)
```

{% fproblem Selecting invitees (version 6) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|Y| \leq k$$ and  $$|G\cap L|+|G|$$ is maximized (where $$Y = \{p_i,p_j \in G\}~|~like(p_i,p_j) = false \wedge i\neq j \}$$).
{% endfproblem %}

```scala
def si6(persons: Set[Person],
        personsLiked: Set[Person],
        personsDisliked: Set[Person],
        like: (Person, Person) => Boolean,
        k: Int): Set[Person] = {

  // Input must satisfy these constraints, otherwise error.
  require(personsLiked <= persons,
          "personsLiked must be a subset of persons")
  require(personsDisliked <= persons,
          "personsDisliked must be a subset of persons")
  require(personsLiked /\ personsDisliked == Set.empty,
          "personsLiked intersect personsDisliked must be emtpy")
  require(personsLiked \/ personsDisliked == persons,
          "personsLiked union personsLiked must equal persons")

	// Specify that invitees is valid if |Y| <= k.
  def atMostKPairDislikes(invitees: Set[Person]): Boolean = {
    { invitees.uniquePairs | like.tupled }.size <= k
  }

  // Specify the optimality condition.
  def gl_g(invitees: Set[Person]): Int = {
    // The number of invitees the host likes.
    val gl = (invitees /\ personsLiked).size
    // The number of total invitees.
    val g  = invitees.size
    gl + g
  }

  val invitees = { powerset(persons) | atMostKPairDislikes _ }.argMax(gl_g)

  // Return a (valid) set of invitees at random.
  invitees.random.get
}

val group = Person.randomGroup(10)    // Generate random group
val personsLiked = group.take(5)      // The first 5 are liked
val personsDisliked = group.drop(5)   // The rest is disliked

def like = group.randomLikeFunction(.7) // Autogenerate random like relations

Viz.render(group.toDotString(personsLiked, personsDisliked, like))

si6(group, personsLiked, personsDisliked, like, k = 2)
```

### Analyzing and comparing formalizations

Simulations are a powerful tool to uncover consequences of formalization
choices, especially those that are hard to derive mathematically. However, the
full power of simulations is yet to be unlocked. Looking at single input
instances of single formalizations is not very informative and wouldn't be worth
the effort of coding. Let's see what we can learn about the three versions of {%
problem Selecting Invitees %} by comparing them to eachother. We follow the
example questions from [Chapter 8](/lovelace/part_iii/simulating).

First we ask: *Are these formalizations truly different, or are they
equivalent?* We can run the simulation for all three versions on the same input
to compare their output. To prevent redundant copying, the implementations can
be found in ```SelectingInvitees.si4(.)```, ```SelectingInvitees.si5(.)``` and ```SelectingInvitees.si6(.)```.

{% question %}
Using the code below, can you find input where two or more of the models give
the same output? If you find such input(s), what is it about them that leads to
equivalence?
{% hidden Hint? %}
Try defining input by hand instead of using the random generation first. For a
reminder, see [Supporting code](/lovelace/part_iii/sim_subset_choice#supporting-code) in this chapter. If you find input for which the formalizations are equivalent, then try finding variations of that input that also lead to equivalence.
{% endhidden %}
{% endquestion %}

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

For some inputs the formalizations might be equivalent, but for many others they
are not. Next, try to answer the question: *How would you be able tell different
formalizations apart in terms of the behaviour that they predict?* Finally your
hard work will pay off, because you can use simulations to do this. The code
below consists of three steps: (1) generate a set of inputs, (2) compute for all
inputs the corresponding output using ```si4```, ```si5``` and ```si6```, (3)
perform data analysis and plotting.

For Step 1 and 3 some additional (helper) code is introduced. Step 1 introduces
code that generates input using the same helper functions we've already seen,
but at a larger scale (i.e., more inputs) and by giving control over input
properties. This is the *constrained input generator* (see [Chapter
7](/lovelace/part_iii/mathlib#simulation-architecture)). In Step 3, we perform
an example analysis of the simulation data.

{% question %}
Using the code below, what kind of differences can you find between the three
formal theories and when do you find them? Under what conditions do they
disappear?
{% hidden Hint? %}
You can manipulate parameters of the input generator to run analyses under
varying conditions. Remember that group sizes larger than 10 will most likely
not finish simulating before the end of the universe due to exponential growth
of the search space.
{% endhidden %}
{% endquestion %}


```scala
// Generate inputs
val inputData: List[SelectingInvitees.Input] =
  SelectingInvitees.inputGenerator(groupSize = 5,
                                   likeDislikeRatio = .2,
                                   pairLikeRatio = .4,
                                   k = 2,
                                   sampleSize = 50)

// Compute outputs
val outputDataSI4: List[Set[Person]] = inputData.map(input =>
  SelectingInvitees.si4(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like,
                        input.k))

val outputDataSI5: List[Set[Person]] = inputData.map(input =>
  SelectingInvitees.si5(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like))

val outputDataSI6: List[Set[Person]] = inputData.map(input =>
  SelectingInvitees.si6(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like,
                        input.k))

// Perform data analyses
def analysis1(io: (SelectingInvitees.Input, Set[Person])): (Double, Double) = {
  val input = io._1
  val output = io._2
  val nrLikes = input.group.uniquePairs.filter(input.like.tupled).size
  val nrDislikes = input.group.uniquePairs.filter(!input.like.tupled(_)).size
  val ldRatio = nrLikes.toDouble / nrDislikes
  val size = output.size.doubleValue
  (ldRatio, size)
}

val dataAnalysis1SI4 = (inputData zip outputDataSI4).map(analysis1)
val dataAnalysis1SI5 = (inputData zip outputDataSI5).map(analysis1)
val dataAnalysis1SI6 = (inputData zip outputDataSI6).map(analysis1)

def analysis2(io: (SelectingInvitees.Input, Set[Person])): (Double, Double) = {
  val input = io._1
  val output = io._2
  val nrLikes = input.group.uniquePairs.filter(input.like.tupled).size
  val nrDislikes = input.group.uniquePairs.filter(!input.like.tupled(_)).size
  val ldRatio = nrLikes.toDouble / nrDislikes
  val avgLikes = output.uniquePairs.filter(input.like.tupled).size
  (ldRatio, avgLikes)
}

val dataAnalysis2SI4 = (inputData zip outputDataSI4).map(analysis2)
val dataAnalysis2SI5 = (inputData zip outputDataSI5).map(analysis2)
val dataAnalysis2SI6 = (inputData zip outputDataSI6).map(analysis2)

// Plot analysis 1
val trace14 = Trace(dataAnalysis1SI4, "SI4", PlotType.Line).mean
val trace15 = Trace(dataAnalysis1SI5, "SI5", PlotType.Line).mean
val trace16 = Trace(dataAnalysis1SI6, "SI6", PlotType.Line).mean

Plot(List(trace14, trace15, trace16),
     xAxisTitle = "pair-wise like/dislike ratio",
     yAxisTitle = "nr invitees").render

// Plot analysis 2
val trace24 = Trace(dataAnalysis2SI4, "SI4", PlotType.Line).mean
val trace25 = Trace(dataAnalysis2SI5, "SI5", PlotType.Line).mean
val trace26 = Trace(dataAnalysis2SI6, "SI6", PlotType.Line).mean

Plot(List(trace24, trace25, trace26),
    xAxisTitle = "pair-wise like/dislike ratio",
    yAxisTitle = "average likes among invitees").render
```

The analysis and plotting functionality within the online Scala system is quite
limited. If you want to explore the simulations more extensively consider
running the simulations in a dedicated Scala development environment (see
[Installing Scala and ```mathlib```](/lovelace/part_iii/simulating#installing-scala-and-mathlib)) and
download the code here. You can also use the code block below and download the
raw data to perform analyses in your favorite statistical analysis software. The
code below might take longer to run as it simulates {% problem Selecting Invitees
%} for many more combinations of parameters. The resulting CSV file will also
be possibly large. Table 2 lists the CSV format.

{% marginnote 'Table-ID2' 'Table 2: CSV format for group size $$n$$.'  %}
<div class="table-wrapper" markdown="block" style="margin-top:3rem;">

| column |type | description |
| :--- | :---: | :--- |
| p0 .. pn | true/false | host likes pi |
| p0-p1 ..  pn-p(n-1) | true/false | pi and pj like each other |
| k | int | k value |
| p0-si4 .. pn-si4 | true/false | pi is invited in si4 |
| p0-si5 .. pn-si5 | true/false | pi is invited in si5 |
| p0-si6 .. pn-si6 | true/false | pi is invited in si6 |

</div>

<div class="table-wrapper" markdown="block" style="margin-top:3rem;">



</div>

```scala
val groupSize = 6
val likeDislikeRatios = Set(0, 0.22, 0.66, 1.0)
val pairLikeRatios = Set(0, 0.22, 0.66, 1.0)
val ks = Set(0, 0.22, 0.66, 1.0)
val sampleSize = 1

val inputData: List[SelectingInvitees.Input] =
  (for(likeDislikeRatio <- likeDislikeRatios;
      pairLikeRatio <- pairLikeRatios;
      k <- ks) yield {
        SelectingInvitees.inputGenerator(
          groupSize,
          likeDislikeRatio,
          pairLikeRatio,
          (k * groupSize).intValue,
          sampleSize
          )
      }).toList.flatten

// Compute outputs
val outputDataSI4: List[Set[Person]] = inputData.map(input =>
  SelectingInvitees.si4(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like,
                        input.k))

val outputDataSI5: List[Set[Person]] = inputData.map(input =>
  SelectingInvitees.si5(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like))

val outputDataSI6: List[Set[Person]] = inputData.map(input =>
  SelectingInvitees.si6(input.group,
                        input.personsLiked,
                        input.personsDisliked,
                        input.like,
                        input.k))

// Safe data to CSV
def inputHeader(input: SelectingInvitees.Input): String = {
  val groupList = input.group.toList
  val people = for(i <- groupList.indices) yield s"p$i"
  val pairs = for(i <- groupList.indices; j <- groupList.indices if i != j) yield s"p$i-p$j"
  people.mkString("", ",\t", ",\t") + pairs.mkString("", ",\t", ",\t") + "k"
}

def outputHeader(input: SelectingInvitees.Input, label: String): String = {
  val groupList = input.group.toList
  val people = for(i <- groupList.indices) yield s"p$i-$label"
  people.mkString(",\t")
}

def dataToCSV(input: SelectingInvitees.Input, outputs: List[Set[Person]]): String = {
  val groupList = input.group.toList
  val hostLikes = groupList.map(person => person in input.personsLiked)
  val likings = for(p1 <- groupList; p2 <- groupList if p1 != p2) yield input.like(p1, p2)
  val k = input.k
  val results = outputs.map(output => groupList.map(_ in output).mkString(",\t"))
  hostLikes.mkString("", ",\t", ",\t") + likings.mkString("", ",\t", ",\t") + input.k  + results.mkString(",\t", ",\t", "")
}

val header = inputHeader(inputData.head) + ",\t" +
  outputHeader(inputData.head, "si4") + ",\t" +
  outputHeader(inputData.head, "si5") + ",\t" +
  outputHeader(inputData.head, "si6")

val rows = for(i <- inputData.indices) yield
  dataToCSV(inputData(i), List(outputDataSI4(i), outputDataSI5(i), outputDataSI6(i)))

val csv = header + "%0A" + rows.mkString("%0A")

Fiddle.print(a(href:=s"data:text/csv,$csv", target:="_blank", attr("download"):="data.csv", "Right click and Save link as..."))
```

### References

van Rooij, I., & Baggio, G. (2021). [Theory before the test: How to build high-verisimilitude explanatory theories in psychological science.](https://journals.sagepub.com/doi/full/10.1177/1745691620970604) *Perspectives on Psychological Science, 16*(4) 682–697.
