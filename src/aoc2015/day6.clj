(ns aoc2015.day6
  (:require [aoc2015.util :as util]
            [digest])
  ; https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html
  (:import java.util.BitSet))

(defn light-lines
  "Return a coll of pairs of [from, to] indexing block of lights"
  [x1 y1 x2 y2]
  (let [width 1000]
    (map (fn [y] [(+ (* y width) x1) (+ (* y width) (inc x2))]) (range y1 (inc y2)))))

(defn do-binary-instr
  "Parse an instruction of the form
  [turn on / turn off / toggle] x1,y1 through x2,y2"
  [lights instr]
  (let [[_ instr-type & args] (re-matches #"(.+) (\d+),(\d+) through (\d+),(\d+)" instr)
        [x1 y1 x2 y2] (map #(Integer/parseInt %) args)
        op ({"turn on" (fn [lights [from to]] (.set lights from to))
             "turn off" (fn [lights [from to]] (.clear lights from to))
             "toggle" (fn [lights [from to]] (.flip lights from to))} instr-type)]
    (doall (map #(op lights %) (light-lines x1 y1 x2 y2)))))

(defn modify-by
  "Return a new vector of lights where we have added delta to the values in [from to],
   with a floor at 0 when delta is negative"
  [delta lights from to]
  (reduce (fn [input index] (assoc input index (max 0 (+ delta (nth input index)))))
          lights
          (range from to)))

(defn do-variable-instr
  "Parse an instruction of the form
  [turn on / turn off / toggle] x1,y1 through x2,y2"
  [lights instr]
  (let [[_ instr-type & args] (re-matches #"(.+) (\d+),(\d+) through (\d+),(\d+)" instr)
        [x1 y1 x2 y2] (map #(Integer/parseInt %) args)
        op ({"turn on" (fn [lights [from to]] (modify-by 1 lights from to))
             "turn off" (fn [lights [from to]] (modify-by -1 lights from to))
             "toggle" (fn [lights [from to]] (modify-by 2 lights from to))} instr-type)]
    (reduce (fn [input pair] (op input pair))
            lights
            (light-lines x1 y1 x2 y2))))

(defn main
  "Day 6 of Advent of Code 2015: Probably a Fire Hazard
      lein run day6"
  [[filename]]
  (let [binary-lights (new BitSet 1000000)
        variable-lights (vec (take 1000000 (repeat 0)))]
    (doall (map #(do-binary-instr binary-lights %) (util/read-lines filename)))
    (println "Number of binary lights on:" (.cardinality binary-lights))
    (println "Sum of variable lights:" (apply + (reduce (fn [lights instr] (do-variable-instr lights instr))
                                                        variable-lights
                                                        (util/read-lines filename))))))
