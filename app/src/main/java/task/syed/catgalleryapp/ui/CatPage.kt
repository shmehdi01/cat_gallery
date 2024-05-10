package task.syed.catgalleryapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import task.syed.catgalleryapp.ui.viewmodel.CatGalleryState
import task.syed.catgalleryapp.ui.viewmodel.CatIntent
import task.syed.catgalleryapp.ui.viewmodel.CatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatPage() {

    val viewModel = hiltViewModel<CatViewModel>()
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(CatIntent.GetCats)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Cat Gallery")
            })
        }
    ) {
        Column(Modifier.padding(it)) {
            when (val state = viewModel.catState.value) {
                is CatGalleryState.Error -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(text = state.error)
                    }
                }

                CatGalleryState.Idle -> {}
                CatGalleryState.Loading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }

                is CatGalleryState.Result -> {
                    LazyColumn {
                        items(state.cats) { item ->
                            ListItem(
                                headlineContent = {
                                    Text(text = "This is cat title")
                                },
                                supportingContent = {
                                    Text(text = "This is cat subtitle")
                                },
                                leadingContent = {
                                    Image(
                                        painter = rememberAsyncImagePainter(item.imageUrl),
                                        contentDescription = null,
                                        modifier = Modifier.size(128.dp).clip(RoundedCornerShape(12.dp)),
                                        contentScale = ContentScale.Crop

                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}