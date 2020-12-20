package com.MentorMitrAndroid.QuestionnaireHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.MentorMitrAndroid.AfterPaymentCollegeStudentDashboard.AfterPaymentCollegeStudentDashboardActivity;
import com.MentorMitrAndroid.AfterPaymentStudentDashboard.AfterPaymentSchoolStudentDashboardActivity;
import com.MentorMitrAndroid.AfterPaymentWorkingProfessionalDashboard.AfterPaymentWorkingProfessionalActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.Adapters.AdapterFragmentQ;
import com.MentorMitrAndroid.QuestionnaireHelper.fragment.FragmentCheckboxes;
import com.MentorMitrAndroid.QuestionnaireHelper.fragment.FragmentEnd;
import com.MentorMitrAndroid.QuestionnaireHelper.fragment.FragmentNumber;
import com.MentorMitrAndroid.QuestionnaireHelper.fragment.FragmentRadioboxes;
import com.MentorMitrAndroid.QuestionnaireHelper.fragment.FragmentStart;
import com.MentorMitrAndroid.QuestionnaireHelper.fragment.FragmentTextSimple;
import com.MentorMitrAndroid.QuestionnaireHelper.models.MyConstants;
import com.MentorMitrAndroid.QuestionnaireHelper.models.Question;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyAnswers;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyPojo;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyProperties;
import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SurveyActivity extends AppCompatActivity {

    private SurveyPojo mSurveyPojo;
    private ViewPager mPager;
    private String style_string = null;

    AdapterFragmentQ mPagerAdapter;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        final ArrayList<Fragment> arraylist_fragments = new ArrayList<>();

        FragmentStart frag_start = new FragmentStart();
        Bundle sBundle = new Bundle();
        SurveyProperties surveyProperties = new SurveyProperties();
        surveyProperties.setIntroMessage("Hey, there!\nAre you worried about exams?" +
                "\nAre you motivated to give your all?" +
                "\n\nIt's okay to be nervous because all you need is to voice your fears and anguish concerning the exam preparation." +
                "\n\nWe are helping individuals address their angst, whilst passionately encouraging them in making better decisions." +
                "\n\nYou are at your first step for a life-changing process. Get started!");
        surveyProperties.setSkipIntro(false);
        surveyProperties.setEndMessage("Congratulations on taking a right step ahead!");
        sBundle.putSerializable("survery_properties", surveyProperties);
        sBundle.putString("style", style_string);
        frag_start.setArguments(sBundle);
        arraylist_fragments.add(frag_start);

        String referenceString;
        referenceString = Objects.requireNonNull(getIntent().getExtras()).getString("reference");

        if(getIntent().getExtras().getString("type") != null){
            type = getIntent().getExtras().getString("type");
        }
        else {
            type = "normal";
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        assert referenceString != null;
        db.collection(referenceString).orderBy("serial").get().addOnCompleteListener(task -> {
           if (task.isSuccessful() && task.getResult()!=null) {
               for (QueryDocumentSnapshot document: task.getResult()) {
                   Map<String, Object> data =  document.getData();
                   Question question = new Question();
                   question.setQuestionTitle(data.get("question").toString());
                   question.setRequired(true);
                   Bundle bundle = new Bundle();
                   switch (data.get("type").toString()) {
                       case MyConstants.STRING:
                           Log.d("hello", "String");
                           FragmentTextSimple fragText = new FragmentTextSimple();
                           bundle.putSerializable("data", question);
                           bundle.putString("style", style_string);
                           bundle.putInt("serial", (int) (long) data.get("serial"));
                           fragText.setArguments(bundle);
                           arraylist_fragments.add(fragText);
                           break;
                       case MyConstants.NUMBER:
                           Log.d("hello", "Number");
                           FragmentNumber fragNum = new FragmentNumber();
                           bundle.putSerializable("data", question);
                           bundle.putString("style", style_string);
                           bundle.putInt("serial", (int) (long) data.get("serial"));
                           fragNum.setArguments(bundle);
                           arraylist_fragments.add(fragNum);
                           break;
                       case MyConstants.CHECKBOXES:
                           Log.d("hello", "Checkboxes");
                           FragmentCheckboxes fragCheck = new FragmentCheckboxes();
                           question.setChoices((List<String>) data.get("choices"));
                           question.setRandomChoices(false);
                           bundle.putSerializable("data", question);
                           bundle.putString("style", style_string);
                           bundle.putInt("serial", (int) (long) data.get("serial"));
                           fragCheck.setArguments(bundle);
                           arraylist_fragments.add(fragCheck);
                           break;
                       case MyConstants.RADIOBUTTONS:
                           Log.d("hello", "Radiobuttons");
                           FragmentRadioboxes fragRadio = new FragmentRadioboxes();
                           question.setChoices((List<String>) data.get("choices"));
                           question.setRandomChoices(false);
                           bundle.putSerializable("data", question);
                           bundle.putString("style", style_string);
                           bundle.putInt("serial", (int) (long) data.get("serial"));
                           fragRadio.setArguments(bundle);
                           arraylist_fragments.add(fragRadio);
                           break;
                   }
               }
               FragmentEnd frag_end = new FragmentEnd();
               frag_end.setArguments(sBundle);
               arraylist_fragments.add(frag_end);
           }
        });

        Log.d("hello", arraylist_fragments.toString());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new AdapterFragmentQ(getSupportFragmentManager(), arraylist_fragments);
        mPager.setAdapter(mPagerAdapter);


    }

    public void go_to_next() {
        mPagerAdapter.notifyDataSetChanged();
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void event_survey_completed(SurveyAnswers instance) {

        SurveyAnswers surveyAnswers = new SurveyAnswers();
        surveyAnswers.setResponse_general((List<Map<String, Object>>)instance.getResponse_general());

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        if(type.equals("sports")){
            db.collection("questions_specific_reply").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(surveyAnswers).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            String type = documentSnapshot.getString("type");

                            if(type.equals("School")){
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else if(type.equals("College")){
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), AfterPaymentCollegeStudentDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), AfterPaymentCollegeStudentDashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    });

                }
            });
        }

        else {
            db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    switch (documentSnapshot.getString("type")){
                        case "School":
                            db.collection("questions_reply").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(surveyAnswers).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                            break;
                        case "College":
                            db.collection("questions_college_reply").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(surveyAnswers)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), AfterPaymentCollegeStudentDashboardActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), AfterPaymentCollegeStudentDashboardActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    });
                            break;
                        case "Parent":

                            db.collection("questions_parents_reply").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(surveyAnswers)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), AfterPaymentSchoolStudentDashboardActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }) ;
                            break;
                        case "Working":

                            db.collection("working_professional_reply").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(surveyAnswers)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), AfterPaymentWorkingProfessionalActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), AfterPaymentWorkingProfessionalActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    });
                            break;
                        default:
                    }
                }
            });
        }
    }
}
