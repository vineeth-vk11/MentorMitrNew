package com.MentorMitrAndroid.ProgramsAndPaymentsHelper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.MentorMitrAndroid.AfterPaymentStudentDashboard.AfterPaymentSchoolStudentDashboardActivity;
import com.MentorMitrAndroid.BeforePaymentDashboardHelper.BeforePaymentDashboardActivity;
import com.MentorMitrAndroid.LoginHelperNew.LoginEnterDetailsActivity;
import com.MentorMitrAndroid.MentorDashboard.MentorDashboardActivity;
import com.MentorMitrAndroid.R;
import com.MentorMitrAndroid.SplashActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.functions.FirebaseFunctions;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class StudentProgram1Month extends AppCompatActivity implements PaymentResultListener {

    Button schoolPayment1;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_program1_month);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = simpleDateFormat.format(c);

        schoolPayment1 = findViewById(R.id.pay_student_1month);
        schoolPayment1.setOnClickListener(v -> {

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
            options.put("description", "Student 1 Month Program");
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", "600000");

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
                Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}