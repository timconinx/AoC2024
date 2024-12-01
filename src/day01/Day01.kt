package day01

import kotlin.collections.mutableListOf
import kotlin.math.abs
import kotlin.collections.listOf
import kotlin.collections.mutableMapOf

fun main() {
	val input = readInput("day01")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	val left: MutableList<Int> = mutableListOf()
	val right: MutableList<Int> = mutableListOf()
	input.forEach() {
		val items = it.split("\\s+".toRegex())
		left.add(items[0].toInt())
		right.add(items[1].toInt())
	}
	left.sort()
	right.sort()
	var sum = 0
	for (i in 0..input.count()-1) {
		sum += abs(left[i] - right[i])
	}
	println(sum)
}

fun part2(input: List<String>) {
	val similarityleft: MutableMap<String, Int?> = mutableMapOf()
	val similarityright: MutableMap<String, Int?> = mutableMapOf()
	input.forEach() {
		val items = it.split("\\s+".toRegex())
		if (items[0] in similarityleft.keys) {
			similarityleft[items[0]] = similarityleft[items[0]]!! + 1
		} else {			
			similarityleft[items[0]] = 1
		}
		if (items[1] in similarityright.keys) {
			similarityright[items[1]] = similarityright[items[1]]!! + 1
		} else {			
			similarityright[items[1]] = 1
		}
	}
	var score = 0
	similarityleft.keys.forEach() {
		val simright = similarityright[it] ?: 0
		score += it.toInt() * similarityleft[it]!! * simright
	}
	println(score)
}