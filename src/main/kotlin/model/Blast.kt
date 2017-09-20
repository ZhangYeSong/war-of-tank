package model

import business.Destroyable
import org.itheima.kotlin.game.core.Painter

class Blast(override var x: Int, override var y: Int) : View, Destroyable {
    override var width: Int = Configs.block
    override var height: Int = Configs.block

    private var cursor = 1
    private var images = arrayListOf<String>()

    init {
        (1..32).forEach { images.add("img/blast_$it.png") }
    }

    override fun draw() {
        var i = cursor++ % images.size
        Painter.drawImage(images[i], x, y)
    }

    override fun isDestroyable(): Boolean {
        return cursor > 32
    }
}
