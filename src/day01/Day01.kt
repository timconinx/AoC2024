package day01

import kotlin.collections.mutableListOf
import kotlin.math.abs
import kotlin.collections.listOf
import kotlin.collections.mutableMapOf

import util.*

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
		similarityleft[items[0]] = if (items[0] in similarityleft.keys) similarityleft[items[0]]!! + 1 else 1
		similarityright[items[1]] = if (items[1] in similarityright.keys) similarityright[items[1]]!! + 1 else 1
	}
	var score = 0
	similarityleft.keys.forEach() {
		score += it.toInt() * similarityleft[it]!! * (similarityright[it] ?: 0)
	}
	println(score)
}