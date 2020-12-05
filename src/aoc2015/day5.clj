(ns aoc2015.day5
  (:require [aoc2015.util :as util]
            [clojure.string :as s]
            [digest]))

(defn is-nice1?
  "returns TRUE if line is nice FALSE if naughty
   naughty: contains ab cd pq or xy
   nice: contains three vowels
   nice: contains at least one letter that appears twice in a row"
  [line]
  (and 
   (not (or (s/includes? line "ab")
            (s/includes? line "cd") 
            (s/includes? line "pq") 
            (s/includes? line "xy")))
   (<= 3 (count (filter #(util/in? [\a \e \i \o \u] %) line)))
   (util/not-nil? (re-find #"(.)\1" line))))

(defn is-nice2?
  "returns TRUE if any pair of characters appears twice (not overlapping)
   and at least one letter repeats with exactly one letter between the two instances"
  [line]
  (and
   (util/not-nil? (re-find #"(..).*\1" line))
   (util/not-nil? (re-find #"(.).\1" line))))

(defn main
  "Day 5 of Advent of Code 2015: Doesn't He Have Intern-Elves For This?
      lein run day5"
  [[filename]]
  (println "Number of nice lines (part 1):" (count (filter is-nice1? (util/read-lines filename))))
  (println "Number of nice lines (part 2):" (count (filter is-nice2? (util/read-lines filename)))))
