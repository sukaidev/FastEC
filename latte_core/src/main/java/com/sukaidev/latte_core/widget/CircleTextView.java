package com.sukaidev.latte_core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by sukaidev on 2019/05/14.
 */
public class CircleTextView extends AppCompatTextView {

    private final Paint PAINT;
    private final PaintFlagsDrawFilter FILTER;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        PAINT = new Paint();
        FILTER = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        PAINT.setColor(Color.WHITE);
        PAINT.setAntiAlias(true);
    }

    public final void setCircleBackground(@ColorInt int color) {
        PAINT.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        final int max = Math.max(width, height);
        setMeasuredDimension(max, max);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(FILTER);
        canvas.drawCircle((float) getWidth() / 2, (float) getHeight() / 2, Math.max(getWidth(), getHeight()), PAINT);
        super.onDraw(canvas);
    }
}
