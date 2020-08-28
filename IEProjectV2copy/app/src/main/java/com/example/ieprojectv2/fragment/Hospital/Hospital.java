package com.example.ieprojectv2.fragment.Hospital;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ieprojectv2.R;

public class Hospital extends Fragment {

    private HospitalViewModel mViewModel;
    private Button near_btn;
    private Button search_btn;

    public static Hospital newInstance() {
        return new Hospital();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hospital_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //to the page which show three nearest hospitals
        near_btn = getActivity().findViewById(R.id.nearest_hospital_btn);
        near_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_hospital_to_map_fragment);

            }
        });

        search_btn = getActivity().findViewById(R.id.search_hospital_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_hospital_to_mapsFragment2);

            }
        });

        mViewModel = new ViewModelProvider(requireActivity()).get(HospitalViewModel.class);
        // TODO: Use the ViewModel
    }

}