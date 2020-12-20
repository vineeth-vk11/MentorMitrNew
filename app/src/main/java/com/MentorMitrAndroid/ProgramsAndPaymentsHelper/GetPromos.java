package com.MentorMitrAndroid.ProgramsAndPaymentsHelper;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class GetPromos {
    public static Map<String, String> map=new HashMap();
    public GetPromos() {
        FirebaseFirestore.getInstance().collection("promocodes").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    map.put(doc.getData().get("promoname").toString(), doc.getData().get("amount").toString());
                                }
                            }
                        }
                );
    }
}
