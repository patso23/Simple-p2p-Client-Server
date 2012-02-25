package p2pServer;
import java.rmi.*;
import java.util.List;

//interface for index server
public interface Server extends java.rmi.Remote {

	
	//registry method
	public boolean registry(Integer peerId, String filename) 
		throws RemoteException; 

	//search method
	public List search(String filename)
		throws RemoteException;
	
}
