package util

data class Coordinate(val x: Int, val y: Int) {
	fun N(): Coordinate {
		return Coordinate(this.x, this.y-1)
	}
	fun NE(): Coordinate {
		return Coordinate(this.x+1, this.y-1)
	}
	fun E(): Coordinate {
		return Coordinate(this.x+1, this.y)
	}
	fun SE(): Coordinate {
		return Coordinate(this.x+1, this.y+1)
	}
	fun S(): Coordinate {
		return Coordinate(this.x, this.y+1)
	}
	fun SW(): Coordinate {
		return Coordinate(this.x-1, this.y+1)
	}
	fun W(): Coordinate {
		return Coordinate(this.x-1, this.y)
	}
	fun NW(): Coordinate {
		return Coordinate(this.x-1, this.y-1)
	}
	fun inBounds(maxx: Int, maxy: Int): Boolean {
		return (x > -1) && (x < maxx) && (y > -1) && (y < maxy)
	}
}