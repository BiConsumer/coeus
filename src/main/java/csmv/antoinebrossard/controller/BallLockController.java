package csmv.antoinebrossard.controller;

import csmv.antoinebrossard.annotation.Port;
import edu.wpi.first.wpilibj.Servo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BallLockController {

    private final Servo servo;
    private boolean locking = true;

    @Inject BallLockController(@Port(Port.Value.BALL_LOCK) Servo servoInject) {
        servo = servoInject;
        servo.set(0);
    }

    public void toggle() {
        locking = !locking;

        if (locking) {
            servo.setAngle(0);
        } else {
            servo.setAngle(90);
        }
    }
}