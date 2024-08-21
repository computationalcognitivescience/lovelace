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

Similarly to math and formal notation, the long history of formal proofs often is lost in the shadow of more recent (western) approaches. Yet, formal proofs, formal reasoning and logic have a long history dating back to hundrends of years BC all across the world such as China, India, Eqypt, Babylon, Ancient Greece and the Middle East. Even a short summary of this history would require volumes of books (REFS). Here, we highlight several important discoveries in the history of formal proofs so that we may appreciate that our current work is supported by a long tradition that spans millenia and many diverse cultures and people.


### India
In ancient India, the Buddhist Madhyamaka school used the catuṣkoṭi (Sanskrit), a way to systematically analyze a proposition $$P$$. It includes four mutually exclusive possibilities for $$P$$ (for a modern interpretation, see Jayatilleke, 1967):

(1) $$P$$; that is, being.<br/>
(2) not $$P$$; that is not being.<br/>
(3) $$P$$ and not $$P$$; that is being and that is not being.<br/>
(4) not ($$P$$ or not $$P$$); that is neither not being not is that being.

The catuṣkoṭi is a logical tool, but its origins can be traced back even further to the Rig Vega (1500-1000 BC), an ancient collection of sūktas{% sidenote 'sn-id-hymm' 'Rig Veda, 10:129-6:<br/>
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
'%}. The first verse, as translated by Kramer (1986) illustrates the origins of the catuṣkoṭi:
{% epigraph 'There was neither non-existence nor existence then;<br/>
Neither the realm of space, nor the sky which is beyond;<br/>
What stirred? Where? In whose protection?<br/>' 'Nasadiya Sukta (Hymn of Creation), concerns the origin of the universe' 'Rig Veda, 10:129-6' %}

### China




### Middle East

In the 9th century, the Islamic philosopher أبو نصر محمد الفارابي (Abu Nasr Muhammad al-Farabi) made major contributions to logic. He built on Greek works such as Aristotilian logic and the Stoic tradition. He argued that:
{% epigraph 'Truth is found in answer and query, or jawab wa-su’al. In this, there is a mas’ul, one who is asked because he has promoted a thesis for which he is responsible, and there is a sa’il, an interrogator, who attempts to question this thesis.' 'أبو نصر محمد الفارابي' 'Paraphrased from Alwali (2018).' %}
Among the many contributions to logic, he was the first to categorize the notion Takhayyul (idea), and Thubut (proof) in his book Ihsaal-ulum (Enumeration of the Sciences) (Alwali, 2018). 




## Formal proof strategies


### Proof by illustration


### Direct proof

### Proof by contradiction

### Other strategies


### References

<!-- Agrawala Vasudeva, Sharana (1983). Hymn of creation : Nāsadīya sūkta, Rgveda, X. 129. Prithivi Prakashan. -->

Alwali, Abduljaleel (2018). Logic Functions in the Philosophy of Al-Farabi in Handbook of the 6th World Congress and School on Universal Logic.


Jayatilleke, K. N. (1967). The Logic of Four Alternatives. Philosophy East and West, 17(1/4), 69–83. https://doi.org/10.2307/1397046

Kramer, K. (1986). World Scriptures: An Introduction to Comparative Religions. Paulist Press. p. 21. ISBN 0-8091-2781-4.

<!-- Walser, J. (2005). Nagarjuna in Context: Mahayana Buddhism and Early Indian Culture. Columbia University Press. [https://doi.org/10.7312/wals13164](https://doi.org/10.7312/wals13164) -->