package ClientServerApplication.ClientApplication.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Configuration class for ClientApplication
 *
 * @author Leigh Edwards
 */
@Configuration
public class ClientConfiguration {

    //Establish constants for socket configuration
    private final String IP_ADDRESS = "127.0.0.1";
    private final int SOCKET_PORT = 1379;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientConfiguration.class);

    /**
     * Configure Socket with pre-stated constant details
     *
     * @return configured Socket
     * @throws IOException
     */
    @Bean
    public Socket configureSocket() throws IOException {
        try {
            Socket socket = new Socket(IP_ADDRESS, SOCKET_PORT);

            return socket;
        }
        catch (IOException ioe) {
            LOGGER.error("Unable to establish new Client Socket {}", SOCKET_PORT, ioe.getCause());
            //After logging error throw exception as fail-fast is acceptable in this circumstance.
            throw ioe;
        }
    }

    /**
     * Configure the PrintStream to be used to output commands to the Server
     *
     * @param socket the configured Socket to connect to the server
     * @return a bean of a PrintStream
     * @throws IOException
     */
    @Bean
    public PrintStream configurePrintStream(final Socket socket) throws IOException {
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            return printStream;
        } catch (IOException ioe) {
            LOGGER.error("Unable to establish an Output Stream with Socket: {}", socket.toString());
            //After logging error throw exception as fail-fast is acceptable in this circumstance.
            throw ioe;
        }
    }

    /**
     * Configure a Scanner object to be used to read responses from the Server
     *
     * @param socket the configured Socket to connect to the server
     * @return a bean of a Scanner qualified as a response scanner
     * @throws IOException
     */
    @Qualifier("responseScanner")
    @Bean
    public Scanner configureResponseScanner(final Socket socket) throws IOException {
        try {
            Scanner responseScanner = new Scanner(socket.getInputStream());
            return responseScanner;
        } catch (IOException ioe) {
            LOGGER.error("Unable to establish an Input Stream with Socket: {}", socket.toString());
            //After logging error throw exception as fail-fast is acceptable in this circumstance.
            throw ioe;
        }
    }

    /**
     * Configure Scanner object to be used to read in commands entered in to the command line by the user
     *
     * @return a bean of a Scanner qualified as a line scanner
     */
    @Qualifier("lineScanner")
    @Bean
    public Scanner configureClientScanner() {
        //Set up new scanner to read in from command line
        Scanner scanner = new Scanner (System.in);

        return scanner;
    }

}
