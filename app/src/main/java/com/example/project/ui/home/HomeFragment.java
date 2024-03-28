package com.example.project.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.databinding.FragmentHomeBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public EditText start, dest;
    String startAddress, destAddress, plc;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Response response=null;
        EditText name, passID, price, editName, editPass;
        Spinner spin, spin2, dest;
        ImageView fingerprint;
        Button btnsubmit;

        Drawable img=getContext().getDrawable(R.mipmap.warning);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        name = binding.editName.findViewById(R.id.name);
        editName=binding.editName.findViewById(R.id.edit_name);
        passID = binding.editPass.findViewById(R.id.passId);
        editPass=binding.editPass.findViewById(R.id.edit_pass);
        spin = binding.spinner.findViewById(R.id.spinner);
        start = binding.editStart.findViewById(R.id.edit_start);
        dest = binding.destAdd.findViewById(R.id.destAdd);
        price = binding.editPrice.findViewById(R.id.edit_price);
        btnsubmit = binding.btn.findViewById(R.id.btn);
        spin2=binding.spinner2.findViewById(R.id.spinner2);
        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("Male");
        spinnerList.add("Female");
        spinnerList.add("Others");
        spinnerList.add("Prefer not to say");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin.setAdapter(spinnerAdapter);
        start.setFocusable(false);
        //Select Destination
        spinnerList = new ArrayList<>();
        spinnerList.add("Haryana");
        spinnerList.add("Jammu and Kashmir");
        spinnerList.add("Ladakh");
        spinnerList.add("Punjab");
        spinnerList.add("Himachal Pradesh");
        spinnerList.add("Uttar Pradesh");
        spinnerList.add("Rajasthan");
        spinnerList.add("Utarakhand");
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dest.setPrompt("Select Destination");
        dest.setAdapter(spinnerAdapter);
        dest.setSelection(0);
       //Select Bus Type
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin2.setAdapter(spinnerAdapter);
        spinnerList = new ArrayList<>();
        spin2.setPrompt("Select Bus Type");
        spinnerList.add("Standard");
        spinnerList.add("AC");
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin2.setAdapter(spinnerAdapter);
        spin2.setSelection(0);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spin2.getSelectedItem().toString().equals("AC")) {
                    if(dest.getSelectedItem().toString().equals("Haryana")){
                        price.setText("Rs. "+65);
                    }else if(dest.getSelectedItem().toString().equals("Jammu and Kashmir")){
                        price.setText("Rs. "+1200);
                    }else if(dest.getSelectedItem().toString().equals("Ladakh")){
                        price.setText("Rs. "+1760);
                    }else if(dest.getSelectedItem().toString().equals("Punjab")){
                        price.setText("Rs. "+945);
                    }else if(dest.getSelectedItem().toString().equals("Himachal Pradesh")){
                        price.setText("Rs. "+705);
                    }else if(dest.getSelectedItem().toString().equals("Uttar Pradesh")){
                        price.setText("Rs. "+699);
                    }else if(dest.getSelectedItem().toString().equals("Rajasthan")){
                        price.setText("Rs. "+900);
                    }else if(dest.getSelectedItem().toString().equals("Uttarakhand")){
                        price.setText("Rs. "+1310);
                    }
                }else {
                    if(dest.getSelectedItem().toString().equals("Haryana")){
                        price.setText("Rs. "+50);
                    }else if(dest.getSelectedItem().toString().equals("Jammu and Kashmir")){
                        price.setText("Rs. "+660);
                    }else if(dest.getSelectedItem().toString().equals("Ladakh")){
                        price.setText("Rs. "+1365);
                    }else if(dest.getSelectedItem().toString().equals("Punjab")){
                        price.setText("Rs. "+676);
                    }else if(dest.getSelectedItem().toString().equals("Himachal Pradesh")){
                        price.setText("Rs. "+526);
                    }else if(dest.getSelectedItem().toString().equals("Uttar Pradesh")){
                        price.setText("Rs. "+18);
                    }else if(dest.getSelectedItem().toString().equals("Rajasthan")){
                        price.setText("Rs. "+290);
                    }else if(dest.getSelectedItem().toString().equals("Uttarakhand")){
                        price.setText("Rs. "+300);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        price.setCursorVisible(false);
        price.setFocusable(false);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(editName.getText().length()==0){
                    name.setCompoundDrawables(null, null, img, null);
                }
                else if(editPass.getText().length()==0){

                }
                else if(start.getText().length()==0){

                }else if(price.getText().length()==0){

                }
             else{
                Toast.makeText(getContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
             }
            }
        });
        View root = binding.getRoot();
        return root;

    }
    @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


}