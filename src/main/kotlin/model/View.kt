package model

import org.itheima.kotlin.game.core.Painter

interface View {
    var x:Int
    var y:Int

    var width:Int
    var height:Int

    fun draw()

    fun checkCollision(view : View) : Boolean{
        return !((view.y + view.height <= this.y)
                || (this.y + this.height <= view.y)
                || (view.x + view.width <= this.x)
                || (this.x + this.width <= view.x))
    }
}