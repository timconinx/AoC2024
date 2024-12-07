package day07

import util.*

fun main() {
	val input = readInput("day07_test")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	var correct = 0L
	input.forEach() {
		val parts = it.split(":\\s+".toRegex())
		val target = parts[0].toLong()
		val eqsolver = EqSolver(target)
		val numbers = parts[1].split(" ").map { v -> v.toLong()}.toMutableList()
		val first = numbers.removeAt(0)
		if (eqsolver.SolveFor(first, numbers)) correct += target
	}
	println(correct)
}

fun part2(input: List<String>) {
	var correct = 0L
	input.forEach() {
		val parts = it.split(":\\s+".toRegex())
		val target = parts[0].toLong()
		val eqsolver = EqSolver(target)
		val numbers = parts[1].split(" ").map { v -> v.toLong()}.toMutableList()
		val first = numbers.removeAt(0)
		if (eqsolver.SolveForConcat(first, numbers)) correct += target
	}
	println(correct)
}