package mpbostock

import kotlin.math.abs

data class Vector2d(val x: Int, val y: Int) {
    fun abs(other: Vector2d) = if ( this.x == other.x ) abs(this.y - other.y) else abs(this.x - other.x)
    companion object {
        fun fromFile(coord: String): Vector2d {
            val csv = coord.split(',').map { it.toInt() }
            return Vector2d(csv[0], csv[1])
        }
    }
}