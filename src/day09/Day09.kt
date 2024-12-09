package day09

import util.*

fun main() {
	val input = readInput("day09")
	part1(input)
	part2(input)
}

fun part1(input: List<String>) {
	val line = input[0].toCharArray().map { c -> c.digitToInt() }
	var diskindex = 0
	val disk: MutableList<Int> = mutableListOf()
	var fileindex = 0
	while (fileindex < line.count()) {
		// first data
		for (i in 0..line[fileindex]-1) {
			disk.add(diskindex)
		}
		diskindex++
		fileindex++
		// then empty space
		if (fileindex < line.count()) {
			for (i in 0..line[fileindex]-1) {
				disk.add(-1)
			}
		}
		fileindex++
	}
	//println(disk)
	var start = 0
	var end = disk.count()-1
	while (true) {
		val posempty = firstEmptyFrom(start, disk)
		val posfilled = lastFilledFrom(end, disk)
		if (posempty < posfilled) {
			disk[posempty] = disk.elementAt(posfilled).also { disk[posfilled] = disk.elementAt(posempty)}
		} else {
			break
		}
	}
	//println(disk)
	var checksum = 0L
	var i = 0
	while (disk[i] > -1) {
		checksum += i * disk[i]
		i++
	}
	println(checksum)
}

fun firstEmptyFrom(start: Int, disk: List<Int>): Int {
	var i = start
	while (i < disk.count()) {
		if (disk[i] == -1) {
			return i
		}
		i++
	}
	return -1
}

fun lastFilledFrom(start: Int, disk: List<Int>): Int {
	var i = start
	while (i > -1) {
		if (disk[i] > -1) {
			return i
		}
		i--
	}
	return -1
}

data class File(val id: Int, var start: Int, val size: Int)

fun part2(input: List<String>) {
	val line = input[0].toCharArray().map { c -> c.digitToInt() }
	var diskindex = 0
	val disk: MutableList<Int> = mutableListOf()
	val files: MutableMap<Int, File> = mutableMapOf()
	var fileindex = 0
	while (fileindex < line.count()) {
		// first data
		files[diskindex] = File(diskindex, disk.count(), line[fileindex])
		for (i in 0..line[fileindex]-1) {
			disk.add(diskindex)
		}
		diskindex++
		fileindex++
		// then empty space
		if (fileindex < line.count()) {
			for (i in 0..line[fileindex]-1) {
				disk.add(-1)
			}
		}
		fileindex++
	}
	var start = 0
	var end = disk.count()-1
	while (true) {
		val lastpos = lastFilledFrom(end, disk)
		if (lastpos == -1) break
		val filesize = files[disk[lastpos]]!!.size
		val filestart = files[disk[lastpos]]!!.start
		val firstpos = firstEmptyBlockOfSizeFromUntil(start, lastpos, filesize, disk)
		if (firstpos > -1) {
			for (i in 0..filesize-1) disk[firstpos+i] = disk[filestart+i].also { disk[filestart+i] = disk[firstpos+i] }
			files[disk[firstpos]]!!.start = firstpos
		}
		end = filestart-1
	}
	var checksum = 0L
	for (i in 0..disk.count()-1) {
		val data = disk[i]
		if (data > -1) checksum += data * i
	}
	println(checksum)
}

fun firstEmptyBlockOfSizeFromUntil(start: Int, until: Int, size: Int, disk: List<Int>): Int {
	var i = start
	while (i < until) {
		if (disk[i] == -1) {
			val foundidx = i
			while (i < disk.count() && disk[i] == -1) {
				if (i - foundidx + 1 == size) return foundidx
				i++
			}
		}
		i++
	}
	return -1
}