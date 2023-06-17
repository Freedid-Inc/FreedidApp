package com.example.freedidapp.utis

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.freedidapp.Shield
import com.example.freedidapp.Users

class TabAdapter(

    fragmentManager: FragmentManager,
    lifecycle: Lifecycle


) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {

        return 2


    }

    override fun createFragment(position: Int): Fragment {

        return if (position == 1)
            Shield()
        else
            Users()


    }
}