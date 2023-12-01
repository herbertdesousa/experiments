package Scenes

import engine.scene.Scene
import engine.scene.SceneManagement

object Scene1 : Scene() {
    override fun onStart() {
        println("On Start Scene1")

        SceneManagement.loadScene(Scene2)
    }

    override fun onUpdate() {
    }

    override fun onFixedUpdate() {
    }
}