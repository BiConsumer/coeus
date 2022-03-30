package csmv.antoinebrossard.annotation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface Channel {

    Value value();

    enum Value {

        WHEEL_FRONT_LEFT(1, PWMVictorSPX.class),
        WHEEL_FRONT_RIGHT(2, PWMVictorSPX.class),
        WHEEL_BACK_LEFT(3, PWMVictorSPX.class),
        WHEEL_BACK_RIGHT(4, PWMVictorSPX.class),

        ROLLER_LEFT(5, PWMVictorSPX.class),
        ROLLER_RIGHT(6, PWMVictorSPX.class),

        BALL_LOCK(7, Servo.class),

        JOYSTICK(0, Joystick.class);

        private final int number;
        private final Class<?> clazz;

        Value(int number, Class<?> clazz) {
            this.number = number;
            this.clazz = clazz;
        }

        public int getNumber() {
            return number;
        }

        public Class<?> getTargetClass() {
            return clazz;
        }
    }
}