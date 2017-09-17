package model

import business.Blockable
import business.Moveable
import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int) : Moveable {

    override var direction = Directions.UP
    override var speed = 8
    override var width = Config.block
    override var height = Config.block
    var badDirection : Directions? = null

    override fun draw() {
        val imagePath = when (direction) {
            Directions.UP -> "img/tank_u.gif"
            Directions.DOWN -> "img/tank_d.gif"
            Directions.LEFT -> "img/tank_l.gif"
            Directions.RIGHT -> "img/tank_r.gif"
        }
        Painter.drawImage(imagePath, x, y)
    }

    fun move(direction: Directions) {
        if (this.badDirection == direction) {
            return
        }
        if (this.direction != direction) {
            this.direction = direction
            return
        }


        when (direction) {
            Directions.UP -> this.y -= speed
            Directions.DOWN -> this.y += speed
            Directions.LEFT -> this.x -= speed
            Directions.RIGHT -> this.x += speed
        }

        if(this.x < 0) this.x = 0
        if(this.x > Config.gameWidth - width) this.x = Config.gameWidth - width
        if(this.y < 0) this.y = 0
        if(this.y > Config.gameHeight - height) this.y = Config.gameHeight - height
    }

    override fun willColilision(block: Blockable): Directions? {
        //碰撞逻辑计算，返回将要碰撞的方向
        var collision = false
        var newX = this.x
        var newY = this.y

        when (this.direction) {
            Directions.UP -> newY -= speed
            Directions.DOWN -> newY += speed
            Directions.LEFT -> newX -= speed
            Directions.RIGHT -> newX += speed
        }

        if ((block.y + block.height <= newY)
                || (newY + this.height <= block.y)
                || (block.x + block.width <= newX)
                || (newX + this.width <= block.x)) {
            return null
        } else {
            return this.direction
        }
    }

    override fun notifyBlock(direction: Directions?) {
        this.badDirection = direction
    }
}
