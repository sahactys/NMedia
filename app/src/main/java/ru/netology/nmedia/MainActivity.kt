package ru.netology.nmedia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutInflater.inflate(R.layout.activity_main, null)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1L,
            "Dmitrii",
            "Text",
            "03.05.2022"
        )
        binding.render(post)
        binding.likeButton.setOnClickListener {
            post.likedByMe = !post.likedByMe
            binding.likeButton.setImageResource(getLikeIconResId(post.likedByMe))
            post.likes = countLikeByMe(post.likedByMe, post.likes)
            post.reductionLike = reductionNumbers(post.likes)
            binding.render(post)
        }

        binding.shareButton.setOnClickListener{
            post.shares+=1
            post.reductionShare = reductionNumbers(post.shares)
            binding.render(post)

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

    private fun countLikeByMe(liked: Boolean, like: Int) =
        if (liked) like + 1 else like - 1

    private fun reductionNumbers(count: Int): String {
        return if (count in 0..999) count.toString()
        else {
            val stepCount = count.toString().length
            val answer: Int
            if (stepCount in 4..6) {
                answer = count / 10.pow(2)
                if (answer % 10 == 0) "${answer / 10}K"
                else "${answer / 10},${answer % 10}K"
            } else {
                answer = (count / 1000) / 10.pow(2)
                if (answer % 10 == 0) "${answer / 10}M"
                else "${answer / 10},${answer % 10}M"
            }
        }
    }

    private fun Int.pow(x: Int): Int = (2..x).fold(this) { r, _ -> r * this }


}