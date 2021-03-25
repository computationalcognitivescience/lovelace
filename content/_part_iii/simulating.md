---
layout: page
title: Simulating in Scala
chapter: 8
nav_exclude: true
---

In [Part II](/lovelace/content/part2) we have practised formalizing verbal theories and we experienced that any verbal theory can be formalized in multiple ways. Sometimes the consequences of formalization choices are self-evident.{% sidenote 'mn-id-easy' 'It is easy to understand that allowing for negative value in <span style="font-variant: small-caps;">Additive Subset Choice </span> will never include negative value items in the subset.' %} More often, however, deeper analysis is needed to uncover such consequences.{% sidenote 'mn-id-hard' 'It is hard to understand the different consequences of binary or tertiary interactions in <span style="font-variant: small-caps;">Subset Choice </span>.' %} Answering questions such as “Does it matter if I formalize this concept one way or another?”, “Are these two formalizations truly different, or are they equivalent?”, and “How would I be able tell different formalizations apart in terms of the behaviour that they predict?” provides important insight in the what theories can (or cannot) explain.

We have seen that sometimes such questions can be answered analytically, using conceptual analysis, rigorous argumentation, or mathematical proofs. These analytical tools are an important part of the theoretical modeler's toolbox. However, they are limited to what our resource-bounded minds can reason about precisely and explicitly. Computer simulations supplement the modeler's mind. They are in a sense automated thought experiments. By running our models on computers other than our minds {% cite guest_how_2020 %}{% marginfigure 'mf-id-guest' 'assets/img/simulation-guest.jpg' 'Post by [Joachim van der Kerckhoven on Twitter.](https://twitter.com/VandekerckhoveJ/status/1256135079086092288)'%}, we can go beyond the limits of mental simulation and explore model behaviour under a wide range of conditions and at larger input scales. This way we can investigate properties of a theoretical model that are difficult to derive through mathematical analysis because they emerge from the complex interaction of the theory's components. In Part III, we focus on this use of computer simulations.




### References

{% bibliography --cited %}