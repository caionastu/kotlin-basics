package app.domain

import java.util.*

class User(val id: UUID, var name: String) {
    override fun toString(): String {
        return "User with name: $name"
    }
}