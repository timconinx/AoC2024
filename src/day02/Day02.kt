package day02

import util.*

fun main() {
	val input = readInput("day02")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	var safe = 0
	for (inp in 0..input.count()-1) {
		val items = input[inp].split("\\s+".toRegex())
		val myfun: (Int, Int) -> Int
		if (items[0].toInt() < items[1].toInt()) {
			myfun = { a: Int, b:Int -> a - b}
		} else if (items[0].toInt() > items[1].toInt()) {
			myfun = { a: Int, b:Int -> b - a}
		} else {
			continue
		}
		var i = 0
		while (i < items.count()-1){
			val diff = myfun(items[i+1].toInt(), items[i].toInt())
			if (diff < 1 || diff > 3) {
				break
			}
			i++
		}
		if (i == items.count()-1){
			safe++
		}
	}
	println(safe)
}
fun part2(input: List<String>) {
	var safe = 0
	for (inp in 0..input.count()-1) {
		val items: MutableList<String> = mutableListOf()
		items.addAll(input[inp].split("\\s+".toRegex()))		
		var dampened = false
		if (items[0] == items[1]) {
			dampened = true
			items.removeAt(0)
		}
		safe += if (checkForSafe(items, dampened)) 1 else 0
	}
	println(safe)
}

fun checkForSafe(items: List<String>, dampened: Boolean): Boolean {
	val myfun: (Int, Int) -> Int
	if (items[0].toInt() < items[1].toInt()) {
		myfun = { a: Int, b:Int -> a - b}
	} else if (items[0].toInt() > items[1].toInt()) {
		myfun = { a: Int, b:Int -> b - a}
	} else {
		return false
	}
	var i = 0
	while (i < items.count()-1){
		val diff = myfun(items[i+1].toInt(), items[i].toInt())
		if (diff < 1 || diff > 3) {
			if (dampened) {
				return false
			} else {
				if (i > 0) {
				    	// try with removing previous value
					val withoutprevious = items.toMutableList()
					withoutprevious.removeAt(i-1)
					if (checkForSafe(withoutprevious, true)) return true
				}
				// try with remove current value
				val withoutcurrent = items.toMutableList()
				withoutcurrent.removeAt(i)
				if (checkForSafe(withoutcurrent, true)) return true
				if (i < items.count()-1) {
					// try with removing next value
					val withoutnext = items.toMutableList()
					withoutnext.removeAt(i+1)
					if (checkForSafe(withoutnext, true)) return true
				}
				return false
			}
		}
		i++
	}
	return true
}