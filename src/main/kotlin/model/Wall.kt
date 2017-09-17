package model

import business.Blockable
import org.itheima.kotlin.game.core.Painter

class Wall(override var x: Int, override var y: Int) : Blockable {

    override var width = Config.block
    override var height = Config.block

    override fun draw() {
        Painter.drawImage("img/wall.gif", x, y)
    }
}