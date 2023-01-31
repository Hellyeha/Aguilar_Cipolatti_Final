package com.example.aguilar_cipolatti_final.providers;

import com.example.aguilar_cipolatti_final.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProvider {

    DatabaseReference mDatabase;

    public UserProvider(){
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public Task<Void> create(User user){
        return mDatabase.child(user.getId()).setValue(user);
    }

}
