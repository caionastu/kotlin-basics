package app

import app.domain.Post
import app.domain.User
import app.repository.PostRepository
import app.repository.UserRepository
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

fun main(args: Array<String>) {
    UserRepository.generate(2)
    UserRepository.findAll()
        .forEach { PostRepository.generate(it, Random.nextInt(1..15)) }

    println("Searching Posts with title.")
    PostRepository.findInTitle("Title")
        .forEach { println(it) }

    println()
    val user = UserRepository.findFirst()
    println("Searching Posts by user id: ${user!!.id}.")
    PostRepository.findAllByUser(user.id).forEach { println(it) }

    println()
    println("Removing posts from user ${user.id}.")
    PostRepository.removeAllFromUser(user.id)

    println()
    println("Searching Posts by user id: ${user!!.id}.")
    PostRepository.findAllByUser(user.id).forEach { println(it) }

    println()
    println("Searching all users.")
    UserRepository.findAll().forEach { println(it) }

    println()
    println("Removing User with id: ${user.id}.")
    UserRepository.remove(user)

    println()
    println("Searching all users.")
    UserRepository.findAll().forEach { println(it) }

    println()
}

fun UserRepository.generate(quantity: Int) {
    for (index in 0..quantity) {
        UserRepository.add(User(UUID.randomUUID(), "Name $index"))
    }
}

fun PostRepository.generate(user: User, quantity: Int) {
    for (index in 0..quantity) {
        PostRepository.add(Post(UUID.randomUUID(), "Title ${index + 1}", "Body ${index + 1}", user))
    }
}