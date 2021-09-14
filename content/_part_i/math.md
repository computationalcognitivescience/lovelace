---
layout: page
title: Math concepts and notation
chapter: 3
nav_exclude: true
---

We saw in the [Introduction](https://computationalcognitivescience.github.io/lovelace/part_i/intro) that, just like sculpting, theoretical modeling requires its own set of dedicated tools. The theoretical modeler's toolbox includes a.o. mathematical concepts, formal expressions, and notational conventions. One can already get quite far with the basics in _set theory_, _functions_ and _logic_. Below we present a brief primer. Readers who have taken introductory classes on these topics can skip this section without loss of continuity. If, however, these materials are new to you, then we recommend carefully studying this chapter before proceeding. A good grasp of the concepts and notation defined here will be necessary for following the examples and exercises in subsequent chapters. In general, developing some fluency in mathematical language is key if one wants to become a theoretical modeler.

{% marginfigure "fig-set-eg" "assets/img/set-eg.svg" "Sets can be visualized as circles." "75%" %}
## Set theory
A set is a collection of distinct objects. For example, a set of people $$P=\{\text{Ramiro},\text{Brenda},\text{Molly}\}$$, animals $$A=\{\text{cat},\text{turtle},\text{blue whale}, \text{cuttlefish}\}$$ or numbers $$ N=\{1,5,7,12\}$$. Sets are usually denoted by a capital letter and their elements listed between curly brackets. They can also be visualized as circles.

Sets can contain an infinite number of objects, e.g. all positive odd numbers $$O=\{1,3,5, 7,\dots\}$$.

### Set membership
{% marginfigure "fig-set-in" "assets/img/set-in.svg" "Set membership." "75%" %}

When we want to write that an object $$x$$ is (or is not) part of a set $$X$$, we use _set membership_ notation:


$$5 \in N\\17 \notin N\\\text{Ramiro}\in P\\\text{Saki}\notin P$$


### Subset and superset
{% marginfigure fig-subset "assets/img/set-sub.svg" "Subset relationship." "75%" %}
Often, we want to express things like 'the set of mammals $$M$$ is part of the set of all animals $$A$$'. We then use _subset_ notation: $$M\subseteq A$$ or $$M\subset A$$. The latter means that $$M$$ is smaller than $$A$$.

{% marginfigure fig-superset "assets/img/set-sup.svg" "Superset relationship." "75%" %}
Vice versa, we can express that 'the set of all things on earth $$T$$ contains all animals $$A$$' using _superset_ notation: $$T\supseteq A$$ or $$T\supset A$$. The latter means that $$T$$ is bigger than $$A$$.



### Intersection, union and difference
Let's look at what more we can do with two sets.
{% marginfigure fig-set-two "assets/img/set-two.svg" "Example sets." "75%" %}
For example, take the set of your friends and my friends.

$$F_{you}=\{\text{John},\text{Roberto},\text{Holly},\text{Doris},\text{Charlene}\}$$

$$F_{me}=\{\text{Vicky},\text{Charlene},\text{Ramiro},\text{Johnnie},\text{Roberto}\}$$


{% marginfigure fig-set-intersection "assets/img/set-intersection.svg" "Set intersection." "75%" %}
Who are our common friends? We use _set intersection_:

$$F_{you}\cap F_{me} = \{\text{Roberto},\text{Charlene}\}$$



Who do we know together? We use _set union_:

$$F_{you}\cup F_{me} = \{\text{John},\text{Roberto},\text{Holly},\text{Doris},\text{Charlene},$$ $$\text{Vicky},$$ $$\text{Ramiro},$$ $$\text{Johnnie}\}$$

{% marginfigure fig-set-union "assets/img/set-union.svg" "Set union." "75%" %}

Who do I know that you do not know? We use _set difference_:

$$F_{me}\setminus F_{you}=\{\text{Vicky},\text{Ramiro},\text{Johnnie}\}$$

{% marginfigure fig-set-difference "assets/img/set-minus.svg" "Set difference." "75%" %}

### Set builder
A more advanced way to denote sets, is to define a set using _set builder_ notation. This allows us to define (build) a new set given other set(s). A set builder consists of two parts, a variable and a logical predicate:

$$\{\text{variable}~|~\text{predicate}\}$$

Let's look at an example and build a set of all mammals from $A$. We explain logical predicates below, for now lets use verbal language:

$$M=\{a~|~a\in A\text{ and }a\text{ is a mammal}\}$$

You can read this as '$$M$$ contains all $$a$$'s _with the property that_ $$a\in A$$ and $$a$$ is a mammal'.

{% marginfigure fig-set-builder "assets/img/set-builder.svg" "Set builder." "75%" %}

### Cardinal product
Set builder notation is useful to filter objects from a single set, but becomes very potent when building from multiple sets. For example:

$$F=\{(p,a)~|~p\in P\text{ AND }a\in A\}$$

{% marginfigure fig-cardinal-product "assets/img/set-cardinal.svg" "Cardinal product." "75%" %}

Read this as '$$F$$ contains all pairs of $$p$$ and $$a$$ _with the property that_ $$p$$ is a person and $$a$$ is an animal'. Pairs are denoted in brackets. You can think of $$F$$ containing all possible combinations of person-animal pairs. For example, these are all the options you have when trying to guess what the favorite animals are of your friends.

Many other set builders are possible too, but this specific 'pair builder' is called the _cardinal product_ of two sets. It is used often enough that it has its own special symbol: $$F=P\times A$$.

### Special sets
Finally, there are some special sets which we often use that have their own symbols:


* Empty set $$\varnothing=\{\}$$
* Natural (whole) numbers (with zero) $$\mathbb{N}_0=\{0,1,2,3,4,\dots\}$$
* Natural (whole) numbers (without zero) $$\mathbb{N}^*=\{1,2,3,4,\dots\}$$
* Integer numbers $$\mathbb{Z}=\{\dots,-3,-2,-1,0,1,2,3,\dots\}$$
* Real numbers <span>$$\mathbb{R}=\{r~|~-\infty<r<\infty\}$$</span>

## Tuple

In sets elements are unordered and no element can exist twice. However, there
are occasions where the ordering of the elements is relevant and when the same
element can exist multiple times. For example, a text is a sequence of
characters where ordering is quite important and characters can occur multiple
times. To express these *sequences* or *lists* we use the mathematical notation
of *tuples*. An $$n$$-tuple is a sequence of $$n\geq0$$ elements. The sequence
is most commonly expressed between parentheses $$()$$, a convention we follow in
this book, but sometimes you will encounter other types of brackets such as
$$\{\}$$, $$[]$$ and $$\langle\rangle$$ derived from variations on the tuple
such as arrays or vectors.

Specific elements in a tuple $$(e_1,e_2,\dots,e_n)$$ can be referred to by
their label $$e$$ and index $$_i$$. Depending on the type of the elements, you can use these in expressions (see below for [functions](#functions) and [logic](#logic) expressions). Here are some example tuples:

A travel route to the south of France:
$$r=(\text{Nijmegen},\text{Liège},\text{Metz},$$
$$\text{Nancy},\text{Dijon},\text{Lyon},\text{Marseille})$$. We can refer to the $$3^\text{th}$$ waypoint with $$r_3$$ which is Metz.

A preference list:
$$p=(\text{chocolate},\text{hiking},\text{sauna},\text{math})$$. This person
likes chocolate more than math.

## Functions
To define functions we go back to set theory. Functions are relations that map all objects from one set (the _domain_) to exactly one object from another set (the _codomain_). We define functions with the following notation, here $$f$$ is the name of the function:


$$f:D \rightarrow C\text{ with }f(d)=c$$

Let's make this more concrete:

$$like: P \rightarrow \mathbb{Z}$$

You can read this as '$$like$$ is a function that maps persons $$p\in P$$ to an integer'.

{% marginfigure fig-function "assets/img/fun-basic.svg" "Function." "75%" %}

We sometimes omit the exact specification of the function when it is clear what it would be. For example, here it could be a list of numbers representing how much you (dis)like the person, based on your social interactions with that person.

### Advanced functions
We can also give functions more complex domains by using set theory. What would a function that captures how much two persons like each other look like?

$$like_2:P\times P\rightarrow \mathbb{Z}$$

The cardinal product $$P\times P$$ denotes all pairs of persons and $$like_2$$ maps pairs to an integer.

{% marginfigure fig-advanced-function "assets/img/fun-adv.svg" "Advanced function." "75%" %}


### Sum and product
We can now define _summation_ and _product_. These functions iterate over members in a set and return a summary value.

Summation $$\sum$$ takes all $$x$$'s from $$X$$, applies $$f(x)$$ to each and adds all values:

$$\sum_{x\in X}f(x)=f(x_1)+f(x_2)+f(x_3)+\dots$$

Product takes $\prod$ all $x$'s from $$X$$, applies $$f(x)$$ to each and multiplies all values:

$$\prod_{x\in X}f(x)=f(x_1)f(x_2)f(x_3)\dots$$

## Logic
Logical predicates can be thought of as a special type of function that returns a Boolean value true ($$\text{true}$$ or $$\top$$) or false ($$\text{false}$$ or $$\bot$$). Predicates can be thought of as asking or claiming whether or not a statement is true or false.

For example, is $$x$$ bigger than $$2$$? Is $$a$$ a mammal and small? Is Emily your friend? Or, $$x$$ is bigger than $$2$$, $$a$$ is a mammal and small, and Emily is my friend.

Let's introduce some formal notation to express these statements:

* _number comparisons_ are familiar to most $$<$$, $$\leq$$, $$>$$, $$\geq$$, $$=$$, and $$\neq$$
* _conjunctions_ (logical $$\text{AND}$$) $$p\wedge q$$ is $$\top$$ if and only if both $$p=\top$$ and $$q=\top$$
* _disjunctions_ (logical $$\text{OR}$$) $$p\vee q$$ is $$\top$$ if $$p=\top$$ or if $$q=\top$$
* _set membership_ can also be used as a predicate $$a\in A$$ is $$\top$$ if $$a$$ is a member of set $$A$$

### Universal quantifier (for all)
Sometimes we want to say something about all objects in a set. We can use quantifier predicates to do this. For example, are all animals in the set mammals? We use the _universal quantifier_:

$$\forall_{a\in A}\text{mammal}(a)$$

You can read this as `does it hold for all objects $$a$$ in $$A$$ that $$a$$ is a mammal?' We implicitly introduced a function $$\text{mammal}:A\rightarrow\{\top,\bot\}$$ with $$\text{mammal}(a)=\top$$ if $$a$$ is a mammal or $$\bot$$ otherwise.

### Existential quantifier (exists)
Another type of question we can ask is, for example, is there someone I know that I like? We use the _existential quantifier_:

$$\exists_{p\in F_{me}}\left[\text{like}(p)>0\right]$$

Which we can read as `does there exist a person $$p$$ in the set of my friends $$F_{me}$$ for which I like them $$\text{like}(p)>0$$?'

## Graph theory

There are cases where we want to express the existence of a relationship between
two elements in a set. For example, who is friends with who, which two
ingredients go well together, the distance between two cities or how often words
co-occur in text. Here, we consider relationships that are Boolean (e.g., you
are friends or not) or numeric (e.g., distances). Graph theory allows us to
express in abstract the set of elements $$V$$ called *vertices* and their
relationships $$E$$ called *edges* which together make up a graph $$G=(V,E)$$.
The set of edges is a subset of the cardinal product of the vertices
$$E\subseteq V\times V$$. If there is a relationship between two vertices $$e\in
V$$ and $$v\in V$$, then $$(u,v)\in E$$.

{% maincolumn 'assets/img/graph-examples.svg' 'Example graphs. Graph A is a
simple graph (a forest of multiple connected graphs) representing two-way like
relationships. Graph B is a weighted graph representing major roads and
distances between cities. Graph C is a directed graph representing causal
relationships (e.g., clouds cause rain). Graph D is a tree representing part
of the taxonomy of animals.' %}

Depending on their structure, graphs can be classified into different types. In
this book, we use only a few types of graphs, but see Further Reading if you are
interested in diving deeper.

### Simple graph

The first graph type is called a *simple graph*. A simple graph has no edges
between a vertex and itself $$\forall_{v\in V}(v,v)\notin E$$, i.e., it has no
self-loops. A simple graph also has at most one edge between any two vertices,
i.e. it has no multi-edges.{% sidenote 'sn-id-multi-edge' 'Multi-edges cannot be
represented by a set of edges alone, since a set cannot contain multiple copies
of the same edge. ' %} All graphs in the examples above are simple graphs. In
this book, we assume a graph is simple unless otherwise noted.

### Connected graph

Not all vertices in a graph need to have an edge, in fact none need to. A graph
where for some vertices there exists no path between them is called a *forest*,
because it is a collection of *connected graphs*. In a connected graph, there
always exists a path between any pair of vertices. Graphs B, C, and D in the examples above are connected graphs, graph A is a forest.

In formal notation Graph A is $$V=\{\text{John},$$ $$\text{Doris},$$
$$\text{Roberto},$$ $$\text{Ramiro},$$ $$\text{Charlene},$$ $$\text{Holly}\}$$
and $$E=\{(\text{John},\text{Doris}),$$ $$(\text{Doris},\text{Roberto}),$$
$$(\text{Ramiro},\text{Charlene}),$$ $$(\text{Ramiro},\text{Holly}),$$
$$(\text{Charlene},\text{Holly})\}$$.

### Weighted graph

Graphs who's relationships between vertices is a number, are called *weighted
graphs*. Here, in addition to the graph $$G=(V,E)$$ a weight function is
supplied $$w:V\times V\rightarrow \mathbb{Z}$$. Often, weighted graphs are fully
connected but some edges may have a neutral weight such as $$0$$. Graph B in the examples above is a weighted graphs. If vertex relationships are Boolean, then the graph is *unweighted*. Graphs A, C and D are unweighted graphs.

Graph B is written up analogously to graph A but we add the weight function
$$w(\text{Amsterdam},\text{Groningen})=180$$, $$w(\text{Amsterdam},\text{The
Hague})=164$$, etc.

### (Un)directed graph

Graphs where edges have no direction are called *undirected graphs*. Here, the
pairs of vertices $$(u,v)\in E$$ are unordered. This means that $$(u,v)=(v,u)$$.
Of course, *directed graphs* also exist. Here, the pair is ordered and edges
have directionality. Graphs A, B and D in the examples above are undirected graphs, graph C is a directed graph.

Graph C is written as graph A, but the order of edges matter. So
$$(\text{Clouds},\text{Rain})\in E$$ but $$(\text{Rain},\text{Clouds})\notin E$$.

### Tree

The final graph type we condider here is more a type of graph with specific
properties, namely a *tree*. Trees are graphs without cycles, which means there
is exactly one path (a sequence of edges) between any two vertices. Another way
of thinking of acyclic graphs is that there is no way to 'walk back' to a vertex
along a different 'route'. Graph D in the examples above is a tree. Trees can
be also be directed, in which case they are called *polytrees*.

Graph D is again written as graph A. Nothing special is needed to denote a tree,
since it follows from the graph's structure that it is a tree. Sometimes, if you
want to be very clear, you can write graph/tree $$T=(V,E)$$ to denote a tree.

### Graph properties

Graphs have many formal properties but for the purposes of this book, only a few
basic properties are important.

{% marginfigure 'mf-id-degree' 'assets/img/vertex-degree.svg' 'Vertex degree.
Vertex $$A$$ has degree 4, vertex $$B$$ has indegree 3, and vertex $$C$$ has
outdegree 2.' '75%' %} The first is the *degree* of a vertex. This is the number
of connections that a vertex $$v$$ has, formally $$deg(v)=\left|\{u=v \wedge
w=v|(u,w) \in E\}\right|$$. If a graph is directed, then we split degree into
*indegree* (the number of edges toward the vertex) and *outdegree* (the number
of edges away from the vertex). For weighted graphs, we ignore the weight.

The maximum number of possible edges in an undirected graph is
<span>$$\frac{|V|(|V|-1)}{2}$$</span>. The first vertex in the graph can have
unqiue edges with $$|V|-1$$ vertices, the second with $$|V|-2$$ vertices, the
third with $$|V|-3$$ vertices, etc., until the $$(|V|-1)^\text{th}$$ vertex
which can only have a unique edge with $$1$$ vertex: $$(|V|-1) + (|V|-2) +
(|V|-3) + \dots + 3 + 2 + 1=\frac{|V|(|V|-1)}{2}$$.

The number of possible edges in a directed graph can be derived from the number
of possible edges in an undirected graph. In a directed graph can be two edges
between any two vertices, doubling the number of possible edges:
$$2\frac{|V|(|V|-1)}{2}=|V|(|V|-1)$$.


## Further reading

For basic principles on mathematics the Internet is a actually a great resource.
Wikipedia has great pages with much detail on the various concepts introduces in this chapter. See for example:

* [https://en.wikipedia.org/wiki/Set_theory](https://en.wikipedia.org/wiki/Set_theory)
* [https://en.wikipedia.org/wiki/Tuple](https://en.wikipedia.org/wiki/Tuple)
* [https://en.wikipedia.org/wiki/Function_(mathematics)](https://en.wikipedia.org/wiki/Function_(mathematics))
* [https://en.wikipedia.org/wiki/Mathematical_logic](https://en.wikipedia.org/wiki/Mathematical_logic)
* [https://en.wikipedia.org/wiki/Graph_theory](https://en.wikipedia.org/wiki/Graph_theory)

If you prefer video lectures, the YouTube channel  [thetrevtutor](https://www.youtube.com/user/thetrevtutor) has some interesting materials.
