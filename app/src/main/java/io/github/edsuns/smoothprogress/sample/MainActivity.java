package io.github.edsuns.smoothprogress.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import io.github.edsuns.smoothprogress.SmoothProgressAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        SmoothProgressAnimator progressAnimator = new SmoothProgressAnimator(progressBar);

        final int[] progress = {10, 30, 50, 70, 85, 95, 100};
        linearLayout.setOnClickListener(new View.OnClickListener() {
            int index = 0;

            @Override
            public void onClick(View v) {
                if (index >= progress.length)
                    index = 0;
                progressAnimator.setProgress(progress[index++]);
            }
        });
    }
}