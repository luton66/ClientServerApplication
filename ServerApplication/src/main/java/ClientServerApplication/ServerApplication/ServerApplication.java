package ClientServerApplication.ServerApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ServerApplication.class, args);

//		Want to investigate this. The use of Files.walk seems to be a recursive action, that will
//		get the details of all files in all folders.
//		Stream<Path> walk = Files.walk(Paths.get("C:\\Gradle"));
//		Stream<Path> walk2 = walk.filter(Files::isRegularFile);
//		List<String> result = walk2.map(Object::toString).collect(Collectors.toList());

		File directory = new File(".");

		System.out.println("Current Directory : " + directory.getCanonicalFile().getName() + "\n");

		List<File> files = Arrays.asList(directory.listFiles());

		System.out.println("Simply listing the files as files.toString will result in");
		files.forEach(file -> System.out.println(file.toString()));

		System.out.println("\nHowever, if we want to remove the .\\ Then we need to use the CanonicalFile details, as such.");
		files.forEach(file -> {
			try {
				System.out.println(file.getCanonicalFile().getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		String x = "x";
	}

}
