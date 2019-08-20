package com.ilivan.chartview;

import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

/**
 * ChartAnimator
 *
 * @author Ilya Litosh
 */
class ChartAnimator {

    private View mView;

    ChartAnimator(View view) {
        mView = view;
        initializeSpringAnimation();
    }

    private Spring mSpring;
    private void initializeSpringAnimation() {
        mSpring = SpringSystem.create().createSpring();
        SpringConfig springConfig = new SpringConfig(60, 7);
        mSpring.setSpringConfig(springConfig);
        mSpring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                mView.setScaleY(value);
            }
        });
    }

    void start() {
        mSpring.setCurrentValue(0);
        mSpring.setEndValue(1);
    }

}
