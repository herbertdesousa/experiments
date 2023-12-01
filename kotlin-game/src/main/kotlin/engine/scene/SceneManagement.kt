package engine.scene

object SceneManagement {
    lateinit var scene: Scene
        private set

    fun loadScene(_scene: Scene) {
        _scene.onStart()

        scene = _scene
    }
}