import engine.InputListeners.KeyListener
import engine.InputListeners.MouseListener
import engine.Window
import engine.util.Time
import org.lwjgl.glfw.GLFW

object Engine {
    lateinit var window: Window

    init {
        println("Engine has been Started")

        createWindow()
    }

    private fun createWindow() {
        val fixedUpdateRate = 0.02f

        var currentTime = Time.now()
        var endTime = Time.addSeconds(Time.now(), fixedUpdateRate)

        window = Window(
            title = "My Game",
            listeners = { window ->
                GLFW.glfwSetCursorPosCallback(window, MouseListener::mousePosCallback)
                GLFW.glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback)
                GLFW.glfwSetScrollCallback(window, MouseListener::mouseScrollCallback)
                GLFW.glfwSetKeyCallback(window, KeyListener::keyCallback)
            },
            onStart = {
                //
            },
            onUpdate = {
                if (currentTime >= endTime) {
                    // Fixed Update

                    endTime = Time.addSeconds(Time.now(), fixedUpdateRate)
                }

                currentTime = Time.now()
            }
        )
    }
}