package in.agrocartt.agrocartt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A small note:-
 *Min SDK Targeted:- 19 {SDK 19 == Android KITKAT}
 *Target SDK Version:- 26 (Android Oreo)
 *
 * Created by AgroCartt Team on 6/7/18.
 */


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    ImageView profileImage;
    TextView textName, textEmail;
    FirebaseAuth mAuth;

    //Getting Firebase data from shared Prefrences:-
    String firebase_UserPhotourl;
    String firebase_userDisplayName;
    String firebase_userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        //-------------------------NAVIGATION HEADER---------------------------------------
        //Getting the views of navigaion header explicitly as mentioned in documentation
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        profileImage = header.findViewById(R.id.firebase_userPhoto);
        textName = header.findViewById(R.id.firebase_userName);
        textEmail = header.findViewById(R.id.firebase_userEmail);
        //FETCHING USER DATA FROM SHARED PREFRENCES (I guess there is no need to check whether the user is present or not because that check has been performed on previous page)

        getFirebaseUserFromSharedPrefrences();
        if(firebase_userDisplayName.length()>0 && firebase_userEmail.length()>0)
        {
            if (getFirebaseUserFromSharedPrefrences() == true) {
                //Setting Data of User:-
                textName.setText(firebase_userDisplayName);
                textEmail.setText(firebase_userEmail);
                //check condition before url is placed

                    Glide.with(this).load(firebase_UserPhotourl).into(profileImage);

            }
        }
        //----------------------------HEADER SECTION END-----------------------------------




        //----------------------------DRAWER SECTION---------------------------------------

        //Sets toolbar burger for Drawer

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        //mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        switch(menuItem.getItemId()){
                            case R.id.signOut:
                                Toast.makeText(MainActivity.this, "Thankyou" + textName, Toast.LENGTH_SHORT).show();
                                signOut();
                                return true;
                        }
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        //----------------------------DRAWER SECTION END---------------------------------------



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
                //Don't add drawer items here it's in the drawer section.
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A simple dialog is shown on back button pressed so that the user does not accidently
     * exits the app
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    //Gets the user data from the shared prefrences this will be used to populate the drawer
    public boolean getFirebaseUserFromSharedPrefrences(){
        SharedPreferences sharePref = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        firebase_UserPhotourl = sharePref.getString("firebase_userPhotourl","Check Internet Connection");
        firebase_userDisplayName = sharePref.getString("firebase_userDisplayName","Check Internet Connection");
        firebase_userEmail = sharePref.getString("firebase_userEmail","Check Internet Connection");

        Log.i("FIREBASEfetch", "Data is"+ firebase_UserPhotourl + firebase_userDisplayName + firebase_userEmail );

        return true;
    }


    //A function for sign out from firebase
    public void signOut(){
        try {
            FirebaseAuth.getInstance().signOut();
            Log.i("Sign Out: ", "SUCCESS");
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        } catch (Exception e) {
            //Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}

