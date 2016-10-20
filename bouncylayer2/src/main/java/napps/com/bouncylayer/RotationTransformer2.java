package napps.com.bouncylayer;

import android.view.View;

import com.wunderlist.slidinglayer.SlidingLayer;

/**
 * Created by "nithesh" on 10/19/2016.
 */

public class RotationTransformer2 extends LayerTransformer2 {

    private static final int DEFAULT_ANGLE = 10;

    private final float mMaxAngle;
    private float mAngle;

    public RotationTransformer2() {
        this(DEFAULT_ANGLE);
    }

    public RotationTransformer2(float maxAngle) {
        mMaxAngle = maxAngle;
    }

    @Override
    protected void onMeasure(View layerView, int screenSide) {

        final int[] pivotPosition = pivotPositionForScreenSide(layerView, screenSide);
        layerView.setPivotX(pivotPosition[0]);
        layerView.setPivotY(pivotPosition[1]);

        mAngle = mMaxAngle *
                (screenSide == SlidingLayer.STICK_TO_LEFT || screenSide == SlidingLayer.STICK_TO_TOP ? -1 : 1);
    }

    @Override
    public void transform(View layerView, float previewProgress, float layerProgress) {
    }

    @Override
    protected void internalTransform(View layerView, float previewProgress, float layerProgress, int screenSide) {

        final float progressRatioToAnimate = Math.max(previewProgress, layerProgress);
        layerView.setRotation(mAngle * (1 - progressRatioToAnimate));
    }

    private int[] pivotPositionForScreenSide(View layerView, int screenSide) {

        switch (screenSide) {

            case SlidingLayer.STICK_TO_LEFT:
                return new int[] { 0, layerView.getMeasuredHeight() };

            case SlidingLayer.STICK_TO_TOP:
                return new int[] { 0, 0 };

            case SlidingLayer.STICK_TO_RIGHT:
                return new int[] { layerView.getMeasuredWidth(), layerView.getMeasuredHeight() };

            case SlidingLayer.STICK_TO_BOTTOM:
                return new int[] { 0, layerView.getMeasuredHeight() };

            default:
                return new int[] { 0, 0 };
        }
    }

}
