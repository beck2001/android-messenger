package kz.iitu.androidmessenger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kz.iitu.androidmessenger.activity.RegisterActivity
import kz.iitu.androidmessenger.databinding.ActivityMainBinding
import kz.iitu.androidmessenger.model.User
import kz.iitu.androidmessenger.ui.fragments.ChatsFragment
import kz.iitu.androidmessenger.ui.objects.AppDrawer
import kz.iitu.androidmessenger.utils.*

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
        AppStatus.updateStatus(AppStatus.ONLINE)
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
        initUser()
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER = it.getValue(User::class.java) ?: User()
            })
    }

    override fun onStop() {
        super.onStop()
        AppStatus.updateStatus(AppStatus.OFFLINE)
    }
}