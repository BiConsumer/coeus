package csmv.antoinebrossard.record;

public class RecordSerializer {

    public String serialize(Record record) {
        return record.getTick()
                + "-" + record.getAction()
                + ":" + String.join(" ", record.getParameters());
    }
}