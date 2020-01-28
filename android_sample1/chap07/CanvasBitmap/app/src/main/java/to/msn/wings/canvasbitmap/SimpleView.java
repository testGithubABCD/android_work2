package to.msn.wings.canvasbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
// import android.graphics.Rect;
// import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SimpleView extends View {
    private Bitmap bmp;
    private Paint p;
    // private Rect rect;
    // private RectF rectf;

    public SimpleView(Context context) {
        super(context);
        initialize();
    }

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public SimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void initialize() {
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ham);
        p = new Paint();
        // rect = new Rect(350, 100, 750, 500);
        // rectf = new RectF(50, 50, 850, 850);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bmp, 0, 10, p);
        // canvas.drawBitmap(bmp, rect, rectf, p);
    }
}
