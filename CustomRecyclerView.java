import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * A RecyclerView subclass that dampens the fling velocity and
 * snaps the most appropriate child to the center when a scroll
 * ends
 *
 */
public class CustomRecyclerView extends RecyclerView {

    /**
     * the default velocityX is too high when we have limited set of
     * items; so we slow down the velocity using a damping factor;
     * The value of the factor is derived from trial and error
     */
    private static final float FLING_DAMPING_FACTOR = 0.3f;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrollStateChanged(int state){
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            // find the child (from all the visible children) nearest to the center of the RecyclerView
            // and snap to that child (in other words, center the child in the RecyclerView)
            int parentCenter = getWidth() / 2;
            int smallestDistance = Integer.MAX_VALUE;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                int childCenter = child.getLeft() + child.getWidth() / 2;
                int distance = childCenter - parentCenter;

                if (Math.abs(distance) < Math.abs(smallestDistance)) {
                    smallestDistance = distance;
                }
            }

            smoothScrollBy(smallestDistance, 0);
        }
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        return super.fling(Math.round(FLING_DAMPING_FACTOR * velocityX), velocityY);
    }

    /**
     * A subclass of ItemDecoration that draws a rounded frame over each child of the
     * RecyclerView. This also adds spacing between each child, if there are multiple
     * children. The responsibility of centering the only child is taken up by the
     * RecyclerView's LayoutManager
     *
     * @see #shouldApplySpacing(RecyclerView)
     */
    public static class FrameDecoration extends RecyclerView.ItemDecoration {

        private Paint mPaint;
        private RectF mRect;
        private float mRoundedEdgeRadius;

        public FrameDecoration(Context c, @ColorRes int frameColor) {
            mRect = new RectF();
            
            mRoundedEdgeRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, c.getResoures().getDisplayMetrics()); // 3dp;
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(ContextCompat.getColor(c, frameColor));
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, c.getResoures().getDisplayMetrics()); // 1dp
        }

        @Override
        public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int top = parent.getTop();
            int bottom = parent.getBottom();

            for(int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);

                int left = child.getLeft();
                int right = child.getRight();
                mRect.set(left, top, right, bottom);
                canvas.drawRoundRect(mRect, mRoundedEdgeRadius, mRoundedEdgeRadius, mPaint);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View child, RecyclerView parent, RecyclerView.State state) {
            if(!shouldApplySpacing(parent)) {
                return;
            }

            int offset = parent.getContext().getResources().getDimensionPixelOffset(R.dimen.pdp_carousel_item_spacing);
            int position = parent.getChildAdapterPosition(child);
            if(position == 0) {
                outRect.set(0, 0, offset, 0);
            } else if (position == parent.getAdapter().getItemCount() - 1) {
                outRect.set(offset, 0, 0, 0);
            } else {
                outRect.set(offset, 0, offset, 0);
            }
        }

        /**
         * Spacing is only applicable in the case of multiple
         * children. The LayoutManager takes care of centering
         * the single child
         *
         * @param recyclerView
         * @return a boolean
         */
        private boolean shouldApplySpacing(RecyclerView recyclerView){
            int childCount = recyclerView.getAdapter().getItemCount();
            return childCount > 1;
        }
    }

    /**
     * A simple extension of the LinearLayoutManager that offsets the first and
     * the last items in the RecyclerView, so that they are centered when the
     * RecyclerView is scrolled to either of its ends
     */
    public static class OffsetLinearLayoutManager extends LinearLayoutManager {
        private int startEndOffset;

        public OffsetLinearLayoutManager(Context context) {
            super(context);
            startEndOffset = calculateStartEndOffset(context);
        }

        @Override
        public int getPaddingLeft() {
            return super.getPaddingLeft() + startEndOffset;
        }

        @Override
        public int getPaddingRight() {
            return super.getPaddingRight() + startEndOffset;
        }

        private int calculateStartEndOffset(Context context) {
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int childWidth = context.getResources().getDimensionPixelSize(R.dimen.pdp_carousel_item_width);

            return (screenWidth - childWidth)/2;
        }

    }
}
