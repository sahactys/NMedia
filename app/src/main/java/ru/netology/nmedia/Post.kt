
package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 1,
    var reductionLike: String = "1",
    var likedByMe: Boolean = false,
    var shares: Int =1 ,
    var reductionShare: String = "1"
)