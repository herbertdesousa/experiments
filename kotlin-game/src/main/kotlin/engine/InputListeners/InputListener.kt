package engine.InputListeners

open class InputListener(keyAmount: Int) {
    private var keys = BooleanArray(keyAmount)

    private fun <T> checkKeyInBounds(key: Int, onSuccess: () -> T): T {
        if (key < keys.size) return onSuccess()

        throw Error("[ENGINE] Key $key is Out Of Bounds")
    }

    fun setKey(key: Int, pressed: Boolean) {
        checkKeyInBounds(key) {
            keys[key] = pressed
        }
    }

    fun getKey(key: Int): Boolean {
        return checkKeyInBounds(key) {
            keys[key]
        }
    }

    fun someKey(pressed: Boolean): Boolean {
        return keys.find { it == pressed } == true
    }
}