^:kindly/hide-code
(ns index
  (:require 
       [assignments.hw3.utils :refer [question answer md]])
  (:import [java.time LocalDate]
           [java.time.format DateTimeFormatter]))

(let [formatter (DateTimeFormatter/ofPattern "M/d/yy")
      current-date (str (.format (LocalDate/now) formatter))]
  (md (str "

### Jaryt Salvo
**Date:** **" current-date "**

**Fall 2024 | MATH6410 Probability Theory**

*************

This project contains solutions to Homework 3 from the Probability Theory course (Math 6410) using Clojure. The code provided here demonstrates a functional programming approach to solving various probability problems, including discrete and continuous probability distributions, conditional probability, and applications to real-world scenarios.

The code is organized into different sections corresponding to each homework problem, with detailed explanations of the logic and mathematical steps involved. By using Clojure and its associated libraries, such as `scicloj.clay` for rendering, `fastmath` for mathematical operations, and custom utility functions, we provide a comprehensive solution set for the assigned problems.

#### Problem Overview

1. Sigma Algebras: Proving properties of set collections.
2. Subset Counting: Proving the number of subsets for a set with n elements.
3. Occupancy Problem: Calculating probabilities for balls in cells.
4. Coin Flipping Game: Analyzing probabilities in a sequential game.
5. Conditional Probability: Rodent litter problem using Bayes' theorem.
6. Probability Density Functions: Proving properties of a defined function.
7. AI Error Distribution: Applying Stirling numbers to a practical scenario.
8. Covid Symptom Spread: Modeling disease spread in a waiting room.

#### Utils

The `utils.clj` file contains various utility functions and helpers used throughout the homework solutions. It includes:

- Formatting functions for markdown and LaTeX rendering
- Statistical functions for probability distributions
- Helper functions for calculations and data manipulations

These utilities are designed to streamline the problem-solving process and provide reusable components for statistical computations.

The code in the `src/assignments` folder was rendered with [Clay](https://scicloj.github.io/clay/) and deployed with [Github Pages](https://pages.github.com/).")))

