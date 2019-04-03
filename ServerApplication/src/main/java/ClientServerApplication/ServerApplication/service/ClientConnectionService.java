package ClientServerApplication.ServerApplication.service;

import ClientServerApplication.ServerApplication.handler.ClientRequestsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class responsible for handling connections between each client and assigning each to a new thread.
 *
 * @author Leigh Edwards
 */
@Service
public class ClientConnectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientConnectionService.class);

    private ServerSocket serverSocket;

    /**
     * Basic constructor for ClientConnectionService
     *
     * @param serverSocket a configured Server Socket
     */
    @Autowired
    public ClientConnectionService(final ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Main class for processing connection requests from clients, and assigning to new threads.
     */
    public void startServer() {
        List<String> clientList = Collections.synchronizedList(new ArrayList<String>());

        while (true) {
            LOGGER.info("Server Starting on port {}", serverSocket.getLocalPort());

            try {
                Socket clientSocket = serverSocket.accept();
                ClientRequestsHandler handler = new ClientRequestsHandler(clientSocket, clientList);
                Thread handlerThread = new Thread(handler);

                handlerThread.start();
            } catch (IOException e) {
                LOGGER.error("Error occurred when receiving connection on ServerSocket. {}", e.toString());
            }
        }
    }
}
