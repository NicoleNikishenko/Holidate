# Holidate

![Screens](https://github.com/NicoleNikishenko/Holidate/assets/65169720/39eefb8f-78f1-432c-96b1-f3cb13426060)


# Intro

My main focus in creating this project was first to implement the proper architecture using MVVM, deciding which Repositories and ViewModels I need and what is the best way to handle the data flow.
The second focus was deciding on the proper local database to use and implementing the local database.
Then, I also noticed another issue - that the Public Holiday API only works with certain countries, and Israel is not one of them. The application is location-based, meaning all Israel-located users will always get an empty list. For better UX, I added an option to select a country from a closed list of available countries using the AvailableCountries endpoint.
Finally, after implementing all the logic in the application and ensuring everything ran smoothly without issues, I added improvements to the UI until I was satisfied with the app's appearance and design.

While working on the project, I deliberated over the optimal approach for storing local data. My dilemma was between a simple solution, such as SharedPrefference or Internal Storage, and an SQL database solution using the Room library. The stored favorite holidays list is short, but it has structured data, and the add and remove from favorite functions are easier to implement using Room. Also, I wanted to support query functions such as findFavoriteById and findFavoritesByCountry. That is why I decided to go with the Room database. 

# Screens Flow Chart 
  
![screen_flow](https://github.com/NicoleNikishenko/Holidate/assets/65169720/b630790f-443c-410f-8437-22e4bc656e48)


# Architecture Stracture Chart

![architecture_stracture](https://github.com/NicoleNikishenko/Holidate/assets/65169720/71264e33-6574-4f50-8707-80e71243b446)



# Libraries used:
Network Libraries - OkHttp, Retrofit

JSON Parsing - Moshi

Dependency Injection - Koin

Fragment Management - Navigation and SafeArgs

Local Database - Room

Location Services
