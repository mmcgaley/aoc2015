(defproject aoc2015 "0.1.0-SNAPSHOT"
  :description "Advent of Code 2015"
  :url "https://github.com/mmcgaley/aoc2015"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [digest "1.4.9"]
                 [instaparse "1.4.10"]]
  :main ^:skip-aot aoc2015.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
