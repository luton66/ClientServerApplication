package ClientServerApplication.ClientApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private Socket socket;
    private PrintStream printStream;
    private Scanner responseScanner;


    private static final String QUIT_COMMAND = "quit";

    @Qualifier("lineScanner")
    private Scanner lineScanner;

    public ClientService(final Socket socket, final Scanner lineScanner) throws IOException {
        this.socket = socket;

        // Initialise the lineScanner to read data from the command line
        this.lineScanner = lineScanner;

        // Initialise the PrintStream used to send data to the server
        try {
            printStream = new PrintStream(socket.getOutputStream());
        }
        catch (IOException ioe) {
            LOGGER.error("Unable to establish an Output Stream with Socket: {}", socket.toString());
            //After logging error throw exception as fail-fast is acceptable in this circumstance.
            throw ioe;
        }

        try {
            responseScanner = new Scanner(socket.getInputStream());
        }
        catch (IOException ioe) {
            LOGGER.error("Unable to establish an Input Stream with Socket: {}", socket.toString());
            //After logging error throw exception as fail-fast is acceptable in this circumstance.
            throw ioe;
        }
    }

    public void startService() {
        while (true) {
            System.out.print("\n> ");
            String userCommand = lineScanner.nextLine().trim().toLowerCase();

            // if user types 'quit' then the command is still sent to the server to register disconnects, but
            // no further actions required by ClientService, and loop is broken.
            if (userCommand.equals(QUIT_COMMAND)) {
                printStream.println(userCommand);
                break;
            }
            else {
                printStream.println(userCommand);
                System.out.println(responseScanner.nextLine());
            }
        }
    }

}
