package day05

import util.*

fun main() {
	val input = readInput("day05")
	part1and2(input)
}

fun part1and2(input: List<String>) {
	// boolean -> before = true, after = false
	val rules: MutableMap<Int, MutableList<Pair<Boolean, Int>>> = mutableMapOf()
	var i = 0
	while (input[i] != "") {
		val pair = input[i].split("|").map { v -> v.toInt() }
		for (j in 0..1) {
			if (rules[pair[j]] == null) {
				rules[pair[j]] = mutableListOf()
			}
			val rule = if (j == 0) true else false
			var other = if (j == 0) 1 else 0
			rules[pair[j]]!!.add(Pair(rule, pair[other]))
		}
		i++
	}
	i++
	var sumcorrect = 0
	var sumfixed = 0
	while (i < input.count()) {
		val pages = input[i].split(",").map { v -> v.toInt() }.toTypedArray()
		val index: MutableMap<Int, Int> = mutableMapOf()
		for (j in 0..pages.count()-1) {
			index[pages[j]] = j
		}
		var rulebroken = false
		for (j in 0..pages.count()-2) {
			for (k in j+1..pages.count()-1) {
				val page1 = pages[j]
				val page2 = pages[k]
				if (rules[page1] != null) {
					rules[page1]!!.forEach() {
						if (it.second == page2) {
							val wrong = if (it.first) (index[page1]!! > index[page2]!!) else (index[page1]!! < index[page2]!!)
							rulebroken = rulebroken || wrong
						}
					}
					if (rulebroken) {
						break
					}
				}
			}
			if (rulebroken) {
				break
			}
		}
		if (!rulebroken) {
			sumcorrect += pages[(pages.count()-1)/2]
		} else {
			for (j in 0..pages.count()-2) {
				for (k in j+1..pages.count()-1) {
					val page1 = pages[j]
					val page2 = pages[k]
					if (rules[page1] != null) {
						var wrong = false
						rules[page1]!!.forEach() {
							if (it.second == page2) {
								wrong = if (it.first) (index[page1]!! > index[page2]!!) else (index[page1]!! < index[page2]!!)
							}
						}
						if (wrong) {
							pages[j] = pages[k].also { pages[k] = pages[j]}
							index[pages[j]] = j
							index[pages[k]] = k
						}
					}
				}
			}
			sumfixed += pages[(pages.count()-1)/2]		
		}
		i++
	}
	println(sumcorrect)
	println(sumfixed)
}
