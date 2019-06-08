package swim.cw.game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Stop extends Activity {
    MediaPlayer mysong4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_stop);
        mysong4=MediaPlayer.create(Stop.this,R.raw.fourth);
        mysong4.start();
    }


    public void Game(View view) {
        final Intent intencja = new Intent(this,MainActivity.class);
        mysong4.stop();
        startActivity(intencja);
    }
}
