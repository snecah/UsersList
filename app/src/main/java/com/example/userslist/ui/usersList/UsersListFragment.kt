package com.example.userslist.ui.usersList

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.userslist.R
import com.example.userslist.databinding.FragmentUsersListBinding
import com.example.userslist.ui.model.User
import com.example.userslist.ui.userDetailed.UserDetailedFragment

private const val KEY = "EditedUser"

class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val viewModel by viewModels<UsersListViewModel>()
    private val binding by viewBinding(FragmentUsersListBinding::bind)
    private val adapter by lazy { UsersListAdapter(viewModel.users.value!!, onUserClick()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(KEY) { requestKey, bundle ->
            val id = bundle.getInt("id")
            val newNumber = bundle.getString("phoneNumber")
            val newName = bundle.getString("userName")
            viewModel.changeUser(id, newNumber, newName)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    private fun onUserClick(): (User) -> Unit = {

        val bundle =bundleOf(
            "id" to it.id,
            "phoneNumber" to it.phoneNumber,
            "userName" to viewModel.constructName(it.name, it.secondName)
        )
        val fragment = UserDetailedFragment()
        fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}