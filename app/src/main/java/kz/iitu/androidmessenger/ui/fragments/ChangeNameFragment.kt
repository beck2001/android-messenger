package kz.iitu.androidmessenger.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_change_name.*
import kz.iitu.androidmessenger.MainActivity
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.utils.*

class ChangeNameFragment : Fragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> {
                changeName()
            }
        }
        return true
    }

    private fun changeName() {
        val firstName = settings_input_first_name.text.toString()
        val lastName = settings_input_last_name.text.toString()
        if (firstName.isEmpty()) {
            showToast(getString(R.string.settings_change_name_first_name_is_empty))
        } else {
            val fullName = "$firstName $lastName"
            REF_DATABASE_ROOT.child(NODE_USERS)
                .child(UID)
                .child(CHILD_FULL_NAME)
                .setValue(fullName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.settings_change_name_data_updated))
                        USER.fullName = fullName
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}