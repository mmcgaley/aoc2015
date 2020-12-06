(ns aoc2015.day7split
  (:require [aoc2015.util :as util]
            [clojure.core.memoize :as m]
            [clojure.string :as s]))

(defn parse-instr
  "return a coll of [op opand1 opand2 ...]"
  [instr]
  (cond
    (s/includes? instr "AND") (conj (map s/trim (s/split instr #"AND")) "AND")
    (s/includes? instr "OR") (conj (map s/trim (s/split instr #"OR")) "OR")
    (s/includes? instr "LSHIFT") (conj (map s/trim (s/split instr #"LSHIFT")) "LSHIFT")
    (s/includes? instr "RSHIFT") (conj (map s/trim (s/split instr #"RSHIFT")) "RSHIFT")
    (s/includes? instr "NOT") (map s/trim (s/split instr #" "))
    :else (list (s/trim instr))))

(defn parse-line
  "return a pair of [wire instr] from a string instr -> wire"
  [line]
  (let [[instr wire] (s/split line #"->")]
  [(s/trim wire) (parse-instr instr)]))

(defn make-map
  "return a map of instructions #{wire instr} where input is a collection of strings"
  [input]
  (into {} (map parse-line input)))

(def calc 
  (m/memo 
   (fn [map wire]
     (if (every? #(Character/isDigit %) wire)
       (Integer/parseInt wire) 
       (let [instr (get map wire)
             f (first instr)]
         (cond 
           (= 1 (count instr)) (calc map f) ; not a number, but just a wire name
           (= "AND" f) (bit-and (calc map (nth instr 1)) (calc map (nth instr 2)))
           (= "OR" f) (bit-or (calc map (nth instr 1)) (calc map (nth instr 2)))
           (= "LSHIFT" f) (bit-shift-left (calc map (nth instr 1)) (calc map (nth instr 2)))
           (= "RSHIFT" f) (bit-shift-right (calc map (nth instr 1)) (calc map (nth instr 2)))
           (= "NOT" f) (bit-not (calc map (nth instr 1)))
           :else (println instr)))))))

(defn override
  [input original]
  (assoc input "b" [(str original)]))

(defn main
  "Day 7 of Advent of Code 2015: Some Assembly Required
      lein run day7split <input>"
  [[filename]]
  (let [input (util/read-lines filename)
        original-map (make-map input)
        original (calc original-map "a")
        override-map (override original-map original)]
    (println "Original:" original)
    (m/memo-clear! calc)
    (println "Override:" (calc override-map "a"))))
