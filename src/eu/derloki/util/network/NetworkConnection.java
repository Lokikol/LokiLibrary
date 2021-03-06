package eu.derloki.util.network;

public abstract class NetworkConnection implements AutoCloseable{
	protected byte[] buffer;
	protected int bufferSize;
	
	public NetworkConnection(int bufferSize){
		this.bufferSize = bufferSize;
		newBuffer();
	}
	
	
	//public abstract void send(byte[] data, int offset, int length);
	public abstract ReceivedData receive();
	public abstract void close();
	
	protected void newBuffer(){
		buffer = new byte[bufferSize];
	}
}
