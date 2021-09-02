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
An example is choosing what career to pursue. For instance, would you rather be an artist, doctor, politician, accountant, scientist or technician?
{% endhidden %}
{% endquestion %}

{% marginnote 'mn-id-whatever' '“This is the essence of intuitive heuristics: when faced with a difficult question, we often answer an easier one instead, usually without noticing the substitution.” – Daniel Kahneman (2017).' %} To make our lives easier, in this chapter we focus on decision making under *certainty*. That is, we consider choice situations where options are well-defined and the value of options is certain. As we will see, this is usually merely a convenient fiction. But it makes learning to model easier, and allows us to postpone the use of probability theory to the Advanced sections.

We will not review existing theoretical models of decision making. Instead, we'll be throwing you into the deep waters: We will present you with a capacity to explain and invite you to articulate a verbal theory and try to formalize these ideas. You may think you are unable to and that you first need to learn how others made models. But trust us. You can do it! The goal is specifically not to mimic existing models of decision making, but rather to develop your own. The purpose is to practice using the theoretician's tools. Think of this and the next chapters as your sandbox for sculpting some sandcastles for fun, and to train your sculpting skills.

{% marginfigure 'mf-id-whatever' '/assets/img/vitrine.png' 'So many flavours! How do we choose a subset?' %} The example decision making capacity that we will use for our practices is called *subset choice*. This is the capacity for chosing a *subset* of options from a larger set. Take for example choosing Italian ice cream. The set of options is the full set of flavours displayed in the vitrine. Depending on your budget and appetite you may choose one, two, three, or---if you are *really* craving---more scoops. For instance, you may choose strawberry, lemon, and mango. Or, alternatively, you may choose chocolate and hazelnut.

{% question %}
Can you think of more everyday situations where the choice options are subsets of available options?
{% hidden Hint? %}

When we select toppings on a pizza.

{% question %}
Can you think of another one?
{% hidden Hint? %}

When we invite people to a party

{% question %}
Can you think of another one?
{% hidden Hint? %}

When a doctor prescribes combinations of medicine.

{% question %}
Can you think of another one?
{% hidden Hint? %}
When you shop for groceries.

{% question %}
Can you think of another one?
{% hidden Hint? %}
When a manager selects members for a committee or team.

{% question %}
Can you think of another one?
{% hidden Hint? %}
When you select ingredients for cooking a meal.

{% question %}
Can you think of another one?
{% endquestion %}

{% endhidden %}
{% endquestion %}

{% endhidden %}
{% endquestion %}

{% endhidden %}
{% endquestion %}

{% endhidden %}
{% endquestion %}

{% endhidden %}
{% endquestion %}

{% endhidden %}
{% endquestion %}


If you went down the rabbit hole of that question, you will have discovered that there are many everyday situations that correspond to choosing subsets. Let's pick one or more of those situations and make a computational-level explanation of the capacity for choosing subsets of that type.

{% question %}
Make a computational level theory of subset choice that incorporates the assumption that people choose options with maximum or satisfactory subjective value (a.k.a. utility).
{% endquestion %}

{% hidden Hint? %}
Think about how you want to approach this exercise. You can, for instance, look back at Chapter 2 to refresh your knowledge of what is a computational level theory of a capacity and how you can write it down. You can also use the mathematics from Chapter 3 where useful. Our advice is to first define an informal specification of the capacity's input domain and output domain, and intuitively articulate the mapping, and only then try to formalize your own intuitions.
{% endhidden %}


{% stopandthink %}
It is important that you perform the exercise in Question 4.4 before continuing in this chapter. You will likely get stuck somewhere. This is part of the learning experience. One can only learn to model by trying, just like one can only learn to ride a bicycle by trying, failing, and trying again. Write down for yourself where you get stuck and why.
{% endstopandthink %}

After you have tried to answer Question 4.4 to the best of your abilities, you can continue with the next section.

# A tutorial by dialogue
{% marginfigure 'mf-id-whatever' '/assets/img/party.jpg' 'How to decide whom to invite to your party?' %}
In this section we illustrate theoretical modeling by dialogues between the two fictive characters, *Verbal* and *Formal*.{% sidenote 'mf-id-whatever' 'These dialogues are adapted from [van Rooij & Blokpoel (2020)](https://econtent.hogrefe.com/doi/10.1027/1864-9335/a000428).' %} We use as subset choice situation how a host invites people to a party. We present multiple dialogues. *Verbal*'s intuitions and *Formal*'s questions vary from dialogue to dialogue, and hence the developed theoretical models differ as well.

## Dialogue 1: Formalizing inviting guests

{% indent 0 %}
**Verbal:** I'd like to explain how a host decides whom to invite to a party.  
{% endindent %}

{% indent 4 %}
**Formal:** Why would the host not invite everybody?
{% endindent %}

{% indent 0 %}
**Verbal:** They may like some people but dislike others.
{% endindent %}

{% indent 4 %}
**Formal:**  Then the host invites everybody they like?
{% endindent %}

{% indent 0 %}
**Verbal:** Not all people get along. If people get into an argument that can spoil a party.
{% endindent %}

{% indent 4 %}
**Formal:**   I see. So a host may choose to invite people they like *and* that all get along.
{% endindent %}

{% indent 0 %}
**Verbal:** Yes, that sounds right. I think that's what a host will tend to do.  Can we formalize this idea?
{% endindent %}

{% indent 4 %}
**Formal:**  Let's see. We can start by defining the initial state (input) of the decision process as consisting of the set of people that the host chooses from. Let us denote this set as $$P = \{p_1, p_2, ..., p_n\}$$. Further, let's define two subsets, $$L \subseteq P$$ and $$D \subseteq P$$, denoting the subsets of people our host likes and dislikes respectively.
{% endindent %}

{% indent 4 %}
**Formal:**  I assume you had in mind that a person cannot be both liked *and* disliked by the host, and that any given person is either liked or disliked by the host.
{% endindent %}

{% indent 0 %}
**Verbal:** Let's indeed assume that for now.
{% endindent %}

{% indent 4 %}
**Formal:**  Then $$L$$ and $$D$$ form a *partition* of $$P$$. To be precise,  $$L \cap D = \emptyset$$ (a person cannot be both liked and disliked) and $$L \cup D = P$$ (any person is either liked or disliked).
{% endindent %}

{% indent 4 %}
**Formal:**  To formalize that different pairs of people in $$P$$ can also like or dislike each other, let us define a 'liking' function $$like : P \times P \rightarrow \{true, false\}$$ that specifies for each pair of persons $$(p_i, p_j) \in P$$ whether or not $$p_i$$ and $$p_j$$ like each other.
{% endindent %}

{% indent 0 %}
**Verbal:** That function $$like$$ is merely a notational device to assign 'true' or 'false' for pairs of persons in $$P$$ to indicate whether or not they like each other?
{% endindent %}

{% indent 4 %}
**Formal:**  Correct.  
{% endindent %}

{% indent 4 %}
**Formal:**  To formalize the hypothesized output we can define the set of (to be) invited  guests $$G$$ to be a subset of the liked people in $$P$$ (i.e., $$G \subseteq L$$) with the additional constraint that all pairs of people in $$G$$ like each other (i.e, $$like(p_i,p_j) = true$$ for each $$p_i,p_j \in G$$, or equivalently, $$\forall_{p_i,p_j \in G}like(p_i,p_j) = true$$).
{% endindent %}

{% indent 4 %}
**Formal:**  Combined, these formalization choices yield a computational-level model:
{% endindent %}

{% fproblem Selecting invitees (version 1) %}
A set of people $$P$$, some of whom the host likes ($$L \subseteq P$$) and some of whom the host dislikes ($$D \subseteq P$$), with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$ specifying for each pair of persons $$(p_i, p_j) \in P$$ whether or not they like each other.;;
A set of liked guests $$G \subseteq L$$ that all like each other (i.e.,  $$like(p_i,p_j) = true$$ for each $$p_i,p_j \in G$$).
{% endfproblem %}

{% indent 4 %}
**Formal:**  Now we know what the formal symbols mean, we can compress this description:
{% endindent %}

{% fproblem Selecting invitees (version 1, compressed) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
A subset $$G \subseteq L$$ such that $$\forall_{p_i,p_j \in G}like(p_i,p_j) = true$$.
{% endfproblem %}

{% indent 0 %}
**Verbal:** That looks great!
{% endindent %}

{% indent 4 %}
**Formal:** But does it correctly express what you had in mind?
{% endindent %}

{% indent 0 %}
**Verbal:** I think so yes.
{% endindent %}

{% indent 4 %}
**Formal:** We should rigorously check if it really captures what you have in mind. Let's explore this with an example. Say a host knows six people they all like, i.e., $$P=L=\{A,B,C,D,E,F\}$$. We can depict their like and dislike relations in a figure. The color of the lines indicate for each pair of persons $$p_i, p_j \in P$$ the value of $$like(p_i, p_j)$$:

{% maincolumn 'assets/img/selecting-example1.png' '' %}

Who would you predict the host would invite?
{% endindent %}

{% indent 0 %}
**Verbal:** Of course in that situation the host would invite $$\{C, D, E, F \}$$.
{% endindent %}

{% indent 4 %}
**Formal:**  Or they would invite $$\{A,B \}$$.
{% endindent %}

{% indent 0 %}
**Verbal:** I would not think so.
{% endindent %}

{% indent 4 %}
**Formal:**  But according to version 1 of the model, subset $$\{A,B \}$$ is as likely to be the selected invitees as $$\{C, D, E, F \}$$, or at least there is no reason why the host would select the one and not the other.
{% endindent %}

{% indent 0 %}
**Verbal:** But a party with only two guests is not much of a party!
{% endindent %}

{% indent 4 %}
**Formal:**  So there are more constraints on the subset of guests that you have in mind but did not tell me yet. The host wants to have at least 3 guests?
{% endindent %}

{% indent 0 %}
**Verbal:** As many as possible, the more the merrier.
{% endindent %}

{% indent 4 %}
**Formal:** Ok. Here an adjusted version of the model:
{% endindent %}

{% fproblem Selecting invitees (version 2) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
A subset $$G \subseteq L$$ such that $$\forall_{p_i,p_j \in G} like(p_i,p_j) = true$$ and the size of $$G$$ is maximized (i.e., there exists no $$G'$$ such that $$\forall_{p_i,p_j \in G'} like(p_i,p_j) = true$$ and $$|G'| > |G|$$).
{% endfproblem %}

{% indent 0 %}
**Verbal:** Yes, that is what I mean!
{% endindent %}

{% indent 4 %}
**Formal:** This model predicts that a host never invites people who they dislike, nor any pair of people who dislike each other. Is this really realistic?
{% endindent %}

{% indent 0 %}
**Verbal:** I hadn't thought about that. It's something I could empirically test. I will be right back.
{% endindent %}

*A few months pass, and...*

{% indent 0 %}
**Verbal:** You are right. In some situations, hosts invite people they do not like. I am not sure why.
{% endindent %}

{% indent 4 %}
**Formal:** Whatever the reason, the theory must then be adjusted. Let us remove the assumption that $$G \subseteq L$$. Then the adjusted theory is as follows:
{% endindent %}

{% fproblem Selecting invitees (version 3) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
A subset $$G \subseteq P$$ such that $$\forall_{p_i,p_j \in G}  like(p_i,p_j) = true$$ and $$|G|$$ is maximized.
{% endfproblem %}

{% indent 4 %}
**Formal:** But this does not seem right, because in this case the theory predicts that the host may end up with a party where everyone likes each other, but the host likes no-one.
{% endindent %}

{% indent 0 %}
**Verbal:** It seems that hosts invite at most a few people they do not like, and also sometimes some people who do not like each other, as long as sufficiently many people like each other.
{% endindent %}

{% indent 4 %}
**Formal:** I am not sure how to formalize that. Can you be more precise?
{% endindent %}

{% indent 0 %}
**Verbal:** For instance, if the situation is like this, with $$L = \{ B, C, D, E, G\}$$ and $$D = \{ A, F\}$$:

{% maincolumn 'assets/img/selecting-example2.png' '' %}

then hosts tend to invite $$\{ C, D, E, F, G \}$$.
{% endindent %}

{% indent 4 %}
**Formal:** What do you think is the reason?
{% endindent %}

{% indent 0 %}
**Verbal:** Possibly hosts tend to *minimize* the number of disliked guests while at the same time *maximizing* the *total* number of guests and *maximizing* the number of guests that like each other?
{% endindent %}

{% indent 4 %}
**Formal:** That makes sense intuitively. But formally it is underdefined. We now have three quantities that all need to be optimized (minimized or maximized), but there can be trade-offs such that it's impossible to always optimize them all at the same time.
{% endindent %}

{% indent 0 %}
**Verbal:** What do you propose?
{% endindent %}

{% indent 4 %}
**Formal:** Perhaps we can come up with different model versions that all more or less capture this intuition, but in different well-defined ways, and then compare them empirically?
{% endindent %}

{% indent 0 %}
**Verbal:** Great idea! Let's do that.
{% endindent %}

{% indent 4 %}
**Formal:** OK. Here are three qualitatively different ways to formalize the idea:
{% endindent %}

{% fproblem Selecting invitees (version 4) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|G\cap D| \leq k$$ and $$|X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}

{% fproblem Selecting invitees (version 5) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, and a function $$like: P \times P \rightarrow \{true, false\}$$.;;
$$G \subseteq P$$ such that $$|G\cap L| + |X| + |G|$$ is maximized (where $$X = \{p_i,p_j \in G\}~|~like(p_i,p_j) = true \wedge i\neq j\}$$).
{% endfproblem %}

{% fproblem Selecting invitees (version 6) %}
A set $$P$$, subsets $$L \subseteq P$$ and $$D \subseteq P$$ with $$L \cap D = \emptyset$$ and $$L \cup D = P$$, a function $$like: P \times P \rightarrow \{true, false\}$$, and a threshold value $$k$$.;;
$$G \subseteq P$$ such that $$|Y| \leq k$$ and  $$|G\cap L|+|G|$$ is maximized (where $$Y = \{p_i,p_j \in G\}~|~like(p_i,p_j) = false \wedge i\neq j \}$$).
{% endfproblem %}

{% indent 4 %}
**Formal:** There may be other possibilities.{% sidenote 'sn-id-whatever' 'We leave this as an exercise for the reader to explore further.'%} I have created simulation code for these versions, available online [here](/lovelace/part_iii/sim_subset_choice), so you can explore their empirical implications via computer simulation.
{% endindent %}


We end Dialogue 1 here. At this juncture you can either go [here]() and check out the code that *Formal* made for *Verbal* and run some simulations of your own; or, alternatively, you can continue with reading Dialogue 2.

## Dialogue 2: Formalizing inviting guests, again

In this Dialogue 2, *Formal* again formalizes *Verbal*'s intuitions about how a hosts selects invitees for a party, but after initially starting off the same way as in Dialogue 1, *Formal*'s questions quickly lead the dialogue in a different direction. You can think of this dialogue as taking place in a different possible parallel universe from the one in which Dialogue 1 took place.

{% indent 0 %}
 I'd like to explain how a host decides whom to invite to a party.  
{% endindent %}

{% indent 4 %}
**Formal:** Why would the host not invite everybody?
{% endindent %}

{% indent 0 %}
**Verbal:** They may like some people but dislike others.
{% endindent %}

{% indent 4 %}
**Formal:** Is liking and disliking all or none, or does it come in degrees?
{% endindent %}

{% indent 0 %}
**Verbal:** I guess it comes in degrees.
{% endindent %}

{% indent 4 %}
**Formal:** Then the host invites everyone they like enough?
{% endindent %}

{% indent 0 %}
**Verbal:** Well, it is also important that the guests like each other enough.
{% endindent %}

{% indent 4 %}
**Formal:** OK. So both the host and everyone at the party must like everyone at the party enough?
{% endindent %}

{% indent 0 %}
**Verbal:** Yes, that sounds right. Can we formalize that idea?
{% endindent %}

{% indent 4 %}
**Formal:**  Let's see. We can start by defining the initial state (input) of the decision process as consisting of the set of people that the host chooses from, $$P$$, and define a function $$like_1: P \rightarrow \mathbb{Z}$$ that assigns a number for each person $$p \in P$$ indicating how much the host (dis)likes that person. Negative numbers indicate degrees of disliking and positive numbers indicate degrees of liking.  
{% endindent %}

{% indent 4 %}
**Formal:**  Can a host also sometimes not like nor dislike a person?  I mean, can they be neutral about a person?
{% endindent %}

{% indent 0 %}
**Verbal:** It may be rare, but it seems possible.
{% endindent %}

{% indent 4 %}
**Formal:**  Ok, then we keep the value '0' as an option for the *like*$$_1$$ function.   
{% endindent %}

{% indent 4 %}
**Formal:**  To formalize that different pairs of people in $$P$$ can also like or dislike each other to various degrees, let us define a function $$like_2 : P \times P \rightarrow \mathbb{Z}$$ that specifies for each pair of persons $$(p_i, p_j) \in P$$ how much they like each other, again including '0' as an option.
{% endindent %}

{% indent 4 %}
**Formal:**  To formalize the hypothesized output we define a threshold number $$k$$ for 'liking enough'. Shall I set $$k \geq 0$$ or $$k > 0$$?
{% endindent %}

{% indent 0 %}
**Verbal:** I suppose, one minimally likes a person if they invite them to ones party? But guests may not be too bothered if there are people around who they feel neutral about.
{% endindent %}

{% indent 4 %}
**Formal:**  If that is the case, then let's define a separate threshold for the host $$k_h > 0$$ and a threshold for the guests $$k_g \geq 0$$.
{% endindent %}

{% indent 0 %}
**Verbal:** Sounds good.
{% endindent %}

{% indent 4 %}
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

{% indent 4 %}
**Formal:** Why would the host not invite everybody?
{% endindent %}

{% indent 0 %}
**Verbal:** They may have limited space and budget available.
{% endindent %}

{% indent 4 %}
**Formal:** Then they invite the number of people that fit the space and the budget?
{% endindent %}

{% indent 0 %}
**Verbal:** Well, yes. But I think there may be more to it. A host wants a party to be fun. So they probably invite a mix of people that make for a maximally fun party.
{% endindent %}

{% indent 4 %}
**Formal:**
What type of mix makes for maximal fun?
{% endindent %}

{% indent 0 %}
**Verbal:** Probably lots of fun people, with great synergies. That boosts the atmosphere. And as few people as possible who do not interact well with others. You know? The right *mix*.
{% endindent %}

{% indent 4 %}
**Formal:**
Not sure if I get it yet. But perhaps we can start by trying to model those interactions you hint at.
{% endindent %}

{% indent 0 %}
**Verbal:** Great!
{% endindent %}

{% indent 4 %}
**Formal:** Let's see. Let's define a function $$fun: P \rightarrow \mathbb{Z}$$ that assigns a number for each person $$p \in P$$ indicating how much *fun* they are individually, and a second function $$synergy: P \times P \rightarrow \mathbb{Z}$$ that assigns a number for each pair of persons $$p_i, p_j \in P$$ indicating how much their interaction adds to, or subtracts from the overall *synergy*.
{% endindent %}

{% indent 0 %}
**Verbal:** So $$synergy(p_i, p_j) = 0$$ means no synergy between $$p_i$$ and $$p_j$$?
{% endindent %}

{% indent 4 %}
**Formal:** Indeed. And if $$synergy(p_i, p_j) > 0$$ or $$synergy(p_i, p_j) < 0$$ then their combined presence adds additional fun or decreases overall fun, respectively. Does this make sense?
{% endindent %}

{% indent 0 %}
**Verbal:** Not sure where you are going with this.
{% endindent %}

{% indent 4 %}
**Formal:** Well, given these assumptions, we could formalize the overall party fun as follows:

{% endindent %}

$$
fun(G) = \sum_{p \in G}fun(p) + \sum_{p_i,p_j \in G} synergy(p_i, p_j)
$$

{% indent 0 %}
**Verbal:** Makes sense. But this only allows for synergies between pairs of people, while sometimes synergies are really a function of small subgroups of people who interact well with each other.
{% endindent %}

{% indent 4 %}
**Formal:**  No problem, we can generalize the formalization to include that idea:
{% endindent %}

$$
fun(G) = \sum_{p \in G}fun(p) + \sum_{G' \subseteq G} synergy(G')
$$

{% indent 4 %}
**Formal:**  Better in line with your thoughts?
{% endindent %}

{% indent 0 %}
**Verbal:** Yes!
{% endindent %}

{% indent 4 %}
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

{% indent 4 %}
**Formal:**  Right! I forgot about those. Can I assume that there is a fixed cost per person, or are some people more expensive to host than others?
{% endindent %}

{% indent 0 %}
**Verbal:** Let's assume for simplicity sake that cost is the same for everyone.
{% endindent %}


{% indent 4 %}
**Formal:**  Then here is my proposal:
{% endindent %}

{% fproblem Selecting invitees (version 9) %}
A set $$P$$ and two functions $$fun: P \rightarrow \mathbb{Z}$$, $$synergy: 2^P \rightarrow \mathbb{Z}$$. Further, three constants: the cost per person $$c \in \mathbb{R}$$, a space constraint $$S \in \mathbb{N}$$ and a budget constraint $$B \in \mathbb{R}$$.;;
A subset $$G \subseteq P$$ such that $$|G| \leq S$$ and  $$c|G|  \leq B$$ and $$fun(G) = \sum_{p \in G}fun(p) + \sum_{G' \subseteq G} synergy(G')$$ is maximized.
{% endfproblem %}

{% indent 4 %}
**Formal:**  If you would like to extend this model to include variable costs, then that is possible. For instance, like this:
{% endindent %}

{% fproblem Selecting invitees (version 10) %}
A set $$P$$, three functions $$fun: P \rightarrow \mathbb{Z}$$, $$synergy: 2^P \rightarrow \mathbb{Z}$$, and $$c: P \rightarrow \mathbb{R}$$. Further, a space constraint $$S \in \mathbb{N}$$ and a budget constraint $$B \in \mathbb{R}$$.;;
A subset $$G \subseteq P$$ such that $$|G| \leq S$$ and  $$\sum_{p \in G} c(p) \leq B$$ and $$fun(G) = \sum_{p \in G}fun(p) + \sum_{G' \subseteq G} synergy(G')$$ is maximized.
{% endfproblem %}

We end Dialogue 3 here. Again, we invite you to compare how this dialogue ended with how Dialogues 1 and 2 ended (e.g., ask yourself, 'Could the constraints of cost also be adopted in the earlier versions 1-7?' 'What other constraints may I want to build in?' 'How do the contraints affect which selections a host makes under different circumstances?').

# Lessons and reflections

We reflect on some of the lessons learned from the examples and dialogues.

We illustrated with various fictive dialogous how theoretical models can end up different based on the intuitions expressed in verbal theories and formalization choices made. You may have wanted to take different turns in the conversations than we explored here. We encourage such exploration. There is no reason why the models created by *Verbal* and *Formal* are to be the preferred ones. The different conversations served to illustrate several didactical points we want to highlight:

### formalization is a dialogical process
It is often through the *interaction* between verbally expressable intuitions and the questions raised in the process of formalization that one comes to more and more well-defined formal characterizations of a (hypothesized) capacity.

### formalization is a revealing process
It makes invisible holes and inconsistencies and hidden assumptions in a verbal theory visible. These hidden problems can be discovered and resolved by explicitly making different model variants.

### formalizations are transparent specifications
Formalizations define mathematically precise and well-defined functions that can serve as specifications for computer simulations. Yet, they can be communicated and understood without reliance on any code, as they specify the theory independently of implementational details.{% sidenote 'sn-id-whatever' 'See also Guest & Martin (2021) for the related notion of *open theory* and Cooper & Guest (2014) for the important distiction between specifications and implementations.'%}

### formalizations make transparent predictions
Unlike predictions 'derived' from verbal theories, formalizations allow predictions to be derived in a transparent and reproducible way. Predictions can be derived analytically (e.g., proof by example) or using simulations one can discover more intricate, and potentially counterintuitive, predictions.{% sidenote 'sn-id-whatever' 'See the [code and simulations]() created by *Formal*.'%}

Other than these important lessons, the dialogues also illustrated how formalizing verbal theories is a *non-deterministic* and *open-ended* process: Each dialogue ended with one or more different candidate theoretical models, which are at best working hypotheses.

# Try Again
Having read the chapter up to here, we recommend trying Exercise 4.4 again. Observe how you can get unstuck with the new knowledge and insights gained. Try to make at least two, and preferably more, variants of the computational level model.

# Further reading

{% marginnote 'mn-id-whatever' '“Human rational behavior (...) is shaped by a scissors whose two blades are the structure of task environments and the computational capabilities of the actor.” – Herbert Simon (1990).' %}

A key historical figure in the cognitive science of decision making is [Herbert Simon](https://www.nobelprize.org/prizes/economic-sciences/1978/simon/biographical/). He won the Nobel Prize in economics for his line of work started in the 1950s on *bounded rationality*: the idea that human rationality is to be understood as bounded by the computational limits of our minds. In the 1970s, Daniel Kahneman and Amos Tversky developed a program of *biases and heuristics* that demonstrated how limits of mind lead to systematic fallacies and judgment errors. From 1990s onwards, Gerd Gigerenzer and colleagues returned to the key ideas from Simon and developed a notion of *ecological rationality* that puts the ideas of 'biases and heuristics' in a more positive light, focusing less on human error and more on human smarts.

For examples of formal modeling of subset choice in the literature, see Fishburn & LaValle (1996), van Rooij, Stege, & Kadlec (2005), and Bossaerts, & Murawski (2017). During your formalization exercises you may have noticed that subset choice models can face so-called combinatorial explosion, because the number of subsets grows exponentially as a function of the number of elements one can choose from. We will return to this issue in later chapters. For more information on this issue we also recommend the textbook [Cognition and Intractability](https://cognitionandintractability.com), in particular [Chapter 1](https://www.cambridge.org/core/books/cognition-and-intractability/introduction/2FBB76A42417F33C409A2EAD17C15046) which is open access.    

### References

Simon, H. A. (1957). *Administrative Behavior*. New York, NY: Free Press.

Simon, H. A. (1990). [Invariants of human behavior](https://www.annualreviews.org/doi/abs/10.1146/annurev.ps.41.020190.000245). *Annual review of psychology, 41*(1), 1-20.

Tversky, A., & Kahneman, D. (1974). Judgment under uncertainty: Heuristics and biases. *Science, 185*(4157), 1124-1131.

Kahneman, D., Slovic, S. P., Slovic, P., & Tversky, A. (Eds.). (1982). [Judgment under uncertainty: Heuristics and biases](). Cambridge university press.

Gigerenzer, G. (1996). [On narrow norms and vague heuristics: A reply to Kahneman and Tversky](https://doi.org/10.1037/0033-295X.103.3.592). *Psychological Review, 103*(3), 592–596.

Todd, P. M., & Gigerenzer, G. (2000). Précis of "Simple heuristics that make us smart". *Behavioral and Brain Sciences, 23*(5), 727-741.

Guest, O., & Martin, A. E. (2021). [How computational modeling can force theory building in psychological science](https://journals.sagepub.com/doi/full/10.1177/1745691620970585). *Perspectives on Psychological Science*.

Cooper, R. P., & Guest, O. (2014). [Implementations are not specifications](https://www.sciencedirect.com/science/article/pii/S1389041713000314?casa_token=0Gn3_OBpKGkAAAAA:xtIOLXCj8XIUhzL7Tn7uJzhGgepPzRgOaW4UT5WMqW8nvjSIBE4_klHDPoh44AYadf_DHWDwYbM): Specification, replication and experimentation in computational cognitive modeling. *Cognitive Systems Research, 27*, 42-49.

Fishburn, P. C., & LaValle, I. H. (1996). [Binary interactions and subset choice](https://www.sciencedirect.com/science/article/abs/pii/0377221795000739). *European journal of operational research, 92*(1), 182-192.

Bossaerts, P., & Murawski, C. (2017). [Computational complexity and human decision-making](https://www.sciencedirect.com/science/article/pii/S1364661317301936). *Trends in Cognitive Sciences, 21*(12), 917-929.

van Rooij, I., Stege, U., & Kadlec, H. (2005). [Sources of complexity in subset choice](https://www.sciencedirect.com/science/article/abs/pii/S0022249605000052). *Journal of Mathematical Psychology, 49*(2), 160-187.

van Rooij, I., Blokpoel, M., Kwisthout, J. & Wareham, T. (2019). [Cognition and Intractability](https://cognitionandintractability.com). Cambridge University Press.  
