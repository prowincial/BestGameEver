package swim.cw.game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StartActivity extends Activity {

    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_start);
        mySong = MediaPlayer.create(StartActivity.this, R.raw.second);
        mySong.start();
    }

    public void Game(View view) {
        final Intent intencja = new Intent(this,MainActivity.class);
        mySong.stop();
        startActivity(intencja);
    }
}
