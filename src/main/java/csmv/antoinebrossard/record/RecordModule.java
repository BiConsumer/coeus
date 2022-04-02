package csmv.antoinebrossard.record;

import csmv.antoinebrossard.record.executor.MovementExecutor;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.key.TypeReference;

public class RecordModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(RecordExecutor.class)
                .asMap(TypeReference.of(String.class))
                .bind("movement").to(MovementExecutor.class);
    }
}