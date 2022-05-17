package kz.iitu.androidmessenger

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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
        APP_ACTIVITY = this
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
        initContacts()
        AppStatus.updateStatus(AppStatus.ONLINE)
    }

    private fun initContacts() {
        if (checkPermissions(READ_CONTACTS)) {
            Toast.makeText(APP_ACTIVITY, "reading contacts", Toast.LENGTH_SHORT).show()
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            initContacts()
        }
    }
}