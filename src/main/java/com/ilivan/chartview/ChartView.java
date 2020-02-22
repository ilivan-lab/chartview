package com.ilivan.chartview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.FontRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import java.text.DecimalFormat;

/**
 * ChartView
 *
 * @author Ilya Litosh
 */
public class ChartView extends FrameLayout {

    private ChartDataSet mChartDataSet = new ChartDataSet();

    public ChartView(Context context) {
        super(context);
        initialize(context);
        addChartView();
        previewChart();
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setAttributes(context, attrs);
        initialize(context);
        addChartView();
        previewChart();
    }

    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context, attrs);
        initialize(context);
        addChartView();
        previewChart();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAttributes(context, attrs);
        initialize(context);
        addChartView();
        previewChart();
    }

    private Chart mChart;
    private void addChartView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mChart = new Chart(getContext());

        addView(mChart, params);
    }

    @Dimension
    private float mLabelsTextSize;
    public float getLabelsTextSize() {
        return mLabelsTextSize;
    }

    public void setLabelsTextSize(float mLabelsTextSize) {
        this.mLabelsTextSize = mLabelsTextSize;
    }

    @Dimension
    private float mGridLineWidth;
    public float getGridLineWidth() {
        return mGridLineWidth;
    }

    public void setGridLineWidth(float mGridLineWidth) {
        this.mGridLineWidth = mGridLineWidth;
    }

    @ColorInt
    private int mGridLineColor;
    public int getGridLineColor() {
        return mGridLineColor;
    }

    public void setGridLineColor(int mGridLineColor) {
        this.mGridLineColor = mGridLineColor;
    }

    @FontRes
    private int mLabelsTextFont;
    public int getLabelsTextFont() {
        return mLabelsTextFont;
    }

    public void setLabelsTextFont(int mLabelsTextFont) {
        this.mLabelsTextFont = mLabelsTextFont;
    }

    @ColorInt
    private int mLabelsTextColor;
    public int getLabelsTextColor() {
        return mLabelsTextColor;
    }

    public void setLabelsTextColor(int mLabelsTextColor) {
        this.mLabelsTextColor = mLabelsTextColor;
    }

    @Dimension
    private float mGridSize;
    public float getGridSize() {
        return mGridSize;
    }

    public void setGridSize(float mGridSize) {
        this.mGridSize = mGridSize;
    }

    @ColorInt
    private int mGridStartColor;
    public int getGridStartColor() {
        return mGridStartColor;
    }

    public void setGridStartColor(int mGridStartColor) {
        this.mGridStartColor = mGridStartColor;
    }

    @ColorInt
    private int mGridEndColor;
    public int getGridEndColor() {
        return mGridEndColor;
    }

    public void setGridEndColor(int mGridEndColor) {
        this.mGridEndColor = mGridEndColor;
    }

    @ColorInt
    private int mChartLineStartColor;
    public int getChartLineStartColor() {
        return mChartLineStartColor;
    }

    public void setChartLineStartColor(int mChartLineStartColor) {
        this.mChartLineStartColor = mChartLineStartColor;
    }

    @ColorInt
    private int mChartLineEndColor;
    public int getChartLineEndColor() {
        return mChartLineEndColor;
    }

    public void setChartLineEndColor(int mChartLineEndColor) {
        this.mChartLineEndColor = mChartLineEndColor;
    }

    private int mThreshold;
    public void setThreshold(int threshold) {
        this.mThreshold = threshold;
    }

    public int getThreshold() {
        return mThreshold;
    }

    private ChartDataSet mPreviewChartDataSet = new ChartDataSet.Builder()
            .add(0, 7.0f)
            .add(1, 12.4f)
            .add(2, 9.3f)
            .add(3, 15.7f)
            .add(4, 18.1f)
            .add(5, 9.3f)
            .add(6, 5.4f)
            .add(7, 19.2f)
            .add(8, 10.7f)
            .add(9, 10.1f)
            .add(10, 6.3f)
            .build();
    private void previewChart() {
        if (isInEditMode()) {
            setChartDataSet(mPreviewChartDataSet);
        }
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        mLabelsTextSize = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getDimension(R.styleable.ChartView_labelsTextSize, 14f);
        mGridLineWidth = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getDimension(R.styleable.ChartView_gridLineWidth, 2f);
        mLabelsTextFont = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getResourceId(R.styleable.ChartView_labelsTextFont, R.font.montserrat_medium);
        mFlexure = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getFloat(R.styleable.ChartView_flexure, 0.5f);
        mThreshold = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getInteger(R.styleable.ChartView_threshold, -1);
        mLabelsTextColor = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getColor(R.styleable.ChartView_labelsTextColor, Color.BLACK);
        mGridLineColor = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getColor(R.styleable.ChartView_gridLineColor, Color.argb(13, 0, 0, 0));
        mGridSize = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getDimension(R.styleable.ChartView_gridSize, 2.0f);
        mGridStartColor = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getColor(R.styleable.ChartView_gridStartColor, Color.argb(255, 48, 207, 208));
        mGridEndColor = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getColor(R.styleable.ChartView_gridEndColor, Color.argb(255, 51, 8, 103));
        mChartLineStartColor = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getColor(R.styleable.ChartView_chartLineStartColor, Color.argb(165, 48, 198, 212));
        mChartLineEndColor = context.obtainStyledAttributes(attrs, R.styleable.ChartView)
                .getColor(R.styleable.ChartView_chartLineEndColor, Color.argb(165, 85, 102, 176));
    }

    private void setChartAttributes() {
        mChart.setChartLineStartColor(mChartLineStartColor);
        mChart.setChartLineEndColor(mChartLineEndColor);
        mChart.setGridHeight(mChartGrid.getHeight());
        mChart.setMarginGridBottom(mChartGrid.getMarginBottom());
        mChart.setMarginGridLeft(mChartGrid.getMarginLeft());
        mChart.setScaleDivisionX(mChartGrid.getScaleDivisionX());
        mChart.setFlexure(mFlexure);
        mChart.setThreshold(mThreshold);
    }

    private Paint mTextPaint = new Paint();
    private Paint mGridLinePaint = new Paint();
    private Paint mGridDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect mLabelTextRect = new Rect();
    private void initialize(Context context) {
        mTextPaint.setColor(mLabelsTextColor);
        mTextPaint.setTextSize(mLabelsTextSize);
        if (isInEditMode() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mTextPaint.setTypeface(context.getResources().getFont(mLabelsTextFont));
        } else {
            mTextPaint.setTypeface(ResourcesCompat.getFont(context, mLabelsTextFont));
        }
        mTextPaint.setAntiAlias(true);
        String labelText = String.valueOf(mPreviewChartDataSet.getMaxValue().getY());
        mTextPaint.getTextBounds(labelText, 0, labelText.length(), mLabelTextRect);

        mGridLinePaint.setStrokeWidth(mGridLineWidth);
        mGridLinePaint.setColor(mGridLineColor);
    }

    public ChartDataSet getChartDataSet() {
        return mChartDataSet;
    }

    /**
     * Returns chart points count
     *
     */
    public int getPointsCount() {
        if (mChartDataSet == null) {
            return 0;
        } else {
            return getChartDataSet().getPoints().size();
        }
    }

    public void setChartDataSet(ChartDataSet mChartDataSet) {
        this.mChartDataSet = mChartDataSet;
        this.post(new Runnable() {
            @Override
            public void run() {
                ChartView.this.invalidate();
            }
        });
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        initChartGrid();
        initShaders();
        drawLabels(canvas);
        drawGrid(canvas);

        setChartAttributes();
        mChart.setChartDataSet(mChartDataSet);
    }

    private void drawLabels(Canvas canvas) {
        drawAxisX(canvas);
        drawAxisY(canvas);
    }

    /**
     * Returns margin of left side
     * for Chart inside grid of chart
     *
     */
    public float marginGridLeft() {
        return mChartGrid.getMarginLeft();
    }

    /**
     * Returns margin of bottom side
     * for Chart inside grid of chart
     *
     */
    public float marginGridBottom() {
        return mChartGrid.getMarginBottom();
    }

    private void drawAxisX(Canvas canvas) {
        String lastValue = "0";
        if (mChartDataSet.getPoints().size() > 0) {
            lastValue = String.valueOf(((int) mChartDataSet.getPoints().get(getPointsCount() - 1).getX()));
        }

        Rect rectLastValue = new Rect();
        mTextPaint.getTextBounds(lastValue, 0, lastValue.length(), rectLastValue);

        float pointsCount = getPointsCount();
        float labelsWidth = getPointsCount() * (rectLastValue.width() + 3);
        while (labelsWidth >= getWidth() - marginGridLeft()) {
            pointsCount = pointsCount / 2.0f;
            labelsWidth = pointsCount * (rectLastValue.width() + 3);
        }

        int step = (int)(getPointsCount() / pointsCount);

        float scaleDivision = mChartGrid.getScaleDivisionX();
        for (int i = 0; i < getPointsCount() - 1; i += step) {
            String labelText = String.valueOf(((int) mChartDataSet.getPoints().get(i).getX()));
            canvas.drawText(labelText, i * scaleDivision + marginGridLeft(), getHeight(), mTextPaint);
        }
    }

    private void drawAxisY(Canvas canvas) {
        float chartGridHeight = mChartGrid.getHeight();
        float scaleDivision = mChartGrid.getScaleDivisionY();
        float maxValue;
        if (mThreshold == -1) {
            maxValue = mChartDataSet.getMaxValue().getY();
        } else {
            maxValue = mThreshold;
        }

        for (float i = chartGridHeight; i > 0; i -= scaleDivision) {
            float axisYLabelValue = Math.abs(i * maxValue / chartGridHeight - maxValue);
            DecimalFormat df = new DecimalFormat("#.#");
            String formattedValue = df.format(axisYLabelValue);
            canvas.drawText(formattedValue.replace(".", ","),0, i, mTextPaint);
        }
    }

    private ChartGrid mChartGrid;
    private void initChartGrid() {
        float marginGridLeft = mLabelTextRect.width() + mLabelTextRect.width() / 3;
        float marginGridBottom = mLabelTextRect.height() + mLabelTextRect.height() / 2;
        mChartGrid = new ChartGrid.Builder()
                .marginLeft(marginGridLeft)
                .marginBottom(marginGridBottom)
                .scaleDivisionX((getWidth() - marginGridLeft) / (getPointsCount() - 1))
                .scaleDivisionY(mLabelTextRect.height() * 3.0f)
                .width(getWidth() - marginGridLeft)
                .height(getHeight() - marginGridBottom)
                .build();
    }

    private LinearGradient mGridDotShader;
    private void initShaders() {
        mGridDotShader = new LinearGradient(
                marginGridLeft(), 0, marginGridLeft(), getHeight() - marginGridBottom(),
                mGridStartColor, mGridEndColor, Shader.TileMode.CLAMP);
        mGridDotPaint.setShader(mGridDotShader);
    }

    private void drawGrid(Canvas canvas) {
        for (float i = mChartGrid.getHeight(); i > 0; i -= mChartGrid.getScaleDivisionY()) {
            canvas.drawLine(marginGridLeft(), i, getWidth(), i, mGridLinePaint);
            for (float j = 0; j < getPointsCount(); j++) {
                float coorX = j * mChartGrid.getScaleDivisionX() + marginGridLeft();
                float coorY = i;
                canvas.drawCircle(coorX, coorY, mGridSize, mGridDotPaint);
            }
        }
    }

    private float mFlexure;
    public float getFlexure() {
        return mFlexure;
    }

    public void setFlexure(float flexure) {
        mFlexure = flexure;
    }

}
