package com.example.android.retrofitpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.retrofitpractice.databinding.FragmentRecipeDetailBinding


class RecipeDetailFragment : Fragment() {

    companion object{
        private const val ID = "ID"
        private const val TITLE="TITLE"
        private const val SUMMARY="SUMMARY"
        fun newInstance (id:Int, title:String, summary:String):Fragment{
            val newRecipeDetailFragment= RecipeDetailFragment()
            val args = Bundle()
            args.putInt(ID, id)
            args.putString(TITLE, title)
            args.putString(SUMMARY, summary)
            newRecipeDetailFragment.arguments= args

            return newRecipeDetailFragment
        }
    }

    private lateinit var binding:FragmentRecipeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentRecipeDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.tvTitle.text= arguments?.getString(TITLE)
        binding.tvSummary.text=arguments?.getString(SUMMARY)
    }

}