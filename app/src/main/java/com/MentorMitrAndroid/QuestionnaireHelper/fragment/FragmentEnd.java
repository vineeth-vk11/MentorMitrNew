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
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyAnswers;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyProperties;
import com.MentorMitrAndroid.R;

public class FragmentEnd extends Fragment {

    private FragmentActivity mContext;
    private TextView textView_end;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_end, container, false);


        Button button_finish = (Button) rootView.findViewById(R.id.button_finish);
        textView_end = (TextView) rootView.findViewById(R.id.textView_end);


        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((SurveyActivity) mContext).event_survey_completed(SurveyAnswers.getInstance());

            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survery_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");

        assert survery_properties != null;
        textView_end.setText(Html.fromHtml(survery_properties.getEndMessage()));

    }
}