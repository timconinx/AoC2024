package day07

class EqSolver(val target: Int) {
	val plus: (Int, Int) -> Int = { a, b -> a+b }
	val times: (Int, Int) -> Int = { a, b -> a*b }
	
	fun SolveFor(i: Int, next: MutableList<Int>): Boolean {
		if (next.isEmpty()) {
			if (i == target) return true else return false
		}
		val newNext = next.toMutableList()
		val first = newNext.removeAt(0)
		if (times(i, first) > target && plus(i, first) > target) {
			return false
		}
		if (times(i, first) > target) return SolveFor(plus(i, first), newNext)
		if (plus(i, first) > target) return SolveFor(times(i, first), newNext)
		return SolveFor(times(i, first), newNext) || SolveFor(plus(i, first), newNext)
	}
}