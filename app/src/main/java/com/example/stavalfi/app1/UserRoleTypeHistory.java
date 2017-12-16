package com.example.stavalfi.app1;

public class UserRoleTypeHistory {
    private String userId;
    private String userRoleTypeId;

    public UserRoleTypeHistory() {

    }

    public UserRoleTypeHistory(String userId, String userRoleTypeId) {
        this.userId = userId;
        this.userRoleTypeId = userRoleTypeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRoleTypeId() {
        return userRoleTypeId;
    }

    public void setUserRoleTypeId(String userRoleTypeId) {
        this.userRoleTypeId = userRoleTypeId;
    }
}
