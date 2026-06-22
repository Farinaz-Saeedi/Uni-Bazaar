package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import me.farinaz.saeedi.ranjbar83.myunibazaar.R;

public class CircularProgressBar extends View {

    private float maxProgress = 100;
    private float currentProgress = 0;
    private float animatedProgress = 0;
    private float strokeWidth = 2;
    private int progressColor = Color.BLUE;
    private int backgroundColor = Color.GRAY;
    private Paint backgroundPaint;
    private Paint progressPaint;
    private RectF ovalBounds;
    private ValueAnimator progressAnimator;

    public CircularProgressBar(Context context) {
        super(context);
        init(null);
    }

    public CircularProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircularProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        progressPaint = new Paint();
        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(strokeWidth);

        ovalBounds = new RectF();

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircularProgressBar);
            strokeWidth = a.getDimension(R.styleable.CircularProgressBar_cp_strokeWidth, 5);
            progressColor = a.getColor(R.styleable.CircularProgressBar_cp_progressColor, Color.BLUE);
            backgroundColor = a.getColor(R.styleable.CircularProgressBar_cp_backgroundColor, Color.GRAY);
            currentProgress = a.getFloat(R.styleable.CircularProgressBar_cp_progress, 0);
            maxProgress = a.getFloat(R.styleable.CircularProgressBar_cp_max, 100);

            backgroundPaint.setStrokeWidth(strokeWidth);
            progressPaint.setStrokeWidth(strokeWidth);
            progressPaint.setColor(progressColor);
            backgroundPaint.setColor(backgroundColor);

            a.recycle();
        }
        this.animatedProgress = this.currentProgress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        if (width < strokeWidth * 2 || height < strokeWidth * 2) {
            return;
        }

        int centerX = width / 2;
        int centerY = height / 2;
        float radius = Math.min(width, height) / 2 - strokeWidth / 2;

        ovalBounds.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);

        float progressSweepAngle = (animatedProgress / maxProgress) * 360;

        canvas.drawArc(ovalBounds, -90, progressSweepAngle, false, progressPaint);
    }

    public void setProgressWithAnimation(float progress, long duration) {

        if (progress > maxProgress) {
            progress = maxProgress;
        }

        if (progress < 0) {
            progress = 0;
        }

        this.currentProgress = progress;

        if (progressAnimator != null && progressAnimator.isRunning()) {
            progressAnimator.cancel();
        }

        progressAnimator = ValueAnimator.ofFloat(animatedProgress, currentProgress);
        progressAnimator.setDuration(duration);
        progressAnimator.addUpdateListener(animation -> {
            animatedProgress = (float) animation.getAnimatedValue();
            invalidate();
        });
        progressAnimator.start();
    }

    public void setProgress(float progress) {
        this.currentProgress = progress;
        this.animatedProgress = progress;
        invalidate();
    }

    public float getProgress() {
        return currentProgress;
    }

    public float getAnimatedProgress() {
        return animatedProgress;
    }

    public void setMaxProgress(float max) {
        this.maxProgress = max;

        setProgress(currentProgress);
        invalidate();
    }

    public float getMaxProgress() {
        return maxProgress;
    }

    public void setStrokeWidth(float width) {
        this.strokeWidth = width;
        backgroundPaint.setStrokeWidth(strokeWidth);
        progressPaint.setStrokeWidth(strokeWidth);
        // requestLayout();
        invalidate();
    }

    public void setProgressColor(int color) {
        this.progressColor = color;
        progressPaint.setColor(progressColor);
        invalidate();
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        backgroundPaint.setColor(backgroundColor);
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (progressAnimator != null) {
            progressAnimator.cancel();
        }
    }
}
