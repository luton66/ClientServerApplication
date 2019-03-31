package ClientServerApplication.ServerApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ServerApplication.class, args);

		Stream<Path> walk = Files.walk(Paths.get("C:\\Gradle"));

		Stream<Path> walk2 = walk.filter(Files::isRegularFile);

		List<String> result = walk2.map(Object::toString).collect(Collectors.toList());

		String x = "x";
	}

}
