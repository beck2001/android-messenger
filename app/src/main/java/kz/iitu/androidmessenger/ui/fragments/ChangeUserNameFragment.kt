package kz.iitu.androidmessenger.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.fragment_change_user_name.*
import kz.iitu.androidmessenger.MainActivity
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.utils.*

class ChangeUserNameFragment : BaseFragment(R.layout.fragment_change_user_name) {

    lateinit var newUsername: String

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        settings_change_username.setText(USER.username)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> {
                changeUsername()
            }
        }
        return true
    }

    private fun changeUsername() {
        newUsername = settings_change_username.text.toString().lowercase()
        if (newUsername.isEmpty()) {
            showToast(getString(R.string.settings_change_username_is_empty))
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(newUsername)) {
                        showToast(getString(R.string.settings_change_username_exists))
                    } else {
                        REF_DATABASE_ROOT.child(NODE_USERNAMES)
                            .child(newUsername)
                            .setValue(UID)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    updateCurrentUsername()
                                }
                            }
                    }
                })
        }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS)
            .child(UID)
            .child(CHILD_USERNAME)
            .setValue(newUsername)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    deleteOldUsernames()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUsernames() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES)
            .child(USER.username)
            .removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast(getString(R.string.settings_change_name_data_updated))
                    fragmentManager?.popBackStack()
                    USER.username = newUsername
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

}