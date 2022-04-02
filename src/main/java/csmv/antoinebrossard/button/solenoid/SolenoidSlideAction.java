package csmv.antoinebrossard.button.solenoid;

import csmv.antoinebrossard.button.ButtonAction;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import javax.inject.Inject;
import javax.inject.Named;

public class SolenoidSlideAction implements ButtonAction {

    private @Inject @Named("slide") DoubleSolenoid slideSolenoid;

    @Override
    public void pressed() {
        System.out.println("SLIDE SOLENOID");
        if (slideSolenoid.get() == DoubleSolenoid.Value.kOff) {
            slideSolenoid.set(DoubleSolenoid.Value.kForward);
        }
        slideSolenoid.toggle();
    }
}