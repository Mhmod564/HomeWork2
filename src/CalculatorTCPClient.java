import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CalculatorTCPClient {

	public static void main(String[] args) {
		try(Socket socket=new Socket("localhost",9090)){
			System.out.println("Connected to server");
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			System.out.println("Enter expression (num op num or) 'close' to exit: ");
			while((userInput=stdIn.readLine())!=null) {
				out.println(userInput);
				 if (userInput.equalsIgnoreCase("close")){
					 System.out.println("Client is shutting down...");
					 break;
					 }
				System.out.println(userInput+" = "+in.readLine());
				System.out.println("Enter expression (num op num or) 'close' to exit:");
			}
			in.close();
			out.close();
 
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();

		}
		

	}

}
