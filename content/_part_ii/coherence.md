---
layout: page
title: Coherence
chapter: 5
nav_exclude: true
---

{% marginfigure 'mf-id-whatever' '/assets/img/coherence_network.jpg' '"But what is coherence? Given a large number of elements (propositions, concepts, or whatever) that are coherent or incoherent with each other in various ways, how can we accept some of these elements and reject others in a way that maximizes coherence? How can coherence be computed?" -- Thagard & Verbeurgt (1998)' %} In the previous chapter you practiced making a formal computational-level model of *subset choice*. In this chapter we study a computational-level model of *coherence* developed by cognitive scientists and published in the journal [*Cognitive Science*](https://www.sciencedirect.com/science/article/pii/S0364021399800330). The pedagogical goal is to see how these scientists motivated their modeling decisions, what properties their formalization has, what limitations it has, and how it may be revised or expanded.

We have selected this particular model for our purposes because it combines simplicity with wide applicability. It also allows us to illustrate how mathematical proofs can be used to derive model properties.

# Coherence as constraint satisfaction

In 1998, computational philosopher Paul Thagard and computer scientist Karsten Verbeurgt proposed a model of a wide range of phenomena that can all be seen as falling under a single banner: 'maximizing coherence'.

{% epigraph 'When we make sense of a text, a picture, a person, or an event, we need to construct an interpretation that fits with the available information better than alternative interpretations. The best interpretation is one that provides the most coherent account of what we want to understand, considering both pieces of information that fit with each other and pieces of information that do not fit with each other. For example, when we meet unusual people, we may consider different combinations of concepts and hypotheses that fit together to make sense of their behavior.' 'Thagard & Verbeurgt' '1998' %}


Thagard and Verbeurgt wanted to explain this capacity for sense making computationally. Intuitively, they saw the computational-level problem as follows:

{% fproblem Coherence (informal) %}
Networks of interconnected elements{% sidenote 'sn-id-whatever' '*Elements* can be any type of mental representations (concepts, propositions, hypotheses, images, goals, actions, plans, etc.).'%}, where connections in the network represent coherence and incoherence relations between pairs of elements.{% sidenote 'sn-id-whatever' '*Coherence* relations include explanation, implication, positive association. *Incoherence* relations include inconsistency, incompatibility, and negative association.'%};;
Accept and reject elements in a way that is maximally coherent.  
{% endfproblem %}

Inspired by the notion of *constraint satisfaction* studied in computer science, the scientists formalized coherence and incoherence relations as positive and negative constraints, respectively. These constraints have well-defined satisfaction conditions: A *positive* constraint $$(a,b) \in C^+$$ is *satisfied* if and only if the elements $$a$$ and $$b$$ are both rejected or both accepted. A *negative* constraint $$(a,b) \in C^-$$ is *satisfied* if and only if the element $$a$$ is accepted and $$b$$ is rejected, or conversely, element $$b$$ is accepted and $$a$$ is rejected.

Using these definitions, the problem of maximizing coherence can be made a bit more formal, as follows:

{% fproblem Coherence (pre-formal) %}
A network of elements, with pairs of elements connected by positive or negative constraints.;;
A partition of the set of elements into sets $$A$$ (accepted) and $$R$$ (rejected) elements such that the number of satisfied constraints is maximized.
{% endfproblem %}

Using mathematical notation from the section on *graph theory* in Chapter 3, we can make this more precise and explicit:{% sidenote 'sn-id-whatever' 'We deviate a bit from the notation used by the original authors and make the link to graph theory transparent.'%}

{% fproblem Coherence (formal, version 1) %}
A graph $$G = (V, E)$$ with vertex set $$V$$ and edge set $$E \subseteq V \times V$$ that partitions into positive constraints $$C^+$$ and negative constraints $$C^-$$ (i.e., $$C^+ \cup C^- = E$$ and $$C^+ \cap C^- = \emptyset $$).
;;
A partition of the vertices $$V$$ into sets $$A$$ and $$R$$ such that $$Coh(A,R)=$$ $$ |\{(u,v)~|~(u,v) \in C^+, $$ $$ u,v \in A $$ $$ \text{ OR } $$ $$ u,v \in R \}|$$ $$ + $$ $$ |\{(u,v)~|~(u,v) \in C^{-}, $$ $$u \in A, v \in R $$ $$ \text{ OR } $$ $$ v \in A, u \in R \}|$$ is maximum.{% sidenote 'sn-id-whatever' '$$Coh(A,R)$$ is said to be *maximum* if there exists no alternative partition $$X,Y$$ with $$Coh(X,Y) > $$ $$ Coh(A,R)$$.'%}
{% endfproblem %}

The following figure illustrates the idea.

{% maincolumn 'assets/img/coherence_capital_example.png' 'An instance of the <span style="font-variant:small-caps;">Coherence</span>  problem, with vertex set $$V = \{a, b, c, d, e, f, g, h, i \}. $$*Solid lines* indicate positive constraints $$C^+$$, and *dotted lines* indicate negative constraints $$C^-$$. Note how it is not possible to satisfy all constraints by a partition of $$V$$ into $$A$$ and $$R$$. But it is still possible to satisfy a *maximum* of constraints.' %}

{% question %}
What is a maximum coherence partition for the input of <span style="font-variant:small-caps;">Coherence</span>  depicted above?
{% hidden Hint? %}
Consider the partition $$A = \{ a, d, f, i \}, R = \{ b, c, e, g, h\}$$ How many constraints are satisfied by that partition? Can you find a partition that satisfies more constraints?
{% endhidden %}
{% endquestion %}


Lastly, we introduce *weights* on the constraints in the model. This captures Thagard and Verbeurgt's intuition that some pairs of elements can cohere and incohere more strongly than others:

{% fproblem Coherence (formal, version 2) %}
A graph $$G = (V, E)$$, where $$E = C^+ \cup C^-$$ ($$C^+ \cap C^- = \emptyset $$), and a weight $$w(u,v) \in \mathbb{N}$$ for each edge $$(u,v) \in E$$.
;;
A partition of the vertices $$V$$ into sets $$A$$ and $$R$$ such that $$Coh(A,R)= \sum_{(u,v) \in C^+, u,v \in A \text{ OR } u,v \in R} w(u,v) + \sum_{(u,v) \in C^{-}, u \in A, v \in R \text{ OR } v \in A, u \in R } w(u,v)$$ is maximum.
{% endfproblem %}

{% maincolumn 'assets/img/coherence_capital_weighted.png' 'An input for version 2 of <span style="font-variant:small-caps;">Coherence</span>. The thickness of the lines represents the weights.' %}

# Equivalence

With version 2 of <span style="font-variant:small-caps;">Coherence</span> we presented *one* possible formal way of expressing Thagard and Verbeurgt's ideas. We could have picked a different mathematical way of expressing the same ideas. For instance, instead of speaking of an accepted set and rejected set, we could define a function $$T$$ that assigns to every vertex a truth value, i.e., $$T: V \rightarrow \{\top, \bot \}$$.{% sidenote 'sn-id-whatever' 'Recall from Chapter 3 that $$\top$$ can be read as *true* and $$\bot$$ can be read as *false*.'%}  We could then express the model as follows:

{% fproblem Coherence (formal, version 3) %}
A graph $$G = (V, E)$$, with a weight $$w(u,v) \in \mathbb{N}$$ for each edge $$(u,v) \in E = C^+ \cup C^- \subseteq V \times V$$ (where $$C^+ \cap C^- = \emptyset $$).;;
A truth assignment $$T: V \rightarrow \{\top, \bot \}$$ such that $$Coh(T)= \sum_{T(u) = T(v)} w(u,v) + \sum_{T(u) \neq T(v)} w(u,v)$$ is maximum.
{% endfproblem %}

{% question %}
Are versions 2 and 3 of <span style="font-variant:small-caps;">Coherence</span> the same or different computational-level models?
{% hidden Hint? %}
While the models look different on the surface the input-output mappings are equivalent. Try to prove the equivalence. That is, assume that $$A = \top$$ and $$R = \bot$$ and convince yourself (or someone else) that there is no input for which the two models produce different outputs.
{% endhidden %}
{% endquestion %}

Answering *Question 5.1* may have been easy enough. Once you recognize that a partition into sets $$A$$ and $$R$$ can be conceptualized as a truth assignment with $$A = \top$$ and $$R = \bot$$, and you notice that then $$T(v)=T(u)$$ means the same as $$u,v \in A \text{ OR } u,v \in R$$, and so on, the equivalence between the two versions becomes apparent.

Now, consider the following computational-level model inspired by the connectionist modeling tradition, and answer *Question 5.2*:

{% fproblem Harmony Maximization %}
A Hopfiel network $$G = (V, E)$$, with a weight $$w(u,v) \in \mathbb{Z}$$ for each edge $$(u,v) \in E = V \times V$$ (i.e., the network is fully connected).;;
An activation pattern $$a: V \rightarrow \{-1,1 \}$$ such that $$ H(a) = \sum_{u,v \in V} a(u)a(v)w(u,v) $$ is maximum.
{% endfproblem %}


{% question %}
Is <span style="font-variant:small-caps;">Harmony Maximization</span> the same or a different computational-level model as versions 2 and 3 of <span style="font-variant:small-caps;">Coherence</span>?
{% hidden Hint? %}
While the models look quite different on the surface the input-output mappings are, again, equivalent. Try to prove the equivalence. That is, assume that $$\top = 1$$ and $$\bot = -1$$ and convince yourself (or someone else) that there is no input for which the two models produce different outputs. Tip: calculate $$\sum_{u,v \in V} a(u)a(v)w(u,v)$$ for a few possible inputs and activations patterns. And compare this to $$\sum_{T(u) = T(v)} w(u,v) + \sum_{T(u) \neq T(v)} w(u,v)$$ for corresponding input and truth assigments. What do you notice?
{% endhidden %}
{% endquestion %}

### Lessons

The exercises illustrate a few lessons. Models that look very different may not be different at all. Before considering models as explanatory competitors, it is good practice to first verify that they are not equivalent. The answer to *Question 5.2* shows that even models from very different modeling traditions (e.g., symbolic versus connectionist){% sidenote 'sn-id-whatever' 'See e.g. [Fodor & Pylyshyn (1988)](https://www.sciencedirect.com/science/article/abs/pii/0010027788900315?dgcid=api_sd_search-api-endpoint).' %} can be equivalent at the computational level of explanation.

# Properties

Let's see if we can prove some properties of <span style="font-variant:small-caps;">Coherence</span>.

## Symmetry property

<span style="font-variant:small-caps;">Coherence</span> has the property that $$Coh(A,R) = Coh(R,A)$$ (or, equivalently, $$Coh(T) = Coh(\neg T)$$). We call this the *symmetry property*.

{% question %}
Prove that the symmetry property holds for <span style="font-variant:small-caps;">Coherence</span>.
{% hidden Hint? %}
Carefully look at the definitions of versions 1, 2 and 3 of <span style="font-variant:small-caps;">Coherence</span>.
{% endhidden %}
{% endquestion %}


The symmetry property seems cognitively implausible when explaining how people decide what to believe as true or false (a capacity known as *belief fixation*). To see why, consider that the model predicts the following: If you would mentally *flip* all your beliefs---such that what you currently believe to be true{% sidenote 'sn-id-whatever' 'e.g., the belief that Amsterdam is the capital of The Netherlands.'%} you will from then on believe to be false and what you currently believe to be false{% sidenote 'sn-id-whatever' 'e.g., the belief that the Moon is made of cheese.'%} you will from then on believe to be true---your new mental state will still be equally (maximally) coherent. This seems wrong!



If the symmetry property would really hold for our mental states then we would never have stable beliefs. We'd go through life flip-flopping our thoughts without anything forcing us to stabilise our beliefs. There would also be no relationship between our beliefs and the world (e.g., believing that the Moon is made of cheese would make perfect sense to all of us).

Judging from experience, it is unlikely that our minds operate this way. More likely, the model needs adjusting.

## Data priority principle

The authors of the <span style="font-variant:small-caps;">Coherence</span> model were aware of the problem posed in the previous subsection. They proposed that the *data priority principle* counters the problem.{% sidenote 'sn-id-whatever' 'Data priority principle: "Propositions that describe the results of observation have a
degree of acceptability on their own." -- Thagard (1989)' %} For instance, if you see the prime minister regularly arriving at the parliament building by bike, then you will believe that he cycles to work. This belief is based on direct observation, and therefore has a degree of acceptability of its own, independent of its coherence with other beliefs you may have. Once you  acceptability may also influence the plausibility of other beliefs: e.g., the belief that the prime minister lives in the Hague where the parliament building is. To the extend that beliefs corresponding to direct observations are truthful or accurate, the beliefs that cohere with them are also more likely to be true; or to Thagard and Verbeurgt proposed.

We can capture this idea in an adjusted formalization of <span style="font-variant:small-caps;">Coherence</span> called <span style="font-variant:small-caps;">Discriminating Coherence</span>

Yet, the belief could still be doubted, e.g., if it were to incohere sufficiently with other beliefs you hold. For instance, if someone told you that the prime minister lives in Amsterdam, not in The Hague the parliament building is *and* you notice that every time the prime minister arrives by bicycle there was is a camera crew and journalist waiting to interview him, then you may no longer believe he is cycling to work from his home. A more coherent interpretation may be that he wants to give a public impression to Dutch voters that he is the kind of person who cycles to work.

{% epigraph 'A pure coherence problem is one that does not favor the acceptance of any particular set of elements. A foundational coherence problem selects a set of favored elements for acceptance as self-justified. A discriminating coherence problem favors a set of elements but their acceptance still depends on their coherence with all the other elements.' 'Thagard & Verbeurgt' '1998' %}

## NP-hard

*Under construction*

## Scope

*Under construction*

# Computing coherence

*Under construction*

# Further reading

*Under construction*

### References

*Under construction*
