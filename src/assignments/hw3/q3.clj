(ns assignments.hw3.q3
  (:require
   [assignments.hw3.utils :refer :all]
   [fastmath.core :as m]
   [scicloj.hanamicloth.v1.api :as haclo]
   [tablecloth.api :as tc]))

(question "Question 3")

(sub-question "3) If $n$ balls are placed at random into $n$ cells, find the probability that exactly one cell remains empty.")

(md "**Solution:**
     
To solve this problem, we'll use the following approach:
     
1. Calculate the total number of ways to place n balls into n cells.
2. Calculate the number of ways to place n balls into n cells with exactly one cell empty.
3. Divide the result from step 2 by the result from step 1 to get the probability.")

(md "**Step 1: Total number of ways**
     
Total ways = $n^n$
     
**Step 2: Number of ways with exactly one cell empty**
     
We can calculate this using the following steps:

a) Choose 1 cell to be empty: $\\binom{n}{1} = n$ ways
b) Distribute $n-1$ balls among $n-1$ cells: $(n-1)^{n-1}$ ways
c) Multiply these together: $n \\cdot (n-1)^{n-1}$ ways")

(md "**Step 3: Calculate the probability**
     
P(exactly one cell empty) = $\\frac{n \\cdot (n-1)^{n-1}}{n^n}$")

(comment
  (defn probability-one-cell-empty [n]
    (/ (* n (m/pow (dec n) (dec n)))
       (m/pow n n))))

(md "Let's calculate this probability for a few values of n:")

(def probabilities
  (map (fn [n] {:n n :probability (probability-one-cell-empty n)})
       (range 2 11)))

(into [] probabilities)

(md "**Interpretation:**
     
1. As $n$ increases, the probability of having exactly one cell empty decreases.
2. This makes sense intuitively: with more balls and cells, it becomes less likely to have exactly one cell empty.
3. For large $n$, this probability approaches $\\frac{1}{e} \\approx 0.3679$, which is a well-known result in probability theory.")

(md "**Verification:**
     
We can verify that our formula approaches $\\frac{1}{e}$ for large n:")

(let [vals (range 1 50)
      probs (map probability-one-cell-empty vals)
      data (tc/dataset {:ns    vals
                        :probs probs})]
  (-> data
      (haclo/layer-line
       {:=x :ns :=x-title "n" :=mark-size 7
        :=y :probs :=y-title "P(exactly one cell empty)"})
      (haclo/update-data (fn [_]
                           (tc/dataset
                            {:ns    (range 1 50)
                             :e-inv (repeat 50 (/ 1 Math/E))})))
      (haclo/layer-line
       {:=x :ns :=y :e-inv :=mark-color :red})))

(let [large-n 143 ;last value before NaN--n^n overflows
      prob (probability-one-cell-empty large-n)
      e-inverse (/ 1 Math/E)]
  (answer (str "For n = " large-n ": \t\t
                Calculated probability: " (format "%.5f" prob) "\t\t
                $1/e$: " (format "%.5f" e-inverse))))

(md "**Conclusion:**
     
We have successfully derived and implemented a formula for the probability of exactly one cell remaining empty when n balls are placed randomly into n cells. This problem is related to the concept of 'occupancy problems' in probability theory and has applications in various fields, including computer science (e.g., hash table collisions) and biology (e.g., certain population models).")
