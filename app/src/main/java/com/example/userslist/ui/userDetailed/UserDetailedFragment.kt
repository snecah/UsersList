package com.example.userslist.ui.userDetailed

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.userslist.R
import com.example.userslist.databinding.FragmentUserDetailedBinding
import com.example.userslist.ui.usersList.UsersListFragment

private const val KEY = "EditedUser"

class UserDetailedFragment : Fragment(R.layout.fragment_user_detailed) {

    private val binding by viewBinding(FragmentUserDetailedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {
            detailedPhoneNumber.setText(arguments?.getString("phoneNumber"))
            detailedUserName.setText(arguments?.getString("userName"))

            saveButton.setOnClickListener {
                val id = arguments?.getInt("id")
                val userName = binding.detailedUserName.text.toString()
                val phoneNumber = binding.detailedPhoneNumber.text.toString()

                if (!areFieldsCorrect(userName, phoneNumber)) {
                    setArgumentToUsersList(userName, phoneNumber, id)
                    navigateToUsersList()
                } else {
                    emptyFieldsMessage()
                }
            }
        }
    }

    private fun navigateToUsersList() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, UsersListFragment()).commit()
    }

    private fun setArgumentToUsersList(userName: String, phoneNumber: String, id: Int?) {
        requireActivity().supportFragmentManager.setFragmentResult(
            KEY,
            bundleOf(
                "userName" to userName,
                "phoneNumber" to phoneNumber,
                "id" to id
            )
        )
    }

    private fun areFieldsCorrect(userName: String, phoneNumber: String): Boolean {
        return userName.isEmpty() or phoneNumber.isEmpty()
    }

    private fun emptyFieldsMessage() {
        Toast.makeText(
            requireContext(),
            "User name and phone must be none empty", Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val id = arguments?.getInt("id")
        outState.putInt("id", id ?: 0)
    }

}