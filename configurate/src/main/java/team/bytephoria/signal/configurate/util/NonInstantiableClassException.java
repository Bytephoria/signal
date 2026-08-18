package team.bytephoria.signal.configurate.util;

public final class NonInstantiableClassException extends UnsupportedOperationException {

    public NonInstantiableClassException() {
        super("This class cannot be instantiated.");
    }

}
