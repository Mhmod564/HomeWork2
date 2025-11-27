import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorTCPServer {

	public static void main(String[] args) {
		try (ServerSocket server=new ServerSocket(9090)){
			System.out.println("Server is listening to 9090");
			Socket s=server.accept();
			System.out.println("client connected");
			BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out=new PrintWriter(s.getOutputStream(),true);
			
			String[]operators= {"+","-","/","*"};
			char operator;
			String inputLine;
			
			while( (inputLine =in.readLine())!=null) {
				 if (inputLine.equalsIgnoreCase("close")){
					 System.out.println("Server is shutting down...");
					 break;
					 }
				System.out.println("Received expression: "+inputLine);
				String newUserInput=inputLine.replace(" ","");
				for(String op:operators) {
					int newInputIndex=newUserInput.indexOf(op);
					int userInputIndex=inputLine.indexOf(op);
					if(userInputIndex!=-1) {
					if(inputLine.charAt(userInputIndex-1)==' '&& inputLine.charAt(userInputIndex+1)==' ') {
						int sumSpace=0;
						for(int i=0;i<inputLine.length();i++) {
							if(inputLine.charAt(i)==' ') {
								sumSpace++;
							}
						}
						if(sumSpace==2) {
							operator=newUserInput.charAt(newInputIndex);
							int num1=Integer.parseInt(newUserInput.substring(0,newInputIndex));
							int num2=Integer.parseInt(newUserInput.substring(newInputIndex+1));
							if(num2==0)out.println("Error: Division by zero.");
							else {
							switch(operator) {
							case '+':
								out.println(num1+num2);
								break;
							case '-':
								out.println(num1-num2);
								break;
							case '/':
								out.println(num1/num2);
								break;
							case '*':
								out.println(num1*num2);
								break;
							}
							
							}
						}else out.println("Error: Invalid expression.yyyyyyyyy");
					}else out.println("Error: Invalid expression.xxxx");
					}
				}
			}
			System.out.println("Client requested to close connection.");
			System.out.println("Client disconnected.");
			server.close();
			in.close();
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
