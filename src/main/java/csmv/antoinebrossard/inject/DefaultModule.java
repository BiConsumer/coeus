package csmv.antoinebrossard.inject;

import csmv.antoinebrossard.record.RecordModule;
import csmv.antoinebrossard.util.ChannelBinder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import me.yushust.inject.AbstractModule;

public class DefaultModule extends AbstractModule {

    @Override
    protected void configure() {
        try {
            ChannelBinder.bind(PWMVictorSPX.class, binder());
            ChannelBinder.bind(Joystick.class, binder());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        install(new RecordModule());
    }
}