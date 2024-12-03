package day03

import util.*
import kotlin.text.Regex

fun main() {
	val input = readInput("day03_test")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	val results = Regex("mul\\(\\d+,\\d+\\)").findAll(input[0])
	val sum = results.map { r -> Regex("\\d+").findAll(r.value).map { v -> v.value.toInt() }.fold(1) { acc, p -> acc * p } }.fold(0) { acc, s -> acc + s }
	println(sum)
}

fun part2(input: List<String>) {
	val results = Regex("(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don\\'t\\(\\))").findAll(input[0])
	var sum = 0
	var enabled = true
	results.forEach() {
		if (Regex("do\\(\\)").containsMatchIn(it.value)) {
			enabled = true
		} else if (Regex("don\\'t\\(\\)").containsMatchIn(it.value)) {
			enabled = false
		} else if (enabled){
			sum += Regex("\\d+").findAll(it.value).map { v -> v.value.toInt() }.fold(1) { acc, p -> acc * p }
		}
	}
	println(sum)
}