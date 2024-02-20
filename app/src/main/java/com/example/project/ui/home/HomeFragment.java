package com.example.project.ui.home;

import static android.app.appsearch.AppSearchResult.RESULT_OK;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.toolbox.HttpResponse;
import com.example.project.R;
import com.example.project.databinding.FragmentHomeBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public EditText start, dest;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Response response=null;
        EditText name, passID, price, editName, editPass;
        Spinner spin;
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
        spinnerList.add("Male");
        spinnerList.add("Female");
        spinnerList.add("Others");
        spinnerList.add("Prefer not to say");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin.setAdapter(spinnerAdapter);
        Places.initialize(getContext().getApplicationContext(), "AIzaSyDHU2QBEwEkibjIJo0hSmZp6t7KXzD6wqU");
        start.setFocusable(false);
        dest.setFocusable(false);
        start.setOnClickListener(new View.OnClickListener() {
//            @Override
            public void onClick(View v) {
//                List<Place.Field> fieldlist= Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME
//                       );
//                Log.d("FieldList: ", "FieldList: "+fieldlist);
//                Intent intent=new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldlist).build(requireContext());
//                startActivityForResult(intent,100);
//                Log.d("startactive", "StartActivity:");
//
//            }

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://geoapify-address-autocomplete.p.rapidapi.com/v1/geocode/autocomplete?text=Polizeigasse&type=street&lang=de&limit=2&filter=countrycode%3Ade%2Ces%2Cfr&bias=proximity%3A10.485306%2C48.852565")
                        .get()
                        .addHeader("X-RapidAPI-Key", "84743f608emsh304f1d6aff5f6afp1d8849jsnfdcf72505aa4")
                        .addHeader("X-RapidAPI-Host", "geoapify-address-autocomplete.p.rapidapi.com")
                        .build();

                try {
                    com.squareup.okhttp.Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }});
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
        });
        double start_latitude=0.0;
        double start_longitude=0.0;
        double end_latitude=0.0;
        double end_longitude=0.0;
        int dist=calculateDistance();
        price.setText("Rs. "+(dist*2));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Log.d("Places_Data", "Places_Data: "+requestCode+", "+resultCode);
            Log.d("Places_Data", "Places_Data: "+data);
        if(requestCode==100 && resultCode==RESULT_OK){
            Place place=Autocomplete.getPlaceFromIntent(data);
            start.setText(place.getAddress() + String.format(place.getName()+String.valueOf(place.getLatLng())));
            dest.setText(place.getAddress() + String.format(place.getName()+String.valueOf(place.getLatLng())));
        }else if(resultCode== AutocompleteActivity.RESULT_ERROR){
            Status status=Autocomplete.getStatusFromIntent(data);

        }
    }
    public int calculateDistance(){

        return 0;
    }
    @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


}