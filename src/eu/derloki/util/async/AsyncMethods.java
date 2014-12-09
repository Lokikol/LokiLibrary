package eu.derloki.util.async;

import java.util.concurrent.Callable;

/**
 * Container class for callable and callbacks
 * @author Loki
 *
 * @param <T> any Object
 */
public class AsyncMethods<T> {

	//callable and callback variables
	private Callable<T> callable;
	private ICallback<T> callback;
	
	/**
	 * create the Object with a callable and a callback
	 * @param callable - Callable
	 * @param callback - ICallback
	 */
	public AsyncMethods(Callable<T> callable, ICallback<T> callback){
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
	public ICallback<T> getCallback(){
		return callback;
	}
	
}
