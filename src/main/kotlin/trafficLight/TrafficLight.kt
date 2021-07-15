package trafficLight

import kotlinx.coroutines.*
import java.awt.Color
import java.util.*

class TrafficLight(private val firstState: TrafficLightState) {
    private var states: LinkedList<TrafficLightState> = LinkedList()
    private var currentState: TrafficLightState? = firstState

    private var job: Job? = null

    init {
        firstState.nextState = firstState
        states.add(firstState)
    }

    fun nextState(state: TrafficLightState) {
        val first = states.first
        val last = states.last
        last.nextState = state
        state.nextState = first
        states.add(state)
    }

    fun nextState(time: Float, color: Color) {
        nextState(TrafficLightState(time, color))
    }

    suspend fun start() = coroutineScope {
        if (job == null || !job!!.isActive) {
            println("Starting Traffic Light.")
            job = launch { run() }
        }
    }

    suspend fun stop() = coroutineScope {
        println("Stopping Traffic Light.")
        job!!.cancelAndJoin()
    }

    suspend fun reset() = coroutineScope {
        stop()
        currentState = states.first
        println("Traffic Light reseted.")
        start()
    }

    private suspend fun run() = coroutineScope {
        while (isActive) {
            val time = currentState!!.time.toLong()
            println("Current State of color ${currentState!!.color}")
            println("Waiting for $time to go to next state.")
            delay(time)
            changeState()
        }
    }

    private fun changeState() {
        currentState = currentState!!.nextState
        println("State changed.")
    }

    fun showStates() {
        println("Listing all traffic light states:")
        states.forEachIndexed { index, state -> println("Index: $index, State: $state") }
        println()
    }

}

fun trafficLight(firstState: TrafficLightState, init: TrafficLight.() -> Unit): TrafficLight {
    val trafficLight = TrafficLight(firstState)
    trafficLight.init()
    return trafficLight
}