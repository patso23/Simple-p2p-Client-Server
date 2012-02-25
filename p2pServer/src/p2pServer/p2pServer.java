package p2pServer;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

public class p2pServer extends UnicastRemoteObject implements Server {

	private static final long serialVersionUID = 1L;

	
	//hashmap that maps filenames to a list of integers(peerids)
	HashMap<String, List<Integer>> Registry = new HashMap<String, List<Integer>>();
	
	
	//constructor
	public p2pServer() throws RemoteException {
		super();		
	}

	
	@Override
	public synchronized boolean registry(Integer peerId, String filename) throws RemoteException {
		// add filenames and peerid to the registry (assign by return peerids to clients)
	 
		List<Integer> tmp = new ArrayList<Integer>();
		List<Integer> put = new ArrayList<Integer>();
		
		//check if file is already in registry
		tmp = Registry.get(filename);
		
		if(tmp == null || tmp.isEmpty())  //file not in registry
		{
			
			put.add(peerId);
			Registry.put(filename, put);
		}
		else //file exists
		{
			//test to see if peer is already listed, if not put in list and put in registry
			Iterator<Integer> i = tmp.listIterator();
			
			while(i.hasNext())
			{
				int x = i.next();
				
				if(x == peerId)  //filename and peer already exist return
				{
					return true;
				}
			}
			
			//filename exists, peer does not
			// map peerId to filename
			tmp.add(peerId);
			Registry.put(filename, tmp);
			
		}
	   
		return true;
	}
	
	
	/**
	 * method for clients to search the index for a file
	 */
	@Override
	public List search(String filename) throws RemoteException {
		// search the data structure for the file names.  return list of peers
		List<Integer> Peers = new ArrayList<Integer>();
		
		
		
		Peers = Registry.get(filename);
		
		return Peers;
	}
	
	
	/**
	 * Entry point for Index server
	 * create server instance, bind with registry, loop until exit
	 * 
	 * 
	 */
	public static void main(String args[]) throws Exception
	{
		//assign security manager
		if(System.getSecurityManager() == null)
		{
			System.setSecurityManager(new RMISecurityManager());
		}
		
		//create instance
		p2pServer server = new p2pServer();
		
		//bind in rmi registry
		try {
			Naming.bind("Server", server);
		 } catch (AlreadyBoundException e) {
			 // TODO Auto-generated catch block
			 System.out.println("\nError - Server is already bound.\n");
			 System.exit(0);
		 }
		
		
		System.out.println("Server running...");
		
		//main program loop
		int num=1;
		System.out.print("Enter 0 to exit: ");
		while(num!=0)
		{
			Scanner scan = new Scanner(System.in);
			num=scan.nextInt();
		}
		
		//unbind and exit
		Naming.unbind("Server");
		System.exit(0);	
	}
	
	
}
