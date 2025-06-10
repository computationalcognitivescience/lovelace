---
layout: page
title: Coherence
chapter: 6
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

Answering *Question 5.2* may have been easy enough. Once you recognize that a partition into sets $$A$$ and $$R$$ can be conceptualized as a truth assignment with $$A = \top$$ and $$R = \bot$$, and you notice that then $$T(v)=T(u)$$ means the same as $$u,v \in A \text{ OR } u,v \in R$$, and so on, the equivalence between the two versions becomes apparent.

Now, consider the following computational-level model inspired by the connectionist modeling tradition, and answer *Question 5.3*:

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

The authors of the <span style="font-variant:small-caps;">Coherence</span> model seemed aware of the problem posed in the previous subsection. They proposed that the *data priority principle* counters the problem.{% sidenote 'sn-id-whatever' 'Data priority principle: "Propositions that describe the results of observation have a
degree of acceptability on their own." -- Thagard (1989)' %} For instance, if you see the prime minister regularly arriving at the parliament building by bike, then you will believe that he cycles to work. This belief is based on direct observation, and therefore has a degree of acceptability of its own, independent of its coherence with other beliefs you may have. By maximizing coherence grounded in observations our beliefs aren't merely circular contrivances of our minds.

Thagard and Verbeurgt proposed two different ways in which this idea could be worked out to a revised theory of coherence:

{% epigraph 'A pure coherence problem is one that does not favor the acceptance of any particular set of elements. A foundational coherence problem selects a set of favored elements for acceptance as self-justified. A discriminating coherence problem favors a set of elements but their acceptance still depends on their coherence with all the other elements' 'Thagard & Verbeurgt' '1998' %}

Let's consider how these intuitive ideas can be formalized, starting with <span style="font-variant:small-caps;">Foundational Coherence</span>.

{% fproblem Foundational Coherence %}
A graph $$G = (V, E)$$, with a weight $$w(u,v) \in \mathbb{N}$$ for each edge $$(u,v) \in E = C^+ \cup C^- \subseteq V \times V$$ (where $$C^+ \cap C^- = \emptyset $$), and special (data) elements $$D \subseteq V$$.;;
A truth assignment $$T: V \rightarrow \{\top, \bot \}$$ such that $$T(d) = \top$$ for each $$d \in D$$ and $$Coh(T)= \sum_{T(u) = T(v)} w(u,v) + \sum_{T(u) \neq T(v)} w(u,v)$$ is maximum.
{% endfproblem %}


The acceptability of beliefs representing direct observations (modelled by $$D$$) can impact the plausibility of other beliefs through maximizing coherence. For instance, if you already know that the parliament building is in The Hague and you observe the prime minister arriving there by bike then it is most coherent to believe that the prime minister lives in The Hague.

{% maincolumn 'assets/img/FoundationalCoherence.png' 'An example instance of the <span style="font-variant:small-caps;">Foundational Coherence</span> problem, with vertex set $$V = \{a, b, c, d, e, f, g, h, i, j \}$$ and $$ \{f, j\} \in D$$.' %}

In <span style="font-variant:small-caps;">Foundational Coherence</span>, the elements of $$D$$ cannot themselves be doubted. However, people are able to doubt that their observations are true if disbelieving them is more globally coherent against the background of many other beliefs. For instance, if someone told you that the prime minister lives in Amsterdam *and* you know that the parliament building is in The Hague *and* you notice that every time the prime minister arrives by bicycle there was is a camera crew and journalists waiting to interview him, then you may no longer believe he is cycling to work from his home. A more coherent interpretation may be that he wants to give a public impression to Dutch voters that he is the kind of person who cycles to work.

We can capture this idea in an adjusted formalization of <span style="font-variant:small-caps;">Coherence</span> called <span style="font-variant:small-caps;">Discriminating Coherence</span>.

{% fproblem Discriminating Coherence %}
A graph $$G = (V, E)$$, with a weight $$w(u,v) \in \mathbb{N}$$ for each edge $$(u,v) \in E = C^+ \cup C^- \subseteq V \times V$$ (where $$C^+ \cap C^- = \emptyset $$), and special (data) elements $$D \subseteq V$$ with a weight $$w(d) \in \mathbb{N}$$ for each $$d \in D$$.;;
A truth assignment $$T: V \rightarrow \{\top, \bot \}$$ such that $$Coh(T)= \sum_{d \in D, T(d) = \top} w(d) + \sum_{T(u) = T(v)} w(u,v) + \sum_{T(u) \neq T(v)} w(u,v)$$ is maximum.
{% endfproblem %}

It seems inutuitive that, to the extent that beliefs corresponding to direct observations are truthful or accurate, the beliefs that cohere with them are also more likely to be true. Thagard and Verbeurgt indeed assumed this to be the case. Perform the next exercises to assess whether or not the intuition is founded.

{% question %}
Are there instances of <span style="font-variant:small-caps;">Foundational Coherence</span> for which the symmetry property holds?
{% hidden Hint? %}
Prove by creating one example instance with the symmetry property, if one can exist; or, alternatively, prove that none can exist.
{% endhidden %}
{% endquestion %}

{% question %}
Are there instances of <span style="font-variant:small-caps;">Discriminating Coherence</span> for which the symmetry property holds?
{% hidden Hint? %}
Prove by creating one example instance with the symmetry property, if one can exist; or, alternatively, prove that none can exist.
{% endhidden %}
{% endquestion %}

## NP-hard

Thagard & Verbeurgt proved another interesting mathematical property of their model, i.e., it is *NP-hard*. You may not yet be familiar with this term. But no worries! We have your back.

Explaining NP-hardness in great depth is beyond the scope of this book{% sidenote 'sn-id-whatever' 'We wrote a different textbook on the topic. [Chapter 1](https://www.cambridge.org/core/services/aop-cambridge-core/content/view/2FBB76A42417F33C409A2EAD17C15046/9781107043992c1_3-22.pdf/introduction.pdf) is a good place to start.'%}, but luckily it is also not necessary. For present purposes it suffices to understand that NP-hard means really quite hard. So hard in fact that such problems are also called *intractable*. Why? Well, if a problem is NP-hard then there cannot exist *any* efficient (read: polynomial-time{% sidenote 'sn-id-whatever' 'An algorithm is said to run in *polynomial time* if the number of steps it takes can be upperbounded by a function of the form $$n^c$$, where $$c$$ is some constant and $$n$$ is the input size. For instance, $$n^2$$ is a polynomial, and so is $$n$$ and $$n^3$$. But e.g. $$2^n$$ is exponential, and not polynomial.'%}) algorithm that computes the problem.{% sidenote 'sn-id-whatever' 'Unless P = NP. For an explanation watch [this video](https://www.youtube.com/watch?v=YX40hbAHx3s).'%} Such problems may be computable in, say, exponential time. But exponential time grows astronomically fast as the input size grows. So fast, in fact, that even for intermediate size inputs the computation time is prohibitive.

{% question %}
In their paper, Thagard and Verbeurgt (1998, p. 8) write "Each person has thousands or millions of beliefs." How long would an exponential algorithm (*any* algorithm) take to compute <span style="font-variant:small-caps;">Coherence</span> for a thousand beliefs?
{% hidden Hint? %}
To start, you can assume that the algorithm takes $$2^n$$ steps. Recall that in <span style="font-variant:small-caps;">Coherence</span>, (possible) beliefs are modeled by the nodes in a constraint network. Hence, $$n = 1000$$.
{% endhidden %}
{% endquestion %}

You may want to compare the number that you obtained to the number of seconds that have past since the Big Bang, i.e., $$10^{27} < 2^{100}$$ seconds. Now you understand why exponential time is prohibitively long. The type of cognitive capacity that <span style="font-variant:small-caps;">Coherence</span> is intended to explain runs on a time scale of seconds or minutes, or in rare cases, hours, days or weeks, but certainly not on a time scale of eons. The property 'NP-hard' is thus a model property that poses a serious challenge for cognitive scientists: How could a computational-level model explain (or even describe) a cognitive capacity if the postulated input-output mapping cannot be realized by any algorithm in a reasonable time?

# Computing coherence

Thagard and Verbeurgt were not so easily swayed by the NP-hard property. They proposed not one, not two, but even *five* algorithms for computing <span style="font-variant:small-caps;">Coherence</span>.

{% epigraph '1. an  exhaustive  search  algorithm  that  considers  all  possible  solutions; 2. an  incremental  algorithm  that  considers  elements  in  arbitrary  order; 3. a connection&  algorithm  that  uses  an  artificial  neural  network  to  assess  coherence; 4. a greedy  algorithm  that  uses  locally  optimal  choices  to approximate  a globally  optimal solution; 5. a semidefinite  programming  (SDP)  algorithm  that  is guaranteed  to  satisfy  a high  proportion  of the  maximum  satisfiable  constraints.' 'Thagard & Verbeurgt' '1998' %}

We will consider some of these algorithms more in depth in Chapter 11. For now, it is important to realize that only one of the algorithms is guaranteed to return the maximum coherence truth assignment for any given instance of <span style="font-variant:small-caps;">Coherence</span>.

{% question %}
Which of the five algorithm is guaranteed to correctly compute <span style="font-variant:small-caps;">Coherence</span>? Is that algorithm tractable?
{% hidden Hint? %}
Recall that <span style="font-variant:small-caps;">Coherence</span> is NP-hard. And the number of possible truth assignments for $$n$$ nodes is $$2^n$$.
{% endhidden %}
{% endquestion %}


Four of the five algorithms proposed are at best heuristics. That is, they do not guarantee that they compute <span style="font-variant:small-caps;">Coherence</span> correctly. Even the fifth algorithm which makes a kind of guarantee makes no guarantees on how much that truth assignment that it returns resembles the maximum coherent truth assignment. In fact, a truth assignment with close to maximum coherence can be arbitrarily different from the maximum coherence truth assignment.{% sidenote 'sn-id-whatever' 'See [van Rooij & Wareham (2012)](https://www.sciencedirect.com/science/article/pii/S002224961200048X).'%} Given that <span style="font-variant:small-caps;">Coherence</span> is NP-hard there cannot exist any tractable algorithm that approximates the problem in the latter sense.

{% question %}
Prove that there cannot exist any tractable (read: polynomial-time) algorithm that for any instance of <span style="font-variant:small-caps;">Coherence</span> returns a truth assignment $$T_{approx}$$ that differs from the maximum coherence truth assignment $$T_{max}$$ in at lost $$k$$ truth values.{% sidenote 'sn-id-whatever' 'In other words, the [hamming distance](https://www.youtube.com/watch?v=P02mJhS9qQ4) $$|T_{approx} - T_{max}| \leq k$$..'%}
{% hidden Hint? %}
Prove by contradition. That is, assume that such an approximation would exist, and prove that then it is also possible to compute the maximum in polynomial-time.
{% endhidden %}
{% endquestion %}

We will return to this topic in Chapter 11. There we will explore how well or poorly different algorithms approximate <span style="font-variant:small-caps;">Coherence</span>, <span style="font-variant:small-caps;">Foundational Coherence</span> and <span style="font-variant:small-caps;">Discriminating Coherence</span>, and the implications thereof.


# Further reading

In this chapter we revisited the genesis of a classic model in the cognitive science literature and some of its formal properties.  

Since its conception, the constraint satisfaction model of coherence has proven applicable in a large variety of contexts. For instance, it has been used to model how people may change beliefs about climate change (Thagard & Findlay, 2010), how doctors explain disease (Thagard 2018), how people fix miscommunications (van Arkel, 2021), how scientists form explanations of natural phenomena (Thagard, 2000; 2007; see also Maier, van Dongen & Borsboom, 2021), and a whole host of other situations (Thagard, 2000).

In his book [Hot Thought](https://books.google.nl/books?hl=nl&lr=&id=tJV735_HoLAC&oi=fnd&pg=PR9&ots=j0Xhg4JHXz&sig=39hXZAOvkN886h-pDp2ixIBe57Y#v=onepage&q&f=false), Paul Thagard (2008) showed how the modeling principles could be extended to include even emotions, not just beliefs and mental representations. This idea led to new accounts of how emotional coherence plays a role in religious beliefs (Thagard, 2005), how juries make guilty versus innocent verdicts (Thagard, 2003), and the formation of social attitudes (Thagard, 2008; see also Klapper et al., 2018). Most recently, Thagard (2021) also applied the model to account for [people's beliefs about COVID](https://www.sciencedirect.com/science/article/pii/S104620232100075X?casa_token=GRLYXkqHf88AAAAA:oF6iYPMbyXsLHTOXASyYnPxAa3rZgigxTM-AUzICBlCIbxrS9mx2kdETvfun5TnZF0gMgwEAMg).

The coherence model also has its critics.{% sidenote 'sn-id-whatever' 'Maybe you have your own criticisms? Then we invite you to articulate them and write down your arguments as precisely and rigorously as possible.'%} For instance, Millgram (2003) critiqued the model for mixing up 'maximizing coherence' with 'approximating truth' (but see also the response by Thagard, 2012). Millgram's critique furthermore inspired work on different notions of approximation (van Rooij & Wareham, 2012). These distinctions will prove relevant in Chapter 11.

The theory of coherence, and especially non-foundational (what we called here dicriminatory) coherence, is also conceptually closely related to the notion of reflective equilibrium advanced by Catherine Z. Elgin (1996, 2013, 2017) among others. Beisbart and colleagues (2021) developed a first formalisation of her notion. 

### References

van Arkel, J. (2021). [How do people resolve misunderstanding in conversation through repair? Introducing a framework built on belief revision and coherence](https://fse.studenttheses.ub.rug.nl/24420/1/MasterThesis_vanArkel.pdf). MSc thesis, University of Groningen.

Beisbart, C., Betz, G., & Brun, G. (2021). Making reflective equilibrium precise. A formal model. Ergo: an open access journal of philosophy, 8(15), 441-472.

Elgin, C. Z. (1996). Considered Judgment. Princeton University Press.

Elgin, C. Z. (2013). Non-foundationalist epistemology: Holism, coherence, and tenability. In: M. Steup and E. Sosa (Eds.), Contemporary Debates in Epistemology ed. Boston: Blackwell, 2005, pp. 156-167.

Elgin, C. Z. (2017) True enough. MIT press.

Klapper, A., Dotsch, R., van Rooij, I., & Wigboldus, D. H. (2018). [Social categorization in connectionist models: A conceptual integration](https://guilfordjournals.com/doi/abs/10.1521/soco.2018.36.2.221). Social Cognition, 36(2), 221-246.

Maier, M., van Dongen, N., & Borsboom, D. (2021). [Comparing Theories with the Ising Model of Explanatory Coherence](https://doi.org/10.31234/osf.io/shaef). PsyArXiv.  

Millgram, E. (2000). [Coherence: The price of the ticket](https://www.jstor.org/stable/2678447?casa_token=XY_Q98F_AyQAAAAA%3AwAFqn3CinPzxidwr2UPOxfGLg9SGDgQHZ_6h3V_KkJ9JY5SQ05MOva2jKIlfDC3SlCHkdYeUKDNOcpGVND3zpwQCtdtVEyARaiMhwrva2QG5qbWwPR8&seq=1#metadata_info_tab_contents). The Journal of Philosophy, 97(2), 82-93.

Thagard, P. (1989). [Explanatory coherence](https://www.cambridge.org/core/journals/behavioral-and-brain-sciences/article/explanatory-coherence/E05CB61CD64C26138E794BC601CC9D7A). Behavioral and brain sciences, 12(3), 435-467.

Thagard, P. (2000). Coherence in thought and action. MIT press.

Thagard, P. (2003). [Why wasn't OJ convicted? Emotional coherence in legal inference](https://www.tandfonline.com/doi/abs/10.1080/0269993024400002?casa_token=z0RH6mGSrDoAAAAA:szmDR7rJGsd_mQaPvSp3sFzwnjWEjCCs_U-y2FATZUamvcJLpN34jqINdQlN-p9155wi51J4c-bl). Cognition and emotion, 17(3), 361-383.

Thagard, P. (2005). [The emotional coherence of religion](https://brill.com/view/journals/jocc/5/1-2/article-p58_3.xml). Journal of Cognition and Culture, 5(1-2), 58-74.

Thagard, P. (2007). [Coherence, truth, and the development of scientific knowledge](https://www.journals.uchicago.edu/doi/abs/10.1086/520941?casa_token=04ZmEvNXeT8AAAAA:6xzphVOxpaSx8-f64n-fu493FFnlLjYmZkcbjh55hZGSHXfUHFtPB9VCXrqGsG3xQwkASnY67ow). Philosophy of science, 74(1), 28-47.

Thagard, P. (2008). [Hot thought: Mechanisms and applications of emotional cognition](https://books.google.nl/books?hl=nl&lr=&id=tJV735_HoLAC&oi=fnd&pg=PR9&ots=j0Xhg4JHXz&sig=39hXZAOvkN886h-pDp2ixIBe57Y#v=onepage&q&f=false). MIT press.

Thagard, P. (2012). [Coherence: The price is right](https://onlinelibrary.wiley.com/doi/abs/10.1111/j.2041-6962.2011.00091.x?casa_token=z3hyGuAPj-EAAAAA:ptpEupaVT_6OuBAQ607Hgi9efhHySlq9jiMz0mxoDQlSssCQ4QoFxRpNDr-05NmfDx11ufjEeQqsIXY). The Southern Journal of Philosophy, 50(1), 42-49.

Thagard, P. (2018). [Social equality: Cognitive modeling based on emotional coherence explains attitude change](https://journals.sagepub.com/doi/full/10.1177/2372732218782995?casa_token=Nq19Q3lFh4sAAAAA%3AQEraSsfWE6j3B0UFDwLkxt52_uMwS9xYYwV-9rxzXpk2I8kNUB-Le1aJmGngN3CGP4WkeOoKF1Y1). Policy Insights from the Behavioral and Brain Sciences, 5(2), 247-256.

Thagard, P. (2018). [How scientists explain disease](https://www.degruyter.com/document/doi/10.1515/9780691187303/html). Princeton University Press.

Thagard, P. (2021). [The cognitive science of COVID-19: Acceptance, denial, and belief change](https://www.sciencedirect.com/science/article/pii/S104620232100075X?casa_token=MINX5tN_rPQAAAAA:WPSOZkiiPxJPci-GwsvEaeMjgXCDOH5_agSqT6wPyzi4F6lBiKhL11MysnMi8qSwk0kF14bv0Q). Methods.

Thagard, P., & Findlay, S. (2010). [Changing minds about climate change: Belief revision, coherence, and emotion.](https://link.springer.com/chapter/10.1007/978-90-481-9609-8_14) In Belief revision meets philosophy of science (pp. 329-345). Springer, Dordrecht.


Thagard, P., & Verbeurgt, K. (1998). [Coherence as constraint satisfaction](https://www.sciencedirect.com/science/article/pii/S0364021399800330). Cognitive Science, 22(1), 1-24.

van Rooij, I., & Wareham, T. (2012). [Intractability and approximation of optimization theories of cognition](https://www.sciencedirect.com/science/article/pii/S002224961200048X). Journal of Mathematical Psychology, 56(4), 232-247.
