package com.ktorjetpackcompose.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ktorjetpackcompose.model.Post
import com.ktorjetpackcompose.viewmodel.PostViewModel

@Composable
fun PostScreen(paddingValues: PaddingValues, viewModel: PostViewModel = viewModel()) {

    val posts = viewModel.getPostList().collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()) {
        items(posts) { post ->
            post?.let {
                PostItem(post)
            }
        }

        posts.apply {
            when {
                loadState.append is LoadState.Loading -> item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp))
                    }
                }
                loadState.refresh is LoadState.Error -> item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp))
                    }
                }
                loadState.append is LoadState.Error -> item {
                    Text(text = "Error: ${posts.loadState.refresh}")
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ){
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = post.title, fontSize = 20.sp, modifier = Modifier.padding(bottom = 4.dp))
            Text(text = post.body)
        }
    }
}