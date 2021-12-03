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

{% marginfigure 'mf-id-whatever' 'assets/img/solar-system.jpg' '' %}  Sometimes descriptive models ignore more than just the scale of things. The picture on the right depicting the relation between the planets and the sun in our solar system illustrates this idea. This picture ignores relative distances between planets and the sun and only captures their ordering.

Models need not be pictorial. For instance, we can also describe the ordering of the planets using functions, such as $$d(x,y)$$ to denote the distance between $$x$$ and $$y$$; and using mathematical symbols, such as $$<$$ for 'smaller than':

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
What could be an example of a model used by scientists to predict a person's behaviour.
{% hidden Hint? %}
Think, for instance, of <a href="https://www.youtube.com/watch?v=SYY_BPciXPw">simple linear regression</a>. The best fitting line in a simple linear regression of data obtained from a sample of people, can be used to predict (unknown) values for a person  the {% m %}y{% em %}-axis (e.g. how often they go to the movies), from their known values on the {% m %}x{% em %}-axis (e.g. their monthly income).
{% endhidden %}
{% endquestion %}

A third important use of models is to _explain_ (or _understand_). {% marginfigure 'mf-id-whatever' 'assets/img/tide_model.png' 'Schematic explaining the lunar tides. From [Wikimedia. CC BY-SA 3.0.](https://en.wikipedia.org/wiki/Tide#/media/File:Tide_overview.svg)' %} Models that explain can be seen as answering 'why' and 'how' questions. For instance, the schematic on the right can be used to explain _why_ there are tides and _how_ they come about: i.e., the gravitational pull of the moon generates a tidal force that causes Earth and its water to bulge out on the side closest to and farthest away from the moon. The solar tide works similarly, but is not shown in the schematic. For a more complete explanation [watch this](https://youtu.be/FtFn6Y1QDyk).

An important thing to notice is that models that _predict_ need not _explain_, and vice versa. For instance, using tide tables you can very precisely predict the tides at any time of the day at different locations. Yet, tide tables do not explain the tides{% sidenote 'sn-id-whatever' 'cf. Cummins (2000)'%}. Similarly, we could construct a big look-up table to predict what types of products people tend to buy at different times during the year. Even if the table would make good predictions it would not explain why people buy more ice cream in Summer and more Christmas trees in December.{% sidenote 'sn-id-whatever' 'Depending on where you are on Earth this may or may not be around the same time.'%} Conversely, while the schematic model of the tidal force created by the moon generates understanding of the 'why' and 'how' of tides, it is too abstract to make very precise, local predictions about the tides. Similarly, a scientific explanation of the 'why' and 'how' of the human ability to make decisions{% sidenote 'sn-id-whatever' 'A topic we will pick up further in Chapter 4.'%} is likely too abstract and generic to predict particular concrete choices that people make.

Besides describing, predicting and explaining, models can also be used for, say, _controlling_, _prescribing_, and _emulating_.

For instance, if one has a model of 'consumer biases' then this model could be used to figure out how to 'nudge' people into buying certain products; or, if one has a model of what causes depression then one could use the model to design a treatment that removes the cause. Both these examples would be instances of using a model to exert _control_ over something, for better or worse. If one uses a normative model of reasoning (as in logic or probability theory) or deciding (as in economic choice theory) then the model could be used to _prescribe_ how one should reason or should decide if one were to be rational. This type of prescriptive model need not be descriptive of actual human behaviour; though there exist psychologists and cognitive scientists who believe that prescriptive models can be descriptive in that sense, too.{% sidenote 'sn-id-whatever' 'Chater & Oaksford (2000). [The rational analysis of mind and behavior](https://link.springer.com/article/10.1023/A:1005272027245) _Synthese, 122_, pp. 93–131.' %} Lastly, we could make a model of human cognition and instantiate it in an artificial agent (e.g., a physical robot or virtual agent). Then the model would serve to _emulate_ (or _simulate_{% sidenote 'sn-id-whatever' 'Whether or not intelligence can really be emulated, and not just merely simulated, is a longstanding debate in AI (see [strong versus weak AI](https://plato.stanford.edu/entries/artificial-intelligence/#PhilArtiInte)).' %}) the thing modeled. The idea that models can be made to emulate human cognition has of course led to the foundation of the field artificial intelligence (AI). Similarly, the idea that models can be made to emulate natural evolution has founded the field of artificial life.  

{% stopandthink %}
So far we have offered several possible reasons for modeling. Can you think of more?
{% hidden Hint? %}
Read Epstein (2008). <a href="http://jasss.soc.surrey.ac.uk/11/4/12.html">Why Model?</a> He identifies 16 possible reasons. Can you come up with reasons 17 and 18?
{% endhidden %}
{% endstopandthink %}


## Explaining capacities

All possible purposes for modeling are valid. One use is not better than another. All depends on one's scientific aims. In the remainder of this book we will focus on modeling with the scientific aim of _explaining_ or otherwise advancing our _understanding_ of (cognitive) psychological phenomena (though other uses of modeling may show their faces along the way insofar as they support this aim). This can also include models that try to explain but fail to do so. As you will see, we can learn as much from model 'failures' as from model 'successes', or from modeling 'hypotheticals' and 'counterfactuals'. But before we dive into modeling, let's think deeply about _what_ it is that we want our models to explain.

{% marginnote 'mn-id-whatever' '“(…) a substantial proportion of research effort in experimental psychology isn’t expended directly in the explanation business; it is expended in the business of discovering and confirming effects” – Cummins (2000).' %}  For psychologists and cognitive scientists whose day-to-day research involves doing experiments in the lab and analysing data, a first obvious contender for 'target explananda'{% sidenote 'sn-id-whatever' 'Terminology: _explanandum_ (plural _explananda_) means the thing to be explained, and _explanans_ means the thing doing the explaining, i.e. the explanation.' %} {% marginnote 'sn-id-whatever' 'Theoretical modeling is different from (statistical) data modeling. This may *prima facie* look odd for cognitive and psychological scientists used to analyzing empirical data. We will be modeling target phenomena, not data.' %} may be one or more of the 'effects' established via such research practices. Take for instance, well-known effects like the Stroop effect, the McGurk effect, the primacy and recency effects, visual illusions etc. Aren't these the things we should be explaining? Not really. Or rather, not primarily. While effects have a role to play in the scientific enterprise they are at best _secondary_ explananda for psychologal and cognitive science. Ideally, we do not construct theories just to explain effects. The Stroop effect, the McGurk effect, the primacy and recency effects, visual illusions etc. rather serve to arbitrate between competing explanations of the _capacities_ for cognitive control, speech perception, memory, and vision, respectively. It is these kinds of capacities that form the _primary_ explananda.

{% question %}
How can effects arbitrate between competing explanations of capacities?
{% hidden Hint? %}
Consider, for instance, the capacity for <em>multiplication</em>. This capacity could be exercised using the <em>partial products algorithm</em> or using <em>successive addition</em>. Both methods realize the same capacity, but the second shows a “linearity effect” (i.e., it takes roughly twice as long to compute {% m %}x \times y{% em %} as it does to compute {% m %}2x \times y{% em %}, for any numbers {% m %}x{% em %} and {% m %}y{% em %}, while the <em>partial products algorithm</em> does not exhibit this linearity effect (see Cummins, 2000, for further discussion).
{% endhidden %}
{% endquestion %}

{% epigraph 'A capacity is a dispositional property of a system at one of its levels of organization: e.g., single neurons have capacities (firing, exciting, inhibiting) and so do minds and brains (vision, learning, reasoning) and groups of people (coordination, competition, polarization). A capacity is a more or less reliable ability (or disposition or tendency) to transform some initial state (or &#39;input&#39;) into a resulting state (&#39;output&#39;).' 'van Rooij & Baggio' '2020' %}

{% marginfigure 'mf-id-whatever' '/assets/img/wave.jpg' '' %}
{% marginfigure 'mf-id-whatever' '/assets/img/rainbow.jpg' '' %}
{% marginfigure 'mf-id-whatever' '/assets/img/decisionmaking.png' '' %}
{% marginfigure 'mf-id-whatever' '/assets/img/communication.png' 'Tides and rainbows are two of the many natural phenomena we can all observe and don&#39;t require intricate methods to be discovered. Unexplained these phenomena are puzzling. The same holds for many cognitive capacities, like decision making and communication, and many others.' %}

Primary explananda are the key phenomena that collectively define a field of study. For instance, cognitive psychology’s primary explananda are the various cognitive capacities that humans and other animals seem endowed with. These include capacities for learning, language, perception, categorization, decision making, planning, problem solving, reasoning, communication, to name a few. While effects (secondary explananda) are often only discovered through intricate methods and experiments, capacities (primary explananda) usually need not be discovered in the same way. We often know roughly the kinds of capacities we'd like to explain before we bring them into the lab. How else could we know there was something to study in the first place?

Just like we knew about tides (or rainbows, nordic lights, thunder and lighting, etc.) from naturalistic observation before seeking explanations for these puzzling phenomena, so too we already know that humans can learn languages, interpret complex visual scenes, navigate dynamic and uncertain environments, and have conversations with conspecifics. These capacities are so remarkable and difficult to explain computationally or mechanistically that we do not know yet how to emulate them in artificial systems at human levels of sophistication. Like the tides, rainbows, etc., these cognitive capacities are puzzling and demand explanation.

How do we explain capacities?

According to the influential tri-level framework proposed by David Marr (1982) capacities can be analyzed at three different levels: the computational level, the algorithmic level, and the implementational level. At the _computational_ level, we ask the question, '_what_ is the nature of the problem solved by the capacity?' An answer to this question comes in the form of a hypothesized input-output mapping, a.k.a. computational problem. At the _algorithmic_ level, we ask: '_how_ is the input-output mapping that characterizes the capacity computed?' An answer to this question comes in the form of a hypothesized algorithm, a step-by-step procedure computing the hypothesized mapping. And finally, at the _implementational_ level, we ask: 'how is the algorithm _physically realized_?' An answer to this question comes in the form of a specification of how the algorithm posed at the algorithmic level is hypothesized to be implemented in the 'stuff' realizing the capacity.

An important feature of Marr's framework is that lower levels of explanation are underdetermined, though constrained, by the higher levels of explanation: A function can, in principle, be computed by different algorithms; and any given algorithm can, in principle, be physically realized in different ways. Let's illustrate these ideas using a capacity called _sorting_ (e.g., one can order people from youngest to oldest, order choice options from least to most preferred, etc.). We will adopt the convention{% sidenote 'sn-id-whatever' 'This convention comes from computer science. We believe that adoption of this convention in psychology and cognitive science will bring more clarity about the scope and commitments of computational-level theories. In the current literature, these aspects often remain implicit or ill-defined, causing crosstalk and making explanatory failures invisible (more on this in Part II of this book).'%} that a computational-level model can be represented as follows:

{% fproblem Name of modelled capacity %}
Specification of the input.;;
Specification of the output as a function of the input.
{% endfproblem %}

For the capacity _sorting_, this looks as follows:

{% fproblem Sorting %}
A list of unordered numbers $$L$$.;;
An ordered list $$L'$$ that consists of the elements in $$L$$.
{% endfproblem %}

In other words, here we stipulate that $$L'$$ = <span style="font-variant:small-caps;">Sorting</span>$$(L)$$. For instance, if $$L$$ is 625739 then $$L'$$ is 235679.

The <span style="font-variant:small-caps;">Sorting</span> function can be computed by different algorithms. For instance, one strategy can be to consider each item, from left to right, to find the smallest element in the list $$L$$, and put it in position 1 of list $$L'$$. Then repeat this for the remainder of the numbers in $$L$$, and put the next smallest number in position 2 in list $$L'$$; and so on, until one has filled up list $$L'$$ using the elements in $$L$$. A different strategy, however, could be to order the numbers in $$L$$ by 'swapping' adjancent numbers: i.e., consider the numbers in position 1 and 2 in $$L$$, and if the second number smaller than the first then swap the two numbers. Repeat for the numbers in position 2 and 3, positions 3 and 4, and so on. Then repeat the whole procedure starting again at position 1, and repeat until no more swaps can be made.

{% maincolumn 'assets/img/marr-sorting.svg' 'Any given function can be computed by different algorithms, and any given algorithm can be physically realized in different ways. This principle is illustrated for the sorting example.' %}

Both algorithms, called _selection sort_ and _bubble sort_ respectively (Knuth, 1968), compute the <span style="font-variant:small-caps;">Sorting</span> function. Besides these two algorithms there exists a host of different sorting algorithms, all of which compute exactly the same function, though their timing profiles may differ.{% sidenote 'sn-id-whatever' 'For a visual and auditory illustration of 15 distinct sorting algorithms [watch this](https://youtu.be/kPRA0W1kECg). '%}
Like the <span style="font-variant:small-caps;">Sorting</span> function can be computed by different algorithms, each algorithm can be realized in different physical ways. For instance, a sorting algorithm can be physically realized by a computer or a brain, or even as a distributed group activity (e.g., by people walking though a maze (van Rooij & Baggio, 2020), or by a group of Hungarian dancers.{% sidenote 'sn-id-whatever' 'Here an example [video](https://www.youtube.com/watch?v=uzyDE_5h-vs) for sorting through a maze, and an example [video](https://www.youtube.com/watch?v=lyZQPjUT5B4) for sorting by Hungarian dancers. '%}


An important lesson is that the _levels of explanation_ of Marr do not correspond to _levels of organisation_.{% sidenote 'sn-id-whatever' 'See also [McClamrock (1991)](https://link.springer.com/article/10.1007/BF00361036). '%} That is, at each level of organisation we can ask the three questions: what is the computational problem? how is the problem computed? how is the algorithm implemented?

Following Marr (1982), in this book we adopt a top-down strategy. We focus on the computational level, but we will have things to say on algorithmic and implementational level considerations, especially in ways that they constrain computational-level theorizing (van Rooij et al., 2019) and vice versa (Blokpoel, 2018). The computational level of analysis is specifically useful as an interface between those aspects of psychology and cognitive science that focus on behavior and those aspects that focus on physical implementation (e.g., neuroscience or genetics) as it casts cognitive and other psychological capacities in an abstract common language of computation.  

## Further Reading

Cognitive science has a long tradition of theoretical modeling due to the central role played by computer theory, theoretical linguistics, and philosophy in the birth of this multidiscipline in the 1950s (a.k.a. the 'cognitive revolution'; Miller, 2003). Other parts of psychological science historically show more neglect of theory and modeling.{% sidenote 'sn-id-whatever' 'A notable exception is [mathematical psychology](https://doi.org/10.1177/1745691620974769) (Navarro, 2021).'%}  However, there are initiatives to bring more awareness of the limits of science without theoretical modeling (Guest & Martin, 2021: Muthukrishna & Henrich, 2019; Smaldino, 2019, van Rooij & Baggio, 2021).{% sidenote 'sn-id-whatever' 'See also the recent Special issue on [Theory in Psychological Science](https://metatheorist.com/SpecialIssue_TheoryInPsych/) in the journal [*Perspectives on Psychological Science*](https://journals.sagepub.com/toc/pps/16/4).'%}

Much has been written on Marr's 3 levels, critiques as well as reformulations (see, e.g., McClamrock, 1991). To learn more about the diversity of views on Marr's levels we recommend reading the special issue in *Topics in Cognitive Science* titled [Thirty Years After Marr's Vision: Levels of Analysis in Cognitive Science](https://onlinelibrary.wiley.com/doi/full/10.1111/tops.12137).

### References

Blokpoel, M. (2018). [Sculpting computational-level models](https://onlinelibrary.wiley.com/doi/full/10.1111/tops.12282). *Topics in Cognitive Science, 10*(3), 641–648.

Cummins, R. (1985). *The Nature of Psychological Explanation*. MIT Press.

Cummins, R. (2000). [How does it work?" versus" what are the laws?": Two conceptions of psychological explanation](https://pdfs.semanticscholar.org/f5b1/b05e8313aee94ccd98e80eab3ec56dbd2c97.pdf). In *Explanation and cognition* (pp. 117–144). Frank C. Keil, Robert Andrew Wilson (Eds). MIT Press.

Guest, O., & Martin, A. E. (2021). [How computational modeling can force theory building in psychological science](https://journals.sagepub.com/doi/full/10.1177/1745691620970585). *Perspectives on Psychological Science*.

Epstein, J. M. (2008). [Why model?](https://www.jasss.org/11/4/12.html). *Journal of artificial societies and social simulation, 11*(4), 12.

Knuth, D. E. (1968). The art of computer programming: Sorting and Searching. Addison-Wesley.

[Pages 69-73](https://moodle.swarthmore.edu/pluginfile.php/251674/mod_folder/content/0/Marr1982Chapter1acc.pdf?forcedownload=1) from: Marr, D. (1982). *Vision: A computational investigation into the human representation and processing of visual information.* New York.

McClamrock, R. (1991). [Marr's three levels: A re-evaluation](https://link.springer.com/article/10.1007/BF00361036). *Minds and Machines, 1*(2), 185-196.

Miller, G. A. (2003). [The cognitive revolution: a historical perspective](https://www.cs.princeton.edu/~rit/geo/Miller.pdf). *Trends in cognitive sciences, 7*(3), 141-144.

Muthukrishna, M., & Henrich, J. (2019). [A problem with theory](https://www.nature.com/articles/s41562-018-0522-1). *Nature Human Behaviour, 3*, 221–22.

Navarro, D. J. (2021). [If mathematical psychology did not exist we might need to invent it: A comment on theory building in psychology](https://doi.org/10.1177/1745691620974769). *Perspectives on Psychological Science*.

Smaldino, P. (2019). [Better methods can't make up for mediocre theory](https://go.gale.com/ps/i.do?id=GALE%7CA640675901&sid=googleScholar&v=2.1&it=r&linkaccess=abs&issn=00280836&p=AONE&sw=w). *Nature, 575*(7781), 9-10.

van Rooij, I., & Baggio, G. (2021). [Theory before the test: How to build high-verisimilitude explanatory theories in psychological science](https://journals.sagepub.com/doi/full/10.1177/1745691620970604). *Perspectives on Psychological Science*.

van Rooij, I., Blokpoel, M., Kwisthout, J. & Wareham, T. (2019). [Cognition and Intractability](https://cognitionandintractability.com). Cambridge University Press.  
