import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MAIN {

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Socket socket = new Socket("192.168.5.74", 22);
		System.out.println(socket.isConnected());
		socket.getOutputStream().write("Hello".getBytes());
	}

}
