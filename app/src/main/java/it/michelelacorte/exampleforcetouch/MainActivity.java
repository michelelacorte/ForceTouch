package it.michelelacorte.exampleforcetouch;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import it.michelelacorte.forcetouch.Callback;
import it.michelelacorte.forcetouch.ForceTouchListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView pressureText;
    private EditText pressureLimit;
    private CheckBox isProgressive;
    private CheckBox isVibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); #gitignore
        pressureText = (TextView) findViewById(R.id.pressureText);
        pressureLimit = (EditText) findViewById(R.id.pressureLimit);
        isProgressive = (CheckBox) findViewById(R.id.isProgressive);
        isVibrate = (CheckBox) findViewById(R.id.isVibrate);
        final ForceTouchListener forceTouchListener = new ForceTouchListener(getApplicationContext(), 70, 0.27f, isProgressive.isChecked(), isVibrate.isChecked(), new Callback() {
            @Override
            public void onForceTouch() {
                functionToInvokeOnForceTouch();
            }
            @Override
            public void onNormalTouch() { functionToInvokeOnNormalTouch(); }
        });
        getWindow().getDecorView().getRootView().setOnTouchListener(forceTouchListener);

        isProgressive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                ForceTouchListener forceTouchListener = new ForceTouchListener(getApplicationContext(), 70, Float.valueOf(pressureLimit.getText().toString()), isChecked, isVibrate.isChecked(), new Callback() {
                    @Override
                    public void onForceTouch() {
                        functionToInvokeOnForceTouch();
                    }

                    @Override
                    public void onNormalTouch() { functionToInvokeOnNormalTouch(); }
                });
                getWindow().getDecorView().getRootView().setOnTouchListener(forceTouchListener);
                schedule(forceTouchListener);
            }
        });

        isVibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                ForceTouchListener forceTouchListener = new ForceTouchListener(getApplicationContext(), 70, Float.valueOf(pressureLimit.getText().toString()), isProgressive.isChecked(), isChecked, new Callback() {
                    @Override
                    public void onForceTouch() {
                        functionToInvokeOnForceTouch();
                    }

                    @Override
                    public void onNormalTouch() { functionToInvokeOnNormalTouch(); }
                });
                getWindow().getDecorView().getRootView().setOnTouchListener(forceTouchListener);
                schedule(forceTouchListener);
            }
        });

        pressureLimit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (keyEvent.getAction() == KeyEvent.ACTION_DOWN ))) {
                    ForceTouchListener forceTouchListener = new ForceTouchListener(getApplicationContext(), 70, Float.valueOf(pressureLimit.getText().toString()), isProgressive.isChecked(), isVibrate.isChecked(), new Callback() {
                        @Override
                        public void onForceTouch() {
                            functionToInvokeOnForceTouch();
                        }

                        @Override
                        public void onNormalTouch() { functionToInvokeOnNormalTouch(); }
                    });
                    getWindow().getDecorView().getRootView().setOnTouchListener(forceTouchListener);
                    schedule(forceTouchListener);
                    hideKeyboard();
                }
                return true;
            }
        });
        schedule(forceTouchListener);

    }

    private void hideKeyboard(){
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void schedule(final ForceTouchListener forceTouchListener){
        pressureLimit.setText("" + forceTouchListener.getPressureLimit());
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
        Log.e(TAG, "Function invoked forced!");
    }


    /**
     * Method invoked on NormalTouch detected
     */
    private void functionToInvokeOnNormalTouch(){
        Log.e(TAG, "Function invoked normal!");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.about:
                aboutAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void aboutAlertDialog()
    {
        AlertDialog builder =
                new AlertDialog.Builder(this, R.style.AlertDialogCustom).setTitle(getResources().getString(R.string.app_name))
                        .setCancelable(false)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.disclaimer_dialog_message)
                        .setPositiveButton(getResources().getString(R.string.disclaimer_dialog_ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }).create();
        builder.show();
        ((TextView)builder.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)builder.findViewById(android.R.id.message)).setGravity(Gravity.CENTER_VERTICAL);
        builder.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
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