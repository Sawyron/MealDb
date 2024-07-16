package org.sawyron.mealapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sawyron.mealapp.data.meals.remote.MealRepositoryImpl
import org.sawyron.mealapp.domain.meal.Meal
import org.sawyron.mealapp.ui.MainViewModel
import org.sawyron.mealapp.ui.theme.MealAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealScreen(MainViewModel(MealRepositoryImpl()))
                }
            }
        }
    }
}

@Composable
fun MealScreen(viewModel: MainViewModel) {
    val meal by viewModel.mealState.collectAsStateWithLifecycle()
    Meal(meal)
    viewModel.fetchMeal()
}

@Composable
fun Meal(meal: Meal) {
    val spacerModifier = Modifier.size(8.dp)
    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.SpaceAround) {
        Row {
            Text(
                text = meal.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = "Area:")
            Text(text = meal.area)
        }
        Column(verticalArrangement = Arrangement.SpaceAround) {
            Text("You'll need", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = spacerModifier)
            LazyColumn {
                items(meal.ingredients.entries.toList(), key = { item -> item.key }) {
                    Row {
                        Text("${it.key}:")
                        Spacer(modifier = spacerModifier.fillMaxHeight())
                        Text(it.value)
                    }
                }
            }
        }
        Row {
            Column {
                Text(text = "How to cook", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = meal.instructions, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
