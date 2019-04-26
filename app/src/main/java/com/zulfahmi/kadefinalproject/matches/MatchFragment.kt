package com.zulfahmi.kadefinalproject.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.zulfahmi.kadefinalproject.matches.search.MatchSearchActivity
import com.zulfahmi.kadefinalproject.R
import com.zulfahmi.kadefinalproject.adapter.PagerAdapter
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.startActivity

class MatchFragment : Fragment() {
    private var leagueId = "4335"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = PagerAdapter(childFragmentManager)
        adapter.addFrag(MatchListTabFragment.newFragment(1, leagueId), "LAST MATCH")
        adapter.addFrag(MatchListTabFragment.newFragment(2, leagueId), "NEXT MATCH")
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.search_menu -> {
                context?.startActivity<MatchSearchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}