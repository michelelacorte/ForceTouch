package it.michelelacorte.exampleforcetouch;

import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import it.michelelacorte.forcetouch.ForceTouchExecution;
import it.michelelacorte.forcetouch.ForceTouchListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView pressureText;
    private TextView pressureLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pressureText = (TextView) findViewById(R.id.pressureText);
        pressureLimit = (TextView) findViewById(R.id.pressureLimit);
        final ForceTouchListener forceTouchListener = new ForceTouchListener(getApplicationContext(), 70, 0.27f, new ForceTouchExecution() {
            @Override
            public void onForceTouch() {
                functionToInvokeOnForceTouch();
            }
        });
        getWindow().getDecorView().getRootView().setOnTouchListener(forceTouchListener);
        pressureLimit.setText("Pressure Limit: " + forceTouchListener.getPressureLimit());
        TaskScheduler timer = new TaskScheduler();
        timer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                pressureText.setText("Pressure: " + forceTouchListener.getPressure());
            }
        },1);

    }

    /**
     * Method invoked on ForceTouch detected
     */
    private void functionToInvokeOnForceTouch(){
        Log.e(TAG, "Function invoked!");
    }
}


class TaskScheduler extends Handler {
    private ArrayMap<Runnable,Runnable> tasks = new ArrayMap<>();
    public void scheduleAtFixedRate(final Runnable task,long delay,final long period) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                task.run();
                postDelayed(this, period);
            }
        };
        tasks.put(task, runnable);
        postDelayed(runnable, delay);
    }
    public void scheduleAtFixedRate(final Runnable task,final long period) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                task.run();
                postDelayed(this, period);
            }
        };
        tasks.put(task, runnable);
        runnable.run();
    }
    public void stop(Runnable task) {
        Runnable removed = tasks.remove(task);
        if (removed!=null) removeCallbacks(removed);
    }

}