package kz.iitu.androidmessenger.ui.fragments

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.utils.replaceFragment
import kz.iitu.androidmessenger.utils.showToast

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        if (register_input_phone_number.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            replaceFragment(EnterCodeFragment())
        }
    }
}