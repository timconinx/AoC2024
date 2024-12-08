package day08

import util.*

fun main() {
	val input = readInput("day08")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	val nodes: MutableMap<Char, MutableList<Coordinate>> = mutableMapOf()
	val antinodes: MutableSet<Coordinate> = mutableSetOf()
	val maxy = input.count()
	val maxx = input[0].count()
	for (i in 0..input.count()-1) {
		val line = input[i].toCharArray()
		for (j in 0..line.count()-1) {
			if (line[j] != '.') {
				if (nodes[line[j]] == null) {
					nodes[line[j]] = mutableListOf()
				}
				nodes[line[j]]!!.add(Coordinate(j, i))
			}
		}
	}
	nodes.keys.forEach() {
		val nod = nodes[it]!!
		for (i in 0..nod.count()-2) {
			for (j in i+1..nod.count()-1) {
				val c1 = nod.elementAt(i)
				val c2 = nod.elementAt(j)
				val antinode1 = Coordinate(c2.x + (c2.x - c1.x), c2.y + (c2.y - c1.y))
				val antinode2 = Coordinate(c1.x - (c2.x - c1.x), c1.y - (c2.y - c1.y))
				if (antinode1.inBounds(maxx, maxy)) antinodes.add(antinode1)
				if (antinode2.inBounds(maxx, maxy)) antinodes.add(antinode2)
			}
		}
	}
	println(antinodes.count())
}

fun part2(input: List<String>) {
	val nodes: MutableMap<Char, MutableList<Coordinate>> = mutableMapOf()
	val antinodes: MutableSet<Coordinate> = mutableSetOf()
	val maxy = input.count()
	val maxx = input[0].count()
	for (i in 0..input.count()-1) {
		val line = input[i].toCharArray()
		for (j in 0..line.count()-1) {
			if (line[j] != '.') {
				if (nodes[line[j]] == null) {
					nodes[line[j]] = mutableListOf()
				}
				nodes[line[j]]!!.add(Coordinate(j, i))
			}
		}
	}
	nodes.keys.forEach() {
		val nod = nodes[it]!!
		for (i in 0..nod.count()-2) {
			for (j in i+1..nod.count()-1) {
				val c1 = nod.elementAt(i)
				val c2 = nod.elementAt(j)
				var k = 0
				var inbounds1 = true
				var inbounds2 = true
				while (inbounds1 || inbounds2) {
					val antinode1 = Coordinate(c2.x + k*(c2.x - c1.x), c2.y + k*(c2.y - c1.y))
					val antinode2 = Coordinate(c1.x - k*(c2.x - c1.x), c1.y - k*(c2.y - c1.y))
					if (antinode1.inBounds(maxx, maxy)) antinodes.add(antinode1) else inbounds1 = false
					if (antinode2.inBounds(maxx, maxy)) antinodes.add(antinode2) else inbounds2 = false
					k++
				}
			}
		}
	}
	println(antinodes.count())
}