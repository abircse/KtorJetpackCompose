package com.ktorjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktorjetpackcompose.model.Post
import com.ktorjetpackcompose.paging.PostPagingSource
import com.ktorjetpackcompose.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchPosts()
    }

    /** For normal list **/
    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val fetchedPosts = repository.fetchPosts()
                _posts.value = fetchedPosts
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }


    /** FoR PAGING LIST **/
    fun getPostList(): Flow<PagingData<Post>> = _postList
    private val _postList = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            prefetchDistance = 1,
            enablePlaceholders = false
        )
    ){ PostPagingSource(repository) }.flow.cachedIn(viewModelScope)

}