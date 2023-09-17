package com.example.caesarappgithub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import com.example.caesarappgithub.Itemgithub.Api.DetailUserResponse
import com.example.caesarappgithub.R
import com.example.caesarappgithub.databinding.ActivityDetailBinding
import com.example.caesarappgithub.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val obj = intent.getParcelableExtra<DetailUserResponse>(EXTRAS) as DetailUserResponse
        supportActionBar?.title = resources.getString(R.string.appbarDetail, obj.login)

        binding.progressBar1.visibility = View.VISIBLE

        binding.usernameakun.visibility = View.GONE
        binding.tabLayout.visibility = View.GONE

        Handler().postDelayed({
            binding.progressBar1.visibility = View.GONE
            binding.tvCompany.visibility = View.VISIBLE
            binding.tvDetailLocation.visibility = View.VISIBLE
            binding.detailname.visibility = View.VISIBLE
            binding.follower.visibility = View.VISIBLE
            binding.following.visibility = View.VISIBLE
            binding.usernameakun.visibility = View.VISIBLE
            binding.fotodetail.visibility = View.VISIBLE
            binding.tabLayout.visibility = View.VISIBLE
            binding.viewPager.visibility = View.VISIBLE

            with(binding) {
                tvCompany.text = obj.company ?: resources.getString(R.string.no_company)
                tvDetailLocation.text = obj.location ?: resources.getString(R.string.no_location)
                detailname.text = obj.name ?: resources.getString(R.string.anon)
                follower.text = resources.getString(R.string.followerTemplate, obj.followers ?: 0)
                following.text = resources.getString(R.string.followingTemplate, obj.following ?: 0)
                usernameakun.text= obj.login ?: resources.getString(R.string.no_username)
            }

            Glide.with(this)
                .load(obj.avatarUrl)
                .into(binding.fotodetail)

            val bundle = Bundle()
            bundle.putString("username", obj.login)

            binding.viewPager.adapter = ViewPagerAdapter(this, bundle)
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
                when (pos) {
                    0 -> tab.text = "Follower"
                    1 -> tab.text = "Following"
                }
            }.attach()
        }, 3000)
    }

    companion object {
        const val EXTRAS = "value"
    }
}
