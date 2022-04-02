package csmv.antoinebrossard.util;

import csmv.antoinebrossard.annotation.Port;
import me.yushust.inject.Binder;
import me.yushust.inject.key.TypeReference;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public final class PortBinder {

    private PortBinder() {
        throw new UnsupportedOperationException("Cannot instantiate this class!");
    }

    public static void bind(Binder binder) throws NoSuchMethodException {
        for (Port.Value value : Port.Value.values()) {
            Constructor<?> constructor = value.getTargetClass().getConstructor(Integer.TYPE);

            try {
                Object instance = constructor.newInstance(value.getPort());

                binder
                        .bind(TypeReference.of(value.getTargetClass()))
                        .qualified(new PortImpl(value))
                        .toInstance(instance);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @SuppressWarnings("ClassExplicitlyAnnotation")
    private static class PortImpl implements Port {

        private final Value value;
        private final int hashCode;

        private PortImpl(Value value) {
            this.value = value;
            this.hashCode = (127 * "value".hashCode()) ^ value.hashCode();
        }

        @Override
        public Value value() {
            return value;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Port.class;
        }

        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Port)) return false;
            return value.equals(((Port) obj).value());
        }
    }
}