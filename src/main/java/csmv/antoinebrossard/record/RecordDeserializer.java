package csmv.antoinebrossard.record;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordDeserializer {

    public List<Record> deserialize(File file) throws FileNotFoundException, ClassNotFoundException {
        Scanner fileReader = new Scanner(file);

        List<String> lines = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            lines.add(fileReader.nextLine());
        }

        fileReader.close();
        return deserialize(lines);
    }

    public List<Record> deserialize(List<String> stringList) throws ClassNotFoundException {
        List<Record> parsedRecords = new ArrayList<>();

        for (String line : stringList) {
            String action = line.split(":")[0];
            String[] parameters = line.split(":")[1].split(" ");

            parsedRecords.add(new Record(
                    Long.parseLong(parameters[0]),
                    action,
                    parameters
            ));
        }

        return parsedRecords;
    }
}