package bouncylayer.napps.com.bouncylayer;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wunderlist.slidinglayer.LayerTransformer;
import com.wunderlist.slidinglayer.SlidingLayer;
import com.wunderlist.slidinglayer.transformer.AlphaTransformer;
import com.wunderlist.slidinglayer.transformer.RotationTransformer;
import com.wunderlist.slidinglayer.transformer.SlideJoyTransformer;

import napps.com.bouncylayer.AlphaTransformer2;
import napps.com.bouncylayer.BouncyLayer;
import napps.com.bouncylayer.LayerTransformer2;
import napps.com.bouncylayer.RotationTransformer2;
import napps.com.bouncylayer.SlideJoyTransformer2;

public class BouncyActivity extends AppCompatActivity {


    private BouncyLayer mBouncyLayer;
    private TextView swipeText;
    private int offsetdistance = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouncy);

        bindViews();
        initState();
    }

    /**
     * View binding
     */
    private void bindViews() {
        mBouncyLayer = (BouncyLayer) findViewById(R.id.bouncyLayer1);
        swipeText = (TextView) findViewById(R.id.swipeText);
    }

    /**
     * Initializes the origin state of the layer
     */
    private void initState() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        setupSlidingLayerPosition(prefs.getString("layer_location", "right"));
        setupSlidingLayerTransform(prefs.getString("layer_transform", "none"));

        setupShadow(prefs.getBoolean("layer_has_shadow", false));
        setupLayerOffset(prefs.getBoolean("layer_has_offset", false));
        setupPreviewMode(prefs.getBoolean("preview_mode_enabled", false));

        mBouncyLayer.setSpringAnimation(40, 5, false, false, BouncyLayer.BOUNCE_X_AXIS);
    }

    private void setupSlidingLayerPosition(String layerPosition) {

        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mBouncyLayer.getLayoutParams();
        int textResource;
        Drawable d;

        switch (layerPosition) {
            case "right":
                mBouncyLayer.setStickTo(SlidingLayer.STICK_TO_RIGHT);
                break;
            case "left":
                mBouncyLayer.setStickTo(SlidingLayer.STICK_TO_LEFT);
                break;
            case "top":
                mBouncyLayer.setStickTo(SlidingLayer.STICK_TO_TOP);
                rlp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                break;
            default:

                mBouncyLayer.setStickTo(SlidingLayer.STICK_TO_BOTTOM);
                rlp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        }

        mBouncyLayer.setLayoutParams(rlp);
    }

    private void setupSlidingLayerTransform(String layerTransform) {

        LayerTransformer2 transformer;

        switch (layerTransform) {
            case "alpha":
                transformer = new AlphaTransformer2();
                break;
            case "rotation":
                transformer = new RotationTransformer2();
                break;
            case "slide":
                transformer = new SlideJoyTransformer2();
                break;
            default:
                return;
        }
        mBouncyLayer.setLayerTransformer(transformer);
    }

    private void setupShadow(boolean enabled) {
//        if (enabled) {
//            mBouncyLayer.setShadowSizeRes(R.dimen.shadow_size);
//            mBouncyLayer.setShadowDrawable(R.drawable.sidebar_shadow);
//        } else {
//            mBouncyLayer.setShadowSize(0);
//            mBouncyLayer.setShadowDrawable(null);
//        }
    }

    private void setupLayerOffset(boolean enabled) {
        mBouncyLayer.setOffsetDistance(offsetdistance);
    }

    private void setupPreviewMode(boolean enabled) {
    }

    public void buttonClicked(View v) {
        switch (v.getId()) {
            case R.id.buttonOpen:
                mBouncyLayer.openLayer(true);
                break;
            case R.id.buttonClose:
                mBouncyLayer.closeLayer(true);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mBouncyLayer.isOpened()) {
                    mBouncyLayer.closeLayer(true);
                    return true;
                }

            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
