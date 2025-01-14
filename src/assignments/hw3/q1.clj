(ns assignments.hw3.q1
  (:require [assignments.hw3.utils :refer :all]))

(question "Question 1")
(sub-question "1) Let S be a sample space.")
(sub-sub "a) Show that the collection B = {∅, S} is a sigma algebra.")

(md "To show that $B = {∅, S}$ is a sigma algebra, we need to verify three properties:
     
1. $∅ ∈ B$ (The empty set is in B)
2. If $A ∈ B$, then $A^c ∈ B$ (B is closed under complementation)
3. If $A1, A2, ... ∈ B$, then $∪Ai ∈ B$ (B is closed under countable unions)")

(md "**Proof:**

1. $∅ ∈ B$: This is true by definition of B.
2. Closure under complementation:
   - $∅^c = S$, which is in B
   - $S^c = ∅$, which is in B
3. Closure under countable unions:
   - $∅ ∪ ∅ = ∅$, which is in B
   - $∅ ∪ S = S$, which is in B
   - $S ∪ S = S$, which is in B

Therefore, $B = {∅, S}$ is a sigma algebra.")

(sub-sub "b) Let B = { all subsets of S, including S itself }. Show that B is a sigma algebra.")

(md "To show that B = { all subsets of S, including S itself } is a sigma algebra, we need to verify the same three properties:
     
1. $∅ ∈ B$ (The empty set is in B)
2. If $A ∈ B$, then $A^c ∈ B$ (B is closed under complementation)
3. If $A1, A2, ... ∈ B$, then $∪Ai ∈ B$ (B is closed under countable unions)")

(md "**Proof:**

1. $∅ ∈ B$: This is true because the empty set is a subset of S.
2. Closure under complementation:
   For any $A ∈ B$, $A^c$ is also a subset of S, so $A^c ∈ B$.
3. Closure under countable unions:
   For any countable collection of subsets $A1, A2, ... ∈ B$, their union $∪Ai$ is also a subset of S, so $∪Ai ∈ B$.")

(answer "Therefore, B = { all subsets of S, including S itself } is a sigma algebra.")

(sub-sub "c) Show that the intersection of two sigma algebras is a sigma algebra.")

(md "Let B1 and B2 be two sigma algebras on a sample space S. We need to show that B = B1 ∩ B2 is also a sigma algebra.")

(md "**Proof:**

1. $∅ ∈ B$:
   Since $B1$ and $B2$ are sigma algebras, $∅ ∈ B1$ and $∅ ∈ B2$. Therefore, $∅ ∈ B1 ∩ B2 = B$.
2. Closure under complementation:
   Let $A ∈ B$. This means $A ∈ B1$ and $A ∈ B2$.
   Since $B1$ and $B2$ are sigma algebras, $A^c ∈ B1$ and $A^c ∈ B2$.
   Therefore, $A^c ∈ B1 ∩ B2 = B$.
3. Closure under countable unions:
   Let $A1, A2, ... ∈ B$. This means for each $i$, $Ai ∈ B1$ and $Ai ∈ B2$.
     
   Since $B1$ and $B2$ are sigma algebras, $∪Ai ∈ B1$ and $∪Ai ∈ B2$.
   Therefore, $∪Ai ∈ B1 ∩ B2 = B$.")

(answer "Therefore, the intersection of two sigma algebras is a sigma algebra.")

(md "**Conclusion:**
     
We have shown that:

a) The collection $B = {∅, S}$ is a sigma algebra.
b) The collection of all subsets of $S$ is a sigma algebra.
c) The intersection of two sigma algebras is a sigma algebra.

These results are fundamental in measure theory and probability theory, providing the basis for defining probability measures on sets.")
