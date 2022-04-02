package csmv.antoinebrossard.record;

import csmv.antoinebrossard.annotation.Port;
import edu.wpi.first.wpilibj.DigitalInput;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class Recorder {

    private @Inject @Port(Port.Value.TEAM_SWITCH) DigitalInput teamSwitch;
    private @Inject @Named("saveDirectory") File saveDirectory;

    private @Inject RecordSerializer serializer;

    private final List<Record> recordList = new ArrayList<>();
    public int tick = 0;

    public void start() {
        tick = 0;
        recordList.clear();
    }

    public void tick() {
        tick++;
    }

    public synchronized void save() {
        List<Record> cloneList = new ArrayList<>(recordList);

        try {
            saveDirectory.mkdirs();

            File autonomousFile = new File(
                    saveDirectory.getPath()
                            + "/"
                            + (teamSwitch.get() ? "right.flux" : "left.flux")
            );

            if (autonomousFile.exists()) {
                autonomousFile.delete();
            }

            autonomousFile.createNewFile();

            Files.write(
                    autonomousFile.toPath(),
                    cloneList.stream()
                            .map(record -> serializer.serialize(record))
                            .collect(Collectors.toList())
            );
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void push(String action, String... parameters) {
        recordList.add(new Record(
                tick,
                action,
                parameters
        ));
    }
}