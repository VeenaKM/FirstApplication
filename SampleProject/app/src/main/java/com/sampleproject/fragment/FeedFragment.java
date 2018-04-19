package com.sampleproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sampleproject.R;
import com.sampleproject.activity.SimpleActivityA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by apple on 17/04/18.
 */

public class FeedFragment extends Fragment {

    View feedView;
    @BindView(R.id.sharedTransition)
    Button btnSharedTransition;
    Unbinder unbinder;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        // Inflate the layout for this fragment
        feedView = inflater.inflate(R.layout.fragment_feed, container, false);
        unbinder = ButterKnife.bind(this, feedView);

        initListener();

        return feedView;
    }

    private void initListener() {
        btnSharedTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNewActivity(v);
            }
        });
    }


    public void gotoNewActivity(View view) {
        Intent intent = new Intent(getActivity(), SimpleActivityA.class);

        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
