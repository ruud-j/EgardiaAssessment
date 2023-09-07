package com.egardia.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.egardia.assessment.design.Toolbar
import com.egardia.assessment.model.Car
import com.egardia.assessment.ui.theme.MyApplicationTheme

class DetailActivity : ComponentActivity() {
    companion object {
        const val EXTRA_CAR = "DetailActivity.EXTRA_CAR"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val car = intent.getParcelableExtra<Car>(EXTRA_CAR)

        setContent {
            MyApplicationTheme {
                car?.let { car ->
                    Scaffold(
                        topBar = {
                            Toolbar(
                                text = "${car.make} ${car.model}",
                                navigationClick = { finish() }
                            )
                        },
                    ) { innerPadding ->
                        Surface(modifier = Modifier.padding(innerPadding)) {
                            CarContent(car = car)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CarContent(modifier: Modifier = Modifier, car: Car) {
        BoxWithConstraints {
            if (maxWidth < 400.dp) {
                Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                    AsyncImage(
                        model = car.picture,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.carplaceholder),
                        error = painterResource(R.drawable.carplaceholder),
                        fallback = painterResource(R.drawable.carplaceholder),
                        modifier = Modifier.height(250.dp)
                    )
                    CarTextContent(car = car)
                }
            } else {
                Row(modifier = modifier.padding(16.dp)) {
                    AsyncImage(
                        model = car.picture,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.carplaceholder),
                        error = painterResource(R.drawable.carplaceholder),
                        fallback = painterResource(R.drawable.carplaceholder),
                        modifier = Modifier
                            .width(300.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    CarTextContent(car = car)
                }
            }
        }
    }

    @Composable
    fun CarTextContent(modifier: Modifier = Modifier, car: Car) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = car.make,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = car.model,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = getString(R.string.car_year, car.year),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = car.formattedKm,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = car.formattedPrice,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}