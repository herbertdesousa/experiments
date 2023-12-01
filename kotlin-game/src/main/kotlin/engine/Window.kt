package engine

import engine.util.Time
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL
import kotlin.properties.Delegates

class Window(
    val width: Int = 1920 / 2,
    val height: Int = 1080 / 2,
    val title: String = "",
    val listeners: (window: Long) -> Unit,
    val onStart: () -> Unit,
    val onUpdate: () -> Unit,
) {
    var window by Delegates.notNull<Long>();

    private var red = 0f;
    var green = 0f;
    var blue = 0f;
    var alpha = 0f

    fun get(): Long = window

    init {
        init()
        loop()

        // Free the memory
        glfwFreeCallbacks(window)
        glfwDestroyWindow(window)

        // Terminate GLFW and the free error callback
        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
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
        window = glfwCreateWindow(width, height, title, NULL, NULL)
            ?: throw IllegalStateException("Failed to Create GLFW Window")

        glfwMakeContextCurrent(window)

        listeners(window)

        // Enable v-sync
        glfwSwapInterval(1)

        // Make the window visible
        glfwShowWindow(window)

        GL.createCapabilities()
    }

    private fun loop() {
        onStart()

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