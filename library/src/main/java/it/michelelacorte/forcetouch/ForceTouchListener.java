package it.michelelacorte.forcetouch;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.browse.MediaBrowser;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.Callable;

/**
 * Created by Michele on 21/10/2016.
 */

/**
 * Force Touch Listener is a class that provide listener to implement Force Touch with Android
 */
public class ForceTouchListener implements View.OnTouchListener {
    //Tag for logcat
    private static final String TAG = "ForceTouchListener";
    //Deafult pressure limit
    private static final float DEFAULT_PRESSURE_LIMIT = 0.27f;
    //Default millis to vibrate
    private static final long DEFAULT_MILLIS_TO_VIBRATE = 70;
    //Local variable
    private Context context;
    private long millisToVibrate;
    private float pressureLimit;
    private float pressure;
    private ForceTouchExecution forceTouchExecution;

    /**
     * Public constructor with Context, millisToVibrate and pressureLimit
     * @param context Context
     * @param millisToVibrate long
     * @param pressureLimit float
     * @param forceTouchExecution ForceTouchExecution
     */
    public ForceTouchListener(Context context, long millisToVibrate, float pressureLimit, ForceTouchExecution forceTouchExecution)
    {
        this.context = context;
        this.millisToVibrate = millisToVibrate;
        this.pressureLimit = pressureLimit;
        this.forceTouchExecution = forceTouchExecution;
    }

    /**
     * Public constructor with Context and pressureLimit, millisToVibrate is setted to 70ms
     * @param context Context
     * @param pressureLimit float
     * @param forceTouchExecution ForceTouchExecution
     */
    public ForceTouchListener(Context context, float pressureLimit, ForceTouchExecution forceTouchExecution)
    {
        this.context = context;
        this.millisToVibrate = DEFAULT_MILLIS_TO_VIBRATE;
        this.pressureLimit = pressureLimit;
        this.forceTouchExecution = forceTouchExecution;
    }

    /**
     * Public constructor with Context and millisToVibrate, pressureLimit is setted to 0.27
     * @param context Context
     * @param millisToVibrate long
     * @param forceTouchExecution ForceTouchExecution
     */
    public ForceTouchListener(Context context, long millisToVibrate, ForceTouchExecution forceTouchExecution)
    {
        this.context = context;
        this.millisToVibrate = millisToVibrate;
        this.pressureLimit = DEFAULT_PRESSURE_LIMIT;
        this.forceTouchExecution = forceTouchExecution;
    }

    /**
     * Public constructor with only Context, millisToVibrate and pressureLimit is setted by default
     * @param context Context
     * @param forceTouchExecution ForceTouchExecution
     */
    public ForceTouchListener(Context context, ForceTouchExecution forceTouchExecution){
        this.context = context;
        this.millisToVibrate = DEFAULT_MILLIS_TO_VIBRATE;
        this.pressureLimit = DEFAULT_PRESSURE_LIMIT;
        this.forceTouchExecution = forceTouchExecution;
    }

    /**
     * Implemented ForceTouchListener
     * @param view VIew
     * @param motionEvent MotionEvent
     * @return boolean
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float pressure = motionEvent.getPressure();
        checkParam(pressureLimit, millisToVibrate);
        if(pressure >= pressureLimit) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                Vibrator vibr = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibr.vibrate(millisToVibrate);
                forceTouchExecution.onForceTouch();
            }
        }
        setPressure(pressure);
        return true;
    }

    /**
     * Check param before continue
     * @param pressureLimit float
     * @param millisToVibrate long
     */
    private void checkParam(float pressureLimit, long millisToVibrate){
        if(pressureLimit < 0 && pressureLimit > 1){
            setPressureLimit(DEFAULT_PRESSURE_LIMIT);
            Log.e(TAG, "Invalid pressureLimit (float between 0 and 1), restored default: " + DEFAULT_PRESSURE_LIMIT);
        }if(millisToVibrate < 0){
            setMillisToVibrate(DEFAULT_MILLIS_TO_VIBRATE);
            Log.e(TAG, "Invalid millisToVibrate, restored default: " + DEFAULT_MILLIS_TO_VIBRATE + " millis");
        }
    }

    /**
     * Set millisToVibrate
     * @param millisToVibrate long
     */
    private void setMillisToVibrate(long millisToVibrate){ this.millisToVibrate = millisToVibrate; }

    /**
     * Set pressureLimit
     * @param pressureLimit float
     */
    private void setPressureLimit(float pressureLimit) { this.pressureLimit = pressureLimit; }

    /**
     * Set runtime pressure
     * @param pressure float
     */
    private void setPressure(float pressure){
        this.pressure = pressure;
    }

    /**
     * Get runtime pressure
     * @return float
     */
    public float getPressure(){
        return pressure;
    }

    /**
     * Get pressure limit
     * @return float
     */
    public float getPressureLimit(){ return pressureLimit; }

    /**
     * Get millis to vibrate
     * @return long
     */
    public long getMillisToVibrate(){ return millisToVibrate; }
}
