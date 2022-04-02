package csmv.antoinebrossard.controller;

import csmv.antoinebrossard.annotation.Port;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RollerController {

    private @Inject @Port(Port.Value.ROLLER_LEFT) PWMVictorSPX rollerLeft;
    private @Inject @Port(Port.Value.ROLLER_RIGHT) PWMVictorSPX rollerRight;

    private final static double MOTOR_SPEED = 1;
    private boolean activated = false;

    public synchronized void rotateOutwards() {
        activated = true;
        rollerLeft.set(MOTOR_SPEED);
        rollerRight.set(-MOTOR_SPEED);
    }

    public synchronized void rotateInwards() {
        activated = true;
        rollerLeft.set(-MOTOR_SPEED);
        rollerRight.set(MOTOR_SPEED);
    }

    public synchronized void still() {
        activated = false;
        rollerLeft.set(0);
        rollerRight.set(0);
    }

    public boolean isActivated() {
        return activated;
    }
}