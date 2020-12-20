package com.MentorMitrAndroid.ProgramsAndPaymentsHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.MentorMitrAndroid.R;
import com.google.android.material.appbar.MaterialToolbar;

public class WorkingProfessionalProgram extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_professional_program);

        MaterialToolbar toolbar = findViewById(R.id.material_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)  {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CardView threemonthsworking=findViewById(R.id.working_3months_cardview);
        threemonthsworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent threemonthworking = new Intent(WorkingProfessionalProgram.this , WorkingProfessionalProgram3Month.class);
                startActivity(threemonthworking);

            }
        });
        CardView onemonthworking=findViewById(R.id.working_1month_cardview);
        onemonthworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onemonthcollege= new Intent(WorkingProfessionalProgram.this , WorkingProfessionalProgram1Month.class);
                startActivity(onemonthcollege);

            }
        });


    }
}
