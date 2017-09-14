import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import model.*
import org.itheima.kotlin.game.core.Window
import java.io.File

class GameWindow : Window(title = "坦克大战", width = Config.gameWidth, height = Config.gameHeight) {
    private val views = arrayListOf<View>()
    private lateinit var tank : Tank

    override fun onCreate() {
        val file = File(javaClass.getResource("/map/1.map").path)
        val lines = file.readLines()

        var lineNumber = 0
        lines.forEach { line ->
            var columnNumber = 0
            line.toCharArray().forEach {
                when (it) {
                    '砖' -> views.add(Wall(columnNumber*Config.block,lineNumber*Config.block))
                    '铁' -> views.add(Steel(columnNumber*Config.block,lineNumber*Config.block))
                    '草' -> views.add(Grass(columnNumber*Config.block,lineNumber*Config.block))
                    '水' -> views.add(Water(columnNumber*Config.block,lineNumber*Config.block))
                }
                columnNumber++
            }
            lineNumber++
        }

        tank = Tank(10*Config.block,12*Config.block)
        views.add(tank)

    }

    override fun onDisplay() {
        views.forEach { it.draw() }
    }

    override fun onKeyPressed(event: KeyEvent) {
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

        }
    }

    override fun onRefresh() {
    }
}

fun main(args: Array<String>) {
    Application.launch(GameWindow::class.java)
}