package csmv.antoinebrossard.button;

import csmv.antoinebrossard.button.lock.BallLockAction;
import csmv.antoinebrossard.button.record.RecordSaveAction;
import csmv.antoinebrossard.button.record.RecordStartAction;
import csmv.antoinebrossard.button.roller.RollerInwardsAction;
import csmv.antoinebrossard.button.roller.RollerOutwardsAction;
import csmv.antoinebrossard.button.solenoid.SolenoidGripAction;
import csmv.antoinebrossard.button.solenoid.SolenoidOffAction;
import csmv.antoinebrossard.button.solenoid.SolenoidSlideAction;
import me.yushust.inject.AbstractModule;

public class ButtonModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(ButtonAction.class)
                .asMap(Integer.class)
                .bind(1).to(RollerInwardsAction.class)
                .bind(2).to(RollerOutwardsAction.class)

                .bind(3).to(BallLockAction.class)

                .bind(4).to(RecordStartAction.class)
                .bind(5).to(RecordSaveAction.class)

                .bind(7).to(SolenoidGripAction.class)
                .bind(8).to(SolenoidSlideAction.class)
                .bind(9).to(SolenoidOffAction.class);
    }
}