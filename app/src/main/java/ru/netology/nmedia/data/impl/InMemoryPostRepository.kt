package ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {
    override val data = MutableLiveData<Post>(
        Post(
            id = 1L,
            "Dmitrii",
            "Text",
            "03.05.2022"
        )
    )

    override fun like() {
        val currentPost = checkNotNull(data.value)

        val likedByMe = currentPost.copy(
            likedByMe = !currentPost.likedByMe
        )
        data.value = likedByMe
        currentPost.likes=countLikeByMe(currentPost.likedByMe,currentPost.likes)
        data.value!!.likes=currentPost.likes
        currentPost.reductionLike = reductionNumbers(currentPost.likes)
        data.value!!.reductionLike=currentPost.reductionLike
    }

    override fun share() {
        val currentPost = checkNotNull(data.value)
        currentPost.shares +=1
        data.value!!.shares = currentPost.shares
        currentPost.reductionShare = reductionNumbers(currentPost.shares)
        data.value!!.reductionShare = currentPost.reductionShare
    }

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