package ClientServerApplication.ClientApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Socket;
import java.util.Scanner;

@SpringBootApplication
public class ClientApplication {

	private Socket socket;
	@Qualifier("lineScanner")
	private Scanner lineScanner;

	@Autowired
	public ClientApplication(final Socket socket, final Scanner lineScanner) {
		this.socket = socket;
		this.lineScanner = lineScanner;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

		System.out.print("\n> ");

	}


}
