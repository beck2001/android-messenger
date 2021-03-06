package kz.iitu.androidmessenger.ui.fragments

import kotlinx.android.synthetic.main.fragment_change_name.*
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.utils.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        initFullNameList()
    }

    private fun initFullNameList() {
        val fullNameParts = USER.fullName.split(" ")
        if (fullNameParts.size == 2) {
            settings_input_first_name.setText(USER.fullName.split(" ")[0])
            settings_input_last_name.setText(USER.fullName.split(" ")[1])
        } else {
            settings_input_first_name.setText(USER.fullName)
        }
    }

    override fun change() {
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