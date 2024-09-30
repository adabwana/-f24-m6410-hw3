(ns assignments.hw3.q7
  (:require
   [assignments.hw3.utils :refer :all]))

(question "Question 7")

(sub-question "7) Assume that the AI (artificial intelligence) system of an electric car emits 10 errors (including pattern detection errors due to insufficient training data or operational errors) in a trip of 3,000 miles. Also assume that the errors are randomly distributed to 8 back-up circuits where one circuit may handle more than one errors. If all back-up circuits receive at least one error in one trip, a warning light the control panel automatically turns on. Find the probability that the warning light turns on during a trip of 3000 miles?")

(md "To solve this problem, we need to find the probability that all 8 back-up circuits receive at least one error out of the 10 errors emitted. This is equivalent to finding the probability of distributing 10 distinct objects (errors) into 8 distinct boxes (circuits) such that no box is empty.

This is a classic occupancy problem, and we can solve it using the concept of Stirling numbers of the second kind and the principle of inclusion-exclusion.")

(md "Let's break down the solution:

1. Total number of ways to distribute 10 errors to 8 circuits: $8^{10}$
   (Each error has 8 choices, and we make this choice 10 times)

2. Number of ways to distribute 10 errors to 8 circuits with all circuits receiving at least one error:
   
     - This is given by the Stirling number of the second kind: $S(10,8)$
     - We also need to account for the number of ways to arrange the 8 circuits, which is $8!$
     - Therefore, the number of favorable outcomes is $8! * S(10,8)$
     
3. Probability = (Favorable outcomes) / (Total outcomes) = $8! * S(10,8) / 8^{10}$")

(comment
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
      (/ favorable-ways total-ways))))


(comment
  (defn probability-all-circuits-receive-error []
    (let [n 10                                            ; number of errors
          k 8                                             ; number of circuits
          total-ways (Math/pow k n)
          favorable-ways (stirling2 n k)]
      (/ favorable-ways total-ways))))

(answer (str "The probability that the warning light turns on (all circuits receive at least one error) is approximately "
             (format "%.6f" (probability-all-circuits-receive-error))))

(md "**Interpretation:**

1. The probability is relatively low, which makes sense given that we're trying to distribute 10 errors among 8 circuits with no empty circuits.
2. This low probability suggests that it's quite unlikely for all circuits to receive an error in a single trip, which is good for the reliability of the system.
3. However, even a small probability of all circuits receiving an error could be concerning for a critical system like an AI in an electric car.")

(md "**Verification:**

We can verify our result using a Monte Carlo simulation:")

(comment
  (defn simulate-error-distribution []
    (let [circuits (vec (repeat 8 0))]
      (reduce (fn [acc _]
                (update acc (rand-int 8) inc))
              circuits
              (range 10)))))

(comment
  (defn all-circuits-have-error? [distribution]
    (every? pos? distribution)))

(comment
  (defn monte-carlo-simulation [num-simulations]
    (let [successes (atom 0)]
      (doseq [_ (range num-simulations)]
        (when (all-circuits-have-error? (simulate-error-distribution))
          (swap! successes inc)))
      (double (/ @successes num-simulations)))))

(let [num-simulations 1000000
      simulated-prob (monte-carlo-simulation num-simulations)]
  (answer
   (str "Simulated probability after " num-simulations " simulations: "
        (format "%.6f" simulated-prob))))

(md "**Conclusion:**

We have calculated the probability of the warning light turning on in an electric car's AI system during a 3000-mile trip. This problem demonstrates the application of Stirling numbers and occupancy problems in a real-world scenario involving error distribution in complex systems.

The low probability we found suggests that it's unlikely for all circuits to receive an error in a single trip, which is generally good for system reliability. However, in critical systems like automotive AI, even low-probability events need to be carefully considered.

This type of analysis is crucial in system design and reliability engineering, especially for safety-critical applications like autonomous vehicles. It helps engineers understand the likelihood of various failure modes and design appropriate safeguards and redundancies.")

