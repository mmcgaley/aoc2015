(ns aoc2015.core
  (:require aoc2015.day1 
            aoc2015.day2
            aoc2015.day3
            aoc2015.day4
            aoc2015.day5
            aoc2015.day6
            aoc2015.day7
            aoc2015.day7split
            aoc2015.day8
            clojure.string)
  (:gen-class))

(defn -main
  "Dispatch to the different day routines for Advent of Code 2015"
  [day & args]
  (let [day-ns (symbol (clojure.string/join "." ["aoc2015" day]))
        day-main (ns-resolve day-ns 'main)]
    (day-main args)))
