# Android App CHANGELOGS

### LOG 1. Added Start Screen (06/07/18) (Contributing Member:- Sankalp Chauhan)
Notes:-
1. 
 * Min SDK Targeted:- 19 {SDK 19 == Android KITKAT}
 * Target SDK Version:- 26 (Android Oreo)
2. 
 * USING GLIDE LIBRARY TO DISPLAY GIF:-
  Some people also use picasso but, glide is the latest library and,
  The way of loading an image in Glide is way faster than Picasso.
  For more details visit:-
  [https://github.com/bumptech/glide](https://github.com/bumptech/glide)
3. 
 * MultiThread
         * Assigned a Handler for the communication between the UI and Background thread in android in start_activity.class this thread can be used for connecting to the server and fetching HTTP requests, and will have no impact on Main Thread of the Application. Instead of using a seperate class I have used Anonymous Inner class for implementing the runnable interface because in this activity, another implementation of runnable is not required For understanding why I have implemented this please refer this article by me:-
[Multithreading Core Java For Android Developers](https://medium.com/@sankalpchauhan.me/core-java-for-android-developers-multithreading-e7ec7d53924c) 
4. The GIF I used can be replaced with appropriate GIF (Further Details are mentioned in the comments)

      * BUG FIXED(6/07/2018):
      
       BUG
       Multiple MainActivities were launching when the user changes the device orientation
       during the GIF display.
       FIX
       Changed the configuration of .startScreen to ensure that it does not restart on orientation change
       I could have disabled screen orientation altogether but it would create problem in TABLETS
-------------------------------------------------------------------------------------------------------------------------------------------------------

### LOG 1. Added Language Selection Screen and Improved Look and Feel 1:00 AM (08/07/18) (Contributing Member:- Sankalp Chauhan)
Short Description:- (Will add detailed description later)

* Now user can select the language of app the language will be stored in shared prefrences, so the app will know which language did the user choose the last time 
* The app will not prompt the user for language each time but only one time after installation
* matched theme with the initiative's default colors 
* added a dialog box on exit
* Minor Bug Fixes and Ripple Effect dependency for pre-lollipop devices (API less than 21) 

       
[Video]







