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
We return to selecting invitees in the
[next chapter](/lovelace/part_iii/sim_subset_choice).

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

Finally, while we won't go into details of object-orientation here, it is useful
to know that some functions accompany certain types. For example, the type
```String``` has functions built-in that can be called with the dot- notation.
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
println(y / 2)            // division
println(y % 2)            // remainder or modulo
println(Math.pow(x, y))   // x^y
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
The list of vowels (for English) is given. Write code that checks if the
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


Collections in Scala are what is called *generic*. The change their type
depending on the values you apply to them. The list containing the forecast
consists of values of type ```Int```, therefore the type of ```forecast``` is
```List[Int]```. In Scala, the type of a generic includes the type of its
contents denoted between square brackets.

{% question %}
What is the type of <code>vowels</code>? Fill in the blanks: <code>List[___]</code>
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

### Set theory
