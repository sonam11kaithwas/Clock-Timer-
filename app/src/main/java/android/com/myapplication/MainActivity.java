package android.com.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView timerLable;
    private boolean TIMERCOUNTSTART = false;
    private Timer timer;
    private TimerTask timerTask;
    private double myTimerTime = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();
    }

    private void findMyViews() {
        timerLable = findViewById(R.id.timer_lable);
        timer = new Timer();
    }

    public void timerAction(View view) {
        switch (view.getId()) {
            case R.id.start_timer_btn:
                startTimerCounting();
                break;
            case R.id.stop_timer_btn:
                stopTimerCounting();
                break;
        }
    }


    private void startTimerCounting() {
        timerTask = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myTimerTime++;
                        getTimerTime();
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void getTimerTime() {
        int round = (int) Math.round(myTimerTime);
        int second = ((round % 86000) % 3600) % 60;
        int minutes = ((round % 86000) % 3600) / 60;
        int hours = ((round % 86000) / 3600);

        String str = String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", second) + " : ";
        timerLable.setText(str);
    }


    private void stopTimerCounting() {
        if (!TIMERCOUNTSTART) {
            TIMERCOUNTSTART = true;
            timerLable.setText("00 hr:00 mm:00 sec");
            if (timerTask != null) {
                myTimerTime = 0.0;
                timerTask.cancel();
            }
        } else {
            TIMERCOUNTSTART = false;
        }
    }
}