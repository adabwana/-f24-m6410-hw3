(ns assignments.hw3.q7-methods
  (:require
   [assignments.hw3.utils :refer :all]
   [scicloj.hanamicloth.v1.api :as haclo]
   [tablecloth.api :as tc]))

(question "Q7: Two Methods")

(md "
**Problem Statement:**

Assume that the AI system of an electric car emits 9 errors in a trip of 3,000 miles. The errors are randomly distributed to 7 back-up circuits where one circuit may handle more than one error. If all back-up circuits receive at least one error in one trip, a warning light turns on. Find the probability that the warning light turns on during a trip of 3,000 miles.

We'll solve this using two methods:
     
1. Using Stirling numbers of the second kind (combinatorial approach)
2. Using the Inclusion-Exclusion Principle (analytical approach)

Let's start with a smaller example to illustrate the concepts: 4 errors distributed to 3 circuits.")

(md "### Method 1: Using Stirling Numbers of the Second Kind")

(md "
This method uses Stirling numbers of the second kind, $S(n,k)$, which count the ways to partition $n$ distinguishable objects into $k$ non-empty subsets.

Key function: `stirling2`")

;; Stirling numbers of the second kind
(comment
  (defn stirling2 [n k]
    (cond
      (or (= k 0) (> k n)) 0
      (or (= k 1) (= k n)) 1
      :else (+ (* k (stirling2 (dec n) k))
               (stirling2 (dec n) (dec k))))))

(md "
For our example (4 errors, 3 circuits):
     
- Total ways: $3^4 = 81$
- Favorable ways: $3! \\times S(4,3)$
- Probability: $\\frac{3! \\times S(4,3)}{3^4}$")

;; Probability calculation using Method 1
(defn probability-method1 [n k]
  (let [total-ways (Math/pow k n)
        favorable-ways (* (factorial k) (stirling2 n k))]
    (/ favorable-ways total-ways)))

(md "Probability for our example:")
(format "%.4f" (probability-method1 4 3))

(md "### Method 2: Using the Inclusion-Exclusion Principle")

(md "
This method systematically includes and excludes overlapping cases.

Key function: `inclusion-exclusion-terms`")

;; Inclusion-Exclusion terms
(defn inclusion-exclusion-terms-0dex [n k]
  (map (fn [i]
         (let [sign (if (even? i) 1 -1)
               combinations (nck k i)
               ways (Math/pow (- k i) n)]
           (* sign combinations ways)))
       (range 0 (inc k))))

(defn inclusion-exclusion-terms-1dex
  "Calculates the terms for the inclusion-exclusion principle, corresponding to the formula:
   
   n: Number of items to distribute (e.g., calls)
   k: Number of groups to distribute into (e.g., days)
   
   Returns a sequence of terms for the inclusion-exclusion sum."
  [n k]
  (map (fn [i]
         (let [sign (if (odd? i) 1 -1)
               combinations (nck k i)  
               ways (Math/pow (- k i) n)]  
           (* sign combinations ways)))
       (range 1 (inc k))))

(md "
For our example (4 errors, 3 circuits):")

;; Compute inclusion-exclusion terms for the example
(def terms-example-0dex
  (map-indexed
   (fn [i term]
     {:i i
      :sign (if (even? i) "+" "-")
      :combination (nck 3 i)
      :ways (Math/pow (- 3 i) 4)
      :term term})
   (inclusion-exclusion-terms-0dex 4 3)))


(def terms-example-1dex
  (map-indexed
   (fn [i term]
     {:i (inc i)
      :sign (if (odd? (inc i)) "+" "-")
      :combination (nck 3 (inc i))
      :ways (Math/pow (- 3 (inc i)) 4)
      :term term})
   (inclusion-exclusion-terms-1dex 4 3)))

;; Print the inclusion-exclusion terms table
(into [] terms-example-0dex)
(into [] terms-example-1dex)

(md "
**Explanation:**
     
- i: Number of circuits excluded
- sign: Alternates between + and - based on the inclusion-exclusion principle
- combination: Ways to choose i circuits to exclude
- ways: Ways to distribute errors into remaining circuits
- term: Calculated as sign × combination × ways

The sum of these terms gives us the favorable ways.")

;; Probability calculation using Method 2
(defn probability-method2 [n k]
  (let [total-ways (Math/pow k n)
        favorable-ways (reduce + (inclusion-exclusion-terms-0dex n k))
        probs (/ favorable-ways total-ways)]
    probs))

(let [n 4 k 3 
      total-ways (Math/pow k n)
      favorable-ways (reduce + (inclusion-exclusion-terms-0dex n k))
      probs (/ favorable-ways total-ways)]
  (answer
   (str "Probability: " (format "%.4f" probs))))
    

(let [n 4 k 3 
      total-ways (Math/pow k n)
      favorable-ways (reduce + (inclusion-exclusion-terms-1dex n k))
      probs (/ favorable-ways total-ways)
      not-probs (- 1 probs)]
  (answer
   (str "Probability: " (format "%.4f" not-probs)))) 

(md "### Comparing Methods for Different Values of n")

(def n-values (range 1 20))
(def k 8)

(def probabilities
  (map (fn [n]
         {:n n
          :method1 (double (probability-method1 n k))
          :method2 (double (probability-method2 n k))})
       n-values))

(def data (tc/dataset probabilities))

;; Plotting the probabilities


(md "
### Conclusion

Both methods yield the same results, but they offer different perspectives:

1. **Stirling Numbers (Method 1):** 
   - Focuses on partitioning errors into non-empty circuits
   - More intuitive for understanding the problem structure
   - Computationally efficient for smaller values

2. **Inclusion-Exclusion (Method 2):**
   - Systematically accounts for all possible cases
   - More generalizable to complex scenarios
   - Can be computationally intensive for large values

For our original problem (10 errors, 8 circuits):")

(format "Probability: %.5f" (probability-method1 9 7))

(md "
This low probability suggests it's unlikely for all circuits to receive an error in a single trip, which is generally good for system reliability. However, in critical systems like automotive AI, even low-probability events need careful consideration.")