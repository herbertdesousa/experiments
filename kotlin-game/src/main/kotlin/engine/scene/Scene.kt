package engine.scene

abstract class Scene {
    abstract fun onStart()
    abstract fun onUpdate()
    abstract fun onFixedUpdate()
}