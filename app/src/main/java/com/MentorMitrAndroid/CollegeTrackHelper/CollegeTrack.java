package com.MentorMitrAndroid.CollegeTrackHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.MentorMitrAndroid.AfterPaymentCollegeStudentDashboard.AfterPaymentCollegeStudentDashboardActivity;
import com.MentorMitrAndroid.AfterPaymentStudentDashboard.AfterPaymentSchoolStudentDashboardActivity;
import com.MentorMitrAndroid.MainActivity;
import com.MentorMitrAndroid.QuestionnaireHelper.models.StoreItems;
import com.MentorMitrAndroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollegeTrack extends AppCompatActivity {

    private EditText etSerialNumber;
    private EditText etCollege;
    private EditText etCourse;
    private EditText etSelection;
    private EditText etAchieve;
    private EditText etImprove;
    DatabaseReference mdatabase;
    String serial,college,course,selection,achieve,improve;

    private ItemAdapter itemAdapter;
    private StoreItems currentItem;

    HorizontalScrollView headerScroll;
    int scrollX=0;
    RecyclerView rvTable;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    FirebaseFirestore db;
    private Map<String, Object> currentUserData;
    private boolean fetchedUserData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_track);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        initViews();
        etSerialNumber = (EditText) findViewById(R.id.etSerialNumber);
        etCollege = (EditText) findViewById(R.id.etCollege);
        etCourse = (EditText) findViewById(R.id.etCourse);
        etSelection = (EditText) findViewById(R.id.etSelection);
        etAchieve = (EditText) findViewById(R.id.etAchieve);
        etImprove = (EditText) findViewById(R.id.etImprove);
        LinearLayout ll=(LinearLayout) findViewById(R.id.hor_linear);

         rvTable = (RecyclerView) findViewById(R.id.rvTable);
        itemAdapter = new ItemAdapter(new ArrayList<StoreItems>(), R.layout.item_layout, this);

            FixedGridLayoutManager manager = new FixedGridLayoutManager();
            manager.setTotalColumnCount(1);
            rvTable.setLayoutManager(manager);
            rvTable.setAdapter(itemAdapter);
            rvTable.addItemDecoration(new DividerItemDecoration(CollegeTrack.this, DividerItemDecoration.VERTICAL));
        rvTable.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                scrollX += dx;

                headerScroll.scrollTo(scrollX, 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.setVisibility(View.VISIBLE);
                headerScroll.setVisibility(View.VISIBLE);
                if (currentItem == null) {
                    //@TODO check edit text values
                    StoreItems item = new StoreItems(serial=etSerialNumber.getText().toString(),
                            college=etCollege.getText().toString(),
                            course=etCourse.getText().toString(),
                            selection=etSelection.getText().toString(),
                            achieve=etAchieve.getText().toString(),
                            improve=etImprove.getText().toString());
                    itemAdapter.addItem(item);
                } else {
                    currentItem.setSerialNumber(serial);
                    currentItem.setCollege(college);
                    currentItem.setCourse(course);
                    currentItem.setSelection(selection);
                    currentItem.setAchieve(achieve);
                    currentItem.setImprove(improve);
                    itemAdapter.updateItem(currentItem);
                    currentItem = null;
                }
                //reset form fields
                etSerialNumber.setText("");
                etCollege.setText("");
                etCourse.setText("");
                etSelection.setText("");
                etAchieve.setText("");
                etImprove.setText("");

                String user_id = mAuth.getCurrentUser().getUid();
               mdatabase= FirebaseDatabase.getInstance().getReference("college").child(user_id);
                Map<String, String> userMap = new HashMap<>();
                userMap.put("email", currentUser.getEmail());
                userMap.put("name", currentUser.getDisplayName());
                mdatabase.setValue(userMap);
                StoreItems store=new StoreItems(serial,college,course,selection,achieve,improve);
                mdatabase.child("college_options").setValue(store).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });


                db.collection("users").document(currentUser.getUid()).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            currentUserData = task.getResult().getData();
                            if ((boolean) currentUserData.get("paid")) {
                                FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener(
                                        new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if(task.isSuccessful())
                                                {
                                                    for(DocumentSnapshot doc : task.getResult().getDocuments())
                                                    {
                                                        if(doc.getData().get("email").toString().equalsIgnoreCase(currentUser.getEmail()))
                                                        {
                                                            moveToPage(doc.getData().get("type").toString());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                );
                            }
                            fetchedUserData = true;
                        }
                    }
                });

            }


        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_edit) {
            etSerialNumber.setText(currentItem.getSerialNumber());
            etCollege.setText(currentItem.getCollege());
            etCourse.setText(currentItem.getCourse());
            etSelection.setText(currentItem.getSelection());
            etAchieve.setText(currentItem.getAchieve());
            etImprove.setText(currentItem.getImprove());
        }else if (item.getItemId() == R.id.option_delete) {
            itemAdapter.deleteItem(currentItem);
            currentItem = null;
        }
        return true;
    }

    public void openMenu(View view){
        currentItem = (StoreItems) view.getTag();
        openContextMenu(view);
    }
    private void initViews()
    {
        rvTable = findViewById(R.id.rvTable);
        headerScroll = findViewById(R.id.headerScroll);
    }

    public void moveToPage(String type) {
        switch (type) {
            case "School":
                Intent schooldashintent = new Intent(CollegeTrack.this, AfterPaymentSchoolStudentDashboardActivity.class);
                schooldashintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(schooldashintent);
                break;
            case "College":
                Intent collegedashintent = new Intent(CollegeTrack.this, AfterPaymentCollegeStudentDashboardActivity.class);
                collegedashintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(collegedashintent);
                break;

            default:
                Toast.makeText(this, "Invalid student type " + (String) currentUserData.get("type"), Toast.LENGTH_SHORT).show();
        }

    }
}