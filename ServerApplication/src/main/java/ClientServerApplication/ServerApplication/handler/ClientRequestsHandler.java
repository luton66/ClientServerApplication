package ClientServerApplication.ServerApplication.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.util.List;

public class ClientRequestsHandler implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientRequestsHandler.class);

    private static final String START_DIRECTORY = ".";
    private static final String CLIENT_DIVIDER = "-";

    private Socket clientSocket;
    private List<String> clientList;

    public ClientRequestsHandler(final Socket clientSocket, final List<String> clientList) {
        this.clientSocket = clientSocket;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        String clientName = clientSocket.getInetAddress().getHostName() + CLIENT_DIVIDER + clientSocket.getPort();

        clientList.add(clientName);
        LOGGER.info("Socket connection established for client: {}", clientName);


    }
}

//		File directory = new File(START_DIRECTORY);
//
//		System.out.println("Current Directory : " + directory.getCanonicalFile().getName() + "\n");
//
//		List<File> files = Arrays.asList(directory.listFiles());
//
//		System.out.println("Simply listing the files as files.toString will result in");
//		files.forEach(file -> System.out.println(file.toString()));
//
//		System.out.println("\nHowever, if we want to remove the .\\ Then we need to use the CanonicalFile details, as such.");
//		files.forEach(file -> {
//			try {
//				System.out.println(file.getCanonicalFile().getName());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
//
//		String x = "x";
//	}
//
//}
