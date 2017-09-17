package business

import model.Directions
import model.View

interface Moveable : View{
    val direction : Directions
    val speed : Int
    fun willColilision(blockable:Blockable):Directions?
    fun notifyBlock(direction: Directions?)
}