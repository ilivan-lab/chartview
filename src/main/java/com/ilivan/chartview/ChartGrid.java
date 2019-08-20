package com.ilivan.chartview;

import java.util.ArrayList;
import java.util.List;

/**
 * ChartGrid
 *
 * @author Ilya Litosh
 */
public class ChartGrid {

    private List<ChartPoint> mDots = new ArrayList<>();
    public List<ChartPoint> getDots() {
        return mDots;
    }

    private float mWidth;
    private float mHeight;
    public float getWidth() {
        return mWidth;
    }

    public void setWidth(float mWidth) {
        this.mWidth = mWidth;
    }

    public float getHeight() {
        return mHeight;
    }

    public void setHeight(float mHeight) {
        this.mHeight = mHeight;
    }

    private float mMarginLeft;
    private float mMarginBottom;
    public float getMarginLeft() {
        return mMarginLeft;
    }

    public void setMarginLeft(float mMarginLeft) {
        this.mMarginLeft = mMarginLeft;
    }

    public float getMarginBottom() {
        return mMarginBottom;
    }

    public void setMarginBottom(float mMarginBottom) {
        this.mMarginBottom = mMarginBottom;
    }

    private float mScaleDivisionX;
    private float mScaleDivisionY;
    public float getScaleDivisionX() {
        return mScaleDivisionX;
    }

    public void setScaleDivisionX(float scaleDivisionX) {
        this.mScaleDivisionX = scaleDivisionX;
    }

    public float getScaleDivisionY() {
        return mScaleDivisionY;
    }

    public void setScaleDivisionY(float scaleDivisionY) {
        this.mScaleDivisionY = scaleDivisionY;
    }

    /**
     * ChartGrid.Builder
     *
     * @author Ilya Litosh
     */
    public static class Builder {

        private ChartGrid mChartGrid = new ChartGrid();

        public Builder dot(float x, float y) {
            ChartPoint chartPoint = new ChartPoint();
            chartPoint.setX(x);
            chartPoint.setY(y);

            return dot(chartPoint);
        }

        public Builder dot(ChartPoint chartPoint) {
            mChartGrid.getDots().add(chartPoint);

            return this;
        }

        public Builder width(float width) {
            mChartGrid.setWidth(width);

            return this;
        }

        public Builder height(float height) {
            mChartGrid.setHeight(height);

            return this;
        }

        public Builder marginLeft(float marginLeft) {
            mChartGrid.setMarginLeft(marginLeft);

            return this;
        }

        public Builder marginBottom(float marginBottom) {
            mChartGrid.setMarginBottom(marginBottom);

            return this;
        }

        public Builder scaleDivisionX(float scaleDivisionX) {
            mChartGrid.setScaleDivisionX(scaleDivisionX);

            return this;
        }

        public Builder scaleDivisionY(float scaleDivisionY) {
            mChartGrid.setScaleDivisionY(scaleDivisionY);

            return this;
        }

        public ChartGrid build() {
            return mChartGrid;
        }

    }

}
