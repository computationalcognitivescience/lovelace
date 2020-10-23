---
layout: page
title: Conceptual foundations
chapter: 2
nav_exclude: true
---

{% epigraph 'The first question that arises frequently—sometimes innocently and sometimes not—is simply, "Why model?" Imagining a rhetorical (non-innocent) inquisitor, my favorite retort is, "You are a modeler."  (...) when you close your eyes and imagine an epidemic spreading, or any other social dynamic, you are running some model or other. It is just an implicit model that you haven&#39;t written down.' 'Joshua M. Epstein' '2008' %}

We need to cover some conceptual and philosophical grounds before we can start modeling. We'll start by considering what models are for.

## Why do scientists model?

{% marginfigure 'mf-id-whatever' 'assets/img/globe.png' '' %} There is not one answer to this question. There are many possible reasons and valid purposes for modeling. For instance, some models have the purpose to *describe* (or *represent*). Descriptive models typically capture some aspects of the thing described and ignore others. Often they maintain relational information about things in the world, while not necessarily respecting the scale of the thing described. A familiar example is the globe that you may have on your desk. This model is substantially smaller than the thing it models (i.e., earth), yet it describes quite accurately the relative shapes, borders and locations of countries, oceans and seas on the surface of the earth. Many details remain omitted of course.

{% marginfigure 'mf-id-whatever' 'assets/img/solar-system.jpg' '' %}  Sometimes descriptive models ignore more than just the scale of things. The picture on the right depicting the relation between the planets and the sun in our solar system illustrates this idea. This picture ignores relative distances between planets and the sun and only captures their ordering. Models need not be pictorial. For instance, we can also describe the ordering of the planets using functions, such as $$d(x,y)$$ to denote the distance between $$x$$ and $$y$$; and using mathematical symbols, such as $$<$$ for 'smaller than':

$$ d(Mercury, Sun) < d(Venus, Sun) < d(Earth, Sun) < d(Mars, Sun)  $$
$$ <d(Jupiter, Sun) < d(Saturn, Sun) < d(Uranus, Sun) < d(Neptune, Sun) $$

In this description even more information is lost. There is no longer any information about the relative sizes of the planets, nor about colours.{% sidenote 'sn-id-whatever' 'Note that in the picture above the relative sizes of the sun and planets were in fact distorted (e.g., [the Sun is many orders of magnitude bigger than Earth](https://en.wikipedia.org/wiki/Solar_System#/media/File:Planets2013.svg)) and the colours of the planets are caricatures at best. Every model, *qua* description or representation, will omit information and distort on some dimensions. This is unavoidable, but we can be aware of it.'%} Yet, the relevant information about *ordering* is perfectly retained. The type of models we will be developing in this book are more of the mathematical type than the pictorial type.

{% stopandthink %}
Do you think cognitive scientists and psychologists also make use of descriptive models?
{% hidden Hint? %}
Aren't all models minimally descriptive of something?
{% endhidden %}
{% endstopandthink %}


{% question %}
What mathematical descriptive model could describe a person's ability to add numbers?
{% hidden Hint? %}
For any two numbers {% m %}x{% em %} and {% m %}y{% em %}, the ability could be described by a function {% m %}f{% em %}, defined as {% m %}f(x,y) = x + y{% em %}.
{% endhidden %}
{% endquestion %}

{% question %}
What mathematical descriptive model could describe a person's ability to multiply numbers?
{% hidden Hint? %}
You don't need a hint.
{% endhidden %}
{% endquestion %}

As models minimally describe something{% sidenote 'sn-id-whatever' 'This need not be a *real* or *concrete* thing, but can also be a *hypothetical* thing. For instance, a globe could describe the geography of an alien planet in a sci-fi novel, and a mathematical model could describe a hypothesized cognitive ability.' %}, they can be used for other purposes, too. For instance, the globe can be used to *predict* that if one starts sailing from the coast of The Netherlands and heads straight West, one will eventually bump into the coast of the United Kingdom. This prediction holds no matter where you start on the Dutch coast (provided you stay on course). To make a more precise prediction of where you most likely will end up, you would need more information about where you started on the Dutch coast (e.g., above or below the longitude coordinate of Zandvoort aan Zee).  

Can we also use models to predict people's behaviour? Sure. You do this all the time yourself. For instance, when you predict how someone will behave in a given situation, based on their past behaviour in *similar* situations, you are using a model of that person{% sidenote 'sn-id-whatever' 'And a model of &#39;similarity&#39;; a topic we will pick up in Chapter 7.' %}. It is just not a model that you have written down (cf. quote at start of this chapter). Similarly, when you predict how someone will behave in a given situation, based on how *other* people behave in similar situations, you are also using a model. This second type of model is more generic and not specific to the person. A third possibility is when you predict how someone will behave in a given situation, based on how *similar* people have behaved in similar situations. Then you are using a model that is neither person specific, nor fully generic, but specific to a group of people who are similar (according to some dimension you assume is relevant{% sidenote 'sn-id-whatever' 'cf. stereotypes.' %}).

{% stopandthink %}
Take a moment to think of concrete situations where you tried to predict a person's behaviour. What type of model did you use, and why?
{% hidden Hint? %}
Think of multiple situations and different people. Is the model that you use the same or different accross situations and people? What makes the models vary?
{% endhidden %}
{% endstopandthink %}

{% question %}
What could be an example of model used by scientists to predict a person's behaviour.
{% hidden Hint? %}
Think, for instance, of [simple linear regression](https://www.youtube.com/watch?v=SYY_BPciXPw). The best fitting line in a simple linear regression of data obtained from a sample of people, can be used to predict (unknown) values for a person  the {% m %}y{% em %}-axis (e.g. how often they go to the movies), from their known values on the {% m %}x{% em %}-axis (e.g. their monthly income).
{% endhidden %}
{% endquestion %}

Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating. Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating. Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating. Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating.Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating. Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating. Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating. Explain the other used of modeling: explanation, understanding, controlling, prescribing or emulating.

{% question %}
In this section we covered 7 possible reasons for modeling. Can you think of more?
{% hidden Hint? %}
Read Epstein (2008). [Why Model?](http://jasss.soc.surrey.ac.uk/11/4/12.html). He identifies 16 possible reasons. 
{% endhidden %}
{% endquestion %}



## Prediction vs. Explanation
{% marginfigure 'mf-id-whatever' '/assets/img/rainbow.jpg' '' %}.
{% marginfigure 'mf-id-whatever' '/assets/img/norway.jpg' '' %}.
{% marginfigure 'mf-id-whatever' '/assets/img/wave.jpg' 'Rainbows, northern lights (aurora borealis), and the tides are well-known phenomena we can all observe. Unexplained these phenomena are puzzling. Explanations postulate mechanisms (interacting component processes) and conditions that together produce these phenomena.' %}.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.
Explain how prediction and explanation are doubly dissociated.

## Explaining Capacities

{% marginnote 'sn-id-whatever' 'Theoretical modeling is different from data or statistical modeling. This may *prima facie* look odd for scientists used to analyzing empirical data. We will be modeling target phenomena, not data. ' %}
The theoretical modeling approach that we adopt here builds on the philosophical view that psychology's primary targets of explanation are behavioural tendencies or dispositions, also knows as *capacities* {% cite cummins_nature_1985 cummins_how_2000 %}. As {% cite van_rooij_theory_2020 %} put it:

{% epigraph 'A capacity is a dispositional property of a system at one of its levels of organization: e.g., single neurons have capacities (firing, exciting, inhibiting) and so do minds and brains (vision, learning, reasoning) and groups of people (coordination, competition, polarization).
    A capacity is a more or less reliable ability (or disposition or tendency) to transform some initial state (‘input’) into a resulting state (‘output’).' 'van Rooij & Baggio' 'Theory before the test, 2020' %}

Take, for instance, the social-cognitive capacity for *goal inference*  {% cite baker_goal_2007 blokpoel_computational-level_2013 %}: Given observations of a person's behaviours one can make inferences about the (presumed) goals of a person. In this case the *input* is the observed behaviour (e.g., 'Jake buys flowers') and background knowledge (e.g., 'Yesterday, Jake broke Cass's car'), and the *output* is an inferred goal (e.g., 'Jake wants to apologize to Cass'). Alternatively, consider the capacity for *causal attribution*  {% cite  de_houwer_levels_2015 ross_intuitive_1977 %}: given observations of a person's behaviours one can make attributions of whether the underlying causes lie in person traits and/or situational factors. In this case the *input* is again the observed behaviour (e.g., 'Jake buys flowers') and some background knowledge but the *output* is a causal attribution in terms of person traits (e.g., 'Jake bought flowers because he is an attentive person') or in terms of situational factors (e.g., as in the *goal inference* example above, or otherwise).

According to the influential tri-level framework proposed by David (1982) capacities can be analyzed at three different levels: the computational level, the algorithmic level, and the implementational level. At the computational level, we ask the question, 'what is the function (or problem) being computed by the capacity?' At the algorithmic level, we ask: 'how is the function computed (by what algorithm)?' And finally, at the implementational level, we ask: 'how is the algorithm physically realized?' An important feature of this framework is that lower levels of explanation are underdetermined, though constrained, by the higher levels of explanation: A function can, in principle, be computed by different algorithms; and any given algorithm can, in principle, be physicially realized in different ways (Fig.~\ref{fig:marr-sorting}).

Let's illustrate these ideas using a capacity called _sorting_ (e.g., one can order people from youngest to oldest, order choice options from least to most preferred, etc.). We will adopt the convention that a computational-level model can be represented as follows:

\vspace{0.5em}
\fproblem{Name of modelled capacity}
{Specification of the input.}
{Specification of the output as a function of the input.}
\vspace{0.5em}


{% maincolumn 'assets/img/marr-sorting.svg' 'XXX' %}

{% youtube 'https://www.youtube.com/watch?v=ywWBy6J5gz8' %}


## Further Reading

Cummins, R. (1985). *The Nature of Psychological Explanation*. MIT Press.

Cummins, R. (2000). [How does it work?" versus" what are the laws?": Two conceptions of psychological explanation](https://pdfs.semanticscholar.org/f5b1/b05e8313aee94ccd98e80eab3ec56dbd2c97.pdf). In *Explanation and cognition* (pp. 117–144). Frank C. Keil, Robert Andrew Wilson (Eds). MIT Press

Guest, O., & Martin, A. E. (2020). How computational modeling can force theory building in psychological science. *Perspectives on Psychological Science* [preprint](https://doi.org/10.31234/osf.io/rybh9)

[Excerpt (pp. 69-73)](http://ling.umd.edu/~ellenlau/courses/ling646/Marr_1982.pdf) from: Marr, D. (1982). *Vision: A computational investigation into the human representation and processing of visual information.* New York.

van Rooij, I., & Baggio, G. (2020). Theory before the test: How to build high-verisimilitude explanatory theories in psychological science. *Perspectives on Psychological Science* [preprint](https://psyarxiv.com/7qbpr/)
