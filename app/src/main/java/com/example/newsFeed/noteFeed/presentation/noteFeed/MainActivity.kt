package com.example.newsFeed.noteFeed.presentation.noteFeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.newsFeed.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    companion object {
        val ARGUMENT_KEY_TEST = "KEY.TEST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter =
            MainViewPagerAdapter(
                this
            )

        initToolbar()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = "Title $position"
            }
        ).attach()
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.tool_bar))
        supportActionBar?.title = getString(R.string.app_name)
    }

    private class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = 3

        override fun createFragment(position: Int): Fragment {
            val fragment =
                MainFeedFragment()
            fragment.arguments = Bundle().apply {
                putString(ARGUMENT_KEY_TEST, "This is a test!")
            }
            return fragment
        }

    }
}
