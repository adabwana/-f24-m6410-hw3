(ns dev
  (:require [scicloj.clay.v2.api :as clay]))

(defn build []
  (clay/make!
   {:format              [:quarto :html]
    :book                {:title "Homework 3"}
    :subdirs-to-sync     ["notebooks" "data"]
    :source-path         ["src/index.clj"
                          "src/assignments/hw3/utils.clj"
                          "src/assignments/hw3/q1.clj"
                          "src/assignments/hw3/q2.clj"
                          "src/assignments/hw3/q3.clj"
                          "src/assignments/hw3/q4.clj"
                          "src/assignments/hw3/q5.clj"
                          "src/assignments/hw3/q6.clj"
                          "src/assignments/hw3/q7.clj"
                          "src/assignments/hw3/q8.clj"
                          "src/assignments/hw3/q7_methods.clj"]
    :base-target-path    "docs"
    :clean-up-target-dir true}))

(comment
  (build))
