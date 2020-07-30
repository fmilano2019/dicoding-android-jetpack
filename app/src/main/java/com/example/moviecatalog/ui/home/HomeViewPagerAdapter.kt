package com.example.moviecatalog.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeViewPagerAdapter(
    private var titles: ArrayList<String>,
    private var fragments: ArrayList<Fragment>,
    manager: FragmentManager,
    behaviour: Int
) : FragmentPagerAdapter(manager, behaviour) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}