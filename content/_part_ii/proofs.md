---
layout: page
title: Formal proofs
chapter: 4
nav_exclude: true
---

In the previous chapter we introduced different mathematical frameworks that alllow us to formally define theoretical models. This allows us not only to precisely communicate the theory, but it also affords further analysis of the theory. For example, we can use formal proofs to derive implications of our theoretical commitments. In this Chapter, we present a brief primer on *formal proofs*. The concept of a formal proof may sound intimidating and may evoke images of walls of math. However, it is good to remind ourselves that in essense a formal proof is nothing more than an argument to convince another person that a property is true, relative to our theoretical assumptions. 

Readers who have taken introductory classes on these topics can skip this chapter without loss of continuity. If, however, these materials are new to you, then we recommend carefully studying this chapter before proceeding.


## Introductory example

Before we explain the history of formal proofs and specific proof techniques, let's review an example proof to get an impression of what a formal proof entails. In [Chapter 2 - Conceptual foundations](/lovelace/part_i/foundations) we informally introduced the notion of sorting.

{% fproblem Sorting (informal) %}
A list of unordered numbers $$L$$.;;
An ordered list $$L'$$ that consists of the elements in $$L$$.
{% endfproblem %}

Using the math notations from [Chapter 3](/lovelace/part_i/math) we can now make sorting formal. Note that we are adding more details, for example the relationship between the numbers in $$L$$ and $$L'$$ and the notion of *ordered* is formally specified.


{% fproblem Sorting (formal) %}
A list of $$p$$ unordered numbers $$L=\langle m_1, m_2, \dots , m_p \rangle$$.;;
An ordered list $$L'=\langle n_1, n_2, \dots , n_p\rangle$$. Here, each $$m_i\in L$$ occurs exactly once in $$L'$$ and for all consequitive pairs $$n_i, n_{i+1}$$ the next number should be (strictly) bigger, i.e., $$n_i < n_{i+1}$$.
{% endfproblem %}

Based on this theory, we may conjecture (i.e., think that it holds) that *any list of numbers* $$L$$ can be sorted as specified by the theory.

{% stopandthink %}
Do you think that the conjecture is true or false? Why do you believe this? How would you convince someone else to accept the conjecture?
{% hidden Hint? %}
The details in the formalization are important.
{% endhidden %}
{% endstopandthink %}

The conjecture, in fact, is not true, which can be shown by a counter example. Consider the input $$L=\langle 6, 3, 1, 3 \rangle$$. From the formalization, each of the numbers should occur exactly once in $$L'$$. However, no permutation of the numbers exists that satisfies the condition that $$n_i < n_{i+1}$$, as we can observe in Table 1.


{% marginnote 'Table-ID1' 'Table 1: All possible permutations of the numbers in $$L$$.'  %}

|$$n_1$$ | | $$n_2$$ | |  $$n_3$$|  |   $$n_4$$   | Is ordered? |
|----:|-----:|-------:|-------:|:----|
| 1 |<| 3 |<| 3 |<| 6 | no, $$3\nless 3$$ |
| 1 |<| 3 |<| 6 |<| 3 | no, $$6\nless 3$$ |
| 1 |<| 6 |<| 3 |<| 3 | no, $$6\nless 3$$ |
| 3 |<| 1 |<| 3 |<| 6 | no, $$3\nless 1$$ |
| 3 |<| 1 |<| 6 |<| 3 | no, $$3\nless 1$$ |
| 3 |<| 3 |<| 1 |<| 6 | no, $$3\nless 1$$ |
| 3 |<| 3 |<| 6 |<| 1 | no, $$3\nless 3$$ |
| 3 |<| 6 |<| 1 |<| 3 | no, $$6\nless 1$$ |
| 3 |<| 6 |<| 3 |<| 1 | no, $$6\nless 3$$ |
| 6 |<| 1 |<| 3 |<| 3 | no, $$6\nless 1$$ |
| 6 |<| 3 |<| 1 |<| 3 | no, $$6\nless 3$$ |
| 6 |<| 3 |<| 3 |<| 1 | no, $$6\nless 3$$ |


This proves by illustration (or counter example) that a list exists that cannot be sorted, because none of the possible permutations is ordered. Hence, the conjecture must be false. This realization may lead to another conjecture: If the list $$L$$ contains a number twice, then no sorted list $$L'$$ can exist. This conjecture cannot be proven by example as we cannot possible show all lists that contain a number twice, there are infinitely many of them.

{% stopandthink %}
Do you think that the second conjecture is true or false? Why do you believe this? How would you convince someone else to accept the conjecture?
{% endstopandthink %}

While we cannot list all possible lists that contain a number twice, we can say something *about* all those lists. Namely, each such list at minimum must contain a pair of the same number $$n_i, n_j \in L$$, where $$n_i=n_j$$. The best place in the output $$L'$$ to place these two numbers, is next to each other, where the numbers before $$n_i$$ are ordered and the numbers after $$n_j$$ are also ordered:

$$L'=\langle n_1, n_2, \dots, n_i, n_j, \dots, n_p\rangle$$

However, the list cannot be sorted, because $$n_i\nless n_j$$ and $$n_j\nless n_i$$. Thus we must conclude that any list that contains a number twice cannot be sorted.

### What to learn from the example?

The constructions of formal proofs is a creative skill, from the sprouting of conjectures to the formal proofs themselves. While there are formal rules and constraints that are navigated, there is no procedure that one can follow to derive a formal property or prove it. With practise and experience, intuitions can be sharpened that will help constructing formal proofs. A futher perspective to consider is that proofs are intended to convince others of a particular point, they are arguments. Sometimes an argument requires more formal details, other times a sketch can be convincing enough. Think of the formal nature of a proof not as an obstacle, but rather as a way to strengthen theoretical arguments. 

We next cover some of the fascinating history of formal proofs, after which we illustrate three commonly used formal proof strategies.


## History of formal proofs

Similarly to math and formal notation, the long history of formal proofs often is lost in the shadow of more recent (western) approaches. Yet, formal proofs, formal reasoning and logic have a long history dating back to hundrends of years BC all across the world such as China, India, Eqypt, Babylon, Ancient Greece and the Middle East. Even a short summary of this history would require volumes of books. Here, we highlight several important discoveries in the history of formal proofs so that we may appreciate that our current work is supported by a long tradition that spans millenia and many diverse cultures and people.


### India
{% marginnote 'sn-id-hymm' 'Rig Veda, 10:129-6:<br/>
नासदासीन्नो सदासीत्तदानीं नासीद्रजो नो व्योमा परो यत्।<br/>
किमावरीवः कुह कस्य शर्मन्नम्भः किमासीद्गहनं गभीरम्॥१॥<br/>
न मृत्युरासीदमृतं न तर्हि न रात्र्या अह्न आसीत्प्रकेतः।<br/>
आनीदवातं स्वधया तदेकं तस्माद्धान्यन्न परः किं चनास॥२॥<br/>
तम आसीत्तमसा गूळ्हमग्रेऽप्रकेतं सलिलं सर्वमा इदम्।<br/>
तुच्छ्येनाभ्वपिहितं यदासीत्तपसस्तन्महिनाजायतैकम्॥३॥<br/>
कामस्तदग्रे समवर्तताधि मनसो रेतः प्रथमं यदासीत्।<br/>
सतो बन्धुमसति निरविन्दन् हृदि प्रतीष्या कवयो मनीषा॥४॥<br/>
तिरश्चीनो विततो रश्मिरेषामधः स्विदासी३दुपरि स्विदासी३त्।<br/>
रेतोधा आसन्महिमान आसन्त्स्वधा अवस्तात्प्रयतिः परस्तात्॥५॥<br/>
को अद्धा वेद क इह प्र वोचत्कुत आजाता कुत इयं विसृष्टिः।<br/>
अर्वाग्देवा अस्य विसर्जनेनाथा को वेद यत आबभूव॥६॥<br/>
इयं विसृष्टिर्यत आबभूव यदि वा दधे यदि वा न।<br/>
यो अस्याध्यक्षः परमे व्योमन्त्सो अङ्ग वेद यदि वा न वेद॥७॥<br/>
'%}
In ancient India, the Buddhist Madhyamaka school founded by नागार्जुन Nāgārjuna (Sanskrit) (c. 150 -- c. 250 CE) used the catuṣkoṭi (Sanskrit), a way to systematically analyze a proposition $$P$$. It includes four mutually exclusive possibilities for $$P$$ (for a modern interpretation, see Jayatilleke, 1967):

(1) $$P$$; that is, being.<br/>
(2) not $$P$$; that is not being.<br/>
(3) $$P$$ and not $$P$$; that is being and that is not being.<br/>
(4) not ($$P$$ or not $$P$$); that is neither not being not is that being.

The catuṣkoṭi is a logical tool, but its origins can be traced back even further to the Rig Vega (1500-1000 BC), an ancient collection of sūktas. The first verse, as translated by Kramer (1986) illustrates the origins of the catuṣkoṭi:
{% epigraph 'There was neither non-existence nor existence then;<br/>
Neither the realm of space, nor the sky which is beyond;<br/>
What stirred? Where? In whose protection?<br/>' 'Nasadiya Sukta (Hymn of Creation), concerns the origin of the universe' 'Rig Veda, 10:129-6' %}

### China

In the Chinese 墨家 Mohist tradition founded by 墨翟 Mozi (c. 470 BC -- c. 391 BC) logic and argumentation are based on analogies rather than mathematical reasoning. In Mohism the concept of fa is a standard or model, in a broad sense (e.g., fa could be a role model such as a teacher). They used the three *fa* method for statements by which "the distinctions between 'this' and 'not' and benefit and harm...can be clearly known" (Book 35, "Rejecting Fatalism"; Fraser, 2023). 
{% marginfigure 'mf-id-mozi' 'assets/img/Mozi.jpg' 'Text from 7th volume of Mozi (墨子卷之七).' %}

(1) Does the statement have for precidence and origin?<br/>
(2) Does the statement show a concern for empiricism?<br/>
(3) Is the statement of consequence and pragmatic utility?

It is important to realise that these methods for reasoning were used in philosophical, societal and political contexts. The three fa were used by the Mozi for the benefit of the people and society (Fraser, 2023).

### Greece

{% marginfigure 'mf-id-prior_analytics' 'assets/img/prior_analytics.jpg' 'Aristotle&#39;s Prior Analytics in Latin, 1290 circa, Biblioteca Medicea Laurenziana, Florence.' %}

Perhaps most recognizable to western culture are the contributions to logic and mathematics by Greek scholars (between c. 520BC -- 200 BC) such as  Pythagoras, Plato and Aristotle. Aristotilian logic is believed to be the first attempt at formal logic with syntax and used variables to show the form of an argument independent of the matter. Aristotle defined a syllogism (an argument) in his book Prior Analytics as consisting of predicates and a conclusion (Aristotle, 1989):

(1) Predicate: $$A$$ belongs to $$S$$ (e.g., <br/>
(2) Predicate: $$A$$ is predicated of $$S$$<br/>
(3) Conclusion: $$A$$ is said of $$S$$

This formal system allowed Aristotle, for example, to deal with contradicitions in a systematic way (Bochenski, 1970).

### Middle East

{% marginfigure 'mf-id-al_farabi' 'assets/img/al_farabi.jpg' 'Pages from Maqala fi aghrad kitab Ma ba&#39;d al-tabi&#39;a (On the scope of Aristotle&#39;s Metaphysics) The Bodleian Library, University of Oxford, Shelfmark: MS. Arab. d. 84.'%}

In the 9th century, the Islamic philosopher أبو نصر محمد الفارابي (Abu Nasr Muhammad al-Farabi) made major contributions to logic. He built on Greek works such as Aristotilian logic and the Stoic tradition. He argued that logic follows a dialogical structure:
{% epigraph 'Truth is found in answer and query, or jawab wa-su’al. In this, there is a mas’ul, one who is asked because he has promoted a thesis for which he is responsible, and there is a sa’il, an interrogator, who attempts to question this thesis.' 'أبو نصر محمد الفارابي' 'Paraphrased from Alwali (2018).' %}
Among the many contributions to logic, he was the first to categorize the notion of Takhayyul (idea), and of Thubut (proof) in his book Ihsaal-ulum (Enumeration of the Sciences) (Alwali, 2018).


## Formal proof strategies

Something about proof intuition.


## Proof by illustration

In the example from the Introduction we showed how a proof by illustration can be used to prove that any list with two or more times the same number cannot be sorted, when 'sort' is defined as the next number being strictly greater than the one before. In general, proof by illustration is useful to prove a statement some property $$P$$ holds for *all* cases or for *some* cases. For example, in the illustration we claimed that all possible lists can be sorted (formally: $$\forall_{l\in L}\text{canBeSorted}(l)$$). Another example statement could be that some lists cannot be sorted (formally: $$\exists_{l\in L}\neg\text{canBeSorted}(l)$$). Proof by illustration can prove two things:


(1) The falsity of universal statement $$U\equiv\forall_x P(x)$$; prove that $$U$$ is false by giving one case $$x$$ for which $$P$$ is false<br/>
(2) The truth of existential statement $$E\equiv\exists_x P(x)$$; prove that $$E$$ is true by giving one case $$x$$ for which $$P$$ is true

We've seen an illustration of (1) in the example in the Introduction. What about the option (2)? We can apply proof by illustration to the existential statement that some lists cannot be sorted (formally: $$\exists_{l\in L}\neg\text{canBeSorted}(l)$$), given the formalization of sorting in the Introduction.

{% stopandthink %}
Can you think of an example list that cannot be sorted?
{% hidden Hint? %}
Its the same illustration that proved the universal statement is false. Can you explain why?
{% endhidden %}
{% endstopandthink %}


## Direct proof

A direct proof consists of building an argument with a particular structure, namely a sequence of statements that themselves are either axiomatic (foundational), assumed to be true, or follow logically from an earlier statement. The final statement in this sequence is the statement we want to prove to be true. In a sense, a proof by illustration is a strategy that can be used in a direct proof. Let's make the second proof in the Introduction a bit more formal. In the proof sketch we implicitly assumed formal definition of sorting, we assumed the illustration list, then we showed that the conclusion must follow from the definition and the illustration. More formally, this could be written as the following sequence of statements:

(1) **Assume:** <span style="font-variant:small-caps;">Sorting (formal)</span>;<br/>
(2) **Assume:** $$L=\langle 6,3,1,3\rangle$$;<br/>
(3) **Infer:** From 1 and 2 that the sorted list must be in the set of all possible permutations of $$L$$;<br/>
(4) **Infer:** From 1 and 3 that each permutation must contain a pair of the same number $$n_i, n_j \in L$$, where $$n_i=n_j$$;<br/>
(5) **Infer:** From 1 that the only place in the output $$L'$$ to place $$n_i$$ and $$n_j$$, is next to each other;<br/>
(6) **Infer:** From 1 that for the list to be sorted, the numbers before $$n_i$$ should be ordered and the numbers after $$n_j$$ should also be ordered: $$L'=\langle n_1, n_2, \dots, n_i, n_j, \dots, n_p\rangle$$;<br/>
(7) **Infer:** From 1, 5 and 6 that the list $$L=\langle 6,3,1,3\rangle$$ cannot be sorted, because $$n_i\nless n_j$$ and $$n_j\nless n_i$$;<br/>
(8) **Conclude:** From 5, 6 and 7 that that any list that contains a number twice cannot be sorted.

In a direct proof, one can use any formally sound inference to infer a statement. However, as illustrated in the Introduction, proof sketches can already be convincing. A further formal analysis such as the sequence above may help to further sharpen one's proof.

### Direct proof example

Let's also look at an example of a direct proof that is not by illustration. Consider the following formalization called <span style="font-variant:small-caps;">Vertex Cover</span>, a classical graph problem from computer science. A vertex cover takes as input a graph and outputs a subset of vertices that *covers* all edges.

{% marginfigure 'mn-fig-vc-ill' 'assets/img/vertex_cover_illustration.svg' 'An example instance of <span style="font-variant:small-caps;">Vertex Cover</span>, with vertex set $$V = \{A, B, C, D, E \}$$. $$A$$ is shown to cover edges $$(A, B), (A, C), (A, D)$$.' %}
{% fproblem Vertex Cover %}
A graph $$G=(V, E)$$.;;
An subset of vertices $$V'\subseteq V$$ such that all edges $$e\in E$$ are *covered*. Here, $$e=(u, v)$$, and being covered means that either $$u\in V'$$ or $$v\in V'$$ (or both).
{% endfproblem %}


{% question %}
Can you complete the vertex cover in the figure?
{% hidden Possible solution: %}
There are several solutions possible. Here is a possible solution, can you find another solution?<br/>
<img src="/lovelace/assets/img/vertex_cover_solution.svg" />
{% endhidden %}
{% endquestion %}

{% stopandthink %}
Take your solution or the one from above and flip the selected vertices, i.e., if a vertex is red make it white and vice versa. What do you observe? Why is this the case?
{% hidden Hint? %}
Look at the edges between the vertices that are now red in the complement of $$V'$$.
{% endhidden %}
{% endstopandthink %}

The vertices in the complement of a vertex cover $$V'$$ have no edges between them, i.e., they are what is called an indepentent set. Can we prove this using a direct proof?

(1) **Assume:** <span style="font-variant:small-caps;">Vertex Cover</span>;<br/>
(2) **Assume:** An indepentent set is a set of vertices that have no edges between them;<br/>
(3) **Assume:** The complement of a subset $$V'\subseteq V$$ contains all the other elements in the set $$V\setminus V'$$;<br/> 
(4) **Infer:** From 1 that an edge is covered if one or more of its vertices is in $$V'$$;<br/>
(5) **Infer:** From 4 that an edge will.. 

and 3 that if all edges $$E$$ have at least one vertex in the cover $$V'$$, then the cover ;<br/>
...<br/>
(N) **Conclude:** The vertices in the complement of a vertex cover $$V'$$ have no edges between them, i.e., they are what is called an indepentent set.

## Proof by contradiction

Is an indirect proof, by assuming the negation of the conclusion, then prove a contradictory statement.


## Proof by contraposition

(1) Assume: <span style="font-variant:small-caps;">Sorting (formal)</span>;<br/>
(2) Assume: $$L=\langle 6,3,1,3\rangle$$;<br/>
(3) Infer: From 1 and 2 that the sorted list must be in the set of all possible permutations of $$L$$;<br/>
(4) Infer: From 1, 2 and 3 that none of the permutations satisfy the sorting condition;<br/>
(5) Infer: From 4 that hence there exists a list that cannot be sorted, namely $$L=\langle 6,3,1,3\rangle$$;<br/>
(6) Conclude: The statement "any list of numbers $$L$$ can be sorted" is false.



### References

<!-- Agrawala Vasudeva, Sharana (1983). Hymn of creation : Nāsadīya sūkta, Rgveda, X. 129. Prithivi Prakashan. -->

Alwali, Abduljaleel (2018). Logic Functions in the Philosophy of Al-Farabi in Handbook of the 6th World Congress and School on Universal Logic.

Bochenski, Joseph M. (1970). A History of Formal Logic. Chelsea Publishing Company.

Fraser, Chris (2023). Mohism in The Stanford Encyclopedia of Philosophy, Edward N. Zalta, Uri Nodelman (eds.)[https://plato.stanford.edu/archives/fall2023/entries/mohism/](https://plato.stanford.edu/archives/fall2023/entries/mohism/)

Jayatilleke, K. N. (1967). The Logic of Four Alternatives. Philosophy East and West, 17(1/4), 69–83. https://doi.org/10.2307/1397046

Kramer, K. (1986). World Scriptures: An Introduction to Comparative Religions. Paulist Press. p. 21. ISBN 0-8091-2781-4.

Aristotle (1989). Prior Analytics (Robin Smith, Trans.). (Original work work published c. 350BC)
<!-- Walser, J. (2005). Nagarjuna in Context: Mahayana Buddhism and Early Indian Culture. Columbia University Press. [https://doi.org/10.7312/wals13164](https://doi.org/10.7312/wals13164) -->