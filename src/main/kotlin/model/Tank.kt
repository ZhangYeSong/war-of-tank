package model

import business.Blockable
import business.Moveable
import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int) : Moveable {

    override var direction = Directions.UP
    override var speed = 8
    override var width = Configs.block
    override var height = Configs.block
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
        if(this.x > Configs.gameWidth - width) this.x = Configs.gameWidth - width
        if(this.y < 0) this.y = 0
        if(this.y > Configs.gameHeight - height) this.y = Configs.gameHeight - height
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

        return if ((block.y + block.height <= newY)
                || (newY + this.height <= block.y)
                || (block.x + block.width <= newX)
                || (newX + this.width <= block.x)) {
            null
        } else {
            this.direction
        }
    }

    override fun notifyBlock(direction: Directions?) {
        this.badDirection = direction
    }

    fun shot() : Bullet {
        return Bullet(this.direction, { bulletWidth, bulletHeight ->
            var bulletX = 0
            var bulletY = 0
            when (this.direction) {
                Directions.UP -> {
                    bulletX = this.x + (this.width - bulletWidth)/2
                    bulletY = this.y - bulletHeight/2
                }Directions.DOWN -> {
                    bulletX = this.x + (this.width - bulletWidth)/2
                    bulletY = this.y + this.height - bulletHeight/2
                }Directions.LEFT -> {
                    bulletX = this.x - bulletWidth/2
                    bulletY = this.y + (this.height - bulletHeight)/2
                }Directions.RIGHT -> {
                    bulletX = this.x + this.width - bulletWidth/2
                    bulletY = this.y + (this.height - bulletHeight)/2
                }
            }

            Pair(bulletX, bulletY)
        })
    }
}
