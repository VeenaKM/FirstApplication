package com.sampleproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.sampleproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleActivityA extends Activity {

    @BindView(R.id.simple_activity_a_imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_a);
        ButterKnife.bind(this);
    }

    public void gotoNextActivity(View view) {
        Intent intent = new Intent(SimpleActivityA.this, SimpleActivityB.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(SimpleActivityA.this,
                        imageView,
                        ViewCompat.getTransitionName(imageView));
        startActivity(intent, options.toBundle());
    }
}