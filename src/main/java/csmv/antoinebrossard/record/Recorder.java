package csmv.antoinebrossard.record;

import java.util.ArrayList;
import java.util.List;

public class Recorder {

    private final List<Record> recordList = new ArrayList<>();
    public long startingMillis = 0;

    public void start() {
        startingMillis = System.currentTimeMillis();
    }

    public void push(String action, String[] parameters) {
        recordList.add(new Record(
                System.currentTimeMillis() - startingMillis,
                action,
                parameters
        ));
    }

    public List<Record> getRecordList() {
        return recordList;
    }
}