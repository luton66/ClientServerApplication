package ClientServerApplication.ClientApplication.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
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

            throw ioe;
        }
    }

    /**
     * Configure command line scanner to take in input from command line
     *
     * @return a scanner
     */
    @Qualifier("lineScanner")
    @Bean
    public Scanner configureClientScanner() {
        //Set up new scanner to read in from command line
        Scanner scanner = new Scanner (System.in);

        return scanner;
    }

}
