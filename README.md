# NewsApp
Simple android application for [NewsAPI](https://newsapi.org/) written in Kotlin using modern practices and tools such as:
* MVVM Architecture
* Android Jetpack (MVVM, LiveData, Navigation Component with Safeargs plugin)
* Single-Activity Architecture
* Dependency Injection using Dagger Hilt
* Room 2
* Retrofit 2
* Coroutines 

## If you want to run:
1. Go to [NewsAPI](https://newsapi.org/) and generate an API key (it's 2 steps only)
2. Put the API key at the gradle.properties file. Your line should look like this: API_KEY = "YOUR_API_KEY"
3. Run the app

## Architecture
The app uses ViewModel to abstract the data from UI and Repository as single source of truth for data.
Repository fetches data for Breaking News fragment and Search News fragment from Web Service and fetches data for Saved News fragment from database.
Detailed view of article opens in web-view.
![schema](https://miro.medium.com/max/3140/0*5gS3JAK8jSL7VkmJ)

## Screenshots
![Screenshot_20201124_172501](https://user-images.githubusercontent.com/68998152/100109320-c7552300-2e7c-11eb-890a-619d4e89f56f.png)
![Screenshot_20201124_173200](https://user-images.githubusercontent.com/68998152/100109331-c9b77d00-2e7c-11eb-8d69-15869c7988b6.png)
![Screenshot_20201124_173330](https://user-images.githubusercontent.com/68998152/100109335-ca501380-2e7c-11eb-8e7a-c9fc8121ebd6.png)
![Screenshot_20201124_173407](https://user-images.githubusercontent.com/68998152/100109340-cae8aa00-2e7c-11eb-9952-fd39d8324d0a.png)
