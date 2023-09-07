# Egardia Assessment Android

## Architecture
The app uses a basic MVVM setup for a proper separation of concerns. Also, there is a Reporitory layer to make fetching the data agnostic to its source. For now, all data will be parsed from the web by Retrofit.

## Jetpack Compose
Compose is relatively new (to me), I only made some smaller components in it before. But as this is the future of Android development, I thought it was nice and clean to make the entire app in Compose.

## Unit testing
Although there is not a lot of logic to test in a simple app like this, I wrote some unit tests for formatting the price and distance Strings of the Car model.
