package com.example.anafor.Nav_Vaccine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anafor.R;

public class VaccineDetailFragment1 extends Fragment {

    ImageView a1, a2;

    String vaccine;

    public VaccineDetailFragment1(VaccineDTO vaccineDTO, String vaccine) {
        this.vaccine = vaccine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vaccine_detail1, container, false);

        a1 = v.findViewById(R.id.a1);
        a2 = v.findViewById(R.id.a2);

        return v;
    }
}