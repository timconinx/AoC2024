package day07

class EqSolver(val target: Long) {
	val plus: (Long, Long) -> Long = { a, b -> a+b }
	val times: (Long, Long) -> Long = { a, b -> a*b }
	val concat: (Long, Long) -> Long = { a, b -> "$a$b".toLong() }
	
	fun SolveFor(i: Long, next: MutableList<Long>): Boolean {
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
	
	fun SolveForConcat(i: Long, next: MutableList<Long>): Boolean {
		if (next.isEmpty()) {
			if (i == target) return true else return false
		}
		val newNext = next.toMutableList()
		val first = newNext.removeAt(0)
		if (concat(i, first) > target && times(i, first) > target && plus(i, first) > target) {
			return false
		}
		if (concat(i, first) > target && times(i, first) > target) return SolveForConcat(plus(i, first), newNext)
		if (concat(i, first) > target && plus(i, first) > target) return SolveForConcat(times(i, first), newNext)
		if (times(i, first) > target && plus(i, first) > target) return SolveForConcat(concat(i, first), newNext)
		
		if (concat(i, first) > target) return SolveForConcat(times(i, first), newNext) || SolveForConcat(plus(i, first), newNext)
		if (times(i, first) > target) return SolveForConcat(concat(i, first), newNext) || SolveForConcat(plus(i, first), newNext)
		if (plus(i, first) > target) return SolveForConcat(concat(i, first), newNext) || SolveForConcat(times(i, first), newNext)
		return SolveForConcat(concat(i, first), newNext) || SolveForConcat(times(i, first), newNext) || SolveForConcat(plus(i, first), newNext)
	}
}