package com.example.prjt


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.FragmentActivity
import com.example.prjt.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.apply{
            adapter = MyPagerAdapter(context as FragmentActivity)
        }
        binding.viewpager.apply {
            setPageTransformer(ZoomOutPageTransformer())
        }
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = "Title $position"
        }.attach()


        Thread {

            var arr=Api.main(Array(13){""})
            runOnUiThread {
                date_text.setText(arr[10])
                decide_text.setText(arr[6])
                death_text.setText(arr[5])
            }



        }.start()
        }
    }


