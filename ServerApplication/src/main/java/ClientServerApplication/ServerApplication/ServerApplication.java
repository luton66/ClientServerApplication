package ClientServerApplication.ServerApplication;

import ClientServerApplication.ServerApplication.service.ClientConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Main Class for ServerApplication.
 *
 * @author Leigh Edwards
 */
@SpringBootApplication
public class ServerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerApplication.class);

	private static ClientConnectionService connectionService;

	/**
	 * Basic constructor to take in the ClientConnectionService class.
	 *
	 * @param service the ClientConnectionService
	 */
	@Autowired
	public ServerApplication (final ClientConnectionService service) {
		this.connectionService = service;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ServerApplication.class, args);
		LOGGER.info("application starting...");

		connectionService.startServer();
	}
}