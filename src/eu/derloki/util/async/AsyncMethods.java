package eu.derloki.util.async;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

/**
 * Container class for callable and callbacks
 * @author Loki
 *
 * @param <T> any Object
 */
public class AsyncMethods<T> {

	//callable and callback variables
	private Callable<T> callable;
	private BiConsumer<Exception,T> callback;
	
	/**
	 * create the Object with a callable and a callback
	 * @param callable - Callable
	 * @param callback - BiConsumer
	 */
	public AsyncMethods(Callable<T> callable, BiConsumer<Exception,T> callback){
		this.callable = callable;
		this.callback = callback;
	}
	
	/**
	 * Get the callable
	 * @return callable
	 */
	public Callable<T> getCallable(){
		return callable;
	}
	
	/**
	 * Get the callback
	 * @return callback
	 */
	public BiConsumer<Exception,T> getCallback(){
		return callback;
	}
	
}
