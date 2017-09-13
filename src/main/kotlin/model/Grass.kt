package model

import org.itheima.kotlin.game.core.Painter

class Grass(override var x: Int, override var y: Int) : View {

    override var width = Config.block
    override var height = Config.block

    override fun draw() {
        Painter.drawImage("img/grass.gif", x, y)
    }
}