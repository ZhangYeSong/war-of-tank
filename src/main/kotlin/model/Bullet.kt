package model

import business.Attackable
import business.AutoMoveable
import business.Destroyable
import business.Sufferable
import org.itheima.kotlin.game.core.Painter
import sun.security.krb5.Config

class Bullet(override var direction: Directions,
             create:(width:Int,height:Int)->Pair<Int,Int>) :
        View, AutoMoveable, Destroyable, Attackable {
    override var x: Int = 0
    override var y: Int = 0
    override var width: Int = 0
    override var height: Int = 0
    override var speed: Int = 8
    override var attackPower: Int = 1

    private var imgPath : String
    private var isDestroyed : Boolean = false

    init {
        this.imgPath = initImagePath()
        val size = Painter.size(imgPath)
        this.width = size[0]
        this.height = size[1]
        val pair = create.invoke(this.width, this.height)
        this.x = pair.first
        this.y = pair.second
    }

    private fun initImagePath(): String {
        return when (this.direction) {
            Directions.UP -> "img/shot_top.gif"
            Directions.DOWN -> "img/shot_bottom.gif"
            Directions.LEFT -> "img/shot_left.gif"
            Directions.RIGHT -> "img/shot_right.gif"
        }
    }

    override fun draw() {
        Painter.drawImage(this.imgPath, this.x, this.y)
    }

    override fun autoMove() {
        when (this.direction) {
            Directions.UP -> this.y -= this.speed
            Directions.DOWN -> this.y += this.speed
            Directions.LEFT -> this.x -= this.speed
            Directions.RIGHT -> this.x += this.speed
        }
    }

    override fun isDestroyable(): Boolean {
        if(this.isDestroyed) return true

        if(x < -this.width) return true
        if(x > Configs.gameWidth) return true
        if(y < -this.height) return true
        if(y > Configs.gameHeight) return true

        return false
    }

    override fun isExplode(suffer: Sufferable): Boolean {
        return checkCollision(suffer)
    }

    override fun notifyAttack(suffer: Sufferable) {
        this.isDestroyed = true
    }

}