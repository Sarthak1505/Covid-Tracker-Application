
# COVID TRACKER APPLICATION

An android which tracks COVID-19 cases data and vaccination slots for India.
This app displays state wise data for COVID-19 cases.
It displays Active cases, Deaths and Recovered for each state and data of whole country.

This app also contains a Vaccine slot checker where user can enter the pincode of location
and get all the available vaccination center data.
The displayed vaccination center data consist of :-
  1. Center name
  2. Center address
  3. Vaccine Name (eg - Covaxin, Covishield)
  4. Available Vaccine
  5. Timings




## Tools And Technology

1. Kotlin
2. Retrofit
3. Dagger Hilt
4. Room Persistance Library
5. MVVM Architecture
6. Kotlin Coroutines
7. View Binding
8. Google Material Design
 

## App Work Flow

![App Flow](https://user-images.githubusercontent.com/69432514/159175269-24814302-f96f-4b32-8254-8d6e46c9049d.png)


##  Features

This app can be divided into three parts :-

1. Covid Cases :- This section displays the data of covid cases for each state.
2. Covid Details :- This shows all the neccessary precautions and symptoms of COVID-19.
3. Vaccine Slots :- In this section all the available vaccination center are displayed for the enterd pincode.
&nbsp;

**COVID CASES :-**
  In this when app is opened for the first time an api call is made and the fetched result is stored in the room database for caching.
  In other cases if data is changed then only cached data is changed and displayed.


**COVID DETAILS :-** This section just displays precautions and symptoms of COVID -19

**VACCINE SLOTS :-** In this user can enter the area pincode and select the desired date. Then an api call is made in backend which fetches the all the neccessary vaccination slot data and displays it in the recycler view.
 Offline Caching is not provided as to display realtime data to user.

## API Used
Covid Data - https://api-covid19-tracker.herokuapp.com/india/state_wise

&nbsp;
Cowin Api for Vaccine Data - https://apisetu.gov.in/api/cowin#/Appointment%20Availability%20APIs/findByPin
## DEMO


https://user-images.githubusercontent.com/69432514/159177626-0a758d89-638c-4538-86b4-bf1586257558.mp4




https://user-images.githubusercontent.com/69432514/159177637-0f73b930-f58d-4249-ab25-e952addaef9c.mp4

