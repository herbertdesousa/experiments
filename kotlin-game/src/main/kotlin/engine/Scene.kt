package engine

abstract class Scene {
    abstract fun onStart()
    abstract fun onUpdate()
    abstract fun onFixedUpdate()
}