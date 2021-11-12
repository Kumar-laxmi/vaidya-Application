package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class PrimaryChecks extends Fragment{
    @Override
    public View onCreateView(
            LayoutInflater inflater,ViewGroup container,
            Bundle savedInstanceState
    ){
        View sFLayout = inflater.inflate(R.layout.primary_checks,container,false);
//        RadioGroup rG1=(RadioGroup) sFLayout.findViewById(R.id.Q)
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
            }
        });

    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
