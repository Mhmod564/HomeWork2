import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class QuoteUDPServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port=8080;
		try(DatagramSocket serverSocket = new DatagramSocket(port)) {
			 System.out.println("Server is running on port " + port);
			 String [] quotes= {"Choose what chooses you.","Energy doesn’t lie.","Small steps, big changes.","Your vibe writes your story.","Stay close to what feels like sunshine."};
		     Random rand = new Random();
			 byte[] receiveBuffer = new byte[1024];
			 byte[] sendBuffer;
			 while (true) {
			     int index = rand.nextInt(quotes.length);
			 // Receive data from client
			 DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			 serverSocket.receive(receivePacket);
			 String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
			 System.out.println("Received: " + clientMessage);
			 if (clientMessage.equalsIgnoreCase("exit")) {
			 System.out.println("Server is shutting down...");
			 break;
			 }
			 // Send response to client
			 String serverResponse = quotes[index];
			 sendBuffer = serverResponse.getBytes();
			 InetAddress clientAddress = receivePacket.getAddress();
			 int clientPort = receivePacket.getPort();
			 DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress,clientPort);
			 serverSocket.send(sendPacket);
			 }
			 } catch (Exception e) {
			 e.printStackTrace();
			 }
			 
	}

}
