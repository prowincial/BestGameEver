package swim.cw.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class ThirdBallView extends View {

    public float mX;
    public float mY;
    private final int mR;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    //construct new ball object
    public ThirdBallView(Context context, float x, float y, int r) {
        super(context);
        //color hex is [transparency][red][green][blue]
        int color = ContextCompat.getColor(context, R.color.colorBall3);

        mPaint.setColor(color);//not transparent. color is gree

        this.mX = x;
        this.mY = y;
        this.mR = r; //radius


    }

    //called by invalidate()
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX-400, mY+300, mR, mPaint);




    }
}
