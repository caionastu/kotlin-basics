package app.repository

import app.domain.User

object UserRepository: IRepository<User>() {
    fun findFirst() = values.firstOrNull()
    fun findAll() = values.toList()
}