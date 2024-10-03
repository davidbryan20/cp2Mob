package com.example.cp2_mob

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cp2_mob.FragmentA

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartButtonClick(view: View) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FragmentA())
            .addToBackStack(null)
            .commit()
    }
}
