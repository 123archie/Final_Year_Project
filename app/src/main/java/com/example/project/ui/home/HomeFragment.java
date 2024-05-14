package com.example.project.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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
import com.example.project.RetrofitClient;
import com.example.project.databinding.FragmentHomeBinding;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public EditText start;
    Spinner spin2, dest;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        EditText name, price, editName;
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
        spinnerList.add("Utarakhand");
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

                if(spin2.getItemAtPosition(0).equals("AC")) {
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
        String destName=dest.getSelectedItem().toString();
        Model model=new Model(destName);
        ApiService apiService= RetrofitClient.getConnection().create(ApiService.class);
//        Call<Model> call=apiService.createTask(model);
//        call.enqueue(new Callback<Model>() {
//            @Override
//            public void onResponse(Call<Model> call, Response<Model> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(getContext(), "Task Created Successfully", Toast.LENGTH_SHORT);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Model> call, Throwable t) {
//                Toast.makeText(getContext(), "Task Creation Failed", Toast.LENGTH_SHORT);
//            }
//        });
    }
    @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }


}

