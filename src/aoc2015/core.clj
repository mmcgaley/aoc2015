(ns aoc2015.core
  (:require aoc2015.day1 
            aoc2015.day2
            clojure.string)
  (:gen-class))

(defn -main
  "Dispatch to the different day routines for Advent of Code 2015"
  [day & args]
  (let [day-ns (symbol (clojure.string/join "." ["aoc2015" day]))
        day-main (ns-resolve day-ns 'main)]
    (day-main args)))
