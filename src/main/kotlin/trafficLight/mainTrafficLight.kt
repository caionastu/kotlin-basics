package trafficLight

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.Color

fun main(args: Array<String>) = runBlocking {
    val trafficLight = trafficLight(TrafficLightState(2000F, Color.green)) {
        nextState(TrafficLightState(3000F, Color.red))
        nextState(TrafficLightState(7000F, Color.pink))
        nextState(TrafficLightState(2000F, Color.yellow))
        nextState(5000F, Color.black)
    }

    startTrafficLight(trafficLight)
}

suspend fun startTrafficLight(trafficLight: TrafficLight) = coroutineScope {
    trafficLight.showStates()
    launch { trafficLight.start() }
    delay(20000)
    trafficLight.stop()
    delay(5000)
    launch { trafficLight.start() }
    delay(2000)
    launch {trafficLight.reset()}
    delay(10000)
    trafficLight.stop()
}

