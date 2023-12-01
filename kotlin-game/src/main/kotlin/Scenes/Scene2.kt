package Scenes

import engine.Scene

class Scene2 : Scene() {
    override fun onStart() {
        println("onStart")
    }

    override fun onUpdate() {
        println("onUpdate")
    }

    override fun onFixedUpdate() {
        println("onFixedUpdate")
    }
}