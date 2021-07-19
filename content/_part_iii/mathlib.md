---
layout: page
title: Scala and mathlib
chapter: 9
nav_exclude: true
---

Learning how to read and write code is quite similar to learning how to use
math to formalise verbal theories. In this chapter we cover the basics of
coding in Scala and ```mathlib```. Understanding Scala code might seem
scary at first, but you have already mastered a more difficult skill: Translating
verbal theory (which is underspecified) to formal, mathematical characterizations.
The translation from the formal domain to simulation code requires understanding
the Scala, you have much less ambiguity to deal with.

At the end of this chapter you will be able to understand how concepts from
[Chapter 3 - Math concepts and notation](/lovelace/part_i/math) can be
translated to Scala code. The chapter is quite detailed and we recommend to use
it as a reference guide when reading chapters on simulations or when writing
your own. The [cheat sheet](/lovelace/part_iii/mathlib#cheat-sheet) down below
provides a helpful overview.

## Scala fundamentals
We cover only a small subset of the full Scala language here. This subset
suffices for implementing basic simulations of formal theories. If after reading
this chapter you want to learn more about Scala, there are various textbooks
(REFS) and online resources such as the official
[Tour of Scala](https://docs.scala-lang.org/tour/tour-of-scala.html).

### Variables, functions and types

The basic expressions in a functional programming language such as Scala are
variables, functions and types. Variables, like their mathematical counterpart,
are containers storing a value. For example:

{% scalafiddle template="mathlib" %}
```scala
val myNumber = 3
```
{% endscalafiddle %}

Let's break this example down into its parts. The word ```val``` tells the
computer that you will specify a *value*, which is a container storing a
constant (in this case the integer 3). The *identifier* (name) of this value is
the word ```number```. The *assignment operator* ```=``` takes the value on its
right side and stores is in the container on the left side.

Any container in Scala has a *type*. For example, the value ```myNumber```
above has type ```Int``` which stands for integer. Scala can often derive these
types automatically, but for clarity you may want to use *explicit types*:

{% scalafiddle template="mathlib" %}
```scala
val mySecondNumber: Int = 2
```
{% endscalafiddle %}

Types are useful, since they provide a safety net against programming mistakes
since you cannot assign a value that is incompatible with the specified type.
Running the following code results in a ```type mismatch``` error and the
compiler (i.e., the software that interprets and runs your code) will even tell
you which type it expected and which type you provided.

{% scalafiddle template="mathlib" %}
```scala
val mySecondNumber: Int = 3.9
```
{% endscalafiddle %}

Functions allow us to write code that takes input, one or more *arguments*, and
returns output. For example, addition $$add(x, y) = x+y$$ can be coded as:

{% scalafiddle template="mathlib" %}
```scala
def add(x: Int, y: Int): Int = {
  x + y
}

add(3, 4)
```
{% endscalafiddle %}

The word ```def``` specifies that we are constructing a *function* with the
*identifier* ```add```. The comma-separated list between parantheses is the list
of arguments you can pass to this function, where each argument has a specified
type. Functions require that the type of the output is explicitly defined, in
this example ```Int```. Then the assignment operator ```=``` links the *body* of
function which is delineated by curly brackets. Whenever we call this function
with the right arguments, the value of the body is computed relative to the
arguments and that value is the output of the function.

{% question %}
Fill in the blanks in the code below and write a function that computes the
following equation: $$f(a, b, c) = a + b * c$$
{% hidden Hint? %}
You need to replace the dots ```...``` with a list of comma separated arguments,
and replace the three question marks ```???``` with the expression that evaluates
the equation.
{% endhidden %}
{% endquestion %}

{% scalafiddle template="mathlib" %}
```scala
def equation(...): Int = {
  ???
}

equation(2, 5, -1) == -3    // Test the function, true if correct.
```
{% endscalafiddle %}

Functions in Scala have types too which becomes clearer with the following
alternative notation.

{% scalafiddle template="mathlib" %}
```scala
def add: (Int, Int) => Int = (x: Int, y: Int) => {
  x + y
}
add(3, 4)
```
{% endscalafiddle %}

This notation is not used often since it is hard to read, but it explicitly
defines the function's type which is very similar to how we express function
types mathematically:

| Scala | ```(Int, Int) => Int``` |
|:--:|:--:|
| Math | $$ add: \mathbb{N} \times \mathbb{N} \rightarrow \mathbb{N} $$ |

In functional programming languages like Scala you can even pass a function as
an argument of another function. This is a powerful way to organize your code
and very useful in writing simulation code that is closely tied to formal
theories. In a sense, a theory is a function itself, mapping a list of arguments
(input) to output. We've seen that some formal theories can have functions as
arguments. For example, {% problem Selecting invitees (version 1) %} in [Chapter
4](/lovelace/part_ii/subset) takes as input the function $$like: P \times P
\rightarrow \{true, false\}$$. Here is a (partial) example of a function as
argument.

```scala
def selectingInvitees(..., like: (Person, Person) => Boolean)
```

We return to selecting invitees in the [next
chapter](/lovelace/part_iii/sim_subset_choice), where we will use this coding
strategy. For now, we should note one syntactic oddity in the Scala language.
Sometimes when you pass a function as an argument, the compiler will complain
with the following message:

```
error: missing argument list for method myFun
Unapplied methods are only converted to functions when a function type is expected.
You can make this conversion explicit by writing `myFun _` or `myFun(_)` instead of `myFun`.
```
 The solution for this problem is often following the instructions in the error
 explicitly and add an underscore ```_``` to the function. For example:

 ```scala
selectingInvitees(..., like)    // Procudes error.
selectingInvitees(..., like _)  // Correct.
 ```

Finally, while we won't go into details of object-orientation here, it is useful
to know that some functions accompany certain types. For example, the type
```String``` has functions built-in that can be called with the dot-notation.
These functions (also called *methods*) have access to the value they are called
upon. The following example called method ```toUpperCase``` that evaluates to
the upper-case version of the original string.

{% scalafiddle template="mathlib" %}
```scala
"This is a String.".toUpperCase
```
{% endscalafiddle %}

{% marginnote 'mn-auto-complete' 'Auto-complete can be triggered with
```Ctrl-space``` or ```Cmd-space``` in Scalafiddle which we use in this book.
Other development tools often use the same key-command, or alternatively the
```Tab``` key.' %} Many Scala development tools allow you to look through the
list of methods by using *auto completion*. Just add a ```.``` to a value and
access auto complete to open the list.

### Blocks and scope

We have implicitly used the notions of *block* and *scope*, but how are they
defined? In Scala a block is a sequence of expressions *delineated by curly
brackets. A block has a value which is the value of the *last statement in the
block.

{% scalafiddle template="mathlib" %}
```scala
{
  val a = 3
  val b = 6
  a + b       // This block evaluates to 9 with type Int.
}
```
{% endscalafiddle %}

Blocks can be nested, but values and functions defined within a block cannot
be accessed outside that block. This property is known as scope, accessing
values or functions out of scope results in a compiler error:

{% scalafiddle template="mathlib" %}
```scala
{
  val x = 3

  {
    val y = 6
    x + y       // Valid, x and y are in this block's scope.
  }

  x + y         // Invalid, y is outside this block's scope.
}
```
{% endscalafiddle %}

### Conditional

The *conditional* expression is more colloquially known as the if-then-else
expression. It allows for branching paths of code, depending on the truth
value of the conditional. An example (try changing the value of ```x```):

{% scalafiddle template="mathlib" %}
```scala
val x = 4
if(x % 2 == 0) {
  println("X is even.")
} else {
  println("X is odd.")
}
```
{% endscalafiddle %}

Each part of the conditional consists of a code block, though for single
expressions the curly brackets to simplify the code:

{% scalafiddle template="mathlib" %}
```scala
val x = 4
if(x % 2 == 0) println("X is even.")
else println("X is odd.")
```
{% endscalafiddle %}

You can have an arbitrary number of branching paths using ```else if```:

{% scalafiddle template="mathlib" %}
```scala
val x = 4
if(x < 0) println("X is negative.")
else if(x == 0) println("X is zero.")
else if(x <= 10) println("X is small.")
else println("X is large.")
```
{% endscalafiddle %}

The conditional expression is a block with nested blocks. This means that it
evaluates to the value of the last expression in the block that is evaluated
by the conditional. This behaviour is useful when defining a function whose
output is computed differently depending on some truth condition. For example,
a function that computes the absolute value of ```x``` multiplies ```x``` with
-1 if ```x``` is negative and otherwise evaluates to ```x```.

{% question %}
Complete the code below by implementing the body of <code>abs(x: Int): Int
</code>. The function should evaluate to the absolute (positive) value of
<code>x</code>.
{% hidden Hint? %}
You need to use the conditional expression. Look at the examples above where the
conditional tests for negative numbers. You need to distinguish two cases:
<code>x</code> is negative or not and for each case compute the right value.
{% endhidden %}
{% endquestion %}

{% scalafiddle template="mathlib" %}
```scala
def abs(x: Int): Int = {
  ???
}

abs(-10) == 10    // Test the function, true if correct.
```
{% endscalafiddle %}


### Basic types

Scala comes with a plethora of types and datastructures, many of which fall
beyond the scope of this book. However, the following basic types and their
operators will be very useful to know.

| Type | Math equivalent | Example value |
| :--- |:--- |:--- |
| ```Int``` | $$\mathbb{N}$$ | 3 |
| ```Double``` |  $$\mathbb{R}$$ | 2.7 |
| ```Boolean``` |  $$\{true, false\}$$ | ```true``` |
| ```Char``` | n.a. | 'c' |
| ```String``` |  n.a. | "Awesome" |

```Int``` and ```Double``` share many operators such as addition ```+```,
subtraction ```-```, multiplication ```*``` and division ```/```. The library
```Math``` also contains several useful functions which you can apply with the
dot notation:

{% scalafiddle template="mathlib" %}
```scala
val x: Double = 3
val y: Double = 6

println(x + y)
println(x - y)
println(x * 3)
println(y / 2)            // Division.
println(y % 2)            // Remainder or modulo.
println(Math.pow(x, y))   // Exponentiation, x^y.
println(Math.min(x, y))
println(Math.max(x, y))
```
{% endscalafiddle %}

For Boolean types these are some common expressions:
{% scalafiddle template="mathlib" %}
```scala
println(true && true)   // Logical and.
println(true || false)  // Logical or.
println(true ^ true)    // Logical xor.
println(!true)          // Negation.
```
{% endscalafiddle %}

And these can be combined with numbers like so:
{% scalafiddle template="mathlib" %}
```scala
val x: Double = 3
val y: Double = 6

println(x <= 3 && x > 1)          // Number between 1 and 3 (inclusive).
println(x % 2 == 0 || x > 0)      // Positive even number.
println(x % 2 == 0 ^ y % 2 == 0)  // Either x or y is even, not both.
println(!(x < 0))                 // x is positive.
```
{% endscalafiddle %}

If you change in the code example above ```Double``` to ```Int``` you might
notice that there is a certain compatility between the two types. That is, the
compiler does not give a type error when you add a double to an integer, even
though the addition function expects two doubles or two integers. {% marginnote
'mn-id-polymorphism' 'This property is known as *polymorphism* and is an
advanced topic. For the curious, see this [overview on
Wikipedia](https://en.wikipedia.org/wiki/Polymorphism_(computer_science)).'
%} This is because Scala knows it can convert an integer to a double value, which
it will automatically do.

{% scalafiddle template="mathlib" %}
```scala
val x: Int = 3
val y: Double = 2.5
x+y    // Evaluates to a Double value 5.5
```
{% endscalafiddle %}

Some types cannot be converted and when you try to mix these, the compiler will
let you know with an error:

{% scalafiddle template="mathlib" %}
```scala
val x: Int = 3
val b: Boolean = true
x + b    // How to add a Boolean to an Int?
```
{% endscalafiddle %}

This conversion also kicks in when calling a function with compatible arguments:

{% scalafiddle template="mathlib" %}
```scala
def add(a: Double, b: Double): Double = a +b
add(1.4, 3)
```
{% endscalafiddle %}

{% question %}
This conversion often only works one way. It is not possible to convert a Double
to an Int without loosing information. Observe what happens when you run the
code example above after changing the type of the arguments of <code>add</code> to
<code>Int</code>.
{% endquestion %}

### Lists, sets and tuples

When you want to store multiple values you can use collections. Example
collections could be the temperature forecast for the next seven days:

|--|
|-:|
|22.2 °C|
|23.1 °C|
|23.7 °C|
|22.3 °C|
|24.3 °C|
|24.7 °C|
|25.1 °C|

Or the people that you know: Erik, Lamar, Angelica, Emanuel, Lorraine, Meghan,
Myron, Erica, Lester, Javier, Kelly, Abraham, Lindsay, Harriet, and Guadalupe.
Or the cost of a menu card item: Vegan pie costs €9,90.

Some collections, like the temperature forecast and menu card item, are ordered:
one value follows the next. In math, these are expressed in a list or sequence
$$\langle 22.2, 23.1, 23.7, 22.3, 24.3, 24.7, 25.1 \rangle$$ or a tuple
$$(\text{vegan pie}, 9.9)$$. Unordered collections, such as people, are
expressed in a set $$\{\text{Erik},$$ $$\text{Lamar},$$ $$\text{Angelica},$$
$$\text{Emanuel},$$ $$\text{Lorraine},$$ $$\text{Meghan},$$ $$\text{Myron},$$
$$\text{Erica},$$ $$\text{Lester},$$ $$\text{Javier},$$ $$\text{Kelly},$$
$$\text{Abraham},$$ $$\text{Lindsay},$$ $$\text{Harriet},$$
$$\text{Guadalupe}\}$$.

In Scala we can store ordered collections in a ```List``` or tuple and unordered
collections in a ```Set```:

{% scalafiddle template="mathlib" %}
```scala
val forecast = List(22.2, 23.1, 23.7, 22.3, 24.3, 24.7, 25.1)
val menuItem = ("Vegan pie", 9.90)
val people   = Set("Erik", "Lamar", "Angelica", "Emanuel", "Lorraine",
                   "Meghan", "Myron", "Erica", "Lester", "Javier", "Kelly",
                   "Abraham", "Lindsay", "Harriet", "Guadalupe")
```
{% endscalafiddle %}

We'll dive into sets below using the ```mathlib``` library, so let's first get
some familiarity with lists. Some basic examples are in the code block below.
Try playing around with them to see what they do.

{% scalafiddle template="mathlib" %}
```scala
val forecastThisWeek = List(22.2, 23.1, 23.7, 22.3, 24.3, 24.7, 25.1)
val forecastNextWeek = List(22.3, 19.8, 18.4, 18.0, 17.6, 17.5, 17.2)

println(23.1 :: forecastThisWeek)               // Prepend element.
println(forecastThisWeek ::: forecastNextWeek)  // Prepend list.
println(forecastThisWeek.size)                  // Number of elements in list.
println(forecastThisWeek.contains(23.7))        // Does the list contain element?
println(forecastThisWeek.head)                  // The first element of the list.
println(forecastThisWeek.tail)                  // Everything except the first element.
println(forecastThisWeek(3))                    // The n-th element.
println(forecastThisWeek.isEmpty)               // Checks whether the list is emtpy.
```
{% endscalafiddle %}

The power of collections lies in being able to apply to all of the elements. The
idea is that if we have a function that applies to one element, e.g., $$sq(x) =
x^2)$$, we can apply it to all elements in the list. The most common of these
applications is called ```map```. It takes as argument a function ```f``` and
evaluates to a list where each element computed using ```f```:


|-:|:-:|:-:|:-:|:-:|
| ```list``` | ```1``` | ```2``` | ```3``` | ```4``` |
|  | $$\downarrow$$ | $$\downarrow$$ | $$\downarrow$$ | $$\downarrow$$ |
|  | ```sq(1)``` | ```sq(2)``` | ```sq(3)``` | ```sq(4)``` |
|  | $$\downarrow$$ | $$\downarrow$$ | $$\downarrow$$ | $$\downarrow$$ |
| ```list.map(sq)``` | ```1``` | ```4``` | ```9``` | ```16``` |

{% scalafiddle template="mathlib" %}
```scala
// Function that computes the square root of x.
def sq(x: Int): Int = x * x
val list = List(1, 2, 3, 4)

println(list.map(sq))
```
{% endscalafiddle %}

The type of the argument of the function must be the same type as the elements
in the list, but its output can be of any type. For example, take an ```Int```
and return a ```String```.

{% scalafiddle template="mathlib" %}
```scala
// Function that creates a String with x "x"s.
def xx(x: Int): String = {
  if(x==1) "x"
  else "x" + xx(x-1)
}
val list = List(1, 2, 3, 4)

println(list.map(xx))
```
{% endscalafiddle %}

What other useful things can we do with lists? Below are some self-explanatory
examples.

{% scalafiddle template="mathlib" %}
```scala
// Function that checks whether x is even.
def isEven(x: Int): Boolean = {
  x % 2 == 0
}
val list = List(1, 2, 3, 4)

println(list.exists(isEven))    // Does list contain an element that isEven?
println(list.exists(_ > 3))     // Implicit function, does the list contain an element larger than 3?
println(list.forall(isEven))    // Do all elements in list return true for isEven?
println(list.forall(_ <= 100))  // Implicit function, does the list contain an element larger than 3?
println(list.filter(isEven))    // Filter out all elements that return true for isEven.
println(list.filter(_ < 3))     // Filter out all elements less than 3.
```
{% endscalafiddle %}

There are some special methods for lists that contain numbers:
{% scalafiddle template="mathlib" %}
```scala
// Function that checks whether x is even.
val list = List(1, 2, 3, 4)

println(list.sum)             // The sum of all numbers in list.
println(list.product)         // The product of all numbers in list.
```
{% endscalafiddle %}

Many of the functions and methods that work for lists, also work for other
collections such as sets. Even a <code>String</code> can be treated as a
collection as it is essentially a list of characters.

{% question %}
Look at the code scaffold below. Implement the body of the function
<code>consonant</code> and choose the method to apply to <code>sentence</code>
such that the code evaluates to the sentence with only consonants.
{% hidden Hint? %}
The list of (English) vowels is given. Write code that checks if the
<code>character</code> does not exist within vowels.
{% endhidden %}
{% endquestion %}

{% scalafiddle template="mathlib" %}
```scala
val sentence = "Hi, you're doing awesome!"
val vowels   = List('a', 'e', 'i', 'o', 'u')

def consonant(character: Char): Boolean = {
  ???
}

sentence.___(consonant)
```
{% endscalafiddle %}

### Generics

Collections in Scala are what is called *generic*. The change their type
depending on the values you apply to them. The list containing the forecast
consists of values of type ```Int```, therefore the type of ```forecast``` is
```List[Int]```. In Scala, the type of a generic includes the type of its
contents denoted between square brackets.

{% question %}
What is the type of <code>vowels</code>? Fill in the blanks: <code>List[___]</code>
{% hidden Hint? %}
What is the type of the values contained in the list?
{% endhidden %}
{% endquestion %}

{% stopandthink %}
What benefits do we get from generics?
{% endstopandthink %}

Collections, courtesy of being generic, can store different types. A set of
doubles? No problem ```Set[Double]```. A list of Booleans? Easy
```Set[Boolean]```! The type of a collection also grants a level of protection
to the programmer. You cannot prepend a Boolean to a list of integers:

{% scalafiddle template="mathlib" %}
```scala
val forecast: List[Double]  = List(22.2, 23.1, 23.7, 22.3, 24.3, 24.7, 25.1)
val forecast2: List[Double] = true :: forecast
```
{% endscalafiddle %}

If you could mix different (incompatible) types in a collection, performing
calculations on that collection would be either undefined (e.g., what does
mean to compute the sum of a list of ```Int```, ```Boolean``` and ```String```?)
or it would have unpredictable effects.

## ```mathlib```

```mathlib``` is a library written to support the development of simulations of
formal theories (computational-level models specifically). {% marginnote
'mn-id-implementation' 'Admittedly, an exact implementation is not always
possible. Especially when the simulation deviates from the formal theory, it is
important to be able for experts to understand how the simulation is different.'
%} Ideally, we want (experts) to be able to read simulation code and
*understand* that it is an exact implementation of the formal theory. As you may
have noticed during the Scala introduction, functional programming is closely
related to the mathematical language of formal theories. Consider the following
example (toy) formal theory:

{% marginfigure 'mf-id-whatever' 'assets/img/pexels-roman-odintsov-6147834.jpg' '' %}
{% fproblem Pizza Toppings %}
A set of toppings $$T$$, a budget $$b\in\mathbb{N}$$, and a cost function
for toppings $$c: T\rightarrow \mathbb{N}$$;;
A selection of toppings $$T'\subseteq T$$ such that $$\sum_{t \in T}c(t) \leq b$$.
{% endfproblem %}

In this section you will learn how to read (and write) simulation code that
implements a formal theory and is easy to understand that it does what we say it
does. Take a look at the following code implementing {% problem Pizza Toppings
%}. (We assume toppings are represented by ```Strings```.)

{% scalafiddle template="mathlib" %}
```scala
def pizzaToppings(toppings: Set[String], budget: Int, cost: String => Int): Set[String] = {
  // Helper function to compute the cost of a subset of toppings.
  def subsetCost(subset: Set[String]): Int = {
    subset.map(cost) // Transform each element in subset to its cost using the cost function.
          .sum       // Sum all costs.
  }

  // Helper function to check if a given subset fits within the budget.
  def subsetWithinBudget(subset: Set[String]): Boolean = {
    subsetCost(subset) <= budget
  }

  powerset(toppings)            // The set of all possible subsets.
    .filter(subsetWithinBudget) // Filter (keep) all subsets that fit the budget.
    .random                     // Get a random subset.
    .getOrElse(Set.empty)       // If to random subset exists, return the emtpy set.
}
```
{% endscalafiddle %}

This code example combines several expressions we covered earlier, but by
breaking down the components it is relatively easy to show that the code
implements {% problem Pizza Toppings %}. Of course, even though math is much
less ambiguous as verbal theory, that does not exclude there being multiple
(equivalent) expressions in math. Some might be easier to translate to Scala
code than others. We will encounter examples where rewriting the mathematical
expressions of the formal theory can help in clarifying the relationship with
the simulation.

In the next section, we explore how ```mathlib``` allows writing code for formal
theories using set theory. The library also contains support for probability and
graph theory. You can find out more at the library [Github
page](https://github.com/markblokpoel/mathlib), but using these advanced
mathematics requires installing a local development environment such as
Jupyter/Almond or Intellij (see [Installing Scala and ```mathlib```](/lovelace/part_iii/simulating#installing-scala-and-mathlib)).

### Set theory

In this book, set theory plays an important role in formalizing verbal theories,
so we start exploring ```mathlib``` there. This section follows the same
structure as [Chapter 3 - Math concepts and notation](/lovelace/part_i/math).

Creating a set is similar to creating other collections such as lists. Set
membership in Scala is an expression that evaluates to ```true``` if the element
is within the set or ```false``` if it is not. The Scala code below creates the
set $$P = \{\text{Ramiro},\text{Brenda},\text{Molly}\}$$, then tests if
$$\text{Ramiro}\in P$$ and if $$\text{Saki}\in P$$.

{% scalafiddle template="mathlib" %}
```scala
val p: Set[String] = Set("Ramiro", "Brenda", "Molly")

println("Ramiro" in p)
println("Saki" in p)
```
{% endscalafiddle %}

Subset and superset expressions also evaluate to ```true``` or ```false```.

{% scalafiddle template="mathlib" %}
```scala
val animals = Set("cat", "cuttlefish", "turtle", "blue whale")
val mammals = Set("cat", "blue whale")
val thingsOnEarth = Set("cat", "cuttlefish", "turtle", "blue whale", "university", "chair")

println(mammals < animals)        // Mammals is a subset of animals.
println(thingsOnEarth > animals)  // Things on Earth is a superset of animals.
```
{% endscalafiddle %}

Intersection, union and difference evaluate to the correct super- or
subset.

{% scalafiddle template="mathlib" %}
```scala
val yourFriends = Set("John", "Roberto", "Holly", "Doris", "Charlene")
val myFriends =  Set("Vicky", "Charlene", "Ramiro", "Johnnie", "Roberto")

println(yourFriends /\ myFriends) // Common friends using intersection.
println(yourFriends \/ myFriends) // Friends we know together using union.
println(myFriends \ yourFriends)  // Who I know that you don't, using difference.
```
{% endscalafiddle %}

Before we look at set builder notation, consider the following. In formal
theories, we often use set theoretic notation not as Boolean tests, but to
stipulate the output. E.g., in {% problem Pizza Toppings %}, the output is a
subset $$T'\subseteq T$$ that fits in budget. This notation, however, does not
translate directly into Scala code. Consider the following rewrite of the formal
theory output:

$$T' \in \mathcal{P}(T)$$ such that $$\sum_{t\in T'}c(t)\leq b$$

This notation still does not easily translate, for two reasons. Firstly, because
the relationship between $$T'$$ and the desired property (fits in budget) is
expressed in natural language. Secondly, because there are multiple outputs
possible. Since the theory does not explicitly state which from the possible
outputs is preferred, we need to assume it either returns the entire set or
selects at random.

We can address the first issues by expressing the set of possible outputs and
the relationship between $$T'$$ and budget-fit explicitly in math using set
building notation. {% marginnote 'mn-id-helperfunctions' 'The practice of
decomposing parts of a theory (or code) into sub-parts is well known in
mathematics and programming. It can help make math and code more readable,
because we can abstract from the inner workings of a function.' %} We make our
lives easier by defining a help function for cost of subsets: $$cost:
\mathcal{P}(T) \rightarrow \mathbb{N}$$, where $$cost(T')=\sum_{t\in T'}c(t)$$.
Then the set of all subsets within budget can be defined as:

$$\left\{T'~\middle|~\mathcal{P}(T) \wedge cost(T')\leq b\right\}$$

Here, $$\mathcal{P}$$ is the powerset notation. It denotes the set consisting of
all possible subsets and $$T'$$ is an element in that set. The set-builder
notation describes the set of all possible subsets that satisfy the budget
constraint. Dealing second issue (multiple possible outputs), we make the
decision to return a random valid output.

{% scalafiddle template="mathlib" %}

```scala
def pizzaToppings(toppings: Set[String], budget: Int, cost: String => Int): Set[String] = {
```

The helper function that computes the cost of a subset is implemented as:
```scala
  // Helper function to compute the cost of a subset of toppings.
  def subsetCost(subset: Set[String]): Int = {
    sum(subset, cost)
  }
```

We add one more helper function to check if a subset fits within the budget.
```scala
  // Helper function to check if a given subset fits within the budget.
  def subsetWithinBudget(subset: Set[String]): Boolean = {
    subsetCost(subset) <= budget
  }
```

Finally, we can define the output of 	 <span style="font-variant:
small-caps">Pizza Toppings</span> using set builder notation:

```scala
  { powerset(toppings) | subsetWithinBudget _ }.random.getOrElse(Set.empty)
}
```
{% endscalafiddle %}

{% marginnote 'mn-id-multicode' 'You may have noticed this code is different
from the example given at the start of this section. Similarly to multiple
mathematical expressions being equivalent, there exist (many) different ways of
implementing the same function.' %} This is a example implementation uses set
builder to construct a set of subsets that fit within budget. Set builder, in
essence, filters out all elements (in the case above the elements are sets) from
the given set that do not satisfy the evaluation function on the right side.

```scala
{ givenSet | evalFun _ }
```

{% question %}
Complete the code scaffold below such that the set builder expression evaluates
to the subset of odd numbers in <code>numbers</code>.
{% endquestion %}

{% scalafiddle template="mathlib" %}
```scala
// Function that checks if a number is odd.
def isOdd(n: Int): Boolean = {
  ???
}

val numbers: Set[Int] = (0 to 11).toSet   // Set consisting of numbers 0 to 11

{ ___ | ___ }
```
{% endscalafiddle %}


The final expression for working with sets is the cardinal product $$\times$$. The
cardinal product between two sets $$A$$ and $$B$$ returns a set with all
possible pairs of elements in $$A$$ and $$B$$. Pairs, in Scala, are represented
as tuples.

```scala
val pair: (Int, String) = (4, "Book")
```

The type of a tuple combines the types of the elements. The cardinal product
between two sets yields a set with the subtype of the tuple.

{% scalafiddle template="mathlib" %}
```scala
val numbers: Set[Int]  = Set(1, 2, 3, 4)
val items: Set[String] = Set("Book", "Candle", "Wine")

val itemNumberPairs: Set[(String, Int)] = items x numbers

println(itemNumberPairs)
```
{% endscalafiddle %}


## Simulation architecture

Now that we've covered the basics for Scala and ```mathlib``` we can go cover
what constitutes a computer simulation of a formal theory. To run a computer
simulation you minimally need two components: The implementation of the formal
theory and sample input. The advantage of simulations are that you can let the
computer do the hard work computing many input-output mappings, but it is still
a lot of work if have to supply each input manually.

{% maincolumn 'assets/img/simulation-components.svg' 'A simulation architecture
consists of the implementation of the formal theory and an input generator that
supplies inputs.' %}

Manually supplying input can still be useful for checking for errors in the
code, or to investigate particular limit cases of the theory and initial
explorations. We will use manual inputs throughout the next chapters.

However, a more complete simulation covers many inputs, preferably exploring a
wide range of different cases. For example, with the {% problem Pizza Toppings %}
example one might want to explore inputs ranging from few to many toppings, with
many different cost functions and budget. In these cases it is useful to write
a helper function that can generate input with various properties.

Unfortunately, like with formalizing verbal theory, there is not a single recipe
for writing input generators. Generators are highly contingent on the theory and
the parts of the input domain you wish to simulate. Within the context of this
book, you don't need to write your own generators, but over the next chapters
you will use example input generators provided by us.

We can put most generators in one of two categories: unconstrained input
generation or constrained input generation. Understanding the difference
between the to types of generators is important, as it impacts the kinds of
inputs one can generate, which in turn will impact the computer simulation
results.

Any procedure for randomly generating input will likely be biased. That means
that some inputs are more likely to be generated than others. This can be due to
particular structure in the input space, or due to properties of the generator.

A simplified representation of this bias is shown in the figure below. Out of
the set of all possible inputs, the darker areas might be more likely to be
generated than the light. Of course, input bias will also bias the output of the
simulations.

{% maincolumn 'assets/img/generator-bias.svg' 'Bias in *unconstrained random
generator* may not cover all possible inputs.' %}

We can deal with this bias by analyzing how well the generated inputs cover the
parts of the space you are interested in. An *unconstrained input generator* is
best used as such. These generators have bias over which we have no control such
as in the figure above. Bias doesn't render the simulations useless, as long as
we can characterize the bias and have some understanding of how it impacts the
results.

Alternatively, we can try to implement a *constrained input generator*. These
types of generators are still biased, but we have some degree of control over
the inputs they generate. Since there is still bias, it is wise to take that
into account, but we may be better able to cover the input space by setting the
right constraints. We can even use multiple sets of constraints to cover more
area of the input space, as is shown in the figure below.

{% maincolumn 'assets/img/generator-bias-constrained.svg' 'Bias in constrained
input generators. Each "bubble" is a particular set of constraints on the same
input generator, moving the bias across all possible inputs. By combining these
biases, we can cover more of the input domain.' %}

So why don't we always write unconstrained input generators? This is because
they are hard to design and implement.

## Cheat sheet

{% scalafiddle template="mathlib" %}
```scala

/**** BASIC TYPES ****/

val integer: Int = 3
val double: Double = 2.7
val bool: Boolean = true
val character: Char = 'c'
val string: String = "Awesome"

val x: Int = integer
val y: Double = double

println(x + y)
println(x - y)
println(x * 3)
println(y / 2)            // Division.
println(y % 2)            // Remainder or modulo.
println(Math.pow(x, y))   // Exponentiation, x^y.
println(Math.min(x, y))
println(Math.max(x, y))

println(true && true)   // Logical and.
println(true || false)  // Logical or.
println(true ^ true)    // Logical xor.
println(!true)          // Negation.

/**** Lists ****/
val forecastThisWeek = List(22.2, 23.1, 23.7, 22.3, 24.3, 24.7, 25.1)
val forecastNextWeek = List(22.3, 19.8, 18.4, 18.0, 17.6, 17.5, 17.2)

println(23.1 :: forecastThisWeek)               // Prepend element.
println(forecastThisWeek ::: forecastNextWeek)  // Prepend list.
println(forecastThisWeek.size)                  // Number of elements in list.
println(forecastThisWeek.contains(23.7))        // Does the list contain element?
println(forecastThisWeek.head)                  // The first element of the list.
println(forecastThisWeek.tail)                  // Everything except the first element.
println(forecastThisWeek(3))                    // The n-th element.
println(forecastThisWeek.isEmpty)               // Checks whether the list is emtpy.

val list = List(1, 2, 3, 4)
def sq(x: Int): Int = x * x   // Function that computes the square root of x.
println(list.map(sq))         // Map each value in list to sq(_)

def isEven(x: Int): Boolean = x % 2 == 0  // Function that checks whether x is even.

println(list.exists(isEven))    // Does list contain an element that isEven?
println(list.exists(_ > 3))     // Implicit function, does the list contain an element larger than 3?
println(list.forall(isEven))    // Do all elements in list return true for isEven?
println(list.forall(_ <= 100))  // Implicit function, does the list contain an element larger than 3?
println(list.filter(isEven))    // Filter out all elements that return true for isEven.
println(list.filter(_ < 3))     // Filter out all elements less than 3.
println(list.sum)               // The sum of all numbers in list.
println(list.product)           // The product of all numbers in list.

/**** Tuples ****/
val menuItem: (String, Double) = ("Vegan pie", 9.90)
println(menuItem._1)            // Get the first value of the tuple.
println(menuItem._2)            // Get the second value of the tuple.

/**** Sets ****/
val animals = Set("cat", "cuttlefish", "turtle", "blue whale")
val mammals = Set("cat", "blue whale")
val thingsOnEarth = Set("cat", "cuttlefish", "turtle", "blue whale", "university", "chair")

println("cat" in animals)         // Test if element is in  the set.
println(mammals < animals)        // Mammals is a subset of animals.
println(thingsOnEarth > animals)  // Things on Earth is a superset of animals.

val yourFriends = Set("John", "Roberto", "Holly", "Doris", "Charlene")
val myFriends =  Set("Vicky", "Charlene", "Ramiro", "Johnnie", "Roberto")

println(yourFriends /\ myFriends) // Common friends using intersection.
println(yourFriends \/ myFriends) // Friends we know together using union.
println(myFriends \ yourFriends)  // Who I know that you don't, using difference.

println({ Set(0, 1, 2, 3, 4, 5) | isEven _ }) // Build a set of even numbers.

println(myFriends x animals)      // Set of all pairs of friends and animals using cardinal product.
```
{% endscalafiddle %}
