package csmv.antoinebrossard.controller;

import csmv.antoinebrossard.annotation.Channel;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RollerController {

    private @Inject @Channel(Channel.Value.ROLLER_LEFT) PWMVictorSPX rollerLeft;
    private @Inject @Channel(Channel.Value.ROLLER_RIGHT) PWMVictorSPX rollerRight;

    private final static double MOTOR_SPEED = 1;

    public synchronized void rotateOutwards() {
        rollerLeft.set(MOTOR_SPEED);
        rollerRight.set(-MOTOR_SPEED);
    }

    public synchronized void rotateInwards() {
        rollerLeft.set(-MOTOR_SPEED);
        rollerRight.set(MOTOR_SPEED);
    }

    public synchronized void still() {
        rollerLeft.set(0);
        rollerRight.set(0);
    }
}