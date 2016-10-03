# This is the website where I got the code for DatePickerDailog
http://www.tutorialspoint.com/android/android_datepicker_control.htm
In app:
In the Habit_AddingPage_Activity, I use these codes to call out the DatePickerDialog when the user
clicks on the edit text. It allows user to set date and shows it on the edit text view.
----------------------------------------------------------------------------------------------
#How to use the finsih() to close an activity
http://stackoverflow.com/questions/21473509/android-how-to-close-an-activity-on-button-click
#Author: mike yaworski
In app:
I used the finish() method to close the activity and go back to the previous activity.
----------------------------------------------------------------------------------------------
#Convert string to Date
http://stackoverflow.com/questions/9945072/convert-string-to-date-in-java
#Author: Boris Strandjev
In app:
I used these codes to convert my date which I got it from the edit text (String type) to a Date type
-----------------------------------------------------------------------------------------------
#Listview item position
https://developer.android.com/reference/android/widget/AdapterView.html#getItemAtPosition%28int%29
In app:
Get the item when the user click on one of the item on the list view
------------------------------------------------------------------------------------------------
#How to use adapter and gson/json for save and load file    
//https://github.com/shidahe/lonelyTwitter   
#Author shidehe
I use the adapter to change the list view. Also use the gson/json to save and load the file.
---------------------------------------------------------------------------------------------------
Video UML:https://www.youtube.com/watch?v=QdX47zX2kfM&feature=youtu.be

Build Gradle for my app
compileSdkVersion 24
buildToolsVersion "24.0.2"
minSdkVersion 18
targetSdkVersion 24
versionCode 1
versionName "1.0"
---------------------------------------------------------------------------------------------------
App Description:
  When open the app, the main page would be the habit history, clicking the view habit button will go
to Habit Editor page where the users can see the habit that they had added. There will be add habit 
button when the user can add new habit and back button which return to the main page.
  When the user are adding the habit, they can set the title, days of the week and the date. A datepicker
dialog will pop out when the user click on the date. After adding the new habit, the user can see 
the habit that they added in the HaHebit Editor page. 
  The user can click on the habit on the list which goes to Detail activity where there are two buttons. The complete button and delete button. User can delete or complete the habit. The cancel button will just close the activity without changing the habit.
Also, the user can click the habiy history list in the main page to delete the history.
---------------------------------------------------------------------------------------------------
Colaboration:
-Consult with Zhimao Lin and Chuan Yang
