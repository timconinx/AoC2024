package day06

import util.*

fun main() {
	val input = readInput("day06")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	val directions: List<Coordinate.() -> Coordinate> = listOf(Coordinate::N, Coordinate::E, Coordinate::S, Coordinate::W)
	var currentdiridx = 0
	var currentdir = directions[0]
	var c: Coordinate = Coordinate(0,0)
	val grid: MutableMap<Coordinate, Char> = mutableMapOf()
	for (i in 0..input.count() -1) {
		val line = input[i].toCharArray()
		for (j in 0..line.count()-1) {
			grid[Coordinate(j, i)] = line[j]
			if (line[j] == '^') c = Coordinate(j, i)
		}
	}
	val allpositions: MutableSet<Coordinate> = mutableSetOf()
	allpositions.add(c)
	while (c.inBounds(input[0].count(), input.count())) {
		while (grid[c.currentdir()] == '#') {
			currentdiridx = (currentdiridx + 1)%4
			currentdir = directions[currentdiridx]
		}
		c = c.currentdir()
		allpositions.add(c)
	}
	println(allpositions.count()-1)
}

data class DirCoordinate(val c: Coordinate, val d: Int)

fun part2(input: List<String>) {
	val directions: List<Coordinate.() -> Coordinate> = listOf(Coordinate::N, Coordinate::E, Coordinate::S, Coordinate::W)
	var start: Coordinate = Coordinate(0,0)
	val grid: MutableMap<Coordinate, Char> = mutableMapOf()
	for (i in 0..input.count() -1) {
		val line = input[i].toCharArray()
		for (j in 0..line.count()-1) {
			grid[Coordinate(j, i)] = line[j]
			if (line[j] == '^') start = Coordinate(j, i)
		}
	}
	// first run
	val allpositions: MutableSet<Coordinate> = mutableSetOf()
	var c: Coordinate = start
	allpositions.add(start)
	var currentdiridx = 0
	var currentdir = directions[0]
	while (c.inBounds(input[0].count(), input.count())) {
		while (grid[c.currentdir()] == '#') {
			currentdiridx = (currentdiridx + 1)%4
			currentdir = directions[currentdiridx]
		}
		c = c.currentdir()
		if (c.inBounds(input[0].count(), input.count())) {
		    allpositions.add(c)
		}
	}
	allpositions.remove(start)
	// try all positions
	var allfound = 0
	for (b in allpositions) {
		var currentdiridx2 = 0
		var currentdir2 = directions[0]
		var found = false
		c = start
		val posanddir: MutableSet<DirCoordinate> = mutableSetOf()
		posanddir.add(DirCoordinate(c, 0))
		while (c.inBounds(input[0].count(), input.count()) && !found) {
			while (grid[c.currentdir2()] == '#' || c.currentdir2() == b) {
				currentdiridx2 = (currentdiridx2 + 1)%4
				currentdir2 = directions[currentdiridx2]
			}
			c = c.currentdir2()
			val newpair = DirCoordinate(c, currentdiridx2)
			if (posanddir.contains(newpair)) {
				allfound++
				found = true
			} else {
				posanddir.add(newpair)
			}
		}
	}
	println(allfound)
}