(ns aoc2015.day1
  (:require [aoc2015.util :as util]))

(defn floor-directions
  "Read input to determine Santa's directions"
  [input]
  (println (first input)))

(defn main
  "Day 1 of Advent of Code 2015: Not Quite Lisp
      lein run day1 <input>
   where <input> is a filename in project resources/"
  [[filename]]
  (println (->> (util/slurp filename)
                (floor-directions))))
