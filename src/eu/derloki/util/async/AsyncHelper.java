package eu.derloki.util.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * AsyncHelper
 * @author Loki
 *
 * A Helper Class to execute code async. and execute a callback
 * afterwards.
 */
public class AsyncHelper {

	/**
	 * execute - Executes the callable async. and afterwards executes the callback with the result
	 * of callable as parameter.
	 * @param executor - Executor to execute
	 * @param callable - async. calls
	 * @param callback - callback after async
	 */
	public static <T> void execute(ExecutorService executor,Callable<T> callable, BiConsumer<Exception, T> callback){
		//create AsyncMethods Object from callable and callback
		AsyncMethods<T> methods = new AsyncMethods<T>(callable, callback);
		//execute everything
		execute(executor, methods);
	}
	
	public static <T> void execute(ExecutorService executor,Callable<T> callable, BiConsumer<Exception, T> callback, int time, TimeUnit timeunit){
		//create AsyncMethods Object from callable and callback
		AsyncMethods<T> methods = new AsyncMethods<T>(callable, callback);
		//execute everything
		execute(executor, methods,time,timeunit);
	}
	
	/**
	 * execute - Executes the callable within the methods async. and afterwards executes the callback within the methods
	 * with the result of callable as parameter.
	 * @param executor - Executor to execute
	 * @param methods - contains callable and callback methods
	 */
	public static <T> void execute(ExecutorService executor, AsyncMethods<T> methods){
		//submit a runnable task using lambdas to the executor
		executor.submit(()->{
			//create a future object and execute the callable part of methods async.
			Future<T> future = executor.submit(methods.getCallable());
			
			//create an instance of T
			T t = null;
			try{
				//get the result of the async. Method
				t = future.get();
				methods.getCallback().accept(null,t);		
			}catch(Exception ex){
				methods.getCallback().accept(ex,null);
			}
			
			//execute the callback with the result as parameter
		});
	}
	
	public static <T> void execute(ExecutorService executor, AsyncMethods<T> methods, int time, TimeUnit timeunit){
		//submit a runnable task using lambdas to the executor
		executor.submit(()->{
			//create a future object and execute the callable part of methods async.
			Future<T> future = executor.submit(methods.getCallable());
			
			//create an instance of T
			T t = null;
			try{
				//get the result of the async. Method
				t = future.get(time,timeunit);
				methods.getCallback().accept(null,t);		
			}catch(Exception ex){
				methods.getCallback().accept(ex,null);
			}
			
			//execute the callback with the result as parameter
		});
	}
	
	
	
}
