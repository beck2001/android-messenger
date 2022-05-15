package kz.iitu.androidmessenger.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kz.iitu.androidmessenger.R
import kz.iitu.androidmessenger.databinding.ActivityRegisterBinding
import kz.iitu.androidmessenger.ui.fragments.EnterPhoneNumberFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        toolbar = binding.registerToolbar
        setSupportActionBar(toolbar)
        title = getString(R.string.register_your_phone_number)
        supportFragmentManager.beginTransaction()
            .add(R.id.registerDataContainer, EnterPhoneNumberFragment())
            .commit()
    }
}