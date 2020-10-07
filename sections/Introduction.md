---
title: Introduction
permalink: /introduction/
sort: 3
---
<div id="toc-wrapper" markdown="1">
* Table of contents
{:toc}
</div>

# For whom is this book?

Are you a student or researcher in cognitive science or psychology? Would you like to be able to make your own, formally precise, theoretical models? You have no prior training in theory or modeling? This book is a place to start!

# What this book is (not) about

To set the right expectations, it is useful to explicitly state what this book is *not* about. This book does not give a comprehensive overview of existing theoretical models in one or more domains of cognitive science (e.g. language, memory, perception, etc.) or subfields of psychology (developmental, cognitive, social, etc.). Also, it does not provide training in generating models of a certain *architecture* type (e.g. [ACT-R](http://act-r.psy.cmu.edu) or neural networks).

So what *is* this book about?

In this book you learn concepts and tools that you can use to make *your own* theoretical models in any domain of cognitive science or subfield of psychology of interest to you (barring some philosophical assumptions; which we'll get to soon), and to assess and situate existing theoretical models in the cognitive science and psychology literature. 

To this end, we will cover:

1. conceptual foundations of computational explanation
2. mathematical concepts and notation
3. formalizing verbal theories
4. implementing and simulating theoretical models
5. comparing models at different levels of explanation

Using a small number of carefully selected examples we cover points 1-5. The selection serves specific pedagogical aims: to practice the basic conceptual and formal skills with a sufficiently diverse, but not too difficult, set of examples. Once the basics are mastered, one can venture into the literature as suggested in our 'Further Reading' sections. The 'Advanced topics' section additionally provides examples of how the learned tools can be applied more widely.  

# Approach

Our approach is shaped by years of experience teaching these materials.

is to set up opportunities for you to try and make your own models and experience that you get stuck. dialogical. We expect you to actively engages with the exercises. As we have explained elsewhere ([van Rooij & Blokpoel, 2020]()):

> Theoretical modeling is like sculpting ([Blokpoel, 2018](https://onlinelibrary.wiley.com/doi/full/10.1111/tops.12282)). There is no fixed procedure. It requires creativity and the occasional courage to try something new. While a sculptor may have a general idea of what type of sculpture they want to make, it is by looking at intermediate states that they decide how to proceed; e.g., chisel away a piece of rock, add some more clay, or start anew. They only know that the sculpture (analogously, theoretical model) is done when they see it meets their standards.

This interactive book introduces you to tools of the trade:

* computational concepts
* mathematical notation
* formal analysis and proofs
* computer simulation

As with any tools, it takes practice to use these tools effectively and skillfully. This book gives the necessary practice. It starts at the basics and progressively trains you in independent theoretical modeling.   





# Scala

The simulations in this book are written using functional programming in Scala.
This choice is deliberate. Functional programming makes interpreting the
semantics of the code transparent, making it easy to understand the relationship
between formalised theory and simulation. We provide an accessible
introduction to basic functional programming in Scala.
