package examples.aaronhoskins.com.services;

public class IntentServiceEvent {
    private String message;

    public IntentServiceEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
