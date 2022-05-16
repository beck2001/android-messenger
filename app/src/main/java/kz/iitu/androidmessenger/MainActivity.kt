package kz.iitu.androidmessenger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kz.iitu.androidmessenger.activity.RegisterActivity
import kz.iitu.androidmessenger.databinding.ActivityMainBinding
import kz.iitu.androidmessenger.ui.fragments.ChatsFragment
import kz.iitu.androidmessenger.ui.objects.AppDrawer
import kz.iitu.androidmessenger.utils.AUTH
import kz.iitu.androidmessenger.utils.initFirebase
import kz.iitu.androidmessenger.utils.replaceActivity
import kz.iitu.androidmessenger.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    lateinit var appDrawer: AppDrawer

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
        if (AUTH.currentUser != null) { // if user is registered in the system chats fragment will be displayed
            setSupportActionBar(toolbar)
            appDrawer.create()
            replaceFragment(ChatsFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }


    }

    private fun initFields() {
        toolbar = binding.mainToolbar
        appDrawer = AppDrawer(this, toolbar)
        initFirebase()
    }
}