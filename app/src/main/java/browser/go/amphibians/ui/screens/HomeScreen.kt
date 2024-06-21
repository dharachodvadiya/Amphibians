package browser.go.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import browser.go.amphibians.R
import browser.go.amphibians.model.Amphibians
import browser.go.amphibians.ui.theme.AmphibiansTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeScreen(amphibiansViewModel: AmphibiansViewModel,
               contentPadding: PaddingValues = PaddingValues(0.dp)) {
    when(amphibiansViewModel.amphibiansUiState){
        is AmphibiansUiState.Success -> AmphibiansItems((amphibiansViewModel.amphibiansUiState as AmphibiansUiState.Success).amphibians,amphibiansViewModel::toggleExpanded, contentPadding = contentPadding)
        is AmphibiansUiState.Error -> ErrorScreen({})
        is AmphibiansUiState.Loading -> LoadingScreen()
    }
}

@Composable
fun AmphibiansItems(amphibianses: List<Amphibians>, onToggleExpanded: (Amphibians) -> Unit,  contentPadding: PaddingValues = PaddingValues(0.dp), modifier: Modifier = Modifier)
{
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        items(amphibianses) { amphibians->
           // Text("Item is $it")
            AmphibiansCardItem(amphibians,{onToggleExpanded(it)})
        }
    }
}

@Composable
fun AmphibiansCardItem(amphibians: Amphibians, onToggleExpanded: (Amphibians) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(10.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(){
            Row(modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = amphibians.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    //amphibians.isExpanded.value != amphibians.isExpanded.value
                    onToggleExpanded(amphibians)
                }) {
                    Icon(
                        imageVector = if (amphibians.isExpanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (amphibians.isExpanded.value) "Collapse" else "Expand"
                    )
                }
            }

            if (amphibians.isExpanded.value){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current).data(amphibians.imgSrc)
                        .crossfade(true).build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = stringResource(R.string.Amphibians_photo),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = amphibians.description +"...",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodyMedium)
            }


        }

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    AmphibiansTheme{

    }
}