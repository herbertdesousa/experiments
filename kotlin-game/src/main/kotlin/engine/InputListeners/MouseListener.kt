package engine.InputListeners

import org.lwjgl.glfw.GLFW.GLFW_PRESS
import org.lwjgl.glfw.GLFW.GLFW_RELEASE

object MouseListener : InputListener(3) {
    private var scrollX = 0.0; var scrollY = 0.0

    private var xPos = 0.0; var yPos = 0.0; var lastX = 0.0; var lastY = 0.0

    var isDragging = false
        private set

    val x get() = xPos

    val y get() = yPos

    val dx get() = lastX - xPos

    val dy get() = lastY - yPos

    val getScrollX get() = scrollX

    val getScrollY get() = scrollY

    fun mousePosCallback(window: Long, _xPos: Double, _yPos: Double) {
        lastX = xPos
        lastY = yPos

        xPos = _xPos
        yPos = _yPos

        isDragging = someKey(true)
    }

    fun mouseButtonCallback(window: Long, key: Int, action: Int, mods: Int) {
        when (action) {
            GLFW_PRESS -> setKey(key, true)
            GLFW_RELEASE -> {
                setKey(key, false)
                isDragging = false
            }
        }

    }

    fun mouseScrollCallback(window: Long, xOffset: Double, yOffset: Double) {
        scrollX = xOffset
        scrollY = yOffset
    }

    fun endFrame() {
        scrollX = 0.0
        scrollY = 0.0
        lastX = xPos
        lastY = yPos
    }
}