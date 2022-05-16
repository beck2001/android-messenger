package kz.iitu.androidmessenger.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.fragment_settings.*
import kz.iitu.androidmessenger.MainActivity
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.activity.RegisterActivity
import kz.iitu.androidmessenger.utils.AUTH
import kz.iitu.androidmessenger.utils.USER
import kz.iitu.androidmessenger.utils.replaceActivity
import kz.iitu.androidmessenger.utils.replaceFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_user_bio.text = USER.bio
        settings_full_name.text = USER.fullName
        settings_user_phone_number.text = USER.phone
        settings_user_status.text = USER.status
        settings_username.text = USER.username
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                replaceFragment(ChangeNameFragment())
            }
        }
        return true
    }

}