package model

import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int) : View {
    var direction = Directions.UP
    var speed = 8

    override var width = Config.block
    override var height = Config.block

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
}