package in.agrocartt.agrocartt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneVerifyActivity extends AppCompatActivity {

    //PHONE VERIFICATION
    String phoneNumber, otp;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @BindView(R.id.sendCodeButton)
    Button btnGenerateOTP;
    @BindView(R.id.phoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.signInBTN)
    Button btnSignIn;
    @BindView(R.id.otpET)
    EditText etOTP;

    FirebaseAuth auth;
    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        ButterKnife.bind(this);
        StartFirebaseLogin();

        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(PhoneVerifyActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(PhoneVerifyActivity.this);
                }
                builder.setTitle("SMS Recieve")
                        .setMessage("You will shortly recieve an OTP standard SMS charges may apply")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                phoneNumber=etPhoneNumber.getText().toString();
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        phoneNumber,                     // Phone number to verify
                                        60,                           // Timeout duration
                                        TimeUnit.SECONDS,                // Unit of timeout
                                        PhoneVerifyActivity.this,        // Activity (for callback binding)
                                        mCallbacks);                      // OnVerificationStateChangedCallbacks

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp=etOTP.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                SigninWithPhone(credential);
            }
        });
    }


    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(PhoneVerifyActivity.this,"Signed In",Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(PhoneVerifyActivity.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void StartFirebaseLogin() {
        auth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(PhoneVerifyActivity.this,"verification completed",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(PhoneVerifyActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i("TAG", e.getMessage());
            }
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(PhoneVerifyActivity.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }




}
