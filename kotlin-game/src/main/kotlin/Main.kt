import engine.InputListeners.KeyListener
import engine.InputListeners.MouseListener
import engine.Window
import org.lwjgl.glfw.GLFW

fun main() {
    Window.run(
        onStart = { window ->
            GLFW.glfwSetCursorPosCallback(window, MouseListener::mousePosCallback)
            GLFW.glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback)
            GLFW.glfwSetScrollCallback(window, MouseListener::mouseScrollCallback)
            GLFW.glfwSetKeyCallback(window, KeyListener::keyCallback)
        },
        onUpdate = {
            if (KeyListener.getKey(GLFW.GLFW_KEY_SPACE)) {
                println("Space pressed")
            }

            if (MouseListener.getKey(GLFW.GLFW_MOUSE_BUTTON_1)) {
                println("Mouse pressed")
            }
        }
    )
}