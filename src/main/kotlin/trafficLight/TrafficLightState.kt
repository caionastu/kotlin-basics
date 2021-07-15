package trafficLight

import java.awt.Color

class TrafficLightState(val time: Float, val color: Color) {
    var nextState: TrafficLightState? = null

    fun hasNext(): Boolean = nextState != null

    override fun toString(): String {
        return "State with time $time and with color ${color}."
    }
}