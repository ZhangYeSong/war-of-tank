package model

import business.Blockable
import org.itheima.kotlin.game.core.Painter

class Steel(override var x: Int, override var y: Int) : Blockable {

    override var width = Configs.block
    override var height = Configs.block

    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }
}