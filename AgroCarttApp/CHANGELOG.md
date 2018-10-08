# Android App CHANGELOGS

### LOG 1. Added Start Screen                                                                                          (06/07/18) (Contributing Member:- Sankalp Chauhan)
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

### LOG 2. Added Language Selection Screen and Improved Look and Feel                                                       1:00 AM (08/07/18) (Contributing Member:- Sankalp Chauhan)
Change Description:- 

1. Major Change (Added Language Selection Screen)
Added a languge screen when the user clicks on the language the language will get selected and stored in an xml format to a file "lang_info" in shared_prefs folder this information will be recieved at runtime and will be used to render the language of the whole app. This also sets a language flag to true in lang_info.xml file which is fetched at runtime and accordingly user will not see the language selection screen everytime the app opens, rather it will be seen only one time after installation.

2. Minor Changes (Improved Look and Feel)
* Matched Colors with the initiative's default colors.
* Added a dialog box on exit (onBackButtonPressed()) so that the user do not accidently close the app
* Minor Bug Fixes and Ripple Effect dependency for pre-lollipop devices (API less than 21) 
(Further Details are mentioned in the code comments)

---------------------------------------------------------------------------------------------------------------------------------

### LOG 3. Added Sign In Screen (Using Firebase), Drawer For Profile Screen                                                   12:45 AM (09/07/18) (Contributing Member:- Sankalp Chauhan)
Change Description:-

1. **Added Sign In Screen** User can now login via Google Sign In, this was implemented using firebase [Firebase Authentication Guide](https://firebase.google.com/docs/auth/)
2. **Added a slide Drawer** Right now it contains placeholders, The placeholders will be replaced by user info.

* Known Issues:- (Please update the section after solving the Issue with explainaition on how issue is resolved)

        Known Issues:-
        Right Now Sign in is only possible through Google Sign In however Facebook sign In and Phone OTP buttons are present but they are non-functional.


----------------------------------------------------------------------------------------------------------------------------------


### LOG 4. Added No Internet Connection Dialog                                                   11:51 PM (10/07/18) (Contributing Member:- Nikhil Dhiman)
Change Description:-
_Same As Title_		

------------------------------------------------------------------------------------------------------------------------------------


### LOG 5. Added Methods For Fetching and Storing User Details from Firebase and Display it on Profile Screen                                          03:30 PM (11/07/18) (Contributing Member:- Sankalp Chauhan)
Change Description:-
The user's data is stored in SharedPreferences on Login Screen and then populated on next activity i.e. Main Activity.
Now when the user opens the app second time the user will not have to login again and the details will already be fetched from the shared prefrences.

-------------------------------------------------------------------------------------------------------------------------------------


* SORRY NOTE

		Sorry for inconvinience:-
		One of the user's pointed this out, the app was non functional from 18th July to 26 July'18 this was due to improper resolving of merge conflict by me (i.e. Sankalp Chauhan) which left unusual charachters at the end of each xml file however this has been resolved as of today i.e. 26 July '18. To make sure this does not happen in future I have downloaded the GUI application to automatically handle merge conflicts, earlier this was done manually.


------------------------------------------------------------------------------------------------------------------------------
### LOG 6. Added Progress Bar and UI improvements                                                                                                  09:15 PM (26/07/18) (Contributing Member:- Sankalp Chauhan)
Change Description:-
* Progress Bar added on login screen and added Phone OTP button.
* Internet Connection is now checked on AppResume also, earlier this was missing

		 Known Issues:-
		 Right Now Sign in is only possible through Google Sign In however Phone OTP buttons are present but they are non-functional.
---------------------------------------------------------------------------------------------------------------------------------		 
### LOG 7. Solved API Authentication Fail Issue                                                                                                   08:00 PM (02/07/18) (Contributing Member:- Sankalp Chauhan & Kapil Chaudhary)
Change Description:-
* The issue was that when building app from android studio it was using SHA1 fingerprint of my Android Studio, this prevented any other Android Studio to make a call to the Firebase Authentication if the build was performed on their end.
* The solution was provided by [@Kapil Chaudhary](https://github.com/Kapil706) it was as follows:-
"[02/08, 18:34] ‪+91 95691 76495‬: If so, easiest solution, create a new debug keystore and put it into the project folder so that it goes into the git. Now any other user who downloads the project files also get the keystore. No more issues.
[02/08, 18:34] ‪+91 95691 76495‬: To generate a new keystore, https://coderwall.com/p/r09hoq/android-generate-release-debug-keystores"
---------------------------------------------------------------------------------------------------------------------------------

### LOG 8. Added Phone OTP Verification using Firebase                                                                                                   07:00 PM (05/07/18) (Contributing Member:- Sankalp Chauhan)
Change Description:-
Added Firebase Phone/OTP verification using the [Documentation Here](https://firebase.google.com/docs/auth/android/phone-auth)

---------------------------------------------------------------------------------------------------------------------------------

### LOG 9. Improved UI and added Sign Out Button on Drawer                                                                                                   11:00 PM (28/08/18) (Contributing Member:- Sankalp Chauhan)
Change Description:-
* Used Adobe XD to create professional look for Sign In Screen 
* Sign Out feature on Drawer Menu
* Performance Improvements.

---------------------------------------------------------------------------------------------------------------------------------


       
![Video](https://github.com/PaytmBuildForIndia/Agrocartt/blob/Contribute/assets/sample.gif)







