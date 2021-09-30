---
layout: page
title: Coherence
chapter: 11
nav_exclude: true
---

If you've been reading Section III in order, you may be surprised we've not
covered algorithmic-level modeling. After all, programming involves designing
algorithms. However, the algorithms required for implementing simulations of
formal theories are not necessarily also algorithmic-level theories (Cooper &
Guest, 2014). It is important to keep levels of explanation distinct and
consistent (see [Chapter 2 - Foundations](/lovelace/part_i/foundations), and
Blokpoel, 2018), and so we've focused on implementing computer simulations at
the computational level.{% sidenote 'id-sn-realist' 'In other words, Formal and
Verbal have no commitment to the cognitive plausibility of the algorithms
underlying the simulation code. The simulation code is only a tool, not a
theory.' %}  Of course, algorithmic-level theories do form an integral part of
tri-level framework (see [Chapter 2 -
Foundations](/lovelace/part_i/foundations)). In this chapter, we take a closer
look at various relationships between computational- and algorithmic-level
modeling. What motivates a modeler to consider algorithmic-level explanations?
How do algorithmic-level models relate to computational-level models? And since
this is a simulation chapter: How can we use computer simulations to scrutinize
algorithmic-level theories for their (in)consistency with computational-level
theory? We will use Coherence theory as a case study to address these questions.

First, let's recap. At the computational level, we ask the question, 'what is
the nature of the problem solved by the capacity?' An answer to this question
comes in the form of a formally worked out input-output mapping. At the
algorithmic level, we ask: 'how is the input-output mapping that characterizes
the capacity computed?' An answer to this question comes in the form of a
hypothesized algorithm, a step-by-step procedure computing the hypothesized
mapping.

{% question %}
In your own words, what is the goal of computational level modeling? And what is
the goal of algorithmic level modeling? Is one better than the other? Why (not)?
{% endquestion %}

Apart from the motivation to explain how the system is computing a particular
function, there are other reasons why cognitive scientists move towards the
algorithmic level. We discuss two.

{% marginfigure 'id-mf-difficult' 'assets/img/thinking-difficult.jpg'
'Computational-level thinking is often experienced as more difficult than
algorithmic-level thinking' %} The first reason is that designing computational
level theories is hard, especially if you do not have the appropriate training.
While actual algorithm design is also far from trivial, the basic principles are
easy to intuit. For many, it is more intuitive to describe cognitive capacities
as a step-by-step process, rather than the abstract problem (function) that is
being solved.

```
1. first people pick a pizza topping they like
2. then they pick a topping that goes well with it
3. then as long as there are toppings
   that go well with each topping already on the pizza:
     they add that topping too
```

While there is no wrong or right when it comes to proposing theories, it is good
to be mindful of possible pitfalls. For example, while the algorithm above makes
sense, what exactly is it computing? Is it computing the best possible
combination of toppings? A good enough combination? Some random combination? By
itself, the algorithm does not explain well what the nature is of the problem
solved by the capacity.

There are two possible reactions to this situation. One can informally posit a
computational level theory to go with the algorithmic level theory. As long as
this remains a formal theory, however, there will be limits to it ability to
explain the capacity due to the inherent ambiguity. In addition, we wouldn't
know for sure if the algorithm is actually computing the verbal theory and we
risk introducing inconsistencies between the theories.

The other response takes formal computational levels serious from the get go.
Arguing that, by the nature of the relationships between Marr's levels, once the
algorithmic level is known the computational level theory is prescribed. One
can, in principle, derive a formal computational level theory from the
algorithm. Barring the practical challenges in doing so, we also encounter
another challenge here. Given a computational level theory, i.e., a function
$$f$$, many alternative algorithms exist that compute the same function $$f$$ (see [Chapter 2 - Foundations](/lovelace/part_i/foundations)). 



The second reason is that computational-level theories often have undesirable
properties that can appear insurmountable. The algorithmic level provides a possible way out.

{% question %}
There may be other reasons to move to the algorithmic level beyond the two
above.
{% question %}
Can you think other reasons to move to the algorithmic level?
{% endquestion %}
{% question %}
For each reason you came up with, answer the following: Does moving to the
algorithmic level because of the reason cause inconsistency between algorithmic
and computational level?
{% endquestion %}
{% endquestion %}

Algorithms afford a different mode of thought for the cognitive scientist: A
step-by-step procedure that specifies how the cognitive system is doing what it
does. It comes more naturally than


- scientists affinity with algorithms
- move to algorithms to solve problems at the computational level

It is also a natural mode of thinking, a

However, in the cognitive science literature
you will often find algorithmic-level theoretical claims in an attempt to
supplement the computational

### References

Blokpoel, M. (2018). [Sculpting computational-level models.](http://onlinelibrary.wiley.com/doi/10.1111/tops.12282/full) *Topics in Cognitive Science, 10*(3), 641-648.

Cooper, R.P., & Guest, O. (2014). [Implementations are not specifications: Specification, replication and experimentation in computational cognitive modeling.](https://www.sciencedirect.com/science/article/abs/pii/S1389041713000314) *Cognitive Systems Research 27*, 42-49.
