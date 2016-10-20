package napps.com.bouncylayer;

import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.wunderlist.slidinglayer.LayerTransformer;

/**
 * Created by "nithesh" on 10/19/2016.
 */

public class AlphaTransformer2 extends LayerTransformer2 {

    private static final int DEFAULT_MULTIPLIER = 1;

    private final float mMultiplier;

    public AlphaTransformer2() {
        this(DEFAULT_MULTIPLIER);
    }

    public AlphaTransformer2(float multiplier) {
        mMultiplier = multiplier;
    }

    @Override
    public void transform(View layerView, float previewProgress, float layerProgress) {

        final float progressRatioToAnimate = Math.max(previewProgress, layerProgress);
        final float alpha = Math.max(0, Math.min(1, progressRatioToAnimate * mMultiplier));
            layerView.setAlpha(alpha);
    }
}

