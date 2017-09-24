package model

import business.Attackable
import business.Blockable
import business.Sufferable
import org.itheima.kotlin.game.core.Painter

class Steel(override var x: Int, override var y: Int) : Blockable, Sufferable {

    override var width = Configs.block
    override var height = Configs.block
    override var blood = 1


    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }

    override fun notifySuffer(attacker: Attackable): Array<View> {
        return arrayOf()
    }
}