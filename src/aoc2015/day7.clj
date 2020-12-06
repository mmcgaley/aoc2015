(ns aoc2015.day7
  (:require [aoc2015.util :as util]
            [clojure.string :as s]
            [clojure.pprint :as pp]
            [instaparse.core :as insta]))

(def parse-instructions
  (insta/parser
    "instr = stmt (<nl> stmt)*
     stmt = expr <ws> <'->'> <ws> output
     expr = wire |
            wire <ws> binop <ws> wire |
            number <ws> binop <ws> wire |
            wire <ws> shiftop <ws> number |
            unop <ws> wire |
            number
     output = wire
     binop = 'AND' | 'OR'
     shiftop = 'LSHIFT' | 'RSHIFT'
     unop = 'NOT'
     ws = #'\\s+'
     nl = #'[\n]+'
     wire = #'[a-z]+'
     number = #'[0-9]+'"))

(defn transform-statement
  ""
  [statement]
  [(last (last (last statement))) (rest (first statement))])

(defn transform-instructions
  "Transform parser output into a wire -> expression map"
  [instructions]
  (let [statements (map rest (rest instructions))
        output-exprs (map transform-statement statements)]
    (into {} output-exprs)))

(defn calc
  "recursively calculate the value associated with wire"
  [wire]
  ; find wire in map
  ; foreach instruction that has an uncalc'ed wire, recursively run calc 
  ; https://clojuredocs.org/clojure.core/recur
  ; return op of values
)

(defn main
  "Day 7 of Advent of Code 2015: Some Assembly Required
      lein run day7 <input>"
  [[filename]]
  (let [input (s/trim (util/slurp-resource filename))]
    (pp/pprint (transform-instructions (parse-instructions input)))))
