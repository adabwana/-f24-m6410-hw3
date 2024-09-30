(ns assignments.hw3.utils
  (:require
   [clojure.math.combinatorics :as combo]
   [scicloj.kindly.v4.api :as kindly]
   [scicloj.kindly.v4.kind :as kind]
   (uncomplicate.neanderthal
    [core :refer [mv]]
    [linalg :refer [trf! tri!]]
    [native :refer [dge dv]])))

(kind/md "## Utils")

^:kindly/hide-code
(def md (comp kindly/hide-code kind/md))
^:kindly/hide-code
(def question (fn [content] ((comp kindly/hide-code kind/md) (str "## " content "\n---"))))
^:kindly/hide-code
(def sub-question (fn [content] ((comp kindly/hide-code kind/md) (str "#### *" content "*"))))
^:kindly/hide-code
(def sub-sub (fn [content] ((comp kindly/hide-code kind/md) (str "***" content "***"))))
^:kindly/hide-code
(def answer (fn [content] (kind/md (str "> <span style=\"color: black; font-size: 1.5em;\">**" content "**</span>"))))
^:kindly/hide-code
(def formula (comp kindly/hide-code kind/tex))

;; Functions from HW3

;; Q2: Subset counting
(defn count-subsets [n]
  (Math/pow 2 n))

;; Q3: Occupancy problem
(defn probability-one-cell-empty [n]
  (/ (* n (Math/pow (dec n) (dec n)))
     (Math/pow n n)))

;; Q4: Coin flipping game
(defn simulate-game []
  (loop [player :A]
    (if (= (rand-int 2) 0)                          ; 0 represents heads
      player
      (recur (if (= player :A) :B :A)))))

(defn simulate-many-games [n]
  (let [results (repeatedly n simulate-game)
        a-wins (count (filter #(= % :A) results))]
    (double (/ a-wins n))))

;; Q7: AI Error Distribution
(defn factorial [n]
  (apply * (range 1 (inc n))))

(defn stirling2 [n k]
  (cond
    (or (= k 0) (> k n)) 0
    (or (= k 1) (= k n)) 1
    :else (+ (* k (stirling2 (dec n) k))
             (stirling2 (dec n) (dec k)))))

(defn probability-all-circuits-receive-error []
  (let [n 10                                            ; number of errors
        k 8                                             ; number of circuits
        total-ways (Math/pow k n)
        favorable-ways (* (factorial k) (stirling2 n k))]
    (/ favorable-ways total-ways)))


(defn simulate-error-distribution []
  (let [circuits (vec (repeat 8 0))]
    (reduce (fn [acc _]
              (update acc (rand-int 8) inc))
            circuits
            (range 10))))

(defn all-circuits-have-error? [distribution]
  (every? pos? distribution))

(defn monte-carlo-simulation [num-simulations]
  (let [successes (atom 0)]
    (doseq [_ (range num-simulations)]
      (when (all-circuits-have-error? (simulate-error-distribution))
        (swap! successes inc)))
    (double (/ @successes num-simulations))))

;; Helper functions
(defn joint-probability
  "Helper function to calculate the joint probability of a subset of events."
  [probs subset]
  (reduce * (map #(nth probs %) subset)))

(defn subsets
  "Generate all non-empty subsets of a set of indices."
  [s]
  (filter seq (combo/subsets s)))

(defn power-set
  "Returns the power set of a given collection (set or vector)."
  [coll]
  (set (map set (combo/subsets (seq coll)))))

(defn probability-at-least-one
  "Calculate the probability of at least one event occurring given a collection of probabilities.
       Uses the inclusion-exclusion principle for any number of events."
  [probs]
  (let [p-none (reduce * (map #(- 1 %) probs))]
    (- 1 p-none)))

(defn solve-probabilities [b1 b2]
  (let [A (dge 2 2 [1 1
                    3 -1]
               {:layout :row})
        b (dv [b1 b2])
        LU (trf! A)
        x (mv (tri! LU) b)]
    x))

(defn nck
  "Calculates the binomial coefficient (n choose k) using iterative and recursive method."
  [n k]
  (if (or (< k 0) (> k n))
    0
    (let [k (min k (- n k))]
      (loop [result 1N, i 0]
        (if (= i k)
          result
          (recur
           (/ (* result (- n i)) (inc i))
           (inc i)))))))