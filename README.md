# Android (Native App) with the Implementaiton of MVP Design pattern


# Why MVP?

Fist let's just answer the question why we even use a design pattern ? 
The purpose of using an architecture like MVP is that we can achieve separation of concern between the different parts of applicaiton that are communicating. There are many benifits of using these approaches like MVC, MVP and MVVM. One of tha benifit is that we can achieve the testable modules which helps us in automated testing. Other benifit is that we can reuse the code. It gives us the opportunity that we can separate the Bussiness logic of the application from the rest of the Application so that we can reuse it somewhere.


# What's in this app?

I have used the MVP pattern besides the Retrofit client libaray for Network Calling.
This app utlilizes the herokuapp Open API given here: https://ghibliapi.herokuapp.com/films and the documentation here:https://ghibliapi.herokuapp.com/#section/Studio-Ghibli-API

App fetches the data from the given API above and shows the listing of Movies in a simple Single Screen having recyclerView.


# Dependencies Required

- Retrofit
- Retrofit Logging Intercepter
- Gson
