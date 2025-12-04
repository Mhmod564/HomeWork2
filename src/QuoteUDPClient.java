import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class QuoteUDPClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);
		try(DatagramSocket clientSocket = new DatagramSocket()) {
			 InetAddress serverAddress = InetAddress.getByName("localhost");
			 int serverPort = 8080;
			 String clientMessage;
			 byte[] sendBuffer;
			 byte[] receiveBuffer = new byte[1024];
			 System.out.println("Enter 'GET' to receive a quote:");
			 while (true) {
				 while(true) {			 
					 clientMessage = scanner.nextLine();
					 if(clientMessage.equalsIgnoreCase("get")||clientMessage.equalsIgnoreCase("exit")) {
						 break;
					 }else System.out.println("Please enter 'GET' to receive a quote:");
				 }
			 
			 sendBuffer = clientMessage.getBytes();
			 DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress,serverPort);
			 clientSocket.send(sendPacket);
			 	if (clientMessage.equalsIgnoreCase("exit")) {
				 	System.out.println("Client is shutting down...");
				 	break;
			 	}
			 DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			 clientSocket.receive(receivePacket);
			 String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
			 System.out.println("Quote received: " + serverResponse);

			}
			 } catch (Exception e) {
			 e.printStackTrace();
			 }
			finally{
				 scanner.close();
			
			}
		}
			 
	}


