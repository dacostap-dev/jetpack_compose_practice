package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GridTask(modifier = Modifier.fillMaxSize())
                }

            }
        }
    }
}


@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier,

        ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = modifier
                .padding(top = 16.dp)
                .padding(end = 16.dp)
                .align(alignment = Alignment.End)
        )
    }
}


@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.androidparty)

    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null, //Es null porq no necesita accesibilidad por ser imagen decorativa
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }

}


@Composable
fun Article(modifier: Modifier = Modifier) {
    Column(modifier) {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = stringResource(id = R.string.article_title),
            fontSize = 24.sp,
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.article_subtitle),
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.article_content),
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(16.dp)

        )
    }
}


@Composable
fun TaskComplete(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = stringResource(R.string.image_success_description)
        )
        Text(
            text = stringResource(R.string.message_text),
            fontWeight = FontWeight.Bold,
            //Usamos directamente Modifier para no acumular y que afecte a la columna
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp, end = 0.dp, start = 0.dp),
        )
        Text(
            text = stringResource(R.string.congrats_text),
            fontSize = 16.sp
        )
    }
}


data class ItemModel(val title: String, val description: String, val color: Color)

@Composable
fun GridTask(modifier: Modifier = Modifier) {

    val itemList = listOf(
        ItemModel(
            title = stringResource(id = R.string.grid_item_title_one),
            description = stringResource(
                id = R.string.grid_item_desc_one
            ),
            color = Color(0xFFEADDFF),
        ),
        ItemModel(
            title = stringResource(id = R.string.grid_item_title_two),
            description = stringResource(
                id = R.string.grid_item_desc_two
            ),
            color = Color(0xFFD0BCFF),
        ),
        ItemModel(
            title = stringResource(id = R.string.grid_item_title_three),
            description = stringResource(
                id = R.string.grid_item_desc_three
            ),
            color = Color(0xFFB69DF8),
        ),
        ItemModel(
            title = stringResource(id = R.string.grid_item_title_four),
            description = stringResource(
                id = R.string.grid_item_desc_four
            ),
            color = Color(0xFFF6EDFF),
        ),
    )

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
        items(itemList) { item ->
            GridItem(
                title = item.title,
                description = item.description,
                color = item.color,
            )


        }
    }
}

@Composable
fun GridItem(title: String, description: String, color: Color, modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    // Definir el tamaño del item como la mitad del ancho de la pantalla
    val itemSize = screenHeight / 2



    Column(
        modifier = modifier
            .size(itemSize)
            .background(color = color)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(text = description)
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GridTask(modifier = Modifier.fillMaxSize())
    }
}