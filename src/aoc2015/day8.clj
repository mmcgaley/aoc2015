(ns aoc2015.day8
  (:require [aoc2015.util :as util]
            [clojure.string :as s]))

(defn special-overhead
  [[_ s]]
  (if (= s "\"") 1 (dec (count s))))

(defn count-overhead
  [line] 
  (let [matches (re-seq #"(\\\\|\\\"|\"|\\x[0-9a-f]{2})" line)]
    (apply + (map special-overhead matches))))

(defn escape
  [line]
  (str \"
       (apply str (map (fn [c] (cond
                          (= \" c) "\\\""
                          (= \\ c) "\\\\"
                          :else c)) line))
       \"))

(defn main
  "Day 8 of Advent of Code 2015: Matchsticks 
      lein run day8 <input>"
  [[filename]]
  (let [input (util/read-lines filename)]
    (println "Total overhead of special characters" (apply + (map count-overhead input)))
    (println "Double-escaped overhead of special characters" (apply + (map #(count-overhead (escape %)) input)))))