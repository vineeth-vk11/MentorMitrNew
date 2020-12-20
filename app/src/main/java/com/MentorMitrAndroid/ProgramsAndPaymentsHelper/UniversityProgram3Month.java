package com.MentorMitrAndroid.ProgramsAndPaymentsHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.MentorMitrAndroid.AfterPaymentCollegeStudentDashboard.AfterPaymentCollegeStudentDashboardActivity;
import com.MentorMitrAndroid.AfterPaymentStudentDashboard.AfterPaymentSchoolStudentDashboardActivity;
import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


public class UniversityProgram3Month extends AppCompatActivity implements PaymentResultListener {

    Button universityPayment3;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_program3_month);


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = simpleDateFormat.format(c);

        universityPayment3 = findViewById(R.id.pay_university_3month);
        universityPayment3.setOnClickListener(v -> {
            startPayment();
        });

    }

    public void startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "MentorMitr");

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "University 3 Month Program");
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", "1200000");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        HashMap<String, Object> data = new HashMap<>();
        data.put("date", date);
        data.put("paid", true);

        db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(getApplicationContext(), AfterPaymentCollegeStudentDashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {

    }


}