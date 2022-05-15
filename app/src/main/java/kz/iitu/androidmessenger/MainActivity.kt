package kz.iitu.androidmessenger

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kz.iitu.androidmessenger.activity.RegisterActivity
import kz.iitu.androidmessenger.databinding.ActivityMainBinding
import kz.iitu.androidmessenger.ui.fragments.ChatsFragment
import kz.iitu.androidmessenger.ui.objects.AppDrawer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var appDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        // TODO add check for user registration later when firebase will be enabled
        if (false) { // if user is registered in the system chats fragment will be displayed
            setSupportActionBar(toolbar)
            appDrawer.create()
            supportFragmentManager.beginTransaction()
                .replace(R.id.dataContainer, ChatsFragment())
                .commit()
        } else {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun initFields() {
        toolbar = binding.mainToolbar
        appDrawer = AppDrawer(this, toolbar)
    }
}