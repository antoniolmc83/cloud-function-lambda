package ca.neilwhite.cloudfunctiondynamodblambda;

import java.io.Serializable;

public class Request implements Serializable {
    private String userId;

    // constructors, getters &


    public Request() {
    }

    public Request(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
