package ClientServerApplication.ServerApplication.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class ServerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConfiguration.class);
    private final int SOCKET_PORT = 1379;

    @Bean
    protected ServerSocket socket() throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
            return serverSocket;
        } catch (IOException ioe) {
            LOGGER.error("Failed to establish Server Socket {}", SOCKET_PORT);

            //After logging error details, throw error as fail-fast is suitable response to failing socket configuration
            throw ioe;
        }
    }


}
