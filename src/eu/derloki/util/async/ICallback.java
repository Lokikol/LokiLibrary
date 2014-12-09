package eu.derloki.util.async;

/**
 * ICallback - Callback Interface, allowing Lambdas
 * @author Loki
 *
 * @param <T>
 */
@FunctionalInterface
public interface ICallback<T> {
	/**
	 * Callback with any Object as parameter
	 * @param t any Object
	 */
	public void callback(T t);
}
