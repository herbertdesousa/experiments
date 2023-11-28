package engine.InputListeners

import org.lwjgl.glfw.GLFW.GLFW_PRESS
import org.lwjgl.glfw.GLFW.GLFW_RELEASE

object KeyListener : InputListener(350) {
    fun keyCallback(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
        when(action) {
            GLFW_PRESS -> setKey(key, true)
            GLFW_RELEASE -> setKey(key, false)
        }
    }
}
