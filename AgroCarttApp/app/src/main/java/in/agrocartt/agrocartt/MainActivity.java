package in.agrocartt.agrocartt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * A small note:-
 *Min SDK Targeted:- 19 {SDK 19 == Android KITKAT}
 *Target SDK Version:- 26 (Android Oreo)
 *
 * Created by AgroCartt Team on 6/7/18.
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
