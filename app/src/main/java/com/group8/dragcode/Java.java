package com.group8.dragcode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

/**
 * Created by Patrick on 3/18/2017.
 */

public class Java extends Fragment {

    private int score = 5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(score>= 0 && score <4){
            return inflater.inflate(R.layout.java, container,false);
        }else if(score >=4 && score < 8){
            return inflater.inflate(R.layout.java_2, container,false);
        }else{
            return inflater.inflate(R.layout.java_3, container,false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Java Part");
    }
}
