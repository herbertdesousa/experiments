import Scenes.Scene1
import Scenes.Scene2
import engine.Engine

fun main() {
    val scene1 = Scene1()
    val scene2 = Scene2()

    Engine(
        startScene = scene1
    )
}