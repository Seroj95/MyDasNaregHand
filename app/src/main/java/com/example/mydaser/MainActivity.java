package com.example.mydaser;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    private int second = 0;
    private boolean isRuning = false;
    private TextView textViewTimer;
    private Button buttonStartPause,buttonReset,buttonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       textViewTimer = findViewById(R.id.textViewTimer);
        buttonStartPause = findViewById(R.id.buttonStart);
        buttonReset = findViewById(R.id.buttonReset);
        buttonSave = findViewById(R.id.buttonSave);
        if (savedInstanceState  != null){
            second=  savedInstanceState.getInt("sec");
            isRuning=savedInstanceState.getBoolean("isRun");
        }
        runTimer();
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sec",second);
        outState.putBoolean("isRun",isRuning);
    }
    public void onClickStartTimer(View view) {
        if (!isRuning) {
            buttonStartPause.setText("Stop");
            isRuning = true;
            buttonSave.setVisibility(View.INVISIBLE);
        } else {
            buttonStartPause.setText("Start");
            isRuning = false;
            buttonSave.setVisibility(View.VISIBLE);
        }
    }
    public void onClickResetTimer(View view) {
        isRuning = false;
        second = 0;
    }
    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int sec = second % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec);
                textViewTimer.setText(time);
                if (isRuning) {
                    second++;
                }
                handler.postDelayed(this, 200);
            }
        });
    }
    public void saveOnClick(View view) {
        Intent intent = new Intent(this,SaveActivity.class);
        intent.putExtra("name",textViewTimer.getText());
        startActivity(intent);
    }
}

