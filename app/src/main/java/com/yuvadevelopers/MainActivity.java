package com.yuvadevelopers;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.blogdemos.R;

public class MainActivity extends AppCompatActivity {
    
    CircularProgressbar progressbar;

    HorizontalProgressbar hProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar = (CircularProgressbar)findViewById(R.id.progressBar);
        progressbar.setProgressType(CircularProgressbar.INDETERMINATE);

        hProgressbar = (HorizontalProgressbar)findViewById(R.id.progressBar2);
//        hProgressbar.setProgress(56);

//       updateProgress();

    }


    int progrerss = 0;
    private void updateProgress(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                progrerss++;
                progressbar.setProgress(progrerss);
                hProgressbar.setProgress(progrerss);
                updateProgress();
            }
        };new Handler().postDelayed(runnable,50);

    }

}
