package business

import model.View

interface Destroyable {
    fun isDestroyable():Boolean
    fun showDestry(): Array<View>? {
        return null
    }
}