package com.example.android.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.retrofitpractice.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity(), RecipeListFragment.OnShowInfoListener {

    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState==null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, RecipeListFragment())
                .commit()
        }
    }

    override fun showRecipeInfo(id: Int, title: String, summary: String) {
        val fragment = RecipeDetailFragment.newInstance(id, title, summary)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}