package model

import org.itheima.kotlin.game.core.Painter

interface View {
    var x:Int
    var y:Int

    var width:Int
    var height:Int

    fun draw()
}