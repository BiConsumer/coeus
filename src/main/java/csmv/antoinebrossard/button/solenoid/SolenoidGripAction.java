package csmv.antoinebrossard.button.solenoid;

import csmv.antoinebrossard.button.ButtonAction;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import javax.inject.Inject;
import javax.inject.Named;

public class SolenoidGripAction implements ButtonAction {

    private @Inject @Named("grip") DoubleSolenoid gripSolenoid;

    @Override
    public void pressed() {
        System.out.println("GRIP SOLENOID");
        if (gripSolenoid.get() == DoubleSolenoid.Value.kOff) {
            gripSolenoid.set(DoubleSolenoid.Value.kForward);
        }
        gripSolenoid.toggle();
    }
}