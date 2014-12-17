package eu.derloki.util.network;

import java.net.InetAddress;

public class ReceivedData {
	public byte[] data = new byte[0];
	public int length = 0;
	public InetAddress address = null;
	public int port = 0;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Received Data from: %s:%d. - Data: %s",address.getHostAddress(),port,new String(data,0,length));
	}
}
