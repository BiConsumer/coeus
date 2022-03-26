package csmv.antoinebrossard.record;

public class Record {

    private final long timestamp;
    private final String action;
    private final String[] parameters;

    public Record(long timestamp, String action, String[] parameters) {
        this.timestamp = timestamp;
        this.action = action;
        this.parameters = parameters;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public String[] getParameters() {
        return parameters;
    }
}