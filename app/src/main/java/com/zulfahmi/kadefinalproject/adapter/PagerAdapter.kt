package com.zulfahmi.kadefinalproject.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragList: MutableList<Fragment> = mutableListOf()
    private val fragTitleList: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment? {
        return fragList[position]
    }

    override fun getCount(): Int {
        return fragList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragTitleList[position]
    }

    fun addFrag(frag: Fragment, title: String){
        fragList.add(frag)
        fragTitleList.add(title)
    }
}