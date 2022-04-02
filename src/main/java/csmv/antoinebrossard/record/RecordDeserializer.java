package csmv.antoinebrossard.record;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Singleton
public class RecordDeserializer {

    public List<Record> deserialize(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);

        List<String> lines = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            lines.add(fileReader.nextLine());
        }

        fileReader.close();
        return deserialize(lines);
    }

    public List<Record> deserialize(List<String> stringList) {
        List<Record> parsedRecords = new ArrayList<>();

        for (String line : stringList) {
            int tick = Integer.parseInt(line.split("-")[0]);
            String action = line.split("-")[1].split(":")[0];
            String[] parameters = line.split(":")[1].split(" ");

            parsedRecords.add(new Record(
                    tick,
                    action,
                    parameters
            ));
        }

        return parsedRecords;
    }
}