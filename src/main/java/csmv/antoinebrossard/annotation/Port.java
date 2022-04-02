package csmv.antoinebrossard.annotation;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface Port {

    Value value();

    enum Value {

        WHEEL_FRONT_LEFT(PWMVictorSPX.class, 1),
        WHEEL_FRONT_RIGHT(PWMVictorSPX.class, 2),
        WHEEL_BACK_LEFT(PWMVictorSPX.class, 3),
        WHEEL_BACK_RIGHT(PWMVictorSPX.class, 4),

        ROLLER_LEFT(PWMVictorSPX.class, 5),
        ROLLER_RIGHT(PWMVictorSPX.class, 6),

        BALL_LOCK(Servo.class, 7),

        TEAM_SWITCH(DigitalInput.class, 9),

        JOYSTICK(Joystick.class, 0);

        private final Class<?> clazz;
        private final int port;

        Value(Class<?> clazz, int port) {
            this.clazz = clazz;
            this.port = port;
        }

        public Class<?> getTargetClass() {
            return clazz;
        }

        public int getPort() {
            return port;
        }
    }
}