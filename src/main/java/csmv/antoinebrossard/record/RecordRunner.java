package csmv.antoinebrossard.record;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

@Singleton
public class RecordRunner {

    private @Inject Map<String, RecordExecutor> recordExecutors;
    int tick = 0;
    private List<Record> current;

    public void play(List<Record> records) {
        tick = 0;
        current = records;
    }

    public void next() {
        if (current == null) {
            return;
        }

        for (Record record : current) {
            if (record.getTick() != tick) {
                continue;
            }

            recordExecutors.get(record.getAction()).execute(record.getParameters());
        }

        tick++;
    }
}