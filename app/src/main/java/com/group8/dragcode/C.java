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

public class C extends Fragment {
    private int NumCompleted = 0;

    // DatabaseHelper myDB;
    // myDB = new DatabaseHelper(this);
    // StatsModel mySM;
    // mySM = new StatsModel(this);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // countCompleted ();
        if(NumCompleted>= 0 && NumCompleted <4){
            return inflater.inflate(R.layout.c, container,false);
        }else if(NumCompleted >=4 && NumCompleted < 8){
            return inflater.inflate(R.layout.c_2, container,false);
        }else{
            return inflater.inflate(R.layout.c_3, container,false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("C Part");
    }

    private int countCompleted (){

        //To count how many questions are completed and are C++

        return NumCompleted;
    }
}
