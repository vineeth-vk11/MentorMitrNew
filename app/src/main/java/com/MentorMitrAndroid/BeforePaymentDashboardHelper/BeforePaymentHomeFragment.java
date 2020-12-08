package com.MentorMitrAndroid.BeforePaymentDashboardHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class BeforePaymentHomeFragment extends Fragment {

    private Timer timer = null;
    private Uri filePath;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Map<String, Object> currentUserData;
    private boolean fetchedUserData = false;

    GoogleSignInAccount account;
    FirebaseFirestore db;
    static int counter;
    WebView mywebview;
    TextView mainusername,mainuserID;
    ImageView mainimg;

    private ImageSwitcher imgsw;
    private int[] images = {R.drawable.main1,R.drawable.main2};
    private int position = 0;

    private VideoView videoView;
    private ImageView imgmine;
    private Button pausebtn;

    static Button know;
    String email;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_before_payment, container, false);

        return view;
    }
}