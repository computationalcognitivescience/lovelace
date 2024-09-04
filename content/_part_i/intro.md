---
layout: page
title: Introduction
chapter: 1
nav_exclude: true
---

{% marginfigure 'mf-id-whatever' 'assets/img/sculptor.png' 'Theoretical modeling is like sculpting.' %}
{% epigraph 'Theoretical modeling is like sculpting (Blokpoel, 2018). There is no fixed procedure. It requires creativity and the occasional courage to try something new. While a sculptor may have a general idea of what type of sculpture they want to make, it is by looking at intermediate states that they decide how to proceed; e.g., chisel away a piece of rock, add some more clay, or start anew. They only know that the sculpture (analogously, theoretical model) is done when they see it meets their standards.' 'van Rooij & Blokpoel' '2020' %}


## For whom is this book?

Are you a student or researcher in cognitive science{% sidenote 'mn-id-whatever' 'Here, *cognitive science* refers to the multidisciplinary study of mind and behaviour, incl. psychology, philosophy, computer science, artificial intelligence, neuroscience, linguistics, anthropology, and education.' %} or psychology?{% sidenote 'mn-id-whatever' 'Here, *psychology* refers to any subfield of psychological study of mind and behaviour, incl. cognitive, developmental, social, evolutionary, clinical, and cultural psychology.' %} Would you like to be able to make your own, formally precise, theoretical models? You have no or little prior training in theory or modeling? This book is a place to start!

Or, perhaps, you do have experience with theory and modeling but want to expand your skill and tool sets; or alternatively, you are just curious to have a taste of theoretical modeling to see what it is like? Whatever your interests, you are welcome here!

## What this book is (not) about

To set the right expectations, it is useful to explicitly state what this book is *not* about. This book does not give a comprehensive overview of existing theoretical models in one or more domains of cognitive science (e.g. language, memory, perception, etc.) or subfields of psychology (developmental, cognitive, social, etc.). Also, it does not provide training in generating models of a certain *architecture* type (e.g. ACT-R{% sidenote 'mn-id-whatever' '[ACT-R](http://act-r.psy.cmu.edu/about/) is a cognitive architecture developed by John R. Anderson and others.' %}  or neural networks{% sidenote 'mn-id-whatever' 'Also known as parallel distributed processing or connectionist models. See e.g. this [classic textbook](https://mitpress.mit.edu/books/parallel-distributed-processing-volume-1) by Rumelhart, McClelland & PDP Research Group.' %}).

So what *is* this book about?

In this book you learn concepts and tools that you can use to make *your own* theoretical models in *any* domain of cognitive science or subfield of psychology of interest to you (barring some philosophical assumptions; which we'll get to soon), and to assess and situate existing theoretical models in the cognitive science and psychology literature.

To this end, we will cover the conceptual foundations of computational explanation; mathematical concepts and notation; formalizing verbal theories; implementing and simulating theoretical models; and comparing models at different levels of explanation. We cover these points in Parts I - III using a small number of carefully selected examples. The main goal is to provide a playground and sandbox for practicing the basic conceptual and formal skills with a sufficiently diverse, but not too difficult, set of examples. The examples are constructed in ways that serve specific pedagogical aims. Once the basics are mastered, one can venture into the literature as suggested in our 'Further Reading' sections. Part IV ('Advanced topics') additionally provides examples of how the learned tools can be applied more widely and in non-traditional ways.  

## Approach

This book takes a dialogical pedagogical approach. The goal is specifically not to merely share information for you to consume passively. Rather the goal is to help you become an active and skilled conversation partner in the scientific *dialogue* that *is* theoretical modeling. Having mastered the skills and tools you will be (better) able to continue this dialogue in your own work with your scientific peers and collaborators, and in 'inner dialogue' with yourself.

We will illustrate the dialectical nature of theoretical modeling with dialogues between two fictive characters, called *Verbal* and *Formal*, who are engaged in the type of sculpting process sketched in the quote at the start of this chapter.

{% marginnote 'mn-id-whatever' '**Verbal:** "I have an intuition that grass is always greener on the other side. Could you help me figure out if this could be true?"' %}

{% marginnote 'mn-id-whatever' '**Formal:** "Sure. Let&#39;s formalize the intuition and reason through the consequences. Let $$g : S \rightarrow \mathbb{N}$$ be a function mapping each side $$s \in S$$ to a natural number in $$\mathbb{N}$$ representing its degree of &#39;greenness&#39;. Then your claim is that $$g(s_i) > g(s_j)$$ for any pair $$s_i, s_j \in S$$. This cannot hold for both orderings of a pair. The intuition is false." ' %}

These characters represent complementary cognitive states of mind required for successful theoretical modeling. Both characters bring raw materials to work with. *Verbal*, on the one hand, brings intuitive ideas about the 'what', 'how' and 'why' of phenomena based on empirical observations and domain knowledge. *Formal*, on the other hand, brings formal concepts and tools from mathematics and knowledge about computational principles. Together, *Verbal* and *Formal* sculpt (and polish) theoretical models of cognitive and/or psychological phenomena. In reality, an individual modeler can take on both roles intermittedly or, alternatively, the two styles of thinking can be distributed over a pair or a team of scientists.

## Tools for Theory

These days it is probably not controversial to say that NHST{% sidenote 'mn-id-whatever' 'NHST stands for *null hypothesis significance testing*.' %} {% marginnote 'mn-id-whatever' 'In the beginning, psychological scientists had a hammer and everything looked like a nail. Problems arose because they didn&#39;t know how to handle the hammer well. All effort was spent on improving the use of the hammer so that they finally could hit nails. After a while no one remembered the original and most important problem: Not everything is a nail.' %} has been psychology's hammer that made cognition and behaviour look like a nail. Fervent use of this hammer, which took center stage in the 'replication crisis', has most certainly contributed to a relative [neglect of solid theory building in psychological science](https://featuredcontent.psychonomic.org/psychological-science-needs-theory-development-before-preregistration/). Why? Well, a hammer (especially in the form of NHST) is not a suitable tool for sculpting theoretical models.

To sculpt theories one needs other types of tools. The theoretician's tools are first and foremost tools for  better *thinking* and more explicitly and precisely *communicating* the resulting theoretical thoughts. These tools include functional analysis ('Can we decompose a system into its component processes and their interactions?'), conceptual analysis ('What do we mean by the words we use when expressing ideas and intuitions?'), formalization ('Can we make our informal intuitions formally precise?'), mathematical proofs ('Can we deduce properties and predictions from formalized ideas'), and computer simulation ('Can we improve our understanding by building a computational system that can mimic what we want to explain?').
{% marginfigure 'mf-id-whatever' 'assets/img/sculpting_tools.jpeg' 'Sculpting requires a set of dedicated tools.' %}

As with any tools, it takes practice to use these tools effectively and skillfully. This book gives the necessary practice. It starts at the basics and progressively trains you in independent theoretical modeling.   

## Scala


The computer simulations in this book are written using functional programming in Scala.


This choice is deliberate. Functional programming makes interpreting the
semantics of the code transparent, making it easy to understand the relationship
between formalized theory and simulation. We provide an accessible
introduction to basic functional programming in Scala.

{% marginfigure '' 'assets/img/scala_logo.png' 'By [Lightbend, Inc.](https://www.lightbend.com/assets/images/brand/scala/scala-logos/svg/scala-full-color.svg), [CC BY 4.0](https://commons.wikimedia.org/w/index.php?curid=94026409).' %}

## Further Reading

We end each chapter with a *Further Reading* section to give pointers to the original literature covering or extending some of the key ideas discussed in the chapter.

The idea that theoretical modeling is like sculpting originates from (Blokpoel, 2018). A tutorial based on this idea, featuring also the fictive characters *Verbal* and *Formal*, was previously published in *Social Psychology* (van Rooij & Blokpoel, 2020). Parts of that tutorial reappear in the next chapter and the chapter on 'Subset Choice' in Part II of this book. For a discussion of problems with NHST we recommend reading work by [Paul Meehl](http://meehl.umn.edu/all-publications) (e.g. Meehl, 1997). Devezer et al. (2020) present a rigorous argument of how 'replicability' and 'good science' are far from the same thing.

### References

Blokpoel, M. (2018). [Sculpting computational-level models](https://onlinelibrary.wiley.com/doi/full/10.1111/tops.12282). *Topics in Cognitive Science, 10*(3), 641–648.

Devezer, B., Navarro, D. J., Vandekerckhove, J, & Buzbas, E. O. (2020). [The case for formal methodology in scientific reform](https://doi.org/10.1098/rsos.200805). *Royal Society open science, 8*(3), 200805.

Meehl, P. E. (1997). [The problem is epistemology, not statistics](https://meehl.umn.edu/sites/meehl.umn.edu/files/files/169problemisepistemology.pdf). In Harlow, Mulaik, & Steiger (Eds.), What If There Were No Significance Tests? (pp. 393-425)

van Rooij, I., & Blokpoel, M. (2020). [Formalizing verbal theories: A tutorial by dialogue](https://psycnet.apa.org/doiLanding?doi=10.1027%2F1864-9335%2Fa000428). *Social Psychology, 51*(5), 285-298
