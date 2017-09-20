package business

import model.Directions

interface AutoMoveable {
    var direction : Directions
    var speed : Int
    fun autoMove()
}