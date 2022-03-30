package csmv.antoinebrossard.util;

import csmv.antoinebrossard.annotation.Channel;
import edu.wpi.first.hal.util.AllocationException;
import me.yushust.inject.Binder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public final class ChannelBinder {

    private ChannelBinder() {
        throw new UnsupportedOperationException("Cannot instantiate this class!");
    }

    public static <T> void bind(
            Class<T> clazz,
            Binder binder
    )
            throws
            NoSuchMethodException
    {
        Constructor<T> constructor = clazz.getConstructor(Integer.TYPE);

        for (Channel.Value value : Channel.Value.values()) {
            if (value.getTargetClass() != clazz) {
                continue;
            }

            try {
                T instance = constructor.newInstance(value.getNumber());

                binder
                        .bind(clazz)
                        .qualified(new ChannelImpl(value))
                        .toInstance(instance);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @SuppressWarnings("ClassExplicitlyAnnotation")
    private static class ChannelImpl implements Channel {

        private final Value value;
        private final int hashCode;

        private ChannelImpl(Value value) {
            this.value = value;
            this.hashCode = (127 * "value".hashCode()) ^ value.hashCode();
        }

        @Override
        public Value value() {
            return value;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Channel.class;
        }

        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Channel)) return false;
            return value.equals(((Channel) obj).value());
        }
    }
}