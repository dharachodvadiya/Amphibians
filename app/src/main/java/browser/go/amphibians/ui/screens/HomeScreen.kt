package browser.go.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import browser.go.amphibians.AmphibiansApp
import browser.go.amphibians.R
import browser.go.amphibians.ui.theme.AmphibiansTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.nio.file.WatchEvent

@Composable
fun HomeScreen(contentPadding: PaddingValues = PaddingValues(0.dp)) {
    AmphibiansItems(contentPadding = contentPadding);
}

@Composable
fun AmphibiansItems(contentPadding: PaddingValues = PaddingValues(0.dp),modifier: Modifier = Modifier)
{
    val itemsList = (0..5).toList()

    LazyColumn(
        contentPadding = contentPadding
    ) {
        items(itemsList) {
           // Text("Item is $it")
            AmphibiansCardItem()
        }
    }
}

@Composable
fun AmphibiansCardItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(10.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = modifier){
            Text(text = stringResource(R.string.Amphibians_name),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleLarge)
            Image(
                painter = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.Amphibians_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
            Text(text = stringResource(R.string.Amphibians_desc),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyMedium)
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
        //AmphibiansCardItem()
        AmphibiansItems();
    }
}