package com.example.midterm;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class AddYearAndModelDialog extends DialogFragment implements View.OnClickListener{
     NumberPicker year_picker;
     Spinner modelSpinner;
     Context app_context;


     /* Interface is for saving the fragment's Car object */
    public interface saveFragment{
        void dataFromFragment(Car car);
    }
    public saveFragment saveFragmentData;

    /* Initializing the context from calling activity*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        app_context = context;
        saveFragmentData =  (saveFragment) getActivity(); //if not used app crashes
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_year_level,container,false);
        modelSpinner = view.findViewById(R.id.carModelspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(app_context, R.array.carmodels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelSpinner.setAdapter(adapter); //sets the spinner with values

        year_picker = view.findViewById(R.id.yearPicker);
        year_picker.setMinValue(2000);
        year_picker.setMaxValue(2020);

        Button saveButton = (Button)view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
         //save the data back to Main
        Car car = getArguments().getParcelable("car");
        car.setYear(year_picker.getValue());
        car.setModel(modelSpinner.getSelectedItem().toString());

        saveFragmentData.dataFromFragment(car);
        dismiss();
    }

}
