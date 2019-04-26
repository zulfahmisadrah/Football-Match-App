package com.zulfahmi.kadefinalproject.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.zulfahmi.kadefinalproject.matches.search.MatchSearchActivity
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.adapter.PagerAdapter
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.startActivity

class FavoriteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = PagerAdapter(childFragmentManager)
        adapter.addFrag(FavoriteMatchFragment(), "MATCHES")
        adapter.addFrag(FavoriteTeamFragment(), "TEAMS")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

}