package ca.neilwhite.cloudfunctiondynamodblambda;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
    private List<Session> sessions;

    // constructors, getters & setters

    public Response() {
    }

    public Response(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
