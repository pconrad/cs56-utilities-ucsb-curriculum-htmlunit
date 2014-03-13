import java.io.*;
import java.net.*;
import java.util.*;

public class SendReceiveLecture {
	Lecture lect;
	int serverPort;
	public void setServerPort(int theServerPort) {
		this.serverPort = theServerPort;
	}
	ArrayList<ObjectOutputStream> clientOutputStreams;
	public class ClientHandler implements Runnable {
		ObjectInputStream inputStream;
		Socket sock;
		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				inputStream = new ObjectInputStream(sock.getInputStream());
			} catch(Exception ex) { ex.printStackTrace(); }
		}
		@Override
		public void run() {
			Lecture lect;
			try {
				while ((lect = (Lecture) inputStream.readObject()) != null) {
					System.out.println(lect);
					tellEveryone(lect);
				}
			} catch (Exception ex) { ex.printStackTrace(); }
		}
	}
	
	public static void main (String[] args) {
		new SendReceiveLecture().go();
	}
	
	public void go() {
		clientOutputStreams = new ArrayList<ObjectOutputStream>();
		try {
			ServerSocket serverSock = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = serverSock.accept();
				ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				outputStream.writeObject(lect);
				clientOutputStreams.add(outputStream);
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("got a connection");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void tellEveryone(Lecture theLect) {
		Iterator<ObjectOutputStream> it = clientOutputStreams.iterator();
		while(it.hasNext()) {
			try {
				ObjectOutputStream outputStream = (ObjectOutputStream) it.next();
				outputStream.writeObject(lect);
				outputStream.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

