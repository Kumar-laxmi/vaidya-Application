package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PrimaryChecks extends Fragment{
    Button next_button;                //Creating Button(NEXT) object
    RadioGroup rg1,rg2,rg3,rg5;    //Creating RadioGroup object
    RadioButton rb1,rb2,rb3,rb5;  //Creating RadioButton Object
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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Extra_Details");
        DatabaseReference myRef1 = database.getReference("Question1");
        DatabaseReference myRef2 = database.getReference("Question2");
        DatabaseReference myRef3 = database.getReference("Question3");
        DatabaseReference myRef4 = database.getReference("Question4");
        DatabaseReference myRef5 = database.getReference("Question5");
        DatabaseReference myRef6 = database.getReference("Responsiveness");

        view.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PrimaryChecks.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
                int radio1 = rg1.getCheckedRadioButtonId();
                rb1 = (RadioButton) view.findViewById(radio1);
                int radio2 = rg2.getCheckedRadioButtonId();
                rb2 = (RadioButton) view.findViewById(radio2);
                int radio3 = rg3.getCheckedRadioButtonId();
                rb3 = (RadioButton) view.findViewById(radio3);
                int radio5 = rg5.getCheckedRadioButtonId();
                rb5 = (RadioButton) view.findViewById(radio5);
                boolean check1 = amnesia.isChecked();
                boolean check2 = headache.isChecked();
                boolean check3 = lightheadedness.isChecked();
                boolean check4 = drowsines.isChecked();
                boolean check5 = rapid_heartbeat.isChecked();

                final EditText[] txtBox = {(EditText) view.findViewById(R.id.text1)};
                myRef.setValue(txtBox[0].getText().toString());
                txtBox[0].setText("");

                myRef1.setValue(rb1.getText().toString());
                rb1.setText("");
                myRef2.setValue(rb2.getText().toString());
                rb2.setText("");
                myRef3.setValue(rb3.getText().toString());
                rb3.setText("");
                if(rb1.getText().toString()=="Yes" && rb2.getText().toString()=="Yes" && rb3.getText().toString()=="Yes")
                    myRef6.setValue("Responsive");
                else
                    myRef6.setValue("Unresponsive");

                myRef5.setValue(rb5.getText().toString());
                rb5.setText("");

                int i=0;
                if (check1) {
                    i++;
                    myRef4.child("Condition-1").setValue("Amnesia");
                }
                if (check2){
                    i++;
                    if(i==1)
                        myRef4.child("Condition-1").setValue("Headache");
                    if (i==2)
                        myRef4.child("Condition-2").setValue("Headache");
                }
                if (check3){
                    i++;
                    if(i==1)
                        myRef4.child("Condition-1").setValue("Lightheadedness");
                    if (i==2)
                        myRef4.child("Condition-2").setValue("Lightheadedness");
                    if (i==3)
                        myRef4.child("Condition-3").setValue("Lightheadedness");
                }
                if (check4){
                    i++;
                    if(i==1)
                        myRef4.child("Condition-1").setValue("Drowsiness");
                    if (i==2)
                        myRef4.child("Condition-2").setValue("Drowsiness");
                    if (i==3)
                        myRef4.child("Condition-3").setValue("Drowsiness");
                    if (i==4)
                        myRef4.child("Condition-4").setValue("Drowsiness");
                }
                if (check5){
                    i++;
                    if(i==1)
                        myRef4.child("Condition-1").setValue("Rapid Heartbeat");
                    if (i==2)
                        myRef4.child("Condition-2").setValue("Rapid Heartbeat");
                    if (i==3)
                        myRef4.child("Condition-3").setValue("Rapid Heartbeat");
                    if (i==4)
                        myRef4.child("Condition-4").setValue("Rapid Heartbeat");
                    if (i==5)
                        myRef4.child("Condition-5").setValue("Rapid Heartbeat");
                }
            }
        });

    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
