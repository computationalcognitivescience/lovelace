---
layout: page
title: Simulating in Scala
chapter: 8
nav_exclude: true
---

In [Part II](/lovelace/content/part2) we have practiced formalizing verbal theories and we experienced that any verbal theory can be formalized in multiple ways. Sometimes the consequences of formalization choices are self-evident.{% sidenote 'mn-id-easy' 'It is easy to understand that if there are only positive constraints, then setting all beliefs to true or false are the valid outputs for <span style="font-variant: small-caps;">Coherence</span>.' %} More often, however, deeper analysis is needed to uncover such consequences.{% sidenote 'mn-id-hard' 'It is hard to understand the different consequences of interacting positive and negative constraints in <span style="font-variant: small-caps;">Coherence</span>.' %} Answering questions such as “Does it matter if I formalize this concept one way or another?”, “Are these two formalizations truly different, or are they equivalent?”, and “How would I be able tell different formalizations apart in terms of the behaviour that they predict?” provides important insight in the what theories can (or cannot) explain.

We have seen that sometimes such questions can be answered analytically, using conceptual analysis, rigorous argumentation, or mathematical proofs. These analytical tools are an important part of the theoretical modeler's toolbox. However, they are limited to what our resource-bounded minds can reason about precisely and explicitly. Computer simulations supplement the modeler's mind. They are in a sense automated thought experiments. By running our models on computers other than our minds (Guest & Martin, 2021) {% marginfigure 'mf-id-guest' 'assets/img/simulation-guest.jpg' 'Olivia Guest, reproduced with permission [Joachim Vanderkerckhoven on Twitter](https://twitter.com/VandekerckhoveJ/status/1256135079086092288).'%}, we can go beyond the limits of mental simulation and explore model behaviour under a wide range of conditions and at larger input scales. This way we can investigate properties of a theoretical model that are difficult to derive through mathematical analysis because they emerge from the complex interaction of the theory's components. In Part III, we focus on this use of computer simulations.


## Why Scala?
Whether you are a veteran coder or just starting out, there is a good chance this is the first time you encounter the programming language Scala. Don't be dissuaded by the unknown; we chose Scala to help you read and write understandable code. Should you wish to use a different programming language, that is perfectly fine too. All coding exercises in this book can be done in any programming language. The coding principles you will learn here will be valuable regardless.

If you're curious why we chose Scala, please read on. Otherwise, continue with the next chapter to learn about [Scala and ```mathlib```](/lovelace/part_iii/mathlib) or with [installing Scala and ```mathlib```](/lovelace/part_iii/simulating#installing-scala-and-mathlib) if you want to write your own simulations. {% marginfigure '' 'assets/img/scala_logo.png' 'By [Lightbend, Inc.](https://www.lightbend.com/assets/images/brand/scala/scala-logos/svg/scala-full-color.svg), [CC BY 4.0](https://commons.wikimedia.org/w/index.php?curid=94026409).' %}


### Trustworthy and transparent code
Scala is a *functional* programming language. Functional programming languages are very closely tied to mathematical concepts, precisely like the concepts we have been using in this book. This is an important design feature to make *correctness* of code more evident to the coder and user. You can trust your code to do the thing is it designed to do, even when the complexity of your code increases. This is where Scala gets its name: **SCA**lable **LA**nguage. It is also the reason why companies like LinkedIn, Airbnb, Netflix and Twitter chose to build their digital infrastructure in Scala.{% sidenote 'mn-id-scale' 'You might not write code on the same scale as Twitter anytime soon, but the same principles that afford their software to scale are principles that will make your simulation code transparent, verifiable, readable and accessible.' %}

Functional programming languages also promote computational-level thinking because the relationship between specification (theory) and code (implementation) is tight. This guards against the common confusion between computational-level and algorithmic-level explanations (see [Chapter 11](/lovelace/part_iii/sim_coherence)). We will be using the ```mathlib``` library, which provides Scala syntax and functionality very similarly to the mathematical concepts used in computational-level modeling. For example, consider this part of {% problem Selecting Invitees (Variant 4) %} which uses [set builder notation](/lovelace/part_i/math#set-builder):

$$X=\left\{p_i,p_j\in G\middle|like(p_i,p_j)=true\wedge i\neq j\right\}$$

Let's assume that $$G$$, the set of invitees, is stored in ```invitees``` and we have a function ```like(x,y)```. In Scala ```mathlib``` the equation can be implemented as:

```scala
val uniquePairs = invitees.uniquePairs  // All pairs (p_i, p_j)
                                        // where p_i != p_j
val x = { uniquePairs | like _ }        // Pairs liking each other.
```

A common implementation, e.g. in Python, would be{% sidenote 'sn-id-python' 'We do not intended to argue that Python is a bad language. One could certainly use a functional programming paradigm in Python, something we would recommend you to try out! However, most code we encounter is written in a less transparent manner as illustrated here.' %}:
```python
x = set()               # Create an empty set,
for pi in invitees:     # then loop over all pi,
  for pj in invitees:   # and loop over all pj,
                        # if the pair is unique
                        # and likes each other,
    if pi != pj and like(pi, pj):
      x.add((pi, pj))   # add the pair to x.
```

Not only is the Scala example easier to check if it precisely implements the computational-level model, it also does not tempt the modeler to apply algorithmic level thinking at the computational level.

### Protection
Another helpful feature of Scala is its type system, which provides protection from running code on input it was not designed for. In many programming languages, Scala included, variables have a *type*. In the case of Scala, once a variable's type has been defined it cannot change. For example, we can say that the variable ```pets``` is a set consisting of animals:
```
val pets: Set[Animal]          
```
If we were to assign a list to ```pets``` in Scala:
```
val pets: Set[Animal] = List[Animal](cat, dog, goldfish)
```
The code will not compile and you will receive an error message that explains you are not allowed to assign a list to ```pets```:
```
error: type mismatch;
 found   : List[Animal]
 required: Set[Animal]
```
This mechanism is called *strict typing* and is used to prevent code from being run on inputs for which the function is not defined.{% sidenote 'mn-id-type' 'Languages like Python and R are dynamically typed. You can set any variable to any type. Sometimes you will get an error message when running your code, but if you are unlucky you may not discover you have used incorrect inputs after trying to understand why your simulation is doing weird things.' %}

### Open source and open science
Scala is open source. It is available free of charge and supported by an active community of developers from academia and industry. The principles outlined in this chapter lead to transparent, accessible and reproducible simulation code. These principles are not limited to Scala and we encourage users of other programming languages to apply them too.

## Installing Scala and ```mathlib```

### References

Guest, O., & Martin, A. E. (2021). [How computational modeling can force theory building in psychological science.](https://doi.org/10.1177/1745691620970585) *Perspectives on Psychological Science 16*(4),789-802.
