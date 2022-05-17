package kz.iitu.androidmessenger.ui.fragments

import kotlinx.android.synthetic.main.fragment_change_bio.*
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.utils.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_change_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_change_bio.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS)
            .child(UID)
            .child(CHILD_BIO)
            .setValue(newBio)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast(getString(R.string.settings_change_name_data_updated))
                    USER.bio = newBio
                    fragmentManager?.popBackStack()
                }
            }
    }

}