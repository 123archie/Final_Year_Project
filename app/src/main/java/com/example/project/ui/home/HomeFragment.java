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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.project.R;
import com.example.project.databinding.FragmentHomeBinding;
import java.util.ArrayList;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public EditText start;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        EditText name, passID, price, editName, editPass;
        Spinner spin, spin2, dest;
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
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence editName, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence editName, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editName) {
                if(Character.isDigit(editName.toString().charAt(editName.length()-1))){
                    Toast.makeText(getContext(), "Name Should not contain digit.", Toast.LENGTH_SHORT).show();
                    editName.setFilters(new InputFilter[] {
                            new InputFilter.LengthFilter(editName.length())
                    });
                }
                else{
                    editName.setFilters(new InputFilter[] {});
                }
            }
        });
        editPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence passID, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence passID, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editPass) {
                if(!checkPassportID(editPass.toString())){
                    Toast.makeText(getContext(), "Passport ID is invalid.", Toast.LENGTH_SHORT).show();
                    editPass.setFilters(new InputFilter[] {
                            new InputFilter.LengthFilter(editPass.length())
                    });
                }else if(checkPassportID(editPass.toString())){
                    editPass.setFilters(new InputFilter[]{});
                }else if(checkPassportID(editPass.toString()) && editPass.length()==8)
                editPass.setFilters(new InputFilter[]{
                        new InputFilter.LengthFilter(8)
                });
            }
        });

        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("Male");
        spinnerList.add("Female");
        spinnerList.add("Others");
        spinnerList.add("Prefer not to say");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinnerfile, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spin.setAdapter(spinnerAdapter);
        start.setFocusable(false);
        //Select Destination
        spinnerList = new ArrayList<>();
        dest.setPrompt("Select Destination");
        spinnerList.add("Haryana");
        spinnerList.add("Jammu and Kashmir");
        spinnerList.add("Ladakh");
        spinnerList.add("Punjab");
        spinnerList.add("Himachal Pradesh");
        spinnerList.add("Uttar Pradesh");
        spinnerList.add("Rajasthan");
        spinnerList.add("Utarakhand");
        spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinnerfile2, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dest.setPrompt("Select Destination");
        dest.setAdapter(spinnerAdapter);
        dest.setSelection(0);
       //Select Bus Type
        spin2.setAdapter(spinnerAdapter);
        spinnerList = new ArrayList<>();
        spin2.setPrompt("Select Bus Type");
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

            }
        });
        price.setCursorVisible(false);
        price.setFocusable(false);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(editName.getText().length()==0){
//                    name.setCompoundDrawables(null, null, img, null);
                }
                else if(editPass.getText().length()>=0 && editPass.getText().length()<8){
//                    editPass.setCompoundDrawables(null, null, img, null);
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

public boolean checkPassportID(String pass) {
    char ch = pass.charAt(pass.length() - 1);
    Log.d("Passport length", "Passport length: "+pass.length());
    if (pass.length() == 1 && !(ch >= 65 && ch <= 90) || (ch=='Q' || ch=='X')) {
        return false;
    }
    if (pass.length() == 2 && !(ch >= 49 && ch <= 57)) {
        return false;
    }
    if (pass.length() == 3 && !(ch >= 48 && ch <= 57)) {
        return false;
    }
    if ((pass.length() == 4 || pass.length() == 5 || pass.length() == 6 || pass.length() == 7) && !(ch >= 48 && ch <= 57)) {
        return false;
    }
    if (pass.length() == 8 && !(ch >= 49 && ch <= 57)) {
        return false;
    }
    return true;
}
}

