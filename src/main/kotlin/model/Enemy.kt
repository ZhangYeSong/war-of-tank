package model

import business.Blockable
import business.Moveable
import org.itheima.kotlin.game.core.Painter

class Enemy(override var x: Int, override var y: Int) : View, Moveable{
    override var width: Int = Configs.block
    override var height: Int = Configs.block
    override var speed: Int = 8
    override var direction : Directions = Directions.DOWN

    override fun draw() {
        val imagePath = when (direction) {
            Directions.UP -> "img/enemy_1_u.gif"
            Directions.DOWN -> "img/enemy_1_d.gif"
            Directions.LEFT -> "img/enemy_1_l.gif"
            Directions.RIGHT -> "img/enemy_1_r.gif"
        }
        Painter.drawImage(imagePath, x, y)
    }

    override fun notifyBlock(direction: Directions?) {

    }

    override fun willColilision(blockable: Blockable): Directions? {
        return null
    }

}