package engine

import engine.InputListeners.KeyListener
import engine.InputListeners.MouseListener
import engine.scene.Scene
import engine.scene.SceneManagement
import engine.util.Time
import org.lwjgl.glfw.GLFW

class Engine(startScene: Scene) {
    lateinit var window: Window

    init {
        println("engine.Engine has been Started")

        SceneManagement.loadScene(startScene)

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
            },
            onUpdate = {
                SceneManagement.scene.onUpdate()

                if (currentTime >= endTime) {
                    SceneManagement.scene.onFixedUpdate()

                    endTime = Time.addSeconds(Time.now(), fixedUpdateRate)
                }

                currentTime = Time.now()
            }
        )
    }
}