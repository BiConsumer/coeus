package csmv.antoinebrossard.record;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class RecordRunner {

    private @Inject Map<String, RecordExecutor> recordExecutors;
    private RunnerThread runner;

    public void play(List<Record> records) {
        long startMillis = System.currentTimeMillis();

        if (runner == null) {
            runner = new RunnerThread(startMillis, records);
            runner.start();
        } else {
            runner.running = false;
            runner = new RunnerThread(startMillis, records);
            runner.start();
        }
    }

    public void stop() {
        if (runner != null) {
            runner.running = false;
        }
    }

    private class RunnerThread extends Thread {

        private final long startMillis;
        private final List<Record> records;

        private boolean running = true;

        private RunnerThread(long startMillis, List<Record> records) {
            this.startMillis = startMillis;
            this.records = records;
        }

        @Override
        public void run() {
            try {
                while (running) {
                    long currentMillis = System.currentTimeMillis() - startMillis;

                    records.stream()
                            .filter(record -> record.getTimestamp() == currentMillis)
                            .forEach(record -> {
                                RecordExecutor executor = recordExecutors.get(record.getAction());
                                executor.execute(record.getParameters());
                            });

                    wait();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}