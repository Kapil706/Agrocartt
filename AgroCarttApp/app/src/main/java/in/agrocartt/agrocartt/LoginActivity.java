package in.agrocartt.agrocartt;



import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;
    //private ImageView user_profile;

    //for getting user's data from firebaseAuth
    String user_photoUrl = "";
    String user_displayName = "";
    String user_email = "";

    //Tag for the logs
    private static final String TAG = "Login_Activity";

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;

    //And also a Firebase Auth object
    FirebaseAuth mAuth;

    ProgressBar progressBar;

    //For Verification Number Screen
    @BindView(R.id.phoneVerify)
    Button verifyBTN;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

       //Network Connection is checked here:
        if(!isConnected(LoginActivity.this)) buildDialog(LoginActivity.this).show();


        //Intialized the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();

       // user_profile = findViewById(R.id.firebase_userPhoto); (DEPRECEATED)

        //Added a GoogleSignInOptions object
        //And we need to build it as below
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Now we will attach a click listener to the sign_in_button
        //and inside onClick() method we are calling the signIn() method that will open
        //google sign in intent
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        progressBar = findViewById(R.id.login_progress);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isConnected(LoginActivity.this)) buildDialog(LoginActivity.this).show();
    }


//Getting user details from Firebase and storing it to shared prefrences
    //--------------------------------------------------------------------------------------------------------------------

    public void firebase_fetch_details(){
       FirebaseUser user = mAuth.getCurrentUser();
       if(user !=null){
           user_photoUrl = user.getPhotoUrl().toString();
           user_displayName = user.getDisplayName();
           user_email = user.getEmail();

           Log.i("Firebase details", "Details are " + "URL: " + user_photoUrl + "Name: " + user_displayName + "Email: "+ user_email);
       }
       dataToSharedPrefrences(user_photoUrl,user_displayName,user_email);
    }

    public void dataToSharedPrefrences(String fireurl, String firedisplayName, String fireemail){
        SharedPreferences sharePref = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor shareEdit = sharePref.edit();
        shareEdit.putString("firebase_userPhotourl", fireurl);
        shareEdit.putString("firebase_userDisplayName", firedisplayName);
        shareEdit.putString("firebase_userEmail", fireemail);
        shareEdit.commit();
        Log.i("FIREBASE DATA", "Data stored in Shared Prefrences");
    }
    //Getting user details from Firebase and storing it to shared prefrences over--------------------------------------------------------------------------------------------------------------------






    /**
     * Checks if the user is connected to the internet
     * @param context
     * @return
     */
    //CHECK NETWORK STATE----------------------------------------------------------------------------------------------------------------------
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        Log.i("NetworkState", "isConnected checking network state.......");
        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {
        Log.i("NetworkState", "Network Failed......... Building Dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or WIFI to access AgroCartt. Press Enable to access network settings");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                startActivity(in);
               //finish();
            }
        });

        return builder;
    }
    //CHECK NETWORK STATE METHODS OVER----------------------------------------------------------------------------------------------------------------------





    @Override
    protected void onStart() {
        super.onStart();

        //if the user is already signed in
        //we will close this activity
        //and take the user to profile activity
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.v("SHA1 Failiure", e.getMessage());
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, "API Call FAIL", Toast.LENGTH_SHORT).show();
            }
        }
    }





    //firebaseAuthentication start----------------------------------------------------------------------------------------------------------------------

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        progressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            firebase_fetch_details();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            Log.i("FIREBASE", "Sign In Success");
                            LoginActivity.this.finish();
                            Toast.makeText(LoginActivity.this, user_displayName + " Signed In", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, a message is displayed to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


    //this method is called on click
    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //firebaseAuthentication start methods over ----------------------------------------------------------------------------------------------------------------------






    //A simple back dialog as is used in MainActivity
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This app requires sign in to work, Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginActivity.this.finish();
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

    @OnClick(R.id.phoneVerify)
    void toPhoneVerifyScreen(View view) {
       Intent i = new Intent(this, PhoneVerifyActivity.class);
       startActivity(i);


    }





}











