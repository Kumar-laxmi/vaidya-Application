package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class PrimaryChecks extends Fragment{
    Button next_button;                //Creating Button(NEXT) object
    RadioGroup rg1,rg2,rg3,rg5;    //Creating RadioGroup object
    /*
           Here rg1 -> RadioGroup of 1st Question
           Here rg2 -> RadioGroup of 2nd Question
           Here rg3 -> RadioGroup of 3rd Question
           4th Question has Check Boxes
           Here rg5 -> RadioGroup of 5th Question
     */
    CheckBox amnesia,headache,lightheadedness,drowsines,rapid_heartbeat;  //Creating CheckBox object
    /*
           Here object name is same as checkbox option name
     */
    EditText text1;     //Creating TextView object
    /*
           this object takes the value from Text-Box in the Last Question
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater,ViewGroup container,
            Bundle savedInstanceState
    ){
        //Linking layout to java file
        View sFLayout = inflater.inflate(R.layout.primary_checks,container,false);



        //Defining Radio-Button Groups Objects
        rg1 = (RadioGroup) sFLayout.findViewById(R.id.rg1);
        rg2 = (RadioGroup) sFLayout.findViewById(R.id.rg2);
        rg3 = (RadioGroup) sFLayout.findViewById(R.id.rg3);
        rg5 = (RadioGroup) sFLayout.findViewById(R.id.rg5);

        // Defining Check-boxes Objects
        amnesia = (CheckBox) sFLayout.findViewById(R.id.amnesia);
        headache = (CheckBox) sFLayout.findViewById(R.id.headache);
        lightheadedness = (CheckBox) sFLayout.findViewById(R.id.lightheadedness);
        drowsines = (CheckBox) sFLayout.findViewById(R.id.drowsines);
        rapid_heartbeat = (CheckBox) sFLayout.findViewById(R.id.rapid_heartbeat);

        // Defining TextView Object
        text1 = (EditText) sFLayout.findViewById(R.id.text1);


        // Defining Button Object
        next_button = (Button) sFLayout.findViewById(R.id.next_button);
        return sFLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PrimaryChecks.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                int radio1 = rg1.getCheckedRadioButtonId();     //data to DB
                int radio2 = rg2.getCheckedRadioButtonId();     //DATA TO DB
                int radio3 = rg3.getCheckedRadioButtonId();     //DATA TO DB
                int radio5 = rg5.getCheckedRadioButtonId();     //DATA TO DB

                boolean check1 = amnesia.isChecked();           //DATA TO DB
                boolean check2 = headache.isChecked();          //DATA TO DB
                boolean check3 = lightheadedness.isChecked();   //DATA TO DB
                boolean check4 = drowsines.isChecked();         //DATA TO DB
                boolean check5 = rapid_heartbeat.isChecked();   //DATA TO DB

                String txtBox = text1.getText().toString();     //DATA TO DB
            }
        });

    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
