package com.MentorMitrAndroid.QuestionnaireHelper.fragment;

import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.MentorMitrAndroid.QuestionnaireHelper.SurveyActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.models.Question;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyAnswers;
import com.MentorMitrAndroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FragmentRadioboxes extends Fragment {

    private Question q_data;
    private int questionSerial;
    private FragmentActivity mContext;
    private Button button_continue;
    private TextView textview_q_title;
    private RadioGroup radioGroup;
    private final ArrayList<RadioButton> allRb = new ArrayList<>();
    private boolean at_leaset_one_checked = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_radioboxes, container, false);

        button_continue = rootView.findViewById(R.id.button_continue);
        textview_q_title = rootView.findViewById(R.id.textview_q_title);
        radioGroup = rootView.findViewById(R.id.user_type_radio);
        button_continue.setOnClickListener(v -> ((SurveyActivity) mContext).go_to_next());

        return rootView;
    }

    private void collect_data() {

        //----- collection & validation for is_required
        //String the_choice = "";
        //List<String> choices = new ArrayList<>();
        String answer = "";
        at_leaset_one_checked = false;
        for (RadioButton rb : allRb) {
            if (rb.isChecked()) {
                at_leaset_one_checked = true;
                //the_choice = rb.getText().toString();
                answer = (rb.getText().toString());
            }
        }

        /*if (the_choice.length() > 0) {
            Answers.getInstance().put_answer(textview_q_title.getText().toString(), the_choice);
        }*/
        SurveyAnswers.getInstance().put_answer(textview_q_title.getText().toString(), answer, questionSerial);


        if (q_data.getRequired()) {
            if (at_leaset_one_checked) {
                button_continue.setVisibility(View.VISIBLE);
            } else {
                button_continue.setVisibility(View.GONE);
            }
        }

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mContext = getActivity();
        q_data = (Question) getArguments().getSerializable("data");
        questionSerial = getArguments().getInt("serial");

        textview_q_title.setText(q_data.getQuestionTitle());


        List<String> qq_data = q_data.getChoices();
        if (q_data.getRandomChoices()) {
            Collections.shuffle(qq_data);
        }

        for (String choice : qq_data) {
            RadioButton rb = new RadioButton(mContext);
            rb.setText(Html.fromHtml(choice));
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(rb);
            allRb.add(rb);

            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    collect_data();
                }
            });
        }

        if (q_data.getRequired()) {
            if (at_leaset_one_checked) {
                button_continue.setVisibility(View.VISIBLE);
            } else {
                button_continue.setVisibility(View.GONE);
            }
        }


    }


}