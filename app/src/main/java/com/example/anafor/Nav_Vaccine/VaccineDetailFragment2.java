package com.example.anafor.Nav_Vaccine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anafor.R;


public class VaccineDetailFragment2 extends Fragment {

    ImageView b1, b2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vaccine_detail2, container, false);

        b1 = v.findViewById(R.id.b1);
        b2 = v.findViewById(R.id.b2);

        return v;
    }
}