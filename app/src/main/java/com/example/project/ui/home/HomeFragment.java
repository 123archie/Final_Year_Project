package com.example.project.ui.home;

import static android.app.appsearch.AppSearchResult.RESULT_OK;

import static com.google.android.libraries.places.widget.AutocompleteActivity.RESULT_ERROR;

import android.content.Intent;
import android.icu.util.IndianCalendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.databinding.FragmentHomeBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public EditText start, dest;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Response response=null;
        EditText name, passID, price;
        Spinner spin;
        ImageView fingerprint;
        Button btnsubmit;
        ArrayList<String> spinnerList = new ArrayList<>();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        name = binding.editName.findViewById(R.id.name);
        passID = binding.editPass.findViewById(R.id.passId);
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

    @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


}