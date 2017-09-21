package business

import model.Directions
import model.View

interface Moveable : View{
    val direction : Directions
    val speed : Int
    fun willColilision(block:Blockable):Directions? {
        //碰撞逻辑计算，返回将要碰撞的方向
        var newX = this.x
        var newY = this.y

        when (this.direction) {
            Directions.UP -> newY -= speed
            Directions.DOWN -> newY += speed
            Directions.LEFT -> newX -= speed
            Directions.RIGHT -> newX += speed
        }

        if(newX < 0) return Directions.LEFT
        if(newX > Configs.gameWidth - width) return Directions.RIGHT
        if(newY < 0) return Directions.UP
        if(newY > Configs.gameHeight - height) return Directions.DOWN

        return if ((block.y + block.height <= newY)
                || (newY + this.height <= block.y)
                || (block.x + block.width <= newX)
                || (newX + this.width <= block.x)) {
            null
        } else {
            this.direction
        }
    }
    fun notifyBlock(direction: Directions?)
}