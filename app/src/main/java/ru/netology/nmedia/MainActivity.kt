package ru.netology.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutInflater.inflate(R.layout.activity_main,null)
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
            post.likes = countLikeByMe(post.likedByMe,post.likes)
            post.reductionLike = reductionNumbers(post.likes)
            binding.render(post)
        }





    }

    private fun ActivityMainBinding.render(post: Post){
        authorName.text = post.author
        dateText.text = post.published
        bodyText.text = post.content
        likeCount.text = post.reductionLike
        likeButton.setImageResource(getLikeIconResId(post.likedByMe))
    }

    @DrawableRes
    private  fun getLikeIconResId(liked:Boolean) =
        if (liked) R.drawable.liked else R.drawable.like

    private fun countLikeByMe(liked:Boolean,like:Int) =
         if (liked) like+1 else like-1

    private fun reductionNumbers(count:Int):String{
        if(count in 0..999) return count.toString()
        else{
            val stepCount=0
            val pastCount=0
            val pastPastCount=0
            while (count/10!=0){
                stepCount+1
                pastCount = count

            }
        }
    }
}