package day10

import util.*

fun main() {
	val input = readInput("day10")
	part1and2(input)
}

fun part1and2(input: List<String>) {
	val grid: MutableMap<Coordinate, Int> = mutableMapOf()
	val startposn: MutableList<Coordinate> = mutableListOf()
	val maxx = input[0].count()
	val maxy = input.count()
	for (i in 0..input.count()-1) {
		val line = input[i].toCharArray().map { c -> c.digitToInt() }
		for (j in 0..line.count()-1) {
			grid[Coordinate(j, i)] = line[j]
			if (line[j] == 0) startposn.add(Coordinate(j, i))
		}
	}
	var scores = 0
	var ratings = 0
	startposn.forEach() {
		val endpoints: MutableSet<Coordinate> = mutableSetOf()
		ratings += searchPaths(it, 0, grid, endpoints, maxx, maxy)
		scores += endpoints.count()
	}
	println(scores)
	println(ratings)
}

fun searchPaths(c: Coordinate, h: Int, grid: Map<Coordinate, Int>, endpoints: MutableSet<Coordinate>, maxx: Int, maxy: Int): Int {
	if (h == 9) {
		endpoints.add(c)
		return 1
	}
	val directions: List<Coordinate.() -> Coordinate> = listOf(Coordinate::N, Coordinate::E, Coordinate::S, Coordinate::W)
	var rating = 0
	for (dir in directions) {
		if (c.dir().inBounds(maxx, maxy) && grid[c.dir()]!! == h+1) rating += searchPaths(c.dir(), h+1, grid, endpoints, maxx, maxy)
	}
	return rating
}