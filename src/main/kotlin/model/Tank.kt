package model

import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int) : View {
    var direction = Directions.UP

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
        this.direction = direction
    }
}