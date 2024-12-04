package day04

import util.*
import kotlin.collections.mutableMapOf
import kotlin.collections.listOf
import kotlin.collections.mapOf

fun main() {
	val input = readInput("day04")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	val directions: List<Coordinate.() -> Coordinate> = listOf(Coordinate::N, Coordinate::NE, Coordinate::E, Coordinate::SE,
                                                               Coordinate::S, Coordinate::SW, Coordinate::W, Coordinate::NW)
	val grid: MutableMap<Coordinate, String> = mutableMapOf()
	for (i in 0..input.count() -1) {
		val line = input[i].split("")
		for (j in 0..line.count()-1) {
			grid[Coordinate(j, i)] = line[j]
		}
	}
	var totalXMAS = 0
	for (c in grid.keys) {
		if (grid[c] == "X") {
			for (dir in directions) {
				if (grid[c.dir()] == "M" && grid[c.dir().dir()] == "A" && grid[c.dir().dir().dir()] == "S") {
					totalXMAS++
				}
			}
		}
	}
	println(totalXMAS)
}

fun part2(input: List<String>) {
	// possible directions of a star-"A"
	val directions: List<Coordinate.() -> Coordinate> = listOf(Coordinate::NE, Coordinate::SE,
                                                               Coordinate::SW, Coordinate::NW)
	// map of "A" to other "M", other "S"
	val crossmap: Map<Coordinate.() -> Coordinate, List<Coordinate.() -> Coordinate>> = mapOf(
		Coordinate::NE to listOf(Coordinate::NW, Coordinate::SE),
		Coordinate::SE to listOf(Coordinate::NE, Coordinate::SW),
		Coordinate::SW to listOf(Coordinate::SE, Coordinate::NW),
		Coordinate::NW to listOf(Coordinate::SW, Coordinate::NE)
	)
	
	val grid: MutableMap<Coordinate, String> = mutableMapOf()
	for (i in 0..input.count() -1) {
		val line = input[i].split("")
		for (j in 0..line.count()-1) {
			grid[Coordinate(j, i)] = line[j]
		}
	}
	var totalMASX = 0
	for (c in grid.keys) {
		if (grid[c] == "M") {
			for (dir in directions) {
				if (grid[c.dir()] == "A" && grid[c.dir().dir()] == "S") {
					val other0 = crossmap[dir]!![0]
					val other1 = crossmap[dir]!![1]
					if ((grid[c.dir().other0()] == "M" && grid[c.dir().other1()] == "S") || (grid[c.dir().other0()] == "S" && grid[c.dir().other1()] == "M")) {
						totalMASX++	
					}
				}
			}
		}
	}
	println(totalMASX/2)
}
