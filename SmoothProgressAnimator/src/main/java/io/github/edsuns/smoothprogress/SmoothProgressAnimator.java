package io.github.edsuns.smoothprogress;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

/**
 * Created by Edsuns@qq.com on 2020/7/31.
 */
public class SmoothProgressAnimator {
    private static final int MIN_PROGRESS = 70;
    private static final long DURATION_PROGRESS = 300L;
    private static final long DURATION_ALPHA = 400L;
    final ProgressBar progressBar;
    private final ObjectAnimator progressBarAnimation;
    private final AccelerateInterpolator accelerateInterpolator;
    private final DecelerateInterpolator decelerateInterpolator;

    public SmoothProgressAnimator(ProgressBar progressBar) {
        this.progressBar = progressBar;
        progressBarAnimation = ObjectAnimator.ofInt(this.progressBar, "progress", 0);
        accelerateInterpolator = new AccelerateInterpolator();
        decelerateInterpolator = new DecelerateInterpolator();
    }

    public void setProgress(int newProgress) {
        boolean shouldHide = newProgress == 100;

        if (!shouldHide) {
            progressBar.setAlpha(1f);
        }

        if (newProgress < progressBar.getProgress()) {
            progressBar.setProgress(0);
        }
        if (newProgress != progressBar.getProgress()) {
            progressBarAnimation.setIntValues(newProgress);
            progressBarAnimation.setDuration(DURATION_PROGRESS);
            progressBarAnimation.setInterpolator(newProgress > MIN_PROGRESS ? accelerateInterpolator : decelerateInterpolator);

            progressBarAnimation.removeAllListeners();
            if (shouldHide)
                progressBarAnimation.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressBar.animate().setInterpolator(decelerateInterpolator)
                                .alpha(0f).setDuration(DURATION_ALPHA).start();
                    }
                });

            progressBarAnimation.start();
        }
    }

    public int getProgress() {
        return progressBar.getProgress();
    }
}
