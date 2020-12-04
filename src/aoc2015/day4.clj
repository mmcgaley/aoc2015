(ns aoc2015.day4
  (:require [aoc2015.util :as util]
            [clojure.string :as s]
            [digest]))

(defn test-one
  "Test one number to see if it's an AdventCoin for secret-key"
  [secret-key number target]
  (s/starts-with? (digest/md5 (str secret-key number)) target))

(defn mine
  "Mine the first AdventCoin for the input key"
  [secret-key target]
  (first (drop-while #(not (test-one secret-key % target)) (range))))

(defn main
  "Day 4 of Advent of Code 2015: The Ideal Stocking Stuffer
      lein run day4"
  [[secret-key]]
  (println "5-zero coin:" (mine secret-key "00000"))
  (println "6-zero coin:" (mine secret-key "000000")))
