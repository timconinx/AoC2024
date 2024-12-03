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
	var sum = 0
	results.forEach() {
		//println(it.value)
		val factors = Regex("\\d+").findAll(it.value)
		var prod = 1
		factors.forEach() {
			prod *= it.value.toInt()
		}
		sum += prod
	}
	println(sum)
}

fun part2(input: List<String>) {
	val results = Regex("(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don\\'t\\(\\))").findAll(input[0])
	var sum = 0
	results.forEach() {
		println(it.value)
		/*val factors = Regex("\\d+").findAll(it.value)
		var prod = 1
		factors.forEach() {
			prod *= it.value.toInt()
		}
		sum += prod
	}
	println(sum)*/
}
}