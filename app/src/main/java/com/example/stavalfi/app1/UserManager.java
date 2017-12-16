package com.example.stavalfi.app1;

import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserManager {

    private static final UserManager ourInstance = new UserManager();

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
    }

    private String loggedInUserId;

    public String getLoggedInUserId() {
        return loggedInUserId;
    }


    public void registerUser(User user) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(DbTables.users);
        String userId = myRef.push().getKey();
        myRef.child(userId).setValue(user);
        // set user role
        if (user.getUsername().equals("1"))
            setRoleTypeOfUser_toAdminRole(userId);
        else
            setRoleTypeOfUser_toUserRole(userId);
    }

    public void setRoleTypeOfUser_toUserRole(String userId) {
        FirebaseDatabase.getInstance()
                .getReference(DbTables.userRoleTypes)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            UserRoleType userRoleType = messageSnapshot.getValue(UserRoleType.class);
                            if (userRoleType.getRole().equals("user")) {
                                setRoleTypeOfUser(userId, messageSnapshot.getKey());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public void setRoleTypeOfUser_toManagerRole(String userId) {
        FirebaseDatabase.getInstance()
                .getReference(DbTables.userRoleTypes)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            UserRoleType userRoleType = messageSnapshot.getValue(UserRoleType.class);
                            if (userRoleType.getRole().equals("manager")) {
                                setRoleTypeOfUser(userId, messageSnapshot.getKey());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public void setRoleTypeOfUser_toAdminRole(String userId) {
        FirebaseDatabase.getInstance()
                .getReference(DbTables.userRoleTypes)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            UserRoleType userRoleType = messageSnapshot.getValue(UserRoleType.class);
                            if (userRoleType.getRole().equals("admin")) {
                                setRoleTypeOfUser(userId, messageSnapshot.getKey());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    private void setRoleTypeOfUser(String userId, String userRoleTypeId) {
        // update user role
        FirebaseDatabase.getInstance()
                .getReference(DbTables.users)
                .child(userId)
                .child("userRoleTypeId")
                .setValue(userRoleTypeId);
        // add to the hisotry
        DatabaseReference myRef = FirebaseDatabase.getInstance()
                .getReference(DbTables.userRoleTypeHistory);
        String userRoleTypeHistoryId = myRef.push().getKey();
        myRef.child(userId).setValue(new UserRoleTypeHistory(userId, userRoleTypeId));
    }

    public void setLoggedInUserId(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }
}
