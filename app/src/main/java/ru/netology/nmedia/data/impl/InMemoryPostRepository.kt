package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {
    override val data = MutableLiveData(
        List(100) { index ->
            Post(
                id = index + 1L,
                "Netology",
                "Some random content ${index + 1}",
                "03.05.2022"
            )
        }
    )
    private val posts get() = checkNotNull(data.value)

    override fun like(postId: Long) {

        data.value = posts.map {
            if (it.id != postId) it
            else {
                it.copy(
                    likedByMe = !it.likedByMe,
                    likes = countLikeByMe(it.likedByMe, it.likes),
                    reductionLike = reductionNumbers(it.likes + 1)
                )

            }

        }
    }

    override fun share(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else {
                it.copy(
                    shares = it.shares + 1,
                    reductionShare = reductionNumbers(it.shares + 1)
                )
            }

        }
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