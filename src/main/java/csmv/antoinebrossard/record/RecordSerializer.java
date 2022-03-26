package csmv.antoinebrossard.record;

import java.util.Arrays;

public class RecordSerializer {

    public String serialize(Record record) {
        return record.getTimestamp()
                + record.getAction()
                + ":" + Arrays.toString(record.getParameters());
    }
}