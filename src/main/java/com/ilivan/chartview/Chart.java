package com.ilivan.chartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Chart
 *
 * @author Ilya Litosh
 */
class Chart extends View {

    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Chart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private ChartDataSet mChartDataSet;
    public void setChartDataSet(ChartDataSet mChartDataSet) {
        this.mChartDataSet = mChartDataSet;
        this.post(new Runnable() {
            @Override
            public void run() {
                Chart.this.invalidate();
            }
        });
    }

    private ChartDataSet getChartDataSet() {
        return mChartDataSet;
    }

    int getPointsCount() {
        if (mChartDataSet == null) {
            return 0;
        } else {
            return getChartDataSet().getPoints().size();
        }
    }

    private ChartAnimator mChartAnimator = new ChartAnimator(this);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAnimatedChart(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawAnimatedChart(canvas);
    }

    private void drawAnimatedChart(Canvas canvas) {
        if (mChartDataSet != null) {
            initShaders();
            drawChart(canvas);

            this.setPivotY(mGridHeight);
            mChartAnimator.start();
        }
    }

    private Paint mChartFigurePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private LinearGradient mChartFigureShader;
    private void initShaders() {
        mChartFigureShader = new LinearGradient(
                0, 0, 0, getHeight(),
                mChartLineStartColor, mChartLineEndColor, Shader.TileMode.CLAMP);
        mChartFigurePaint.setShader(mChartFigureShader);
    }

    private float mScaleDivisionX;
    void setScaleDivisionX(float mScaleDivisionX) {
        this.mScaleDivisionX = mScaleDivisionX;
    }

    private int mChartLineStartColor;
    void setChartLineStartColor(int mChartLineStartColor) {
        this.mChartLineStartColor = mChartLineStartColor;
    }

    private int mChartLineEndColor;
    public void setChartLineEndColor(int mChartLineEndColor) {
        this.mChartLineEndColor = mChartLineEndColor;
    }

    private Path mChartFigurePath = new Path();
    private void drawChart(Canvas canvas) {
        float prevX = mMarginGridLeft;
        float prevY = getHeight() - mMarginGridBottom;

        float maxValue;
        if (mThreshold == -1) {
            maxValue = mChartDataSet.getMaxValue().getY();
        } else {
            maxValue = mThreshold;
        }

        mChartFigurePath.reset();
        mChartFigurePath.moveTo(prevX, prevY);

        for (int i = 0; i < getPointsCount(); i++) {
            float currentValue = mChartDataSet.getPoints().get(i).getY();
            float coorX = i * mScaleDivisionX + mMarginGridLeft;
            float coorY = mGridHeight * (maxValue - currentValue) / maxValue;

            float delta = computeDelta(coorX, prevX);

            mChartFigurePath.cubicTo(
                    prevX + delta, prevY,coorX - delta, coorY, coorX, coorY);

            prevX = coorX;
            prevY = coorY;
        }

        mChartFigurePath.lineTo(getWidth(), getHeight() - mMarginGridBottom);

        canvas.drawPath(mChartFigurePath, mChartFigurePaint);
    }

    private float mMarginGridLeft;
    void setMarginGridLeft(float mMarginGridLeft) {
        this.mMarginGridLeft = mMarginGridLeft;
    }

    private float mGridHeight;
    void setGridHeight(float mGridHeight) {
        this.mGridHeight = mGridHeight;
    }

    private float mMarginGridBottom;
    void setMarginGridBottom(float mMarginGridBottom) {
        this.mMarginGridBottom = mMarginGridBottom;
    }

    private float mFlexure;
    void setFlexure(float flexure) {
        mFlexure = flexure;
    }

    private int mThreshold;
    void setThreshold(int threshold) {
        mThreshold = threshold;
    }

    private float computeDelta(float coorX, float prevX) {
        return (coorX - prevX) * mFlexure;
    }

}
