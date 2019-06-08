package swim.cw.game;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.SensorEventListener;


public class MainActivity extends Activity {

    BallView mBallView = null;
 //   SecondBallView sBallView = null;
 //   ThirdBallView tBallView = null;
    Handler RedrawHandler = new Handler();
    Timer mTmr = null;
    TimerTask mTsk = null;
    int mScrWidth, mScrHeight;
    android.graphics.PointF mBallPos, mBallSpd;
 //   android.graphics.PointF mBallPos1, mBallSpd1;
  //  android.graphics.PointF mBallPos2, mBallSpd2;
    MediaPlayer mysong2;
    MediaPlayer mysong3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(0xFFFFFFFF,
                LayoutParams.FLAG_FULLSCREEN|LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysong2 = MediaPlayer.create(MainActivity.this,R.raw.first);
        mysong3 = MediaPlayer.create(MainActivity.this,R.raw.third);
        mysong2.start();

        final FrameLayout mainView = (android.widget.FrameLayout) findViewById(R.id.main_view);


        Display display = getWindowManager().getDefaultDisplay();
        mScrWidth = display.getWidth();
        mScrHeight = display.getHeight();
        mBallPos = new android.graphics.PointF();
        mBallSpd = new android.graphics.PointF();
//        mBallPos1 = new android.graphics.PointF();
//        mBallSpd1 = new android.graphics.PointF();
//        mBallPos2 = new android.graphics.PointF();
//        mBallSpd2 = new android.graphics.PointF();


        mBallPos.x = mScrWidth/2;
        mBallPos.y = mScrHeight/2;
        mBallSpd.x = 0;
        mBallSpd.y = 0;

//        mBallPos1.x = mScrWidth/2;
//        mBallPos1.y = mScrHeight/2;
//        mBallSpd1.x = 0;
//        mBallSpd1.y = 0;
//
//        mBallPos2.x = mScrWidth/2;
//        mBallPos2.y = mScrHeight/2;
//        mBallSpd2.x = 0;
//        mBallSpd2.y = 0;


        mBallView = new BallView(this,mBallPos.x,mBallPos.y,80);
//        sBallView = new SecondBallView(this,mBallPos1.x, mBallPos1.y,80);
//        tBallView = new ThirdBallView(this,mBallPos2.x, mBallPos2.y,80);

        mainView.addView(mBallView);
//        mainView.addView(sBallView);
//        mainView.addView(tBallView);
        mBallView.invalidate();
//        sBallView.invalidate();
//        tBallView.invalidate();


        ((SensorManager)getSystemService(Context.SENSOR_SERVICE)).registerListener(
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {

                        mBallSpd.x = -event.values[0];
                        mBallSpd.y = event.values[1];
//                        mBallSpd1.x = -event.values[0];
//                        mBallSpd1.y = event.values[1];
//                        mBallSpd2.x = -event.values[0];
//                        mBallSpd2.y = event.values[1];

                    }
                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
                },
                ((SensorManager)getSystemService(Context.SENSOR_SERVICE))
                        .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);


        mainView.setOnTouchListener(new android.view.View.OnTouchListener() {
            public boolean onTouch(android.view.View v, android.view.MotionEvent e) {

                mBallPos.x = e.getX();
                mBallPos.y = e.getY();
//                mBallPos1.x = e.getX();
//                mBallPos1.y = e.getY();
//                mBallPos2.x = e.getX();
//                mBallPos2.y = e.getY();

                return true;
            }});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Exit");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle() == "Exit")
            finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPause()
    {
        mTmr.cancel();
        mTmr = null;
        mTsk = null;
        mysong2.stop();
        mysong3.start();

        final Intent intencja = new Intent(this,Stop.class);
      //  mysong3.stop();
        startActivity(intencja);


        super.onPause();
    }

    @Override
    public void onResume()
    {

        mTmr = new Timer();
        mTsk = new TimerTask() {
            public void run() {

                android.util.Log.d(
                        "TiltBall","Timer Hit - " + mBallPos.x + ":" + mBallPos.y);
//                android.util.Log.d(
//                        "TiltBall","Timer Hit - " + mBallPos1.x + ":" + mBallPos1.y);
//                android.util.Log.d(
//                        "TiltBall","Timer Hit - " + mBallPos2.x + ":" + mBallPos2.y);

                mBallPos.x += mBallSpd.x;
                mBallPos.y += mBallSpd.y;
//                mBallPos1.x += mBallSpd1.x;
//                mBallPos1.y += mBallSpd1.y;
//                mBallPos2.x += mBallSpd2.x;
//                mBallPos2.y += mBallSpd2.y;

                if (mBallPos.x >= mScrWidth) finish();
                if (mBallPos.y >= mScrHeight) finish();
                if (mBallPos.x <= 0) finish();
                if (mBallPos.y <= 0) finish();
//                if (mBallPos1.x >= mScrWidth) finish();
//                if (mBallPos1.y >= mScrHeight) finish();
//                if (mBallPos1.x <= 0) finish();
//                if (mBallPos1.y <= 0) finish();
//                if (mBallPos2.x >= mScrWidth) finish();
//                if (mBallPos2.y >= mScrHeight) finish();
//                if (mBallPos2.x <= 0) finish();
//                if (mBallPos2.y <= 0) finish();

                mBallView.mX = mBallPos.x;
                mBallView.mY = mBallPos.y;
//                sBallView.mX = mBallPos1.x;
//                sBallView.mY = mBallPos1.y;
//                tBallView.mX = mBallPos2.x;
//                tBallView.mY = mBallPos2.y;

                RedrawHandler.post(new Runnable() {
                    public void run() {
                        mBallView.invalidate();
                    }});
//                RedrawHandler.post(new Runnable() {
//                    public void run() {
//                        sBallView.invalidate();
//                    }});
//                RedrawHandler.post(new Runnable() {
//                    public void run() {
//                        tBallView.invalidate();
//                    }});

            }};

        mTmr.schedule(mTsk,3,3);
        super.onResume();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }





}