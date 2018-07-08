package in.agrocartt.agrocartt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
//Glide Library Import
import com.bumptech.glide.Glide;

/**
 * Created by AgroCartt Team on 6/7/18.
 * @AgroCartt
 */

public class startScreen extends AppCompatActivity {
    public static int TIME_OUT_ASSIGNED = 4000;     //Change the value of variable for varying the timeout
    private ImageView imageView;
    private Boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Setting ImageView
        imageView = findViewById(R.id.org_gif);

        // USING GLIDE LIBRARY TO DISPLAY GIF
        //some people also use picasso but, glide is the latest library and,
        //The way of loading an image in Glide is way faster than Picasso.
        //For more details visit:-
        // https://github.com/bumptech/glide

        Glide.with(this).load(R.drawable.agro).into(imageView);

        /**
         * @MultiThread (A short Note)
         * Assigning a Handler for the communication between the UI and Background thread in android.
         * this thread can be used for connecting to the server and fetching HTTP requests, and will have
         * no impact on Main Thread of Application. Regards Sankalp.
         *
         * Instead of using a seperate class I have used Anonymous Inner class for implementing the
         * runnable interface because in this activity, another implementation of runnable is not required
         * For understanding why I have implemented this please refer this article by me:-
         * @https://medium.com/@sankalpchauhan.me/core-java-for-android-developers-multithreading-e7ec7d53924c
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Checks whether the user selected the language previously if not it will start LanguageSetting else MainActivity
                if(getLanguageSet() == false) {
                    Intent i = new Intent(startScreen.this, LanguageSetting.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(startScreen.this, LoginActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, TIME_OUT_ASSIGNED);

    }

    /**
     *
     * @returns the language that was selected by user previously from the SharedPrefrences
     */
    public boolean getLanguageSet(){
        SharedPreferences sharePref = getSharedPreferences("lang_info", Context.MODE_PRIVATE);
        check = sharePref.getBoolean("languageSet", false);
        return check;
    }
}
