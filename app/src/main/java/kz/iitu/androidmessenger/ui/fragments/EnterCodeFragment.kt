package kz.iitu.androidmessenger.ui.fragments

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_enter_code.*
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.utils.AppTextWatcher
import kz.iitu.androidmessenger.utils.showToast

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length >= 6) {
                verifyCode()
            }
        })
    }

    fun verifyCode() {
        showToast("Ok")
    }
}