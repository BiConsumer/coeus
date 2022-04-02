package csmv.antoinebrossard.record;

public class Record {

    private final int tick;
    private final String action;
    private final String[] parameters;

    public Record(int tick, String action, String[] parameters) {
        this.tick = tick;
        this.action = action;
        this.parameters = parameters;
    }

    public int getTick() {
        return tick;
    }

    public String getAction() {
        return action;
    }

    public String[] getParameters() {
        return parameters;
    }
}