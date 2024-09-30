(ns assignments.hw3.q8
  (:require
    [assignments.hw3.utils :refer :all]))

(question "Question 8")

(sub-question "8) In 2024, Covid symptoms include running nose, cough, sore throat, fever, chills, body aches, and fatigue. An infected person may develop one or more symptoms simultaneously after three days. If the contaminated airborne particles and droplets spread randomly to four visitors sitting in a waiting room, what is the probability that all visitors developed at least one Covid-symptom after three days?")

(md "To solve this problem, we need to model it as a probability distribution problem. We can think of this as distributing symptoms (or the virus) to the four visitors, where each visitor must receive at least one symptom.

This is similar to the occupancy problem we solved in Question 7, but with a twist: we don't have a fixed number of 'objects' (symptoms) to distribute. Instead, we need to consider the probability of each visitor developing at least one symptom independently.")

(md "Let's approach this step-by-step:

1. First, let's consider the probability of a single visitor developing at least one symptom.
2. Then, we'll calculate the probability of all four visitors developing at least one symptom.

For step 1:
- There are 7 possible symptoms.
- The probability of not developing a specific symptom is the complement of developing it.
- To not develop any symptom, a visitor must not develop each of the 7 symptoms.")

(def num-symptoms 7)

(defn probability-no-symptoms [p]
      (Math/pow (- 1 p) num-symptoms))

(defn probability-at-least-one-symptom [p]
      (- 1 (probability-no-symptoms p)))

(md "For step 2:
- All four visitors need to independently develop at least one symptom.
- This is the same as the probability of one visitor developing a symptom, raised to the power of 4.")

(defn probability-all-visitors-symptomatic [p]
      (Math/pow (probability-at-least-one-symptom p) 4))

(md "Now, let's calculate this probability for a range of p values, where p is the probability of developing each individual symptom:")

(def p-values [0.1 0.2 0.3 0.4 0.5])

(def probabilities-all-visitors
  (map (fn [p] {:p p :probability (probability-all-visitors-symptomatic p)})
       p-values))

(answer "Probabilities for all visitors developing at least one symptom:")
(into [] probabilities-all-visitors)

(doseq [p p-values]
       (println (str "For p = " p ", the probability that all visitors
  develop at least one symptom is approximately "
                     (format "%.4f" (probability-all-visitors-symptomatic
                                           p)))))

(md "**Interpretation:**

1. As the probability of developing each individual symptom (p) increases, the probability of all visitors developing at least one symptom increases rapidly.
2. Even with relatively low probabilities for individual symptoms, the chance of all four visitors showing symptoms can be quite high due to the multiple symptoms and visitors involved.
3. This demonstrates how easily a highly symptomatic disease can spread in a confined space like a waiting room.")

(md "**Sensitivity Analysis:**

Let's see how the result changes with the number of visitors:")

(defn probability-all-visitors-symptomatic-n [p n]
      (Math/pow (probability-at-least-one-symptom p) n))

(def probabilities-by-visitor-count
  (map (fn [n] {:visitors n :probability (probability-all-visitors-symptomatic-n 0.2 n)})
       (range 2 7)))

(answer "Probabilities for different numbers of visitors (p = 0.2):")
(into [] probabilities-by-visitor-count)

(doseq [n (range 2 7)]
       (let [p 0.2]                                         ; Let's fix p at 0.2 for this analysis
                 (println (str "For " n " visitors and p = " p ", the probability is approximately ")
                                       (format "%.4f" (probability-all-visitors-symptomatic-n p n)))))

(md "**Conclusion:**

This problem demonstrates the application of probability theory to epidemiology and public health. We've modeled the spread of Covid symptoms in a waiting room scenario, showing how individual probabilities of developing symptoms combine to affect group outcomes.

Key takeaways:
1. The probability of all visitors developing symptoms increases with both the individual symptom probability and the number of visitors.
2. Even with moderate individual probabilities, the chance of all members of a group showing symptoms can be high, especially in larger groups.
3. This kind of modeling is crucial for understanding disease spread and informing public health policies, especially in confined spaces like waiting rooms, classrooms, or public transportation.

In practice, more complex models would be used, accounting for factors like varying symptom probabilities, incubation periods, and individual susceptibilities. However, this simplified model provides valuable insights into the basic principles of disease spread probability.")
