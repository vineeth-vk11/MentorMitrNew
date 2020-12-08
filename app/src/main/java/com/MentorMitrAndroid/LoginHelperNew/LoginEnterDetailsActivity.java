package com.MentorMitrAndroid.LoginHelperNew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginEnterDetailsActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextInputLayout fullNameEditText;
    TextInputLayout emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_enter_details);

        mAuth = FirebaseAuth.getInstance();

        fullNameEditText = findViewById(R.id.fullname_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);

        Button signupButton = findViewById(R.id.signup_button);
        Button loginButton = findViewById(R.id.login_button);

        final View logoView = findViewById(R.id.logo_signup);

        signupButton.setOnClickListener(v -> {
            if(TextUtils.isEmpty(fullNameEditText.getEditText().getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(emailEditText.getEditText().getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
            }
            else {
                saveData();
            }
        });
    }

    private void saveData(){
        CollectionReference usersCollection = FirebaseFirestore.getInstance().collection("users");
        CollectionReference mentorCollection = FirebaseFirestore.getInstance().collection("mentors");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        RadioGroup userType = findViewById(R.id.user_type_radio);
        RadioButton checkedType = userType.findViewById(userType.getCheckedRadioButtonId());
        String type;
        switch (checkedType.getId()) {
            case R.id.school_student:
                type = "School";
                break;
            case R.id.college_student:
                type = "College";
                break;
            case R.id.parent:
                type = "Parent";
                break;
            case R.id.working_professional:
                type = "Working";
                break;

            case R.id.mentor_login:
                type = "Mentor";
                break;
            default:
                type = "School";
        }

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fullNameEditText.getEditText().getText().toString())
                .setPhotoUri(Uri.parse(checkedType.getText().toString()))
                .build();
        currentUser.updateProfile(profileUpdates);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", emailEditText.getEditText().getText().toString());
        /*  userMap.put("ismentor", false);*/
        userMap.put("name", fullNameEditText.getEditText().getText().toString());
        userMap.put("type", type);
        userMap.put("paid", false);
        userMap.put("userhours", 0);
        userMap.put("sessionflag", false);
        userMap.put("nextmonth", 100);


        Map<String, Object> mentorMap = new HashMap<>();
        mentorMap.put("email", emailEditText.getEditText().getText().toString());
        mentorMap.put("ismentor", false);
        mentorMap.put("name", fullNameEditText.getEditText().getText().toString());
        mentorMap.put("type", type);
        mentorMap.put("paid", false);
        if(checkedType.getId()==R.id.mentor_login)
        {
            mentorCollection.document(currentUser.getUid()).set(mentorMap);
            FirebaseFirestore.getInstance().collection("users").add(mentorMap);
            Intent intent = new Intent(LoginEnterDetailsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else {
            usersCollection.document(currentUser.getUid()).set(userMap);
            Intent intent = new Intent(LoginEnterDetailsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        Toast.makeText(this, "You have successfully logged in!", Toast.LENGTH_SHORT).show();
    }

}