package com.example.moviecatalog.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeViewPagerAdapter(
    private var titles: ArrayList<String>,
    private var fragments: ArrayList<Fragment>,
    manager: FragmentManager
) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}