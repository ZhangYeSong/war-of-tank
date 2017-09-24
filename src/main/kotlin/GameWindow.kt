import business.*
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import model.*
import org.itheima.kotlin.game.core.Window
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow : Window(title = "坦克大战", width = Configs.gameWidth, height = Configs.gameHeight) {
    private val views = CopyOnWriteArrayList<View>()
    private lateinit var tank : Tank
    private var gameOver : Boolean = false

    override fun onCreate() {
        val file = File(javaClass.getResource("/map/1.map").path)
        val lines = file.readLines()

        var lineNumber = 0
        lines.forEach { line ->
            var columnNumber = 0
            line.toCharArray().forEach {
                when (it) {
                    '砖' -> views.add(Wall(columnNumber* Configs.block,lineNumber* Configs.block))
                    '铁' -> views.add(Steel(columnNumber* Configs.block,lineNumber* Configs.block))
                    '草' -> views.add(Grass(columnNumber* Configs.block,lineNumber* Configs.block))
                    '水' -> views.add(Water(columnNumber* Configs.block,lineNumber* Configs.block))
                    '敌' -> views.add(Enemy(columnNumber* Configs.block,lineNumber* Configs.block))
                }
                columnNumber++
            }
            lineNumber++
        }

        tank = Tank(10* Configs.block,12* Configs.block)
        views.add(tank)

        views.add(Camp(Configs.gameWidth / 2 - Configs.block, Configs.gameHeight - 96))

    }

    override fun onDisplay() {
        views.forEach { it.draw() }
    }

    override fun onKeyPressed(event: KeyEvent) {
        if (gameOver) {
            return
        }

        when (event.code) {
            KeyCode.W -> {
                tank.move(Directions.UP)
            }

            KeyCode.S -> {
                tank.move(Directions.DOWN)
            }

            KeyCode.A -> {
                tank.move(Directions.LEFT)
            }

            KeyCode.D -> {
                tank.move(Directions.RIGHT)
            }

            KeyCode.J -> {
                val bullet = tank.shot()
                views.add(bullet)
            }

        }
    }

    override fun onRefresh() {
        //销毁检测
        views.filter { it is Destroyable }.forEach {
            it as Destroyable
            if (it.isDestroyable()) {
                views.remove(it)
                val destry = it.showDestry()
                destry?.let { views.addAll(destry) }
            }
        }

        if (gameOver) {
            return
        }

        //碰撞检测
        views.filter { it is Moveable }.forEach { move ->
            move as Moveable
            var badDirection : Directions? = null
            views.filter { (it is Blockable) and (it != move) }.forEach blockTag@ { block ->
                block as Blockable
                val direction = move.willColilision(block)
                direction?.let {
                    badDirection = direction
                    return@blockTag }
            }
            move.notifyBlock(badDirection)
        }

        //自动移动检测
        views.filter { it is AutoMoveable }.forEach {
            it as AutoMoveable
            it.autoMove()
        }

        //自动射击检测
        views.filter { it is AutoShot }.forEach {
            it as AutoShot
            val autoShotView = it.autoShot()
            autoShotView?.let { views.add(autoShotView) }
        }

        //判断攻击效果
        views.filter { it is Attackable }.forEach { attacker ->
            attacker as Attackable
            views.filter{ (it is Sufferable) and (it != attacker.source) and (it != attacker) }.forEach suffer@{ suffer ->
                suffer as Sufferable
                if (attacker.isExplode(suffer)) {
                    attacker.notifyAttack(suffer)
                    val sufferViews = suffer.notifySuffer(attacker)
                    views.addAll(sufferViews)
                    return@suffer
                }
            }
        }

        if (views.filter { it is Camp }.isEmpty()) {
            gameOver = true
        }
    }
}

fun main(args: Array<String>) {
    Application.launch(GameWindow::class.java)
}