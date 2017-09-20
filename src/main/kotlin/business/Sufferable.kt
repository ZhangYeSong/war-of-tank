package business

import model.View

interface Sufferable : View {
    var blood : Int
    fun notifySuffer(attacker: Attackable) : Array<View>
}