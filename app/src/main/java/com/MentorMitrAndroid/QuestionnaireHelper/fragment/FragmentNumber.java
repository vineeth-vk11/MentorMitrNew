package com.MentorMitrAndroid.QuestionnaireHelper.fragment;

import android.app.Service;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.MentorMitrAndroid.QuestionnaireHelper.SurveyActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.models.Question;
import com.MentorMitrAndroid.QuestionnaireHelper.models.SurveyAnswers;
import com.MentorMitrAndroid.R;

public class FragmentNumber extends Fragment {

    private int questionSerial;
    private FragmentActivity mContext;
    private Button button_continue;
    private TextView textview_q_title;
    private EditText editText_answer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_text_simple, container, false);

        button_continue = rootView.findViewById(R.id.button_continue);
        textview_q_title = rootView.findViewById(R.id.textview_q_title);
        editText_answer = rootView.findViewById(R.id.editText_answer);
        editText_answer.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        button_continue.setOnClickListener(v -> {
            /*Answers.getInstance().put_answer(textview_q_title.getText().toString(), editText_answer.getText().toString().trim());
            ((SurveyActivity) mContext).go_to_next();*/
            String question = textview_q_title.getText().toString();
            String answer = editText_answer.getText().toString().trim();
            SurveyAnswers.getInstance().put_answer(question, answer, questionSerial);
            ((SurveyActivity) mContext).go_to_next();
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        Question q_data = (Question) getArguments().getSerializable("data");
        questionSerial = getArguments().getInt("serial");


        if (q_data.getRequired()) {
            button_continue.setVisibility(View.GONE);
            editText_answer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        button_continue.setVisibility(View.VISIBLE);
                    } else {
                        button_continue.setVisibility(View.GONE);
                    }
                }
            });
        }


        textview_q_title.setText(Html.fromHtml(q_data.getQuestionTitle()));
        editText_answer.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText_answer, 0);


    }
}