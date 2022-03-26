package csmv.antoinebrossard.record;

import edu.wpi.first.wpilibj2.command.Command;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;

public class RecordModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(RecordExecutor.class)
                .asMap(TypeReference.of(String.class, Command.class))
                .bind("deez").toInstance((parameters) -> {
                   System.out.println("TEST");
                });
    }
}