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
theory.' %}

Of course, algorithmic-level theories do form an integral part of tri-level
framework (see [Chapter 2 - Foundations](/lovelace/part_i/foundations)) and
we've briefly touched upon several algorithmic level theories for {% problem
Coherence %} in [Chapter 5 - Coherence](/lovelace/part_ii/coherence). In this
chapter, we take a closer look at the relationship between computational- and
algorithmic-level modeling. Specifically, you will learn how to use computer simulations to
scrutinize claims about (in)consistencies between algorithmic- and
computational-level theories. We will use Coherence theory as a case study.

First, let's recap the levels of explanation.

{% maincolumn 'assets/img/marr-sorting.svg' 'Illustration of Marr&apos;s levels
of explanation from [Chapter 2 - Foundations](/lovelace/part_i/foundations). Any
given function can be computed by different algorithms, and any given algorithm
can be physically realized in different ways. This principle is illustrated for
the sorting example.' %}

{% question %}
In your own words, what is the goal of computational level modeling? And what is
the goal of algorithmic level modeling? Is one better than the other? Why (not)?

{% hidden Hint %}
Marr's (1982) levels provide different kinds of explanations.
{% endhidden %}

{% hidden Hint %}
At the computational level, we ask the question, 'what is the nature of the
problem solved by the capacity?' An answer to this question comes in the form of
a formally worked out input-output mapping. At the algorithmic level, we ask:
'how is the input-output mapping that characterizes the capacity computed?' An
answer to this question comes in the form of an algorithm: a step-by-step
procedure computing the input-output mapping.
{% endhidden %}

{% hidden Hint %}
For example, take word processing. A computational level theory may explain the
nature of the transformation of an acoustic signal into a word. It may explain
that the transformation has certain properties such as abstraction and
ambiguity. An algorithmic level theory of the same phenomenon will explain what
processing steps need to take place to compute the transition from signal into
word. Such an account will be able to explain the order in which the
sub-computations are executed. Is the acoustic signal first segregated and then
processed by part, or is it processed as a whole?
{% endhidden %}

{% endquestion %}

It is good
to realize that there is ongoing discussion about the explanatory contributions
of the levels (see e.g. Peebles,  & Cooper, 2015). Here, we take the
position that each level of explanation has a non-redundant explanatory
contribution to make (Bechtel & Shagrir, 2015, Egan, 2017); and that in the end a full explanation of the capacity
requires theories at all three of Marr's levels. Moreover, we acknowledge that in such a tri-level theory, it is important that there is internal
consistency between the levels (Blokpoel, 2018). Consistency means that the
mapping computed at each level is equivalent:

$$
\begin{eqnarray}
\text{Computational level } & f: I \rightarrow O\\
\text{Algorithmic level } & A: I \rightarrow O\\
\text{Implementational level } & P: I \rightarrow O\\
\text{Consistency property } & \forall_{i \in I}f(i)=A(i)=P(i)
\end{eqnarray}
$$


## Reflections

{% marginfigure 'id-mf-rocket' 'assets/img/water-rocket.jpg' 'The rocket
algorithm is inconsistent with the computational-level function. It will not
reach space. To be fair, [the water rocket world
record](http://www.wra2.org/WRA2_Standings.php) is 830 meters, which is really
impressive.<br/>Creative Commons license
([source](http://www.uswaterrockets.com/construction_&_tutorials/Gardena_Launcher/tutorial.htm)).'
%} When consistency is violated, the theories at each level describe different
input-output mappings and hence they will have different properties. Sometimes,
these differences are completely arbitrary. In this case, no guarantee that the consistency property holds (i.e., that the functions computed by $$f$$, $$A$$ and $$P$$ are equivalent). Such algorithms are
called *heuristics* relative to their computational level counterparts.
Sometimes, a guarantee of *approximation* is possible. Different kinds of
approximation are possible. We will come back to this later in this chapter.
Regardless of the nature of the inconsistency, it is theoretically problematic
to combine explanations that are not consistent. It would be like thinking that
your rocket can reach space $$f(energy, gravity)=height)$$, but the
water-and-pressure-based exhaust (the algorithm) can never generate enough
energy. In this illustration, it might be easy to revise the computational level
by *adding a constraint* on the energy input. In more complex theories,
inconsistencies are harder to discover and correct. By understanding the
possible cause of the inconsistencies, we will be in a better position to prevent
or restore them.

## Causing intra-level inconsistencies

So how come inconsistencies between levels arise? We discuss two common
causes.

{% marginfigure 'id-mf-difficult' 'assets/img/thinking-difficult.jpg'
'Computational-level thinking is often experienced as more difficult than
algorithmic-level thinking' %} The first cause is moving to algorithmic level
theory too early, without a clear computational-level theory. Designing
computational level theories is hard, especially if you do not have the
appropriate training. While actual algorithm design is also far from trivial,
the basic principles are easy to intuit. For many, it is more intuitive to
(verbally) describe cognitive capacities as a step-by-step process, rather than
the abstract problem (function) that is being solved.

```
Subset Choice algorithm (pizza toppings example)
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
solved by the capacity. That is, it doesn't explain well the input-output
mapping. Let's consider two possible reactions to this situation.

The first response we consider is that writing down a verbal computational level
theory to go with the algorithmic level theory. As long as this remains a verbal
theory, however, there will be limits to it ability to explain the capacity due
to the inherent ambiguity. In addition, we wouldn't know for sure if the
algorithm is actually computing the verbal theory and there is a high risk
introducing inconsistencies between the theories.

The second response argues that, by the nature of the relationships between
Marr's levels, once the algorithmic level is known the computational level
theory is prescribed. One can, in principle, derive a formal computational level
theory from the algorithm, or even claim that the algorithm *is* the
computational-level theory. There several theoretical challenges here. First,
even though there is only one input-output mapping that exactly matches the
algorithmic level theory, there are (infinitely) many ways to describe that
mapping. For example, the same mapping could be described with a Bayesian
formalism, but possibly also with a logic formalism.{% sidenote 'id-sn-formalism' 'Assuming that the formalisms are computationally equally expressive.' %} ...

Given a computational level theory, i.e.,
a function $$f$$, many alternative algorithms exist that compute the same
function $$f$$ (see [Chapter 2 - Foundations](/lovelace/part_i/foundations) and
the figure on sorting above).



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

## Further reading
For example, it has been
debated whether or not  each cognitive capacity has a computational level
explanation (Marr, 1977).

McClamrock, R. (1991). [Marr's three levels: A re-evaluation](https://link.springer.com/article/10.1007/BF00361036). *Minds and Machines, 1*(2), 185-196.

### References

Blokpoel, M. (2018). [Sculpting computational-level models.](http://onlinelibrary.wiley.com/doi/10.1111/tops.12282/full) *Topics in Cognitive Science, 10*(3), 641-648.

Cooper, R.P., & Guest, O. (2014). [Implementations are not specifications: Specification, replication and experimentation in computational cognitive modeling.](https://www.sciencedirect.com/science/article/abs/pii/S1389041713000314) *Cognitive Systems Research 27*, 42-49.

Egan, F. (2017). *Function-theoretic explanation.* In Explanation and integration in mind and brain science (pp. 145-163). Oxford University Press.

Marr, D. (1977). [Artificial intelligence—A personal view.](https://doi.org/10.1016/0004-3702(77)90013-3) *Artificial Intelligence 9*, 37-48.

Marr, D. (1982). *Vision: A computational investigation into the human representation and processing of visual information.* New York.

McClamrock, R. (1991). [Marr's three levels: A re-evaluation](https://link.springer.com/article/10.1007/BF00361036). *Minds and Machines, 1*(2), 185-196.

Peebles, D. & Cooper, R.P. (2015). [Thirty years after Marr's Vision: Levels of analysis in Cognitive Science.](https://doi.org/10.1111/tops.12140) *Topics in Cognitive Science, 7*(2), i-iii, 185-381.
