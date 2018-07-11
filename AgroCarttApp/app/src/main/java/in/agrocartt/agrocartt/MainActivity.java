package in.agrocartt.agrocartt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
import android.graphics.Bitmap;
=======
>>>>>>> a06ef9cc60137b00c27c27dcb732cf34f9228a9b
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
=======
import android.view.MenuItem;
>>>>>>> a06ef9cc60137b00c27c27dcb732cf34f9228a9b
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A small note:-
 *Min SDK Targeted:- 19 {SDK 19 == Android KITKAT}
 *Target SDK Version:- 26 (Android Oreo)
 *
 * Created by AgroCartt Team on 6/7/18.
 */


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
<<<<<<< HEAD
    ImageView profileImage;
    TextView textName, textEmail;
    FirebaseAuth mAuth;

    //Getting Firebase data from shared Prefrences:-
    String firebase_UserPhotourl;
    String firebase_userDisplayName;
    String firebase_userEmail;

=======
    ImageView imageView;
    TextView textName, textEmail;
    FirebaseAuth mAuth;

>>>>>>> a06ef9cc60137b00c27c27dcb732cf34f9228a9b
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

<<<<<<< HEAD
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
                if (firebase_UserPhotourl.length()>0) {
                    Glide.with(this).load(firebase_UserPhotourl).into(profileImage);
                } else {
                    Glide.with(this).load(R.mipmap.defaultuserpic).into(profileImage);
                }
            }
        }
        //----------------------------HEADER SECTION END-----------------------------------




        //----------------------------DRAWER SECTION---------------------------------------
=======
        imageView = findViewById(R.id.firebase_userPhoto);
        textName = findViewById(R.id.firebase_userName);
        textEmail = findViewById(R.id.firebase_userEmail);
>>>>>>> a06ef9cc60137b00c27c27dcb732cf34f9228a9b

        //Sets toolbar burger for Drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);

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
                        // For example, swap UI fragments here

                        return true;
                    }
                });

<<<<<<< HEAD
        //----------------------------DRAWER SECTION END---------------------------------------


=======
        //Will be activated when firebase gets activated
        //getuserdata(textName, textEmail, imageView);


    }
>>>>>>> a06ef9cc60137b00c27c27dcb732cf34f9228a9b

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
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

<<<<<<< HEAD

    //Gets the user data from the shared prefrences this will be used to populate the drawer
    public boolean getFirebaseUserFromSharedPrefrences(){
        SharedPreferences sharePref = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        firebase_UserPhotourl = sharePref.getString("firebase_userPhotourl","Check Internet Connection");
        firebase_userDisplayName = sharePref.getString("firebase_userDisplayName","Check Internet Connection");
        firebase_userEmail = sharePref.getString("firebase_userEmail","Check Internet Connection");

        Log.i("FIREBASEfetch", "Data is"+ firebase_UserPhotourl + firebase_userDisplayName + firebase_userEmail );

        return true;
    }

=======
    /**
     * CODE GIVING NULLPOINTEREXCEPTION
     *
    public void getuserdata(TextView mName, TextView mEmail, ImageView photo){
        FirebaseUser user = mAuth.getCurrentUser();

        Uri getImage = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();



        mName.setText(user.getDisplayName());
        mEmail.setText(user.getEmail());
    }
     **/
>>>>>>> a06ef9cc60137b00c27c27dcb732cf34f9228a9b

}

