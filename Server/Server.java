import java.io.* ;
import java.net.* ;
import java.util.* ;
import java.util.concurrent.ConcurrentHashMap;

public final class Server 
{
	public static ConcurrentHashMap<String, Book> books;

	public static void main(String argv[]) throws Exception 
	{
		// Get the port number from the command line.
		int port;
		if (argv.length > 0)
			port = Integer.valueOf(argv[0]);
		else
			port = 5555;
		ConcurrentHashMap<String,Book> books = new ConcurrentHashMap<String,Book>();
		// System.out.println(sss.charAt(0));
		// Establish the listen socket.
		ServerSocket socket = new ServerSocket(port);
		
		// Process HTTP service requests in an infinite loop.
		System.out.println("Starting server");
		while (true) 
		{
			// Listen for a TCP connection request.
			Socket connection = socket.accept();
			
			// Construct an object to process the HTTP request message.
			HttpRequest request = new HttpRequest(connection,books);
			
			// Create a new thread to process the request.
			Thread thread = new Thread(request);
			
			// Start the thread.
			thread.start();
		}
    }
}
