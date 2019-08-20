package com.ilivan.chartview;

/**
 * ChartPoint
 *
 * @author Ilya Litosh
 */
public class ChartPoint {

    private float mX;
    private float mY;

    public float getX() {
        return mX;
    }

    public void setX(float x) {
        this.mX = x;
    }

    public float getY() {
        return mY;
    }

    public void setY(float y) {
        this.mY = y;
    }

    @Override
    public String toString() {
        return "x: " + mX + ", y:" + mY;
    }

}
