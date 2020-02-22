package com.ilivan.chartview;

import java.util.ArrayList;
import java.util.List;

/**
 * ChartDataSet
 *
 * @author Ilya Litosh
 */
public class ChartDataSet {

    private List<ChartPoint> mPoints = new ArrayList<>();

    private ChartPoint mMaxValue = new ChartPoint();
    private ChartPoint mMinValue = new ChartPoint();

    public ChartPoint getMaxValue() {
        return mMaxValue;
    }

    public void setMaxValue(float maxValue) {
        if (maxValue > mMaxValue.getY()) {
            mMaxValue = new ChartPoint();
            mMaxValue.setX(-1);
            mMaxValue.setY(maxValue);
        }
    }

    public ChartPoint getMinValue() {
        return mMinValue;
    }

    public List<ChartPoint> getPoints() {
        return mPoints;
    }

    public void setPoints(List<ChartPoint> points) {
        this.mPoints = points;
    }

    public void add(float x, float y) {
        ChartPoint chartPoint = new ChartPoint();
        chartPoint.setX(x);
        chartPoint.setY(y);

        add(chartPoint);
    }

    public void add(ChartPoint chartPoint) {
        if (mPoints.size() == 0) {
            mMinValue = chartPoint;
            mMaxValue = chartPoint;
        }

        if (chartPoint.getY() > mMaxValue.getY()) {
            mMaxValue = chartPoint;
        }
        if (chartPoint.getY() < mMinValue.getY()) {
            mMinValue = chartPoint;
        }

        mPoints.add(chartPoint);
    }

    /**
     * Builder
     *
     * @author Ilya Litosh
     */
    public static class Builder {

        private ChartDataSet mDataset = new ChartDataSet();

        public Builder add(float x, float y) {
            mDataset.add(x, y);

            return this;
        }

        public Builder add(ChartPoint chartPoint) {
            mDataset.add(chartPoint);

            return this;
        }

        public ChartDataSet build() {
            return mDataset;
        }

    }

}
