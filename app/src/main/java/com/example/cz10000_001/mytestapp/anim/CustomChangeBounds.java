package com.example.cz10000_001.mytestapp.anim;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

/**
 * Created by cz10000_001 on 2018/2/23.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class CustomChangeBounds  extends ChangeBounds{

    @Override
    public Animator createAnimator(final ViewGroup sceneRoot,
                                   TransitionValues startValues,
                                   final TransitionValues endValues) {

        Animator changeBounds = super.createAnimator(sceneRoot, startValues, endValues);
        if (startValues == null || endValues == null || changeBounds == null)
            return null;

//        if (endValues.view instanceof ViewGroup) {
//            ViewGroup vg = (ViewGroup) endValues.view;
//            float offset = vg.getHeight() / 3;
//            for (int i = 0; i < vg.getChildCount(); i++) {
//                View v = vg.getChildAt(i);
//                v.setTranslationY(offset);
//                v.setAlpha(0f);
//                v.animate()
//                        .alpha(1f)
//                        .translationY(0f)
//                        .setDuration(150)
//                        .setStartDelay(150)
//                        .setInterpolator(AnimationUtils.loadInterpolator(vg.getContext(),
//                                android.R.interpolator.fast_out_slow_in));
//                offset *= 1.8f;
//            }
//        }

        changeBounds.setDuration(300);
        changeBounds.setInterpolator(AnimationUtils.loadInterpolator(sceneRoot.getContext(),
                android.R.interpolator.fast_out_slow_in));
        return changeBounds;
    }

}
