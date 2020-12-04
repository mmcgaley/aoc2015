(ns aoc2015.day1
  (:require [aoc2015.util :as util]))

(defn travel
  "Travel up or down a floor, depending on the direction"
  [floors direction]
  (cond
    (= direction \() (conj floors (inc (last floors)))
    (= direction \)) (conj floors (dec (last floors)))))

(defn floor-directions
  "Read input to determine Santa's directions"
  [input]
  (reduce travel [0] input))

(defn main
  "Day 1 of Advent of Code 2015: Not Quite Lisp
      lein run day1 <input>
   where <input> is a filename in project resources/"
  [[filename]]
  (let [input (util/slurp-resource filename)
        directions (floor-directions input)]
    (println "Floor reached:" (last directions))
    (println "First basement index:" (->> directions
                                          (take-while #(>= % 0))
                                          (count)
                                          ))))