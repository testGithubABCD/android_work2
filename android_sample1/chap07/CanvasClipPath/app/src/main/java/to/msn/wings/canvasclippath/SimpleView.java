package to.msn.wings.canvasclippath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SimpleView extends View {
    private Bitmap bmp;
    private Paint p;
    private Path path;
    private RectF rect;

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
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sea);
        p = new Paint();
        path = new Path();
        rect = new RectF(100, 100, 1000, 900);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        p.setStrokeWidth(5);
        path.addOval(rect, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bmp, 0, 0, p);
    }
}

