package app.domain

import java.util.*

class Post(val id: UUID, var title: String, var body: String, val user: User) {
    override fun toString(): String {
        return "The user ${user.name} have a new post with title '$title' saying about '$body'."
    }
}