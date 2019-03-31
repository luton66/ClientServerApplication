package ClientServerApplication.ClientApplication;

import ClientServerApplication.ClientApplication.configuration.StartSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

	StartSequence start;

	@Autowired
	public ClientApplication(final StartSequence start) {
		this.start = start;
	}


	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);


	}


}
