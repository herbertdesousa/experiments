package engine.util

object Time {
    fun now() = System.nanoTime() * 1E-9

    fun addSeconds(time: Double, seconds: Float): Double = (time + seconds)
}