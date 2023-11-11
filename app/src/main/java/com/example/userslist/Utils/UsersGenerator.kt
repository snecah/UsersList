package com.example.userslist.Utils

import com.example.userslist.ui.model.User
import kotlin.random.Random

fun generateUsers(): MutableList<User> {
    val contacts = mutableListOf<User>()
    for (i in 0..10) {
        val name = generateName()
        val secondName = generateSecondName()
        val phoneNumber = generatePhoneNumber()
        val contact = User(i, name, secondName, phoneNumber)
        contacts.add(contact)
    }
    return contacts
}

private fun generatePhoneNumber(): String {
    var phoneNumber = "+7"
    for (j in 0..8) {
        val digit = Random.nextInt(10)
        phoneNumber += digit.toString()
    }
    return phoneNumber
}

private fun generateSecondName(): String? {
    val surnames = listOf(
        "Smith",
        "James",
        "Cooper",
        null,
        "Allen",
        "Harris",
        "Bell",
        "Johnson",
        "Roberts",
        "Edwards"
    )
    return surnames.random()
}

private fun generateName(): String {
    val names = listOf(
        "Alice",
        "Bob",
        "Charlie",
        "David",
        "Emma",
        "Frank",
        "Martin",
        "David",
        "Carl",
        "Masha",
        "Victor"
    )
    return names.random()
}

class UsersGenerator {
    companion object {
        val contacts = generateUsers()
    }
}