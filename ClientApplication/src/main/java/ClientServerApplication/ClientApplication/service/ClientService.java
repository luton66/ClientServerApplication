package ClientServerApplication.ClientApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Service class to handle the inputting of requests from a user, and presenting the following responses.
 *
 * @author Leigh Edwards
 */
@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private Socket socket;
    private PrintStream printStream;
    private Scanner lineScanner;
    private Scanner responseScanner;

    private static final String QUIT_COMMAND = "quit";
    private static final String DELIMETER = ";";


    /**
     * Main Autowired consructor for Client Service. Configuration for each element can be found in
     * ClientConfiguration class.
     *
     * @param socket the connection Socket for communication with Server
     * @param lineScanner a Scanner responsible for reading responses from command line
     * @param printStream a PrintStream to send requests to the servier
     * @param responseScanner a Scanner responsible for reading responses from the server.
     * @throws IOException
     */
    public ClientService(final Socket socket, @Qualifier("lineScanner") final Scanner lineScanner,
                         final PrintStream printStream, @Qualifier("responseScanner") final Scanner responseScanner) throws IOException {
        this.socket = socket;
        this.lineScanner = lineScanner;
        this.printStream = printStream;
        this.responseScanner = responseScanner;
    }

    /**
     * The main method for establishing connection with a server and dealing with commands.
     */
    public void startService() {
        LOGGER.info("Service started.");

        while (true) {
            System.out.print("\n> ");
            String userCommand = lineScanner.nextLine().trim();

            // if user types 'quit' then the command is still sent to the server to register disconnects, but
            // no further actions required by ClientService, and loop is broken.
            if (userCommand.toLowerCase().equals(QUIT_COMMAND)) {
                LOGGER.info("Quit command registered for socket {}", socket.toString());
                printStream.println(userCommand);
                break;
            }
            else {
                // All other commands are dealt with server-side
                printStream.println(userCommand);
                String responseString = responseScanner.nextLine();
                Arrays.asList(responseString.split(DELIMETER)).forEach(System.out::println);
            }
        }
    }

}
