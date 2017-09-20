package business

import model.View

interface Attackable : View{
    var attackPower : Int
    fun isExplode(suffer: Sufferable) : Boolean
    fun notifyAttack(suffer: Sufferable)
}