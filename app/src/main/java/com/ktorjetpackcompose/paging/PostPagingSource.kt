package com.ktorjetpackcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ktorjetpackcompose.model.Post
import com.ktorjetpackcompose.repository.PostRepository

class PostPagingSource(private val repo: PostRepository
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {

        // Get the current page (default to 1 if null)
        val page = params.key ?: 1
        return try {

            // Make a GET request to fetch the posts (you can use query parameters for pagination)
            val response = repo.fetchPostsByPage(page, params.loadSize)

            // Return the loaded data and pagination info
            LoadResult.Page(
                data = response,
                prevKey = if (page > 1) page - 1 else null,  // Previous page key (null if no previous page)
                nextKey = if (response.isNotEmpty()) page + 1 else null  // Next page key (null if no next page)
            )
        } catch (e: Exception) {
            // Handle errors, e.g. network issues
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        // We can return the closest page key for refresh purposes
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id }
    }
}