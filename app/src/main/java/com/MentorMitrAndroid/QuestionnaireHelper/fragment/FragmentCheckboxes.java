package com.MentorMitrAndroid.QuestionnaireHelper.fragment;

import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
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

public class FragmentCheckboxes extends Fragment {

    private Question q_data;
    private int questionSerial;
    private FragmentActivity mContext;
    private Button button_continue;
    private TextView textview_q_title;
    private LinearLayout linearLayout_checkboxes;
    private final ArrayList<CheckBox> allCb = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_checkboxes, container, false);

        button_continue = rootView.findViewById(R.id.button_continue);
        textview_q_title = rootView.findViewById(R.id.textview_q_title);
        linearLayout_checkboxes = rootView.findViewById(R.id.linearLayout_checkboxes);
        button_continue.setOnClickListener(v -> ((SurveyActivity) mContext).go_to_next());

        return rootView;
    }

    private void collect_data() {

        //----- collection & validation for is_required
        List<String> choices = new ArrayList<>();
        //String the_choices = "";
        boolean at_leaset_one_checked = false;
        for (CheckBox cb : allCb) {
            if (cb.isChecked()) {
                at_leaset_one_checked = true;
                //the_choices = the_choices + cb.getText().toString() + ", ";
                choices.add(cb.getText().toString());
            }
        }

        /*if (the_choices.length() > 2) {
            the_choices = the_choices.substring(0, the_choices.length() - 2);
            Answers.getInstance().put_answer(textview_q_title.getText().toString(), the_choices);
        }*/

        SurveyAnswers.getInstance().put_answer(textview_q_title.getText().toString(), choices, questionSerial);

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

        textview_q_title.setText(q_data != null ? q_data.getQuestionTitle() : "");

        if (q_data.getRequired()) {
            button_continue.setVisibility(View.GONE);
        }

        List<String> qq_data = q_data.getChoices();
        if (q_data.getRandomChoices()) {
            Collections.shuffle(qq_data);
        }

        for (String choice : qq_data) {
            CheckBox cb = new CheckBox(mContext);
            cb.setText(Html.fromHtml(choice));
            cb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout_checkboxes.addView(cb);
            allCb.add(cb);


            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    collect_data();
                }
            });
        }

    }


}