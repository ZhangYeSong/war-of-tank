package model

import business.Attackable
import business.Blockable
import business.Sufferable
import org.itheima.kotlin.game.core.Painter

class Camp(override var x: Int, override var y: Int) :
        View, Blockable, Sufferable {
    override var width: Int = Configs.block*2
    override var height: Int= Configs.block +32
    override var blood: Int = 12

    override fun draw() {
        //根据血量进行变化
        if (blood <= 3) {
            width = Configs.block
            height = Configs.block
            x = (Configs.gameWidth - Configs.block) / 2
            y = Configs.gameHeight - Configs.block
            Painter.drawImage("img/camp.gif", x, y)

        } else if (blood <= 6) {

            Painter.drawImage("img/wall_small.gif", x, y)
            Painter.drawImage("img/wall_small.gif", x + 32, y)
            Painter.drawImage("img/wall_small.gif", x + 64, y)
            Painter.drawImage("img/wall_small.gif", x + 96, y)

            Painter.drawImage("img/wall_small.gif", x, y + 32)
            Painter.drawImage("img/wall_small.gif", x, y + 64)

            Painter.drawImage("img/wall_small.gif", x + 96, y + 32)
            Painter.drawImage("img/wall_small.gif", x + 96, y + 64)

            Painter.drawImage("img/camp.gif", x + 32, y + 32)

        } else {

            Painter.drawImage("img/steel_small.gif", x, y)
            Painter.drawImage("img/steel_small.gif", x + 32, y)
            Painter.drawImage("img/steel_small.gif", x + 64, y)
            Painter.drawImage("img/steel_small.gif", x + 96, y)

            Painter.drawImage("img/steel_small.gif", x, y + 32)
            Painter.drawImage("img/steel_small.gif", x, y + 64)

            Painter.drawImage("img/steel_small.gif", x + 96, y + 32)
            Painter.drawImage("img/steel_small.gif", x + 96, y + 64)

            Painter.drawImage("img/camp.gif", x + 32, y + 32)

        }

    }

    override fun notifySuffer(attacker: Attackable): Array<View> {
        blood -= attacker.attackPower
        if (blood == 3 || blood == 6) {
            val x = x - 32
            val y = y - 32
            return arrayOf(Blast(x, y)
                    , Blast(x + 32, y)
                    , Blast(x + Configs.block, y)
                    , Blast(x + Configs.block + 32, y)
                    , Blast(x + Configs.block * 2, y)
                    , Blast(x, y + 32)
                    , Blast(x, y + Configs.block)
                    , Blast(x, y + Configs.block + 32)
                    , Blast(x + Configs.block * 2, y + 32)
                    , Blast(x + Configs.block * 2, y + Configs.block)
                    , Blast(x + Configs.block * 2, y + Configs.block + 32))
        }
        return arrayOf()
    }

}