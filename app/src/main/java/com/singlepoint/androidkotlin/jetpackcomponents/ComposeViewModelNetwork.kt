package com.singlepoint.androidkotlin.jetpackcomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Post(val userId: Int, val id: Int, val title: String, val body: String)


interface ApiService{
    @GET("posts")
    suspend fun getPosts() : List<Post>
}

object RetrofitInstance {
    val api : ApiService = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").build().create(ApiService::class.java)
}

class NetworkViewModel : ViewModel(){
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts : StateFlow<List<Post>> get() = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts(){
        viewModelScope.launch {
            try{
                val response = RetrofitInstance.api.getPosts()
                _posts.value = response
            }catch(e: Exception){

            }
        }
    }
}


class NetworkActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkScreen()
        }
    }

    @Composable
    fun NetworkScreen(networkViewModel: NetworkViewModel = viewModel()) {
        val posts by networkViewModel.posts.collectAsState()

        LazyColumn {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }

    @Composable
    fun PostItem(post: Post) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = post.body)
        }
    }
}