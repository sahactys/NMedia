package ru.netology.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
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

        viewModel.data.observe(this) { post -> binding.render(post) }

        binding.likeButton.setOnClickListener {
            viewModel.onLikeClick()
        }
        binding.shareButton.setOnClickListener {
            viewModel.onShareClick()
        }


    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        dateText.text = post.published
        bodyText.text = post.content
        likeCount.text = post.reductionLike
        likeButton.setImageResource(getLikeIconResId(post.likedByMe))
        shareCount.text = post.reductionShare
    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if (liked) R.drawable.liked else R.drawable.like








}
