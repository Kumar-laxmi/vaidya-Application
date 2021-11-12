package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class MainPage extends Fragment{
    @Override
    public View onCreateView(
            LayoutInflater inflater,ViewGroup container,
            Bundle savedInstanceState
    ){
        //Linking layout to java file
        View fFLayout = inflater.inflate(R.layout.main_pages,container,false);
        return fFLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.bt_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MainPage.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
