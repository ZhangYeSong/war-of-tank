package business

import model.View

interface Attackable : View{
    var attackPower : Int
    var source : View
    fun isExplode(suffer: Sufferable) : Boolean
    fun notifyAttack(suffer: Sufferable)
}