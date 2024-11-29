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

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

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