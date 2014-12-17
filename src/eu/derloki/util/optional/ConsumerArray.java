package eu.derloki.util.optional;

import java.util.Objects;

@FunctionalInterface
public interface ConsumerArray<T> {

	public void accept(T[] array);
	
	default ConsumerArray<T> andThen(ConsumerArray<? super T> after) {
        Objects.requireNonNull(after);
        return (T[] t) -> { accept(t); after.accept(t); };
    }
	
}
