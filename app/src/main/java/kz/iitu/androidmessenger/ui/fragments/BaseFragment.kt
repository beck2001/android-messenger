package kz.iitu.androidmessenger.ui.fragments

import androidx.fragment.app.Fragment
import kz.iitu.androidmessenger.MainActivity

open class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).appDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).appDrawer.enableDrawer()
    }
}