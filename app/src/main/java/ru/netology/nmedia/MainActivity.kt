package ru.netology.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.data.impl.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {


    private val viewModel by viewModels<PostViewModel>()

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutInflater.inflate(R.layout.activity_main, null)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostsAdapter(viewModel::onLikeClick, viewModel::onShareClick)
        binding.postsRecyclerView.adapter = adapter
        viewModel.data.observe(this) { posts -> adapter.submitList(posts) }


    }

}












