package model

import business.*
import org.itheima.kotlin.game.core.Painter
import java.time.Clock
import java.util.*

class Enemy(override var x: Int, override var y: Int) :
        View, Moveable, AutoMoveable, Blockable, AutoShot, Sufferable, Destroyable{
    override var width: Int = Configs.block
    override var height: Int = Configs.block
    override var speed: Int = 8
    override var blood: Int = 1
    override var direction: Directions = Directions.DOWN

    private var badDirection: Directions? = null
    private var lastShotTime = 0L
    private var shotFrequency = 1000
    private var lastMoveTime = 0L
    private var MoveFrequency = 50

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
        this.badDirection = direction
    }

    override fun autoMove() {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastMoveTime < MoveFrequency) return

        lastMoveTime = currentTimeMillis

        if (badDirection == direction) {
            direction = getRandomDirection(badDirection)
            return
        }

        when (direction) {
            Directions.UP -> this.y -= speed
            Directions.DOWN -> this.y += speed
            Directions.LEFT -> this.x -= speed
            Directions.RIGHT -> this.x += speed
        }

        if (this.x < 0) this.x = 0
        if (this.x > Configs.gameWidth - width) this.x = Configs.gameWidth - width
        if (this.y < 0) this.y = 0
        if (this.y > Configs.gameHeight - height) this.y = Configs.gameHeight - height
    }

    private fun getRandomDirection(badDirection: Directions?): Directions {
        val randomInt = Random().nextInt(4)
        val randomDirection = when (randomInt) {
            0 -> Directions.UP
            1 -> Directions.DOWN
            2 -> Directions.LEFT
            3 -> Directions.RIGHT
            else -> Directions.DOWN
        }

        return if (randomDirection == badDirection) {
            getRandomDirection(badDirection)
        } else {
            randomDirection
        }
    }

    override fun autoShot(): View? {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastShotTime < shotFrequency) return null

        lastShotTime = currentTimeMillis

        return Bullet(this, this.direction, { bulletWidth, bulletHeight ->
            var bulletX = 0
            var bulletY = 0
            when (this.direction) {
                Directions.UP -> {
                    bulletX = this.x + (this.width - bulletWidth) / 2
                    bulletY = this.y - bulletHeight / 2
                }
                Directions.DOWN -> {
                    bulletX = this.x + (this.width - bulletWidth) / 2
                    bulletY = this.y + this.height - bulletHeight / 2
                }
                Directions.LEFT -> {
                    bulletX = this.x - bulletWidth / 2
                    bulletY = this.y + (this.height - bulletHeight) / 2
                }
                Directions.RIGHT -> {
                    bulletX = this.x + this.width - bulletWidth / 2
                    bulletY = this.y + (this.height - bulletHeight) / 2
                }
            }

            Pair(bulletX, bulletY)
        })
    }

    override fun notifySuffer(attacker: Attackable): Array<View> {
        if (attacker.source is Enemy) {
            return arrayOf()
        }

        blood -= attacker.attackPower
        return arrayOf(Blast(x, y))
    }

    override fun isDestroyable(): Boolean {
        return blood <= 0
    }

    override fun showDestry(): Array<View>? {
        return null
    }

}