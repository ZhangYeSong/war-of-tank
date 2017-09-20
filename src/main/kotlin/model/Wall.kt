package model

import business.Attackable
import business.Blockable
import business.Destroyable
import business.Sufferable
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter

class Wall(override var x: Int, override var y: Int) :
        Blockable, Sufferable, Destroyable {
    override var blood: Int = 3
    override var width = Configs.block
    override var height = Configs.block

    override fun draw() {
        Painter.drawImage("img/wall.gif", x, y)
    }

    override fun notifySuffer(attacker: Attackable) : Array<View>{
        blood -= attacker.attackPower
        Composer.play("snd/hit.wav")
        return arrayOf(Blast(x, y))
    }

    override fun isDestroyable(): Boolean = blood <= 0
}