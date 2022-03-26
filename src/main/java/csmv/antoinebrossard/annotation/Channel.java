package csmv.antoinebrossard.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface Channel {

    Value value();

    enum Value {

        WHEEL_FRONT_LEFT(1),
        WHEEL_FRONT_RIGHT(2),
        WHEEL_BACK_LEFT(3),
        WHEEL_BACK_RIGHT(4),

        ROLLER_LEFT(5),
        ROLLER_RIGHT(6),

        JOYSTICK(0);

        private final int number;

        Value(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }
}