package com.example.project.ui.home;

import android.content.Intent;
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
    String startAddress, destAddress;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Response response=null;
        EditText name, passID, price, editName, editPass;
        Spinner spin, spin2;
        ImageView fingerprint;
        Button btnsubmit;
        ArrayList<String> spinnerList = new ArrayList<>();
        Drawable img=getContext().getDrawable(R.mipmap.warning);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        name = binding.editName.findViewById(R.id.name);
        editName=binding.editName.findViewById(R.id.edit_name);
        passID = binding.editPass.findViewById(R.id.passId);
        editPass=binding.editPass.findViewById(R.id.edit_pass);
        spin = binding.spinner.findViewById(R.id.spinner);
        start = binding.editStart.findViewById(R.id.edit_start);
        dest = binding.editDest.findViewById(R.id.edit_dest);
        price = binding.editPrice.findViewById(R.id.edit_price);
        btnsubmit = binding.btn.findViewById(R.id.btn);
        spin2=binding.spinner2.findViewById(R.id.spinner2);
        spinnerList.add("Male");
        spinnerList.add("Female");
        spinnerList.add("Others");
        spinnerList.add("Prefer not to say");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin.setAdapter(spinnerAdapter);
        spinnerList = new ArrayList<>();
        spin2.setPrompt("Select Bus Type");
        spinnerList.add("Standard");
        spinnerList.add("AC");
        spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin2.setAdapter(spinnerAdapter);
        spin2.setSelection(0);
        start.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startAddress=start.getText().toString();
                Log.d("StartText", "StartText: "+startAddress);
                responseAddress(startAddress);

            }
        });
        dest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                destAddress=dest.getText().toString();
                Log.d("DestText", "DestText: "+destAddress);
                responseAddress(destAddress);
            }
        });
        double start_latitude=0.0;
        double start_longitude=0.0;
        double end_latitude=0.0;
        double end_longitude=0.0;
        int dist=calculateDistance(startAddress, destAddress);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spin2.getSelectedItem().toString().equals("AC")) {
                    price.setText("Rs. " + (dist * 50));
                }else {
                    price.setText("Rs. " + (dist * 40));
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

                }else if(dest.getText().length()==0){

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

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//            Log.d("Places_Data", "Places_Data: "+requestCode+", "+resultCode);
//            Log.d("Places_Data", "Places_Data: "+data);
//        if(requestCode==100 && resultCode==RESULT_OK){
//            Place place=Autocomplete.getPlaceFromIntent(data);
//            start.setText(place.getAddress() + String.format(place.getName()+String.valueOf(place.getLatLng())));
//            dest.setText(place.getAddress() + String.format(place.getName()+String.valueOf(place.getLatLng())));
//        }else if(resultCode== AutocompleteActivity.RESULT_ERROR){
//            Status status=Autocomplete.getStatusFromIntent(data);
//
//        }
//    }
    public void responseAddress(String address){
        if(!Places.isInitialized()) {
            Places.initialize(getContext().getApplicationContext(), "AIzaSyDHU2QBEwEkibjIJo0hSmZp6t7KXzD6wqU");
        }
        start.setFocusable(false);
        dest.setFocusable(false);
        start.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                List<Place.Field> fieldlist= Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME
                       );
                Log.d("FieldList: ", "FieldList: "+fieldlist);
                Intent intent=new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldlist).build(requireContext());
                startActivityForResult(intent,100);
                Log.d("startactive", "StartActivity:");

            }
    });
        dest.setOnClickListener(new View.OnClickListener() {
                        @Override
            public void onClick(View v) {
                List<Place.Field> fieldlist= Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME
                );
                Log.d("FieldList: ", "FieldList: "+fieldlist);
                Intent intent=new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldlist).build(requireContext());
                startActivityForResult(intent,100);
                Log.d("startactive", "StartActivity:");

            }
        });}
    public int calculateDistance(String startAddress, String destAddress){
//        int dist=Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1)*6371);
//        return dist;
        return 1;
    }
    @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


}