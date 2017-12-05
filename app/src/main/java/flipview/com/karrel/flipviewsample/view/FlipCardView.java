package flipview.com.karrel.flipviewsample.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import flipview.com.karrel.flipviewsample.R;
import flipview.com.karrel.flipviewsample.databinding.ViewFlipBinding;
import flipview.com.karrel.flipviewsample.presenter.FlipCardPresenter;
import flipview.com.karrel.flipviewsample.presenter.FlipCardPresenterImpl;

/**
 * Created by Rell on 2017. 12. 1..
 */
public class FlipCardView extends LinearLayout implements FlipCardPresenter.View {
    private String TAG = FlipCardView.class.getSimpleName();

    private ViewFlipBinding binding;
    private FlipCardPresenter presenter;

    public FlipCardView(Context context) {
        super(context);
        init();
    }

    public FlipCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d(TAG, "init");
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_flip, this, true);

        presenter = new FlipCardPresenterImpl(this);
        binding.flipEventView.setPresenter(presenter);

        binding.imageA.setCameraDistance(40000f);
        binding.imageB.setCameraDistance(40000f);
    }

    @Override
    public void showFrontCard(boolean b) {
        Log.e(TAG, String.format("showFrontCard(%s)", b));
        binding.imageA.setVisibility(b ? View.VISIBLE : View.GONE);
        binding.imageB.setVisibility(!b ? View.VISIBLE : View.GONE);
    }

    @Override
    public void rotateY(boolean b, float rotateY) {
        if (b) binding.imageA.setRotationY(rotateY);
        else binding.imageB.setRotationY(rotateY);
    }

    @Override
    public void rotateAnimation(long duration, boolean isShowFrontCard, float startY, float endY) {
        Log.d(TAG, String.format("rotate startY : %s, endY : %s", startY, endY));
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(isShowFrontCard ? binding.imageA : binding.imageB, "rotationY", 0f);
        animation1.setDuration(duration);
        animation1.start();

    }
}
