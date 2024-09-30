(ns assignments.hw3.q6
  (:require
    [assignments.hw3.utils :refer :all]))

(question "Question 6")

(sub-question "6) Let X be a continuous random variable with pdf $f(x)$ and cdf $F(x)$. For a fixed number $x_0$, define the function")

(formula "g(x) = \\begin{cases} 
    \\frac{f(x)}{1 - F(x_0)} & \\text{if } x \\geq x_0 \\\\
    0 & \\text{if } x < x_0
\\end{cases}")

(sub-sub "Prove that $g(x)$ is a pdf (Assume that $F(x_0) < 1$.)")

(md "To prove that $g(x)$ is a probability density function (pdf), we need to show two properties:

1. $g(x) \\geq 0$ for all $x$
2. The integral of $g(x)$ over its entire domain equals 1

Let's prove each of these properties:")

(md "**1. Non-negativity: $g(x) \\geq 0$ for all $x$**

For $x < x_0$, $g(x) = 0$, which satisfies the non-negativity condition.

For $x \\geq x_0$:
- $f(x) \\geq 0$ (since $f(x)$ is a pdf)
- $1 - F(x_0) > 0$ (since we're given that $F(x_0) < 1$)

Therefore, $g(x) = f(x) / (1 - F(x_0)) \\geq 0$ for all $x \\geq x_0$.

Thus, $g(x) \\geq 0$ for all $x$.")

(md "**2. Integral of $g(x)$ equals 1**

Let's calculate the integral of $g(x)$ over its entire domain:")

(formula "\\int_{-\\infty}^{\\infty} g(x) dx = \\int_{-\\infty}^{x_0} g(x) dx + \\int_{x_0}^{\\infty} g(x) dx")

(md "For the first part of the integral:")

(formula "\\int_{-\\infty}^{x_0} g(x) dx = \\int_{-\\infty}^{x_0} 0 dx = 0")

(md "For the second part:")

(formula "\\int_{x_0}^{\\infty} g(x) dx = \\int_{x_0}^{\\infty} \\frac{f(x)}{1 - F(x_0)} dx")
(formula "= \\frac{1}{1 - F(x_0)} \\int_{x_0}^{\\infty} f(x) dx")
(formula "= \\frac{1}{1 - F(x_0)} [1 - F(x_0)]")
(formula "= 1")

(md "The last step uses the fact that $\\int_{x_0}^{\\infty} f(x) dx = 1 - F(x_0)$, which is the probability that $X > x_0$.")

(md "Therefore, the total integral is:")

(formula "\\int_{-\\infty}^{\\infty} g(x) dx = 0 + 1 = 1")

(answer "We have proven that $g(x)$ satisfies both conditions to be a pdf: it is non-negative for all $x$, and its integral over the entire domain equals 1.")

(md "**Interpretation:**

The function $g(x)$ represents the conditional probability density of $X$ given that $X \\geq x_0$. This is why:

1. It's zero for $x < x_0$, as we're only considering the case where $X \\geq x_0$.
2. For $x \\geq x_0$, it's the original pdf $f(x)$ scaled up by a factor of $1 / (1 - F(x_0))$. This scaling ensures that the total probability for $X \\geq x_0$ sums to 1.

This type of function is often used in survival analysis and reliability theory, where we're interested in the distribution of a variable given that it has survived up to a certain point.")

(md "**Conclusion:**

We have proven that $g(x)$ is indeed a valid probability density function. This result is important in probability theory and statistics, particularly when dealing with truncated or conditional distributions. It shows how we can derive a new pdf from an existing one by conditioning on a certain event (in this case, $X \\geq x_0$). This concept has applications in various fields, including actuarial science, engineering reliability, and medical survival analysis.")