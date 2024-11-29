package com.ktorjetpackcompose.repository

import com.ktorjetpackcompose.config.KtorClient
import com.ktorjetpackcompose.model.Post
import com.ktorjetpackcompose.utils.GenericApiHelper
import io.ktor.client.statement.HttpResponse

class PostRepository {

    private val client = KtorClient.httpClient
    private val apiHelper = GenericApiHelper(client)

    /** How to make GET REQUEST with pagination**/
    suspend fun fetchPostsByPage(page: Int, limit: Int = 10): List<Post> {
        val url = "/posts"
        val headers = mapOf("Authorization" to "authorization")
        val params = mapOf("page" to page.toString(), "limit" to limit.toString())
        return apiHelper.getRequest(url, params = params)
    }

    /** How to make GET REQUEST **/
    suspend fun fetchPosts(): List<Post> {
        val url = "/posts"
        return apiHelper.getRequest(url)
    }

    /** How to make POST REQUEST **/
    suspend fun addPost(person: Post, param1: String, param2: Int, authorization: String): HttpResponse {
        val url = "/posts"
        val params = mapOf("param1" to param1, "param2" to param2.toString())
        return apiHelper.postRequest<HttpResponse>(url = url, body = person, params = params)
    }

    /** How to make PUT REQUEST **/
    suspend fun updatePost(id: Int, post: Post): Post {
        val url = "posts/$id"
        return apiHelper.putRequest(url = url, body = post)
    }

    /** How to make DELETE REQUEST **/
    suspend fun deletePost(id: Int): HttpResponse {
        val url = "posts/$id"
        return apiHelper.deleteRequest(url = url)
    }
}