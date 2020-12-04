(ns aoc2015.day3
  (:require [aoc2015.util :as util]
            [clojure.set :as setstuff]))

(defn move
  "determine coordinates of next house"
  [[x y] direction]
   (cond
     (= direction \<) [(dec x) y]
     (= direction \^) [x (inc y)]
     (= direction \>) [(inc x) y]
     (= direction \v) [x (dec y)]
     :else [x y]))

(defn travel
  "Travel to the next house < ^ > or v"
  [[current visited] direction]
  (let [new (move current direction)]    
    [new (conj visited new)]))

(defn radio-directions
  "Read input to determine Santa's directions"
  [input]
  (reduce travel [[0 0] #{[0 0]}] input))

(defn robo-santa
  "Split the list of directions into two lists (even indices, odd indices). Travel one, 
   the other, merge sets of visited houses"
  [directions]
  (let [evens (take-nth 2 (rest directions))
        odds (take-nth 2 directions)
        [_ visited-even] (radio-directions evens)
        [_ visited-odd] (radio-directions odds)]
    (setstuff/union visited-even visited-odd)))

(defn main
  "Day 3 of Advent of Code 2015: Perfectly Spherical Houses in a Vacuum
      lein run day3 <input>
   where <input> is a filename in project resources/"
  [[filename]]
  (let [line (util/slurp-resource filename)
        [_ visited-alone] (radio-directions line)
        visited-w-robot (robo-santa line)]
    (println "Number of house visited:" (count visited-alone))
    (println "Number of house visited with robot:" (count visited-w-robot))))
