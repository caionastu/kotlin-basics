package app.repository

import app.domain.Post
import java.util.*

object PostRepository : IRepository<Post>() {

    fun findInTitle(text: String) = values.filter { it.title.contains(text) }
        .sortedWith(compareBy({ it.user.name }, { it.title }))

    fun findAllByUser(userId: UUID) = values.filter { it.user.id == userId }

    fun removeAllFromUser(userId: UUID) {
        values.removeAll { it.user.id == userId }
    }
}