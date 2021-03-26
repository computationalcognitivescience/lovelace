---
layout: page
title: Subset choice
chapter: 4
nav_exclude: true
---

{% marginfigure 'mf-id-whatever' '/assets/img/choiceoptions.jpg' 'So many possibilities! How do we choose one?' %} We start our first adventure in theoretical modeling in the cognitive domain of *decision-making*. Decision making is the capacity for choosing one or more options from a given set of options. This can range from mundane decisions (e.g., choosing ice cream flavours or what movie to watch) to highly specialized decisions (e.g., deciding what medicine to prescribe as a doctor or what experiments to perform as a scientist). There is a long tradition of cognitive and psychological research on this topic, with roots in behavioural economics in the 1950s and onwards (see the Further Reading section for some pointers).

A classic distinction in the literature on decision-making is between deciding under *certainty*, *risk*, or *uncertainty*. When probabilities of different outcomes for choice options are known we say the decision is made under *risk*.


{% question %}
What could be an example of decision-making under risk?
{% hidden Hint? %}
 An example is deciding between lotteries. Imagine two lotteries. In lottery A you win 100 euros with probability 0.25 and lose 10 euro with probability 0.75, and in lottery B you win 500 euros with probability 0.20 and lose 20 euro with probability 0.80. Which lottery would you choose to participate in?
{% endhidden %}
{% endquestion %}

When exact probability information is unavailable, e.g., because the choice problem is more ill-defined, we say the decision is made under *uncertainty*.

{% question %}
What could be an example of decision-making under uncertainty?
{% hidden Hint? %}
An example is choosing what career to pursue: e.g., Would you rather be an artist, doctor, politician, accountant, or technician?
{% endhidden %}
{% endquestion %}

{% marginnote 'mn-id-whatever' '“This is the essence of intuitive heuristics: when faced with a difficult question, we often answer an easier one instead, usually without noticing the substitution.” – Daniel Kahneman (2017).' %} To make our lives easier, in this chapter we focus on decision making under *certainty*. That is, we consider choice situations where options are well-defined and the value of options is certain. As we will see, this is usually merely a convenient fiction. It makes learning to model easier, and allows us to delay the use of probability theory to the Advanced sections.

We will not retrace tradition or review existing theoretical models of decision making. {% marginnote 'mn-id-whatever' '“Human rational behavior (...) is shaped by a scissors whose two blades are the structure of task environments and the computational capabilities of the actor.” – Herbert Simon (1990).' %} Instead, we'll be throwing you into the deep waters: We will present you with (more or less fictive) verbal theories in this domain and will invite you to try and formalize them. You may think you are unable to and that you first need to learn how others made models. But trust us. You can do it! The goal is specifically not to mimic (what you think) existing models of decision making (are like), but rather to develop your own. The purpose is to practice using the theoretician's tools. Think of this and the next chapters as your sandbox for sculpting some sandcastles for fun, and to train your sculpting skills.

The example decision making capacity that we will use for our practices is called *subset choice*. This is the ability to select a *subset* of options from a larger set.

Subset Choice
* Selecting toppings on a pizza
* Inviting people to a party
* Prescribing combinations of medicine
* Doing groceries
* Selecting member for a committee or team


{% stopandthink %}
Okay, let's take a minute and pause.
{% endstopandthink %}



**Exercise**
Can you think of more everyday situations where the choice options are sets as opposed to single elements?


# Theoretical Modeling Illustrations
\noindent In this section we illustrate theoretical modeling by dialogues between the two fictive characters, *Verbal* and *Formal*. We use two toy social psychological phenomena: (1) a host inviting people to a party, and (2) party guests  self-organizing into smaller subgroups. These example scenarios can be seen as special cases of the more general capacities for *social decision-making* and *social group formation*. We present multiple dialogues. *Verbal*'s intuitions and *Formal*'s questions vary from dialogue to dialogue, and hence the developed theoretical models differ as well.  

## Dialogue 1: Formalizing inviting guests
{% indent 0 %}
**Verbal:** I'd like to explain how a host decides whom to invite to a party.  
{% endindent %}

{% indent 1 %}
**Formal:** Why would the host not invite everybody?
{% endindent %}

{% indent 0 %}
**Verbal:** They may like some people but dislike others.
{% endindent %}

{% indent 1 %}
**Formal:**  Then the host invites everybody they like?
{% endindent %}

{% indent 0 %}
**Verbal:** Not all people get along. If people get into an argument that can spoil a party.
{% endindent %}

{% indent 1 %}
**Formal:**   I see. So a host may choose to invite people they like *and* that all get along.
{% endindent %}

{% indent 0 %}
**Verbal:** Yes, that sounds right. I think that's what a host will tend to do.  Can we formalize this idea?
{% endindent %}


{% indent 1 %}
**Formal:**  Let's see. We can start by defining the initial state (input) of the decision process as consisting of the set of people that the host chooses from. Let us denote this set as $$P = \{p_1, p_2, ..., p_n\}$$. Further, let's define two subsets, $$L \subseteq P$$ and $$D \subseteq P$$, denoting the subsets of people our host likes and dislikes respectively.
{% endindent %}

{% indent 1 %}
**Formal:**  I assume you had in mind that a person cannot be both liked *and* disliked by the host, and that any given person is either liked or disliked by the host.
{% endindent %}

{% indent 0 %}
**Verbal:** Let's indeed assume that for now.
{% endindent %}

{% indent 1 %}
**Formal:**  Then $$L$$ and $$D$$ form a *partition* of $$P$$. To be precise,  $$L \cap D = \emptyset$$ (a person cannot be both liked and disliked) and $$L \cup D = P$$ (any person is either liked or disliked).
{% endindent %}

{% indent 1 %}
**Formal:**  To formalize that different pairs of people in $$P$$ can also like or dislike each other, let us define a 'liking' function $$like : P \times P \rightarrow \{true, false\}$$ that specifies for each pair of persons $$(p_i, p_j) \in P$$ whether or not $$p_i$$ and $$p_j$$ like each other.
{% endindent %}

{% indent 0 %}
**Verbal:** That function $$like$$ is merely a notational device to assign 'true' or 'false' for pairs of persons in $$P$$ to indicate whether or not they like each other?
{% endindent %}

{% indent 1 %}
**Formal:**  Correct.  
{% endindent %}

{% indent 1 %}
**Formal:**  To formalize the hypothesized output we can define the set of (to be) invited  guests $$G$$ to be a subset of the liked people in $$P$$ (i.e., $$G \subseteq L$$) with the additional constraint that all pairs of people in $$G$$ like each other (i.e, $$like(p_i,p_j) = true$$ for each $$p_i,p_j \in G$$, or equivalently, $$\forall_{p_i,p_j \in G}like(p_i,p_j) = true$$).
{% endindent %}

{% indent 1 %}
**Formal:**  Combined, these formalization choices yield a computational-level model:
{% endindent %}

{% fproblem Selecting invitees (version 1) %}
A set of people $$P$$, some of whom the host likes ($$L \subseteq P$$) and some of whom the host dislikes ($$D \subseteq P$$), with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$ specifying for each pair of persons $$(p_i, p_j) \in P$$ whether or not they like each other.;;
A set of liked guests $$G \subseteq L$$ that all like each other (i.e.,  $$like(p_i,p_j) = true$$ for each $$p_i,p_j \in G$$).
{% endfproblem %}

{% indent 1 %}
**Formal:**  Now we know what the formal symbols mean, we can compress this description:
{% endindent %}

{% fproblem Selecting invitees (version 1, compressed) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
A subset $$G \subseteq L$$ such that $$\forall_{p_i,p_j \in G}like(p_i,p_j) = true$$.
{% endfproblem %}

{% indent 0 %}
**Verbal:** That looks great!
{% endindent %}

{% indent 1 %}
**Formal:** But does it correctly express what you had in mind?
{% endindent %}

{% indent 0 %}
**Verbal:** I think so yes.
{% endindent %}

{% indent 1 %}
**Formal:** We should rigorously check if it really captures what you have in mind. Let's explore this with an example. Say a host knows six people they all like, i.e., $$P=L=\{A,B,C,D,E,F\}$$. We can depict their like and dislike relations in a figure. The color of the lines indicate for each pair of persons $$p_i, p_j \in P$$ the value of $$like(p_i, p_j)$$:

fig/selecting-example1.pdf

Who would you predict the host would invite?
{% endindent %}

{% indent 0 %}
**Verbal:** Of course in that situation the host would invite $$\{C, D, E, F \}$$.
{% endindent %}

{% indent 1 %}
**Formal:**  Or they would invite $$\{A,B \}$$.
{% endindent %}

{% indent 0 %}
**Verbal:** I would not think so.
{% endindent %}

{% indent 1 %}
**Formal:**  But according to version 1 of the model, subset $$\{A,B \}$$ is as likely to be the selected invitees as $$\{C, D, E, F \}$$, or at least there is no reason why the host would select the one and not the other.
{% endindent %}

{% indent 0 %}
**Verbal:** But a party with only two guests is not much of a party!
{% endindent %}

{% indent 1 %}
**Formal:**  So there are more constraints on the subset of guests that you have in mind but did not tell me yet. The host wants to have at least 3 guests?
{% endindent %}

{% indent 0 %}
**Verbal:** As many as possible, the more the merrier.
{% endindent %}

{% indent 1 %}
**Formal:** Ok. Here an adjusted version of the model:
{% endindent %}

{% fproblem Selecting invitees (version 2) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
A subset $$G \subseteq L$$ such that $$\forall_{p_i,p_j \in G} like(p_i,p_j) = true$$ and the size of $$G$$ is maximized (i.e., there exists no $$G'$$ such that $$\forall_{p_i,p_j \in G'} like(p_i,p_j) = true$$ and $$|G'| > |G|$$).
{% endfproblem %}

{% indent 0 %}
**Verbal:** Yes, that is what I mean!
{% endindent %}

{% indent 1 %}
**Formal:** This model predicts that a host never invites people who they dislike, nor any pair of people who dislike each other. Is this really realistic?
{% endindent %}

{% indent 0 %}
**Verbal:** I hadn't thought about that. It's something I could empirically test. I will be right back.
{% endindent %}

*A few months pass, and...*

{% indent 0 %}
**Verbal:** You are right. In some situations, hosts invite people they do not like. I am not sure why.
{% endindent %}

{% indent 1 %}
**Formal:** Whatever the reason, the theory must then be adjusted. Let us remove the assumption that $$G \subseteq L$$. Then the adjusted theory is as follows:
{% endindent %}

{% fproblem Selecting invitees (version 3) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
A subset $$G \subseteq P$$ such that $$\forall_{p_i,p_j \in G}  like(p_i,p_j) = true$$ and $$|G|$$ is maximized.
{% endfproblem %}

{% indent 1 %}
**Formal:** But this does not seem right, because in this case the theory predicts that the host may end up with a party where everyone likes each other, but the host likes no-one.
{% endindent %}

{% indent 0 %}
**Verbal:** It seems that hosts invite at most a few people they do not like, and also sometimes some people who do not like each other, as long as sufficiently many people like each other.
{% endindent %}

{% indent 1 %}
**Formal:** I am not sure how to formalize that. Can you be more precise?
{% endindent %}

{% indent 0 %}
**Verbal:** For instance, if the situation is like this, with $$L = \{ B, C, D, E, G\}$$ and $$D = \{ A, F\}$$:

fig/selecting-example2.pdf

then hosts tend to invite $$\{ C, D, E, F, G \}$$.
{% endindent %}

{% indent 1 %}
**Formal:** What do you think is the reason?
{% endindent %}

{% indent 0 %}
**Verbal:** Possibly hosts tend to *minimize* the number of disliked guests while at the same time *maximizing* the *total* number of guests and *maximizing* the number of guests that like each other?
{% endindent %}

{% indent 1 %}
**Formal:** That makes sense intuitively. But formally it is underdefined. We now have three quantities that all need to be optimized (minimized or maximized), but there can be trade-offs such that it's impossible to always optimize them all at the same time.
{% endindent %}

{% indent 0 %}
**Verbal:** What do you propose?
{% endindent %}

{% indent 1 %}
**Formal:** Perhaps we can come up with different model versions that all more or less capture this intuition, but in different well-defined ways, and then compare them empirically?
{% endindent %}

{% indent 0 %}
**Verbal:** Great idea! Let's do that.
{% endindent %}

{% indent 1 %}
**Formal:** OK. Here are three qualitatively different ways to formalize the idea:
{% endindent %}

{% fproblem Selecting invitees (version 4) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|G\cap D| \leq k$$ and $$|X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G~|~like(p_i,p_j) = true \wedge i\neq j \}$$).
{% endfproblem %}

{% fproblem Selecting invitees (version 5) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
$$G \subseteq P$$ such that $$|G\cap L| + |X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G\}~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}

{% fproblem Selecting invitees (version 6) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|Y| \leq k$$ and  $$|G\cap L|+|G|$$ is maximized (where $$Y = \{p_i,p_j \in G\}~|~like(p_i,p_j) = false \wedge i\neq j \}$$).
{% endfproblem %}

{% indent 1 %}
**Formal:** There may be other possibilities.\footnote{We leave this as an exercise for the reader to explore further.} I have created simulation code for these versions, available online here https://computationalcognitivescience.github.io/socialpsychtutorial/, so you can explore their empirical implications via computer simulation.
{% endindent %}


We end Dialogue 1 here. At this juncture you can either go online and check out the code that *Formal* made for *Verbal* and run some simulations of your own; or, alternatively, you can continue with reading Dialogue 2.

## Dialogue 2: Formalizing inviting guests, again

In this Dialogue 2, *Formal* again formalizes *Verbal*'s intuitions about how a hosts selects invitees for a party, but after initially starting off the same way as in Dialogue 1, *Formal*'s questions quickly lead the dialogue in a different direction. You can think of this dialogue as taking place in a different possible parallel universe from the one in which Dialogue 1 took place.

{% indent 0 %}
 I'd like to explain how a host decides whom to invite to a party.  
{% endindent %}

{% indent 1 %}
**Formal:** Why would the host not invite everybody?
{% endindent %}

{% indent 0 %}
**Verbal:** They may like some people but dislike others.
{% endindent %}

{% indent 1 %}
**Formal:** Is liking and disliking all or none, or does it come in degrees?
{% endindent %}

{% indent 0 %}
**Verbal:** I guess it comes in degrees.
{% endindent %}

{% indent 1 %}
**Formal:** Then the host invites everyone they like enough?
{% endindent %}

{% indent 0 %}
**Verbal:** Well, it is also important that the guests like each other enough.
{% endindent %}

{% indent 1 %}
**Formal:** OK. So both the host and everyone at the party must like everyone at the party enough?
{% endindent %}

{% indent 0 %}
**Verbal:** Yes, that sounds right. Can we formalize that idea?
{% endindent %}

{% indent 1 %}
**Formal:**  Let's see. We can start by defining the initial state (input) of the decision process as consisting of the set of people that the host chooses from, $$P$$, and define a function $$like_1: P \rightarrow \mathbb{Z}$$ that assigns a number for each person $$p \in P$$ indicating how much the host (dis)likes that person. Negative numbers indicate degrees of disliking and positive numbers indicate degrees of liking.  
{% endindent %}

{% indent 1 %}
**Formal:**  Can a host also sometimes not like nor dislike a person?  I mean, can they be neutral about a person?
{% endindent %}

{% indent 0 %}
**Verbal:** It may be rare, but it seems possible.
{% endindent %}

{% indent 1 %}
**Formal:**  Ok, then we keep the value '0' as an option for the *like*$$_1$$ function.   
{% endindent %}

{% indent 1 %}
**Formal:**  To formalize that different pairs of people in $$P$$ can also like or dislike each other to various degrees, let us define a function $$like_2 : P \times P \rightarrow \mathbb{Z}$$ that specifies for each pair of persons $$(p_i, p_j) \in P$$ how much they like each other, again including '0' as an option.
{% endindent %}

{% indent 1 %}
**Formal:**  To formalize the hypothesized output we define a threshold number $$k$$ for 'liking enough'. Shall I set $$k \geq 0$$ or $$k > 0$$?
{% endindent %}

{% indent 0 %}
**Verbal:** I suppose, one minimally likes a person if they invite them to ones party? But guests may not be too bothered if there are people around who they feel neutral about.
{% endindent %}

{% indent 1 %}
**Formal:**  If that is the case, then let's define a separate threshold for the host $$k_h > 0$$ and a threshold for the guests $$k_g \geq 0$$.
{% endindent %}

{% indent 0 %}
**Verbal:** Sounds good.
{% endindent %}

{% indent 1 %}
**Formal:**  Combined, these formalization choices yield a computational-level model:
{% endindent %}

{% fproblem Selecting invitees (version 7) %}
A set $$P$$, two functions $$like_1: P \rightarrow \mathbb{Z}$$ and $$like_2 : P \times P \rightarrow \mathbb{Z}$$, and two threshold values $$k_h > 0$$ and $$k_g \geq 0$$.;;
A subset $$G \subseteq P$$ such that $$\forall_{p \in G}~like_1(p) \geq k_h$$ and $$\forall_{p_i,p_j \in G}~like_2(p_i, p_j) \geq k_g$$.
{% endfproblem %}

We end Dialogue 2 here. At this juncture you could choose to compare how this dialogue ended with where Dialogue 1 ended (e.g., ask yourself 'Which of the models best matches my own intuitions? How would I change or adapt the models?'); or, alternatively, you can continue reading the next dialogue.

## Dialogue 3: Formalizing inviting guests, one last time

In this Dialogue 3, *Formal* again formalizes *Verbal*'s intuitions about how a hosts selects invitees for a party, but after initially starting off the same way as in Dialogues 1 and 2, *Verbal*'s different intuitions quickly lead the dialogue in a different direction. Again, you can think of this dialogue as taking place in a different possible parallel universe from the one in which Dialogues 1 and 2 took place.

{% indent 0 %}
**Verbal:** I'd like to explain how a host decides whom to invite to a party.  
{% endindent %}

{% indent 1 %}
**Formal:** Why would the host not invite everybody?
{% endindent %}

{% indent 0 %}
**Verbal:** They may have limited space and budget available.
{% endindent %}

{% indent 1 %}
**Formal:** Then they invite the number of people that fit the space and the budget?
{% endindent %}

{% indent 0 %}
**Verbal:** Well, yes. But I think there may be more to it. A host wants a party to be fun. So they probably invite a mix of people that make for a maximally fun party.
{% endindent %}

{% indent 1 %}
**Formal:**
What type of mix makes for maximal fun?
{% endindent %}

{% indent 0 %}
**Verbal:** Probably lots of fun people, with great synergies. That boosts the atmosphere. And as few people as possible who do not interact well with others. You know? The right *mix*.
{% endindent %}

{% indent 1 %}
**Formal:**
Not sure if I get it yet. But perhaps we can start by trying to model those interactions you hint at.
{% endindent %}

{% indent 0 %}
**Verbal:** Great!
{% endindent %}

{% indent 1 %}
**Formal:** Let's see. Let's define a function $$fun: P \rightarrow \mathbb{Z}$$ that assigns a number for each person $$p \in P$$ indicating how much *fun* they are individually, and a second function $$synergy: P \times P \rightarrow \mathbb{Z}$$ that assigns a number for each pair of persons $$p_i, p_j \in P$$ indicating how much their interaction adds to, or subtracts from the overall *synergy*.
{% endindent %}

{% indent 0 %}
**Verbal:** So $$synergy(p_i, p_j) = 0$$ means no synergy between $$p_i$$ and $$p_j$$?
{% endindent %}

{% indent 1 %}
**Formal:** Indeed. And if $$synergy(p_i, p_j) > 0$$ or $$synergy(p_i, p_j) < 0$$ then their combined presence adds additional fun or decreases overall fun, respectively. Does this make sense?
{% endindent %}

{% indent 0 %}
**Verbal:** Not sure where you are going with this.
{% endindent %}

{% indent 1 %}
**Formal:** Well, given these assumptions, we could formalize the overall party fun as follows:

{% endindent %}

$$
fun(G) = \sum_{p \in G}fun(p) + \sum_{p_i,p_j \in G} synergy(p_i, p_j)
$$

{% indent 0 %}
**Verbal:** Makes sense. But this only allows for synergies between pairs of people, while sometimes synergies are really a function of small subgroups of people who interact well with each other.
{% endindent %}

{% indent 1 %}
**Formal:**  No problem, we can generalize the formalization to include that idea:
{% endindent %}

$$
fun(G) = \sum_{p \in G}fun(p) + \sum_{G' \subseteq G} synergy(G')
$$

{% indent 1 %}
**Formal:**  Better in line with your thoughts?
{% endindent %}

{% indent 0 %}
**Verbal:** Yes!
{% endindent %}

{% indent 1 %}
**Formal:**  Good. Let us use $$2^P = P \times P \times ... \times P$$ to denote the *powerset* of $$P$$, i.e., all possible subsets of $$P$$. Then we can finalize the model as follows:
{% endindent %}

{% fproblem Selecting invitees (version 8) %}
A set $$P$$, two functions $$fun: P \rightarrow \mathbb{Z}$$ and $$synergy: 2^P \rightarrow \mathbb{Z}$$.;;
A subset $$G \subseteq P$$ such that $$fun(G) = \sum_{p \in G}fun(p) + \sum_{G' \subseteq G} synergy(G')$$ is maximized.
{% endfproblem %}

{% indent 0 %}
**Verbal:** Wait! You forgot about the most important constraints.
The host has limited space and budget.
{% endindent %}

{% indent 1 %}
**Formal:**  Right! I forgot about those. Can I assume that there is a fixed cost per person, or are some people more expensive to host than others?
{% endindent %}

{% indent 0 %}
**Verbal:** Let's assume for simplicity sake that cost is the same for everyone.
{% endindent %}


{% indent 1 %}
**Formal:**  Then here is my proposal:
{% endindent %}

{% fproblem Selecting invitees (version 9) %}
A set $$P$$ and two functions $$fun: P \rightarrow \mathbb{Z}$$, $$synergy: 2^P \rightarrow \mathbb{Z}$$. Further, three constants: the cost per person $$c \in \mathbb{R}$$, a space constraint $$S \in \mathbb{N}$$ and a budget constraint $$B \in \mathbb{R}$$.;;
A subset $$G \subseteq P$$ such that $$|G| \leq S$$ and  $$c|G|  \leq B$$ and $$fun(G) = \sum_{p \in G}fun(p) + \sum_{G' \subseteq G} synergy(G')$$ is maximized.
{% endfproblem %}

{% indent 1 %}
**Formal:**  If you would like to extend this model to include variable costs, then that is possible. For instance, like this:
{% endindent %}

{% fproblem Selecting invitees (version 10) %}
A set $$P$$, three functions $$fun: P \rightarrow \mathbb{Z}$$, $$synergy: 2^P \rightarrow \mathbb{Z}$$, and $$c: P \rightarrow \mathbb{R}$$. Further, a space constraint $$S \in \mathbb{N}$$ and a budget constraint $$B \in \mathbb{R}$$.;;
A subset $$G \subseteq P$$ such that $$|G| \leq S$$ and  $$\sum_{p \in G} c(p) \leq B$$ and $$fun(G) = \sum_{p \in G}fun(p) + \sum_{G' \subseteq G} synergy(G')$$ is maximized.
{% endfproblem %}

We end Dialogue 3 here. Again, we invite you to compare how this dialogue ended with how Dialogues 1 and 2 ended (e.g., ask yourself, 'Could the constraints of cost also be adopted in the earlier versions 1-7?' 'What other constraints may I want to build in?' 'How do the contraints affect which selections a host makes under different circumstances?').

## Dialogue 4: Formalizing group formation

Dialogues 1-3 illustrated three distinct ways in which *Verbal* and *Formal* could settle on formalizations of the social decision-making capacity of a host deciding whom to invite to their party. This social decision-making capacity is a cognitive capacity of an individual, i.e., the host (their brain/mind) is doing the deciding. Not all social psychological capacities need to be like this. Some capacities emerge from the *interaction between* individuals, as we will see in the next and last dialogue.

{% indent 0 %}
**Verbal:**  Can we explain how people naturally divide into smaller subgroups at a party?
{% endindent %}

{% indent 1 %}
**Formal:** Sure. You think there is systematicity to it?
{% endindent %}

{% indent 0 %}
**Verbal:** Clearly. People tend to want to be with people who are like them.
{% endindent %}

{% indent 1 %}
**Formal:** You mean, to be with people they like?
{% endindent %}

{% indent 0 %}
**Verbal:**  Well, the question is what makes people like each other. Previous research suggests people like being with people who are similar to them; have a lot in common, such as hobbies, preferences, political beliefs, etc.
{% endindent %}

{% indent 1 %}
**Formal:** So similarity between people may drive how they form groups?
{% endindent %}

{% indent 0 %}
**Verbal:**  Indeed. Can we model this?
{% endindent %}

{% indent 1 %}
**Formal:** It is easy to define a similary measure $$sim : P \times P \rightarrow \mathbb{R}$$ for every pair of persons in $$P$$. But how does it factor into the grouping process? Any intuitions?
{% endindent %}

{% indent 0 %}
**Verbal:** I think guests at a party tend to self-organize into subgroups with high ingroup similarity.
{% endindent %}

{% indent 1 %}
**Formal:** By 'high' do you mean 'maximum average ingroup similarity', or  'satisfactory high levels of ingroup similarity'?
{% endindent %}

{% indent 0 %}
**Verbal:** Good question. Either could be the case. I honestly don't know.
{% endindent %}

{% indent 1 %}
**Formal:** How about we formalize both ideas, and then test empirically which one accounts best for natural grouping behaviour?
{% endindent %}

{% indent 0 %}
**Verbal:** Sounds good!
{% endindent %}

{% indent 1 %}
**Formal:** Here you go.
{% endindent %}

{% fproblem Party subgrouping (version 1) %}
A set of guests $$G$$ and a function $$sim: G \times G \rightarrow \mathbb{R}$$.;;
A partition of $$G$$ into non-overlapping subsets $$G_1, G_2, ..., G_k$$ that maximizes average ingroup similarity:
$$$\frac{1}{k}\sum_{i=1,2,\dots k}sim(G_i) $$$

Where ingroup similarity for subset $$G_i$$ is defined as mean pair-wise similarity:
$$$sim(G_i)=\frac{1}{|G_i|}\sum_{g_i, g_j \in G_i} sim(g_i, g_j)$$$
{% endfproblem %}

{% fproblem Party subgrouping (version 2) %}
A set of guests $$G$$, a function $$sim: G \times G \rightarrow \mathbb{R}$$, and threshold of satisfactory similarity $$s \in \mathbb{R}$$.;;
A partition of $$G$$ into non-overlapping subsets $$G_1, G_2, ..., G_k$$ where each partition has satisfactory ingroup similarity:
$$$\forall_{i=1,2,\dots k}\left[sim(G_i) \geq s\right]$$$

Where ingroup similarity for subset $$G_i$$ is defined as mean pair-wise similarity:
$$$sim(G_i)=\frac{1}{|G_i|}\sum_{g_i, g_j \in G_i} sim(g_i, g_j)$$$
{% endfproblem %}

{% indent 0 %}
**Verbal:** OK, I see how the two models formalize the intuitive idea of 'maximum ingroup similarity' and  'satisfactory ingroup similarity', thanks.
{% endindent %}

{% indent 1 %}
**Formal:** Be aware, there may be other possibilities! These two ways of formalizing were just two ways I came up with.
{% endindent %}

{% indent 0 %}
**Verbal:** Understood. But for now I think they are good working hypotheses.
{% endindent %}

{% indent 0 %}
**Verbal:**  One last question. The models look very similar. How can I test which of them best explains patterns of group formation that I observe in my studies?
{% endindent %}

{% indent 1 %}
**Formal:**
You can run simulations for these models for different parameter settings and compare the output to the subgroup formations you've observed in your empirical studies. I have created code for the simulations here https://computationalcognitivescience.github.io/socialpsychtutorial/. Check it out!
{% endindent %}
