package com.example.sony.bookmycar.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sony.bookmycar.FragmentListener;
import com.example.sony.bookmycar.R;
import com.example.sony.bookmycar.TripModel;

/**
 * Created by SONY on 25-10-2017.
 */
public class ConfirmTripFragment extends Fragment {

    FragmentListener listener;
    TripModel trip;
    AppCompatSpinner spinner;
    EditText daysEditText;
    Button calcFareButton,nextButton;
    TextView result;
    double fare = 0.0;

    public static ConfirmTripFragment newInstance(){
        return new ConfirmTripFragment();
    }

    public void setListsner( FragmentListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_trip,container,false );

        Bundle bundle = getArguments();
        if (bundle != null) {
            try {
                trip = bundle.getParcelable("trip");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        spinner = (AppCompatSpinner) view.findViewById(R.id.spinner);
        daysEditText = (EditText)view.findViewById(R.id.daysEditText);
        calcFareButton = (Button)view.findViewById(R.id.calcFareButton);
        result = (TextView)view.findViewById(R.id.result);
        calcFareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fare = 0.0;
                //calculate fare

                result.setText("Fare = "+fare);
            }
        });

        view.findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(trip);
                listener.onClick(trip);
            }
        });

        return view;
    }

    private void validate(TripModel trip){
        trip.setCarType(spinner.getSelectedItem().toString());
        trip.setNumOfDays(Integer.parseInt(daysEditText.getText().toString()));
        trip.setFare(fare);
    }
}
