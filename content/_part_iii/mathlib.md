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
[Chapter 3 - Math concepts and notation](/lovelace/part_i/math) can be translated
to Scala code. The chapter is quite detailed and we recommend to use it as a
reference guide when reading chapters on simulations or when writing your own.

## Scala fundamentals
We cover only a small subset of the full Scala language here. This subset
suffices for implementing basic simulations of formal theories. If after reading
this chapter you want to learn more about Scala, there are various textbooks
(REFS) and online resources such as the official
[Tour of Scala](https://docs.scala-lang.org/tour/tour-of-scala.html).

### Expressions: Variables, functions and types

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
\rightarrow \{true, false\}$$. Here is an (partial) example of a function as
argument.

```scala
def selectingInvitees(..., like: (Person, Person) => Boolean)
```
We return to selecting invitees in the
[next chapter](/lovelace/part_iii/sim_subset_choice).

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

{% scalafiddle template="mathlib" %}
```scala
def abs(x: Int): Int = {
  if(x < 0)
    -1 * x
  else
    x
}
```
{% endscalafiddle %}


### Useful types and datastructures

Scala comes with a plethora of types and datastructures, many of which fall
beyond the scope of this book. However, the following basic types and their
operators will be very useful to know.

| Type | Math equivalent | Example value |
| :--- |:--- |:--- |
| Int | $$\mathbb{N}$$ | 3 |
| Double |  $$\mathbb{R}$$ | 2.7 |
| Boolean |  $$\{true, false\}$$ | ```true``` |
| String |  n.a. | "Awesome" |

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
println(y / 2)            // division
println(y % 2)            // remainder or modulo
println(Math.pow(x, y))   // x^y
println(Math.min(x, y))
println(Math.max(x, y))
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





List

Set

## ```mathlib```

### Set theory
