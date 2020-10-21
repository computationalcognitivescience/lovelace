---
layout: page
title: Conceptual foundations
chapter: 2
nav_exclude: true
---

{% epigraph 'The first question that arises frequently—sometimes innocently and sometimes not—is simply, "Why model?" Imagining a rhetorical (non-innocent) inquisitor, my favorite retort is, "You are a modeler."  (...) when you close your eyes and imagine an epidemic spreading, or any other social dynamic, you are running some model or other. It is just an implicit model that you havent written down.' 'Joshua M. Epstein' '2008' %}

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
What mathematical descriptive model could describe the human ability to add numbers?
{% hidden Hint? %}
$$f(x,y) = x + y$$
{% endhidden %}
{% endquestion %}

While models typically minimally describe, usually they describe for one of more other purposes. For instance, models may be useful for predicting, explanation, understanding, controlling, prescribing or emulating.

{% question %}
Can you think of at least one example for each of them?
{% hidden Hint? %}
Think of everyday situations or applications.
{% endhidden %}
{% endquestion %}


## Prediction versus explanation

Consider the following list of possible purposes of modeling: description, prediction, explanation, understanding, control, prescription, emulation.

{% question %}
Can you think of more uses?
{% hidden Hint? %}
Think of everyday situations or applications.
{% endhidden %}
{% endquestion %}

## Effects versus Capacities

##  


{% marginnote 'sn-id-whatever' 'Theoretical modeling is different from data or statistical modeling. This may *prima facie* look odd for scientists used to analyzing empirical data. We will be modeling target phenomena, not data. ' %}
The theoretical modeling approach that we adopt here builds on the philosophical view that psychology's primary targets of explanation are behavioural tendencies or dispositions, also knows as *capacities* {% cite cummins_nature_1985 cummins_how_2000 %}. As {% cite van_rooij_theory_2020 %} put it:

{% epigraph 'A capacity is a dispositional property of a system at one of its levels of organization: e.g., single neurons have capacities (firing, exciting, inhibiting) and so do minds and brains (vision, learning, reasoning) and groups of people (coordination, competition, polarization).
    A capacity is a more or less reliable ability (or disposition or tendency) to transform some initial state (‘input’) into a resulting state (‘output’).' 'van Rooij & Baggio' 'Theory before the test, 2020' %}


Take, for instance, the social-cognitive capacity for *goal inference*  {% cite baker_goal_2007 blokpoel_computational-level_2013 %}: Given observations of a person's behaviours one can make inferences about the (presumed) goals of a person. In this case the *input* is the observed behaviour (e.g., 'Jake buys flowers') and background knowledge (e.g., 'Yesterday, Jake broke Cass's car'), and the *output* is an inferred goal (e.g., 'Jake wants to apologize to Cass'). Alternatively, consider the capacity for *causal attribution*  {% cite  de_houwer_levels_2015 ross_intuitive_1977 %}: given observations of a person's behaviours one can make attributions of whether the underlying causes lie in person traits and/or situational factors. In this case the *input* is again the observed behaviour (e.g., 'Jake buys flowers') and some background knowledge but the *output* is a causal attribution in terms of person traits (e.g., 'Jake bought flowers because he is an attentive person') or in terms of situational factors (e.g., as in the *goal inference* example above, or otherwise).

{% marginfigure 'mf-id-whatever' '/assets/img/rainbow.jpg' '' %}.
{% marginfigure 'mf-id-whatever' '/assets/img/norway.jpg' '' %}.
{% marginfigure 'mf-id-whatever' '/assets/img/wave.jpg' 'Rainbows, northern lights (aurora borealis), and the tides are well-known phenomena we can all observe. Unexplained these phenomena are puzzling. Explanations postulate mechanisms (interacting component processes) and conditions that together produce these phenomena.' %}.



## Levels of explanation

## Levels of organisation

## Further Reading

* Blokpoel, M. (2018). [Sculpting computational-level models](https://onlinelibrary.wiley.com/doi/full/10.1111/tops.12282). *Topics in Cognitive Science, 10*(3), 641–648.
* Cummins, R. (1985). *The Nature of Psychological Explanation*. MIT Press.
* Cummins, R. (2000). [How does it work?" versus" what are the laws?": Two conceptions of psychological explanation](https://pdfs.semanticscholar.org/f5b1/b05e8313aee94ccd98e80eab3ec56dbd2c97.pdf). In *Explanation and cognition* (pp. 117–144). Frank C. Keil, Robert Andrew Wilson (Eds). MIT Press
* Guest, O., & Martin, A. E. (2020). *How computational modeling can force theory building in psychological science*. [Preprint](https://doi.org/10.31234/osf.io/rybh9) on PsyArXiv
* [Excerpt (pp. 69-73)](http://ling.umd.edu/~ellenlau/courses/ling646/Marr_1982.pdf) from: Marr, D. (1982). *Vision: A computational investigation into the human representation and processing of visual information.* New York.
* van Rooij, I., & Baggio, G. (2020). *Theory before the test: How to build high-verisimilitude explanatory theories
in psychological science*. [Preprint](https://psyarxiv.com/7qbpr/) on PsyArXiv
