package com.example.android.retrofitpractice

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.retrofitpractice.api.Api
import com.example.android.retrofitpractice.databinding.FragmentRecipeListBinding
import com.example.android.retrofitpractice.model.Recipe
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory


class RecipeListFragment : Fragment() {

    interface OnShowInfoListener {
        fun showRecipeInfo(id: Int,
                         title:String,
                         summary:String)
    }
    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var listener:OnShowInfoListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener= context as OnShowInfoListener

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRecipeListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)
        
        api.getRecipe(4631).enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) =
                    if (response.isSuccessful) {

                        val recipe = response.body()
                        binding.tvExampleQuery.text = recipe?.title

                        binding.btnShowSummary.setOnClickListener {
                            listener.showRecipeInfo(
                                    recipe!!.id,
                                    recipe.title,
                                    recipe.summary)
                        }

                    } else {
                        val error = response.body().toString()
                        val code = response.code().toString()
                        Log.e("error", error)
                        Log.e("code",code)

                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Toast.makeText(context, "Error onFailure", Toast.LENGTH_SHORT).show()
            }

        })

    }


}