package client;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.*;

//interface for Clientserver
public interface ClientServer extends java.rmi.Remote {

	//method to return file from client
	public byte[] obtain(String file, int peer, String instanceName) 
		throws RemoteException, FileNotFoundException, IOException;
	
	
}
