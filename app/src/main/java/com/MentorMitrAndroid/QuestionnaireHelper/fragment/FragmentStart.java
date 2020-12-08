package com.MentorMitrAndroid.QuestionnaireHelper.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.MentorMitrAndroid.QuestionnaireHelper.SurveyActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyProperties;
import com.MentorMitrAndroid.R;

public class FragmentStart extends Fragment {

    private FragmentActivity mContext;
    private TextView textView_start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_start, container, false);

        textView_start = (TextView) rootView.findViewById(R.id.textView_start);
        Button button_continue = (Button) rootView.findViewById(R.id.button_continue);
        button_continue.setOnClickListener(v -> ((SurveyActivity) mContext).go_to_next());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survey_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");

        assert survey_properties != null;
        textView_start.setText(Html.fromHtml(survey_properties.getIntroMessage()));




    }
}