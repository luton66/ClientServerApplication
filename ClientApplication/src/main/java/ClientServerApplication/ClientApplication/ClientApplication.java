package ClientServerApplication.ClientApplication;

import ClientServerApplication.ClientApplication.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

	private static ClientService clientService;

	@Autowired
	public ClientApplication(final ClientService clientService) {
		this.clientService = clientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

		clientService.startService();
	}
}
