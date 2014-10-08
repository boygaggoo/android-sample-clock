package com.sakebook.android.sample.watch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class WatchView extends View {

    // TODO: default setting from attrs
    private float positionX =200f;
    private float positionY =200f;
    private float radius =200f;
    private float stroke;
    private int hour;
    private int minute;
    private int color = Color.WHITE;
    private Drawable backDrawable;


    private Path mPath = new Path();

    public WatchView(Context context) {
        super(context);
        init(null, 0);
    }

    public WatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public WatchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
            attrs, R.styleable.WatchView, defStyle, 0);

        positionX = a.getFloat(R.styleable.WatchView_positionX, positionX);
        positionY = a.getFloat(R.styleable.WatchView_positionY, positionY);
        radius = a.getFloat(R.styleable.WatchView_radius, radius);

        if (a.hasValue(R.styleable.WatchView_backDrawable)) {
            backDrawable = a.getDrawable(R.styleable.WatchView_backDrawable);
            backDrawable.setCallback(this);
        }

        a.recycle();

        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {

    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("Watch", "onDraw");

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        float centerX = contentWidth / 2;
        float centerY = contentHeight / 2;

        drawCircleFrame(canvas);
        drawCircle(canvas);
        drawLongLine(minute, canvas);
        drawShortLine(hour, minute, canvas);
    }

    /**
     * 指定した場所に円を書く
     * */
    private void drawCircleFrame(Canvas canvas) {
        mPath.reset();
        mPath.addCircle(positionX, positionY, radius, Path.Direction.CCW);
        mPath.close();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath, paint);
    }

    private void drawCircle(Canvas canvas) {
        mPath.reset();
        mPath.addCircle(positionX, positionY, radius, Path.Direction.CCW);
        mPath.close();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath, paint);
    }

    private void drawLongLine(int minute, Canvas canvas) {
        double y = (Math.sin(minute * 2 * Math.PI / 60 - (Math.PI / 2)) * (radius-10));
        double x = (Math.cos(minute * 2 * Math.PI / 60 - (Math.PI / 2)) * (radius-10));

        Path line = new Path();
        line.reset();
        line.moveTo(positionX, positionY);
        line.lineTo((positionX + (float) x), (positionY + (float) y));
        line.close();

        Paint slP = new Paint();
        slP.setAntiAlias(true);
        slP.setColor(Color.BLACK);
        slP.setStrokeWidth(stroke);
        slP.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(line, slP);
    }


    private void drawShortLine(int hour, int minute, Canvas canvas) {
        double y = (Math.sin(((hour * 60) + minute) * 2 * Math.PI / 720 - (Math.PI / 2)) * radius * 0.7);
        double x = (Math.cos(((hour * 60) + minute) * 2 * Math.PI / 720 - (Math.PI / 2)) * radius * 0.7);

        Path line = new Path();
        line.reset();
        line.moveTo(positionX, positionY);
        line.lineTo((positionX + (float) x), (positionY + (float) y));
        line.close();

        Paint slP = new Paint();
        slP.setAntiAlias(true);
        slP.setColor(Color.RED);
        slP.setStrokeWidth(stroke);
        slP.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(line, slP);
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
        invalidateTextPaintAndMeasurements();
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
        invalidateTextPaintAndMeasurements();
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidateTextPaintAndMeasurements();
    }

    public void setStroke(float stroke) {
        this.stroke = stroke;
        invalidateTextPaintAndMeasurements();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setBackDrawable(Drawable backDrawable) {
        this.backDrawable = backDrawable;
        invalidateTextPaintAndMeasurements();
    }

    public void setmPath(Path mPath) {
        this.mPath = mPath;
        invalidateTextPaintAndMeasurements();
    }
}

