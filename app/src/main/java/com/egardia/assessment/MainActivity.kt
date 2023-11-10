package com.egardia.assessment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.egardia.assessment.model.Car
import com.egardia.assessment.ui.theme.MyApplicationTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.egardia.assessment.design.Toolbar
import com.egardia.assessment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.cars.observe(this) { cars ->
            setContent {
                MyApplicationTheme {
                    Scaffold(
                        topBar = {
                            Toolbar(text = getString(R.string.app_name))
                        },
                    ) { innerPadding ->
                        Surface(modifier = Modifier.padding(innerPadding)) {
                            CarsContent(
                                cars = cars,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CarsContent(modifier: Modifier = Modifier, cars: List<Car>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(cars) { car ->
            CarListItem(car = car)
        }
    }
}

@Composable
fun CarListItem(modifier: Modifier = Modifier, car: Car) {
    val context = LocalContext.current

    Row(modifier = modifier
        .fillMaxWidth()
        .clickable {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_CAR, car)
            context.startActivity(intent)
        }
    ) {
        AsyncImage(
            model = car.picture,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.carplaceholder),
            error = painterResource(R.drawable.carplaceholder),
            fallback = painterResource(R.drawable.carplaceholder),
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column() {
            Text(
                text = car.make,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = car.model,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = car.formattedPrice,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}