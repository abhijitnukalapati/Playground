import android.annotation.TargetApi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by abhijitnukalapati on 6/7/16.
 */
public class IndicatorImageView extends ImageView {

    public Paint mIndicatorPaint;

    public IndicatorImageView(Context context) {
        super(context);
        init();
    }

    public IndicatorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndicatorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndicatorImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int radius = getResources().getDimensionPixelSize(R.dimen.indicator_radius);
        if(isViewReady()) {
            canvas.drawCircle(getWidth() - radius - getPaddingRight(), getPaddingTop() + radius, radius, mIndicatorPaint);
        }
    }

    private void init(){
        mIndicatorPaint = new Paint(ANTI_ALIAS_FLAG);
        mIndicatorPaint.setStyle(Paint.Style.FILL);
        mIndicatorPaint.setColor(Color.GREEN);
    }

    /**
     * Checks if the view is ready for a background or
     * foreground to be drawn. This is true only when
     * the view's size is greater than the padding area
     *
     * @return true if the view has content (minus padding), false otherwise
     */
    private boolean isViewReady() {
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();

        return (getWidth() * getHeight()) > (horizontalPadding * verticalPadding);
    }
}
