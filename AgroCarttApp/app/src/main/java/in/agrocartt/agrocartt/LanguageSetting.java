package in.agrocartt.agrocartt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LanguageSetting extends AppCompatActivity implements View.OnClickListener {
    private TextView lang1, lang2, lang3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "More Languages Coming Soon...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        lang1 = findViewById(R.id.eng);
        lang2 = findViewById(R.id.punjabi);
        lang3 = findViewById(R.id.hindi);
        lang1.setOnClickListener(this);
        lang2.setOnClickListener(this);
        lang3.setOnClickListener(this);

    }

    //Implementation of onClickListner
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.eng:{
                //TODO Logic here
                isLanguageSet(true,"english");
                moveToNextActivity();
                finish();
            }
            break;
            case R.id.punjabi:{
                //TODO Logic here
                Log.i("PUNJABI", "PUNJABI SELECTED");
                moveToNextActivity();
                isLanguageSet(true,"punjabi");
                finish();
            }
            break;
            case R.id.hindi:{
                //TODO Logic here
                Log.i("HINDI", "HINDI SELECTED");
                isLanguageSet(true, "hindi");
                moveToNextActivity();
                finish();
            }
            break;
            default:{
                Toast.makeText(LanguageSetting.this, "Please select a language to continue", Toast.LENGTH_LONG).show();
            }
        }

    }

    /**
     * Stores the state of the language setting in SharedPrefrences with the filename "lang_info.xml"
     * @param bool for storing the state of setting with key language set and value true or false
     * @param language for storing the language selected with key selectedLanguage and value = hindi, english,punjabi
     */
    public void isLanguageSet(Boolean bool, String language){
        SharedPreferences sharePref = getSharedPreferences("lang_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor shareEdit = sharePref.edit();
        shareEdit.putBoolean("languageSet", bool);
        shareEdit.putString("selectedLanguage", language);
        shareEdit.commit();
    }


    // Sets an intent to move to next Activity
    public void moveToNextActivity(){
        Intent i = new Intent(LanguageSetting.this, MainActivity.class);
        startActivity(i);
    }



}
