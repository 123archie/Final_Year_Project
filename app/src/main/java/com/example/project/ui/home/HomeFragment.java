package com.example.project.ui.home;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.databinding.FragmentHomeBinding;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Response response=null;
        EditText name, passID, start, dest, price;
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
        try {
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();Log.d("Message2", "Message2"+client);
//            Request request = new Request.Builder()
//                    .url("https://api.geoapify.com/v1/geocode/autocomplete?text=India&apiKey=255bfc459b7f40c7932ffefeacc9d4d7")
//                    .method("GET",null)
//                    .build();Log.d("Message2", "Request2"+request);
//            response=client.newCall(request).execute();

            URL url = new URL("http://api.geoapify.com/v1/geocode/autocomplete?text=India&apiKey=255bfc459b7f40c7932ffefeacc9d4d7");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestProperty("Accept", "application/json");
            Log.d("Message2", "Message2: "+http.getResponseCode());
            System.out.println(http.getResponseCode()+" "+http.getResponseMessage());
            http.disconnect();
            }
        catch (IOException io) {
            Log.e("EErr msg:", "EErr msg: " + io.getMessage());
        }
            View root = binding.getRoot();


            return root;
        }
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }}