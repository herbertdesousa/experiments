package engine

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL
import java.lang.IllegalStateException
import kotlin.properties.Delegates

object Window {
    private val width = 1920 / 2
    private val height = 1080 / 2
    private val title = "My Game"
    private var glfwWindow by Delegates.notNull<Long>()

    fun get(): Window {
        return Window
    }

    fun run() {
        init()
        loop()
    }

    private fun init() {
        GLFWErrorCallback.createPrint(System.err).set()

        if (!glfwInit()) throw IllegalStateException("Unable to initialize GLFW")

        // Configure GLFW
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE)

        // Create Window
        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL)
            ?: throw IllegalStateException("Failed to Create GLFW Window")

        // Make OpenGL context current
        glfwMakeContextCurrent(glfwWindow)
        // Enable v-sync
        glfwSwapInterval(1)

        // Make the window visible
        glfwShowWindow(glfwWindow)

        GL.createCapabilities()
    }

    private fun loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            // Poll events
            glfwPollEvents()

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
            glClear(GL_COLOR_BUFFER_BIT)

            glfwSwapBuffers(glfwWindow)
        }
    }
}