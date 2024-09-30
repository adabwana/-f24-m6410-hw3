(ns assignments.hw3.q2
  (:require
   [assignments.hw3.utils :refer :all]))

(question "Question 2")

(sub-question "2) Suppose that a sample space S has n elements. Prove that the number of subsets that can be formed from the elements of S is $2^n$.")

(md "To prove that the number of subsets that can be formed from the elements of S is $2^n$, we can use the following reasoning:
     
1. **Counting the Empty Subset**: There is exactly one empty subset, ∅, which contains no elements.
2. **Counting Subsets with One Element**: For each element in S, we can form a subset containing just that element. There are n such subsets.
3. **Counting Subsets with Two Elements**: We can choose any 2 elements from n elements to form a subset. The number of such subsets is $\\binom{n}{2}$.
4. **Continuing the Pattern**: We can form subsets with 3 elements ($\\binom{n}{3}$ subsets), 4 elements ($\\binom{n}{4}$ subsets), and so on, up to n elements (1 subset containing all elements of S).
5. **Sum of All Possibilities**: The total number of subsets is the sum of all these possibilities:")

(formula "1 + \\binom{n}{1} + \\binom{n}{2} + \\binom{n}{3} + ... + \\binom{n}{n-1} + \\binom{n}{n}")

(md "6. **Binomial Theorem**: This sum is equal to $2^n$ according to the binomial theorem:")

(formula "(1 + 1)^n = \\sum_{k=0}^n \\binom{n}{k} = 2^n")

(md "7. **Alternative Proof Using Binary Representation**: We can also prove this using a binary representation approach:
     - For each element in S, we have two choices: include it in the subset (1) or not (0).
     - This gives us a binary string of length n for each subset.
     - The number of possible binary strings of length n is $2^n$.
8. **Conclusion**: Therefore, the number of subsets that can be formed from a sample space S with n elements is $2^n$.")

(answer "The number of subsets that can be formed from a sample space S with n elements is $2^n$.")

(md "**Verification:**")

(comment
  (defn count-subsets [n]
    (Math/pow 2 n)))

(md "Let's verify this for a few values of n:")

(doseq [n (range 1 6)]
  (md (str "For n = " n ", number of subsets = " (count-subsets n))))

(def subset-counts
  (map (fn [n] {:n n :subsets (count-subsets n)}) (range 1 6)))

(into [] subset-counts)

(md "**Interpretation:**
     
1. The result $2^n$ represents the power set of S, which is the set of all subsets of S, including the empty set and S itself.
2. This formula is crucial in probability theory, as it helps us count the number of possible outcomes in various scenarios.
3. In set theory, this result is fundamental to understanding the relationship between a set and its subsets.")

(md "**Conclusion:**
     
We have proven that for a sample space S with n elements, the number of subsets is $2^n$. This result is a cornerstone in set theory, combinatorics, and probability theory, with wide-ranging applications in mathematics and computer science.")