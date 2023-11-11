package com.example.userslist.ui.usersList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.userslist.Utils.UsersGenerator
import com.example.userslist.ui.model.User

class UsersListViewModel : ViewModel() {
    private val _usersHardcoded = MutableLiveData(UsersGenerator.contacts)


    val users get() = _usersHardcoded

    fun constructName(name: String, secondName: String?): String {
        return if (secondName.isNullOrEmpty()) name
        else name + " " + secondName
    }

    fun changeUser(id: Int, newNumber: String?, newName: String?) {
        _usersHardcoded.value!![id] = User(id, newName ?: "", null, newNumber ?: "")
    }
}