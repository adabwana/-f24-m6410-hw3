(ns assignments.hw3.q5
  (:require
    [assignments.hw3.utils :refer :all]))

(question "Question 5")

(sub-question "5) Two litters of a particular rodent species have been born, one with two brown-haired and one gray-haired (litter 1), and the other with three brown-haired and two gray-haired (litter 2). We select a litter at random and then select an offspring at random from the selected litter.")

(sub-sub "a) What is the probability that the animal chosen is brown-haired?")

(md "To solve this problem, let's use the law of total probability. We'll calculate the probability of choosing a brown-haired animal from each litter and then combine these probabilities.

Let's define our events:
- L1: Litter 1 is chosen
- L2: Litter 2 is chosen
- B: A brown-haired animal is chosen

We know:
- $P(L1) = P(L2) = 1/2$ (equal chance of choosing either litter)
- $P(B|L1) = 2/3$ (2 out of 3 animals in litter 1 are brown)
- $P(B|L2) = 3/5$ (3 out of 5 animals in litter 2 are brown)")

(formula "P(B) = P(B|L1) * P(L1) + P(B|L2) * P(L2)")
(formula "P(B) = \\frac{2}{3} * \\frac{1}{2} + \\frac{3}{5} * \\frac{1}{2}")
(formula "P(B) = \\frac{1}{3} + \\frac{3}{10}")
(formula "P(B) = \\frac{10}{30} + \\frac{9}{30}")
(formula "P(B) = \\frac{19}{30}")

(defn probability-brown []
      (/ 19 30))

(answer (str "The probability that the chosen animal is brown-haired is " (probability-brown) " or approximately " (format "%.4f" (double (probability-brown)))))

(sub-sub "b) Given that a brown-haired offspring was selected, what is the probability that the sampling was from litter 1?")

(md "To solve this, we'll use Bayes' theorem. We want to find P(L1|B).

Bayes' theorem states:
$P(L1|B) = \\frac{P(B|L1) * P(L1)}{P(B)}$

We already know:
- $P(B|L1) = \\frac{2}{3}$
- $P(L1) = \\frac{1}{2}$
- $P(B) = \\frac{19}{30}$ (from part a)")

(formula "P(L1|B) = \\frac{P(B|L1) * P(L1)}{P(B)}")
(formula "P(L1|B) = \\frac{\\frac{2}{3} * \\frac{1}{2}}{\\frac{19}{30}}")
(formula "P(L1|B) = \\frac{\\frac{1}{3}}{\\frac{19}{30}}")
(formula "P(L1|B) = \\frac{10}{19}")

(defn probability-litter1-given-brown []
      (/ 10 19))

(answer (str "Given that a brown-haired offspring was selected, the probability that the sampling was from litter 1 is "
             (probability-litter1-given-brown)
             " or approximately "
             (format "%.4f" (double (probability-litter1-given-brown)))))

(md "**Interpretation:**

1. The probability of choosing a brown-haired animal (19/30 ≈ 0.6333) is slightly higher than 0.5, which makes sense because both litters have more brown-haired animals than gray-haired ones.

2. Given that a brown-haired animal was chosen, the probability that it came from litter 1 (10/19 ≈ 0.5263) is slightly higher than 0.5. This is because:
   - Litter 1 has a higher proportion of brown-haired animals (2/3) compared to litter 2 (3/5).
   - However, litter 2 has more brown-haired animals in total (3 vs 2).
   - These factors nearly balance out, resulting in a probability just slightly favoring litter 1.")

(md "**Conclusion:**

This problem demonstrates the application of the law of total probability and Bayes' theorem in a real-world scenario. It shows how we can calculate probabilities when dealing with multiple groups (litters) and how we can update our probabilities based on new information (knowing the chosen animal is brown-haired). These concepts are fundamental in probability theory and have wide-ranging applications in fields such as genetics, medical diagnosis, and data analysis.")
