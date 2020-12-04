# Advent of Code 2015

Mags & Cian's AoC work!

## Using the code

Each day is written up in a separate namespace, e.g. `aoc2015.day1`.

There are general utilities used across days in `aoc2015.util`.

See the "scaffolding" in `aoc2015.core` that allows us to run each day's script, for example:

    lein run day1 arg0 arg1 arg2

## Resources

Input files etc. can be stashed in `resources/` and imported using one of the utility functions, e.g.

    (aoc2015.util/slurp-resource "input.txt")
