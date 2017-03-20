package com.group8.dragcode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Patrick on 3/19/2017.
 */

public class lang_cpp extends Fragment{

    private int NumCompleted =0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        countCompleted ();
        if(NumCompleted>= 0 && NumCompleted <4){
            return inflater.inflate(R.layout.lang_cpp, container,false);
        }else if(NumCompleted >=4 && NumCompleted < 8){
            return inflater.inflate(R.layout.lang_cpp_2, container,false);
        }else{
            return inflater.inflate(R.layout.lang_cpp_3, container,false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("C++ Part");
    }

    private int countCompleted (){
        //To count how many questions are completed and are Java
        return NumCompleted;
    }
}
