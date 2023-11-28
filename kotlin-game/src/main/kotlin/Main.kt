import engine.InputListeners.KeyListener
import engine.InputListeners.MouseListener
import engine.Window
import org.lwjgl.glfw.GLFW

fun main() {
    Window.run(
        onStart = {
            GLFW.glfwSetCursorPosCallback(Window.get(), MouseListener::mousePosCallback)
            GLFW.glfwSetMouseButtonCallback(Window.get(), MouseListener::mouseButtonCallback)
            GLFW.glfwSetScrollCallback(Window.get(), MouseListener::mouseScrollCallback)
            GLFW.glfwSetKeyCallback(Window.get(), KeyListener::keyCallback)
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