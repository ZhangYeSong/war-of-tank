import javafx.application.Application
import javafx.scene.input.KeyEvent
import model.*
import org.itheima.kotlin.game.core.Window
import java.io.File

class GameWindow : Window(title = "坦克大战", width = Config.gameWidth, height = Config.gameHeight) {
    val views = arrayListOf<View>()

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

    }

    override fun onDisplay() {
        views.forEach { it.draw() }
    }

    override fun onKeyPressed(event: KeyEvent) {
    }

    override fun onRefresh() {
    }
}

fun main(args: Array<String>) {
    Application.launch(GameWindow::class.java)
}