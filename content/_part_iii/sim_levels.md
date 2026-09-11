---
layout: page
title: Levels of explanation
chapter: 11
nav_exclude: true
---

In this chapter we explore again use computer simulation as a theoretic tool. In the previous chapter, we simulated purely the computational-level theories. Here, we will use simulations to analyze the relationship between a computational-level and algorithmic-level theory.

A complete theory (Marr, 1982) or theory proper (Egan, 2025) includes at least three sub-theories: One theory of the function being computed, of the algorithm or process that computes the function and of the implementation of the algorithm in physical stuff. Since each sub-theory explains the cognitive capacity from a different perspective, it is important that they are compatible with each other. The notion or degree of compatibility required has been debated, where some allow for a loose relationship (Marr, 1982; Chomsky 1965; Coorper & Peebles, 2018) and others argue for a strict relationship (Blokpoel, 2018; Egan, 2025). It is important to view compatibility from the use of a theory. In this book, we use theory to explain cognitive capacities. Yet, a theory by itself does not explain. Rather, it is the theoretician that explains. She does so by using a (sub-)theory and inferring consequences from assumptions. These inferences should hold up to scrutiny and follow a meta-theoretical calculus (such as logical inference) that does not break the rules of computationalism (Guest & Martin, 2021; van Rooij & Baggio, 2021). When combining inferences from theories at different levels, the formal relationship between the sub-theories becomes part of the foundation that should uphold computationalism (similar to Blokpoel & Guest, in prep). 

What happens when we combine inferences from theories that are not 'compatible'? How can we recognize compatibility? These questions can be addressed using formal analysis (see e.g., van Rooij & Baggio, 2021, Blokpoel & Guest, in prep), for example by proving how an algorithm is extensionally equivalent to the function. These analyses are, unfortunately, not easy. We can support our theoretical thinking using computer simulations by systematically comparing theories. The computational-level theory implemented by an extensionally equivalent algorithm (e.g., exhaustive search) and the algorithmic-level theory implemented as itself.



{% fproblem Biased Coherence %}
A graph $$G = (V, E)$$, with a weight $$w(u,v) \in \mathbb{N}$$ for each edge $$(u,v) \in E = C^+ \cup C^- \subseteq V \times V$$ (where $$C^+ \cap C^- = \emptyset $$), and a set of biased beliefs $$B \subseteq V$$ with a truth-value assignment $$T_{bias}: B \rightarrow \{\top, \bot \}$$ and with a weight $$w(b) \in \mathbb{N}$$ for each $$b \in D$$.;;
One of possibly several optimal truth assignment $$T_{opt}: V \rightarrow \{\top, \bot \}$$ such that it maximizes$$Coh(T_{opt})=Coh^+(T_{opt})+Coh^-(T_{opt})+Coh^{bias}(T_{opt})$$, where:

$$
Coh^+(T)=\sum_{T(u) = T(v)} w(u,v)
$$

$$
Coh^-(T)=\sum_{T(u) \neq T(v)} w(u,v)
$$

$$
Coh^{bias}(T) = \sum_{\substack{b \in B\\T(b) = T_{bias}(b)}} w(b)
$$
{% endfproblem %}



<pre class="mathlib">
import 

def cohPlus(assignment: TruthValueAssignment): Double = {
  sum(
    { positiveConstraints | isSatisfiedPositiveConstraint(assignment) _ },
    (edge: WUnDiEdge[Belief]) => edge.weight
  )
}

def cohMin(assignment: TruthValueAssignment): Double = {
  sum(
    { negativeConstraints | isSatisfiedNegativeConstraint(assignment) _ },
    (edge: WUnDiEdge[Belief]) => edge.weight
  )
}

def cohBias(assignment: TruthValueAssignment): Double = {
  def isCorrect(b: Belief): Boolean = assignment(b) == biasAssignment(b)
  
  val correctlyBiasedBeliefs = { biasBeliefs | isCorrect _ } 

  sum(correctlyBiasedBeliefs, biasWeights(_))
}
</pre>

### References

Blokpoel, Mark (2017). Sculpting Computational-Level Models. *Topics in Cognitive Science, 10*(3), 641–648. [https://doi.org/10.1111/tops.12282](https://doi.org/10.1111/tops.12282)

Blokpoel, Mark & Guest, Olivia (in prep). A Metamethodology for computational(-level) simulations.

Cooper, Rick P., & Peebles, David (2018). On the Relation Between Marr’s Levels: A Response to Blokpoel (2017). Topics in *Cognitive Science, 10*(3), 649–653. [https://doi.org/10.1111/tops.12283](https://doi.org/10.1111/tops.12283)

Egan, Francis (2025). Deflating Mental Representation. The MIT Press.


Guest, Olivia, & Martin, Andrea E. (2021). How Computational Modeling Can Force Theory Building in Psychological Science. *Perspectives on Psychological Science, 16*(4), 789–802. [https://doi.org/10.1177/1745691620970585](https://doi.org/10.1177/1745691620970585)

Marr, David (1982). Vision: A computational investigation into the human representation and processing of visual information. W.H. Freeman.

van Rooij, Iris, & Baggio, Giosuè (2021). Theory before the test: How to build high-verisimilitude explanatory theories in psychological science. *Perspectives on Psychological Science, 16*(4), 682–697.

van Rooij, I. & Wareham, T. (2012). Intractability and approximation of optimization theories of cognition. *Journal of Mathematical Psychology, 56*,232-247.
