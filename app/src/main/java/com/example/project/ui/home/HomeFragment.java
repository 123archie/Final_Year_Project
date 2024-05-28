package com.example.project.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.ApiService;
import com.example.project.Model;
import com.example.project.R;
import com.example.project.ResponseModel;
import com.example.project.RetrofitClient;
import com.example.project.databinding.FragmentHomeBinding;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public EditText start;
    EditText price;
    Spinner spin2, dest;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        EditText name, editName;
        Button btnsubmit;
        Drawable img=getContext().getDrawable(R.mipmap.warning);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        start = binding.editStart.findViewById(R.id.edit_start);
        dest = binding.destAdd.findViewById(R.id.destAdd);
        price = binding.editPrice.findViewById(R.id.edit_price);
        btnsubmit = binding.btn.findViewById(R.id.btn);
        spin2=binding.spinner2.findViewById(R.id.spinner2);
        ArrayList<String> spinnerList = new ArrayList<>();
        start.setFocusable(false);
        //Select Destination
        spinnerList = new ArrayList<>();
        spinnerList.add("Select Destination");
        spinnerList.add("Haryana");
        spinnerList.add("Jammu and Kashmir");
        spinnerList.add("Ladakh");
        spinnerList.add("Punjab");
        spinnerList.add("Himachal Pradesh");
        spinnerList.add("Uttar Pradesh");
        spinnerList.add("Rajasthan");
        spinnerList.add("Uttarakhand");
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinnerfile2, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dest.setAdapter(spinnerAdapter);
        dest.setSelection(0);
       //Select Bus Type
        spin2.setAdapter(spinnerAdapter);
        spinnerList = new ArrayList<>();
        spinnerList.add("Select Bus Type");
        spinnerList.add("Standard");
        spinnerList.add("AC");
        spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinnerfile, spinnerList);
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
//
//
            }
        });
        dest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(dest.getSelectedItem().toString().equals("Haryana")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 50);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+65);
                    }
                }else if(dest.getSelectedItem().toString().equals("Jammu and Kashmir")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 660);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+1200);
                    }
                }else if(dest.getSelectedItem().toString().equals("Ladakh")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 1365);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+1760);
                    }
                }else if(dest.getSelectedItem().toString().equals("Punjab")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 676);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+945);
                    }
                }else if(dest.getSelectedItem().toString().equals("Himachal Pradesh")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 526);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+705);
                    }
                }else if(dest.getSelectedItem().toString().equals("Uttar Pradesh")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 18);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+699);
                    }
                }else if(dest.getSelectedItem().toString().equals("Rajasthan")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 290);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+900);
                    }
                }else if(dest.getSelectedItem().toString().equals("Uttarakhand")){
                    if(spin2.getSelectedItem().equals("Standard")) {
                        price.setText("Rs. " + 300);
                    }else if(spin2.getSelectedItem().equals("AC")){
                        price.setText("Rs. "+1310);
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
                if(dest.getSelectedItemPosition()==0){
                    Toast.makeText(getContext(), "Please select your destination", Toast.LENGTH_SHORT).show();
                }else if(spin2.getSelectedItemPosition()==0){
                    Toast.makeText(getContext(), "Please select bus type", Toast.LENGTH_SHORT).show();
                }else{
                    sendDataToDatabase();
                }

            }
        });
        View root = binding.getRoot();
        return root;

    }
    private void sendDataToDatabase(){
        String destination=dest.getSelectedItem().toString();
        int fare=Integer.valueOf(price.getText().toString().substring(4));
        Log.d("Dest+Fare", "Dest+Fare: "+destination+", "+fare);
        ApiService apiService= RetrofitClient.getConnection().create(ApiService.class);
        Model model=new Model(destination, fare);
        Log.d("ModelName", "ModelName: "+model);
//        try{
            Call<ResponseModel> call = apiService.createTask(model);
            Log.d("Call_Model", "Call_Model: "+call.toString());
            call.enqueue(new Callback<ResponseModel>() {
                @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if(response.isSuccessful()){
                    ResponseModel responseModel=response.body();
                    Log.d("ResponseCode", "ResponseCode: "+response.code());
//                    Log.d("ResponseBody", "ResponseBody: "+response.message());
                    Toast.makeText(getContext(), "Task created Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Task Creation Failed", Toast.LENGTH_SHORT).show();
                }
            }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {

                }

        });
//    }catch(Exception e){
//            Log.d("ApiService", "ApiService: "+e.getLocalizedMessage());
//        }
    }
    @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


}

