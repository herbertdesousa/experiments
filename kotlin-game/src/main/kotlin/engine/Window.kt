package engine

import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL
import kotlin.properties.Delegates

object Window {
    private val width = 1920 / 2
    private val height = 1080 / 2
    private val title = "My Game"
    private var window by Delegates.notNull<Long>()

    private var red = 0f; var green = 0f; var blue = 0f; var alpha = 0f

    fun get(): Long = window

    fun run(onStart: (window: Long) -> Unit, onUpdate: () -> Unit) {
        println("Game Started")

        init(onStart)
        loop(onUpdate)

        // Free the memory
        glfwFreeCallbacks(window)
        glfwDestroyWindow(window)

        // Terminate GLFW and the free error callback
        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }

    private fun init(onStart: (window: Long) -> Unit) {
        GLFWErrorCallback.createPrint(System.err).set()

        if (!glfwInit()) throw IllegalStateException("Unable to initialize GLFW")

        // Configure GLFW
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE)

        // Create Window
        window = glfwCreateWindow(width, height, title, NULL, NULL)
            ?: throw IllegalStateException("Failed to Create GLFW Window")

        glfwMakeContextCurrent(window)

        onStart(window)

        // Enable v-sync
        glfwSwapInterval(1)

        // Make the window visible
        glfwShowWindow(window)

        GL.createCapabilities()
    }

    private fun loop(onUpdate: () -> Unit) {
        while (!glfwWindowShouldClose(window)) {
            // Poll events
            glfwPollEvents()

            glClearColor(red, green, blue, alpha)
            glClear(GL_COLOR_BUFFER_BIT)

            onUpdate()

            glfwSwapBuffers(window)
        }
    }
}