package com.example.project.ui.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        EditText name, passID, start, dest, price;
        Spinner spin;
        ImageView fingerprint;
        Button btnsubmit;
        ArrayList<String> spinnerList=new ArrayList<>();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        name=binding.editName.findViewById(R.id.name);
        passID=binding.editPass.findViewById(R.id.passId);
        spin=binding.spinner.findViewById(R.id.spinner);
        start=binding.editStart.findViewById(R.id.edit_start);
        dest=binding.editDest.findViewById(R.id.edit_dest);
        price=binding.editPrice.findViewById(R.id.edit_price);
        btnsubmit=binding.btn.findViewById(R.id.btn);
        spinnerList.add("Male");
        spinnerList.add("Female");
        spinnerList.add("Others");
        spinnerList.add("Prefer not to say");
        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinnerAdapter);

        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
//    public boolean regexName(String name){
//        int i=0;
//        while(i<name.length()){
//            char ch=name.charAt(i);
//            if()
//        }
//    }
}