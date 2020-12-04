(ns aoc2015.day2
  (:require [aoc2015.util :as util]
             [clojure.string :as s]))

(defn parse-parcel
  "Parse the input line of the form AxBxC"
  [line] 
  (sort (map #(Integer/parseInt %) (s/split line #"x"))))

(defn calc-paper
  "Calculate the amount of paper for one parcel. Enough for each face plus the smallest face."
  [[a b c]]
  (+ (* 3 a b) (* 2 a c) (* 2 b c)))

(defn calc-ribbon
  "Calculate the amount of ribbon for one parcel. Perimeter of smallest face plus the volume of the parcel."
  [[a b c]]
  (+ (* 2 (+ a b)) (* a b c)))

(defn main
  "Day 2 of Advent of Code 2015: I Was Told There Would Be No Math
      lein run day2 <input>
   where <input> is a filename in project resources/"
  [[filename]]
  (let [lines (util/read-lines filename)
        parcels (map parse-parcel lines)]
    (println "Paper required:" (apply + (map calc-paper parcels)))
    (println "Ribbon required:" (apply + (map calc-ribbon parcels)))))