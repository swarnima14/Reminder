package com.app.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);

        Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();

        long today= MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);

        CalendarConstraints.Builder constraintsBuilder=new CalendarConstraints.Builder();
        constraintsBuilder.setStart(today);
        constraintsBuilder.setValidator(DateValidatorPointForward.now());


        MaterialDatePicker.Builder builder=MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT A DATE");
        builder.setSelection(today);
        builder.setCalendarConstraints(constraintsBuilder.build());
        final MaterialDatePicker materialDatePicker=builder.build();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               /* new DatePickerDialog(EnterDetails.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();*/
                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Toast.makeText(MainActivity.this, materialDatePicker.getHeaderText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}