package csmv.antoinebrossard.button.solenoid;

import csmv.antoinebrossard.button.ButtonAction;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import javax.inject.Inject;
import javax.inject.Named;

public class SolenoidOffAction implements ButtonAction {

    private @Inject @Named("grip") DoubleSolenoid gripSolenoid;
    private @Inject @Named("slide") DoubleSolenoid slideSolenoid;

    @Override
    public void pressed() {
        System.out.println("TURNING OFF SOLENOIDS");
        gripSolenoid.set(DoubleSolenoid.Value.kOff);
        slideSolenoid.set(DoubleSolenoid.Value.kOff);
    }
}