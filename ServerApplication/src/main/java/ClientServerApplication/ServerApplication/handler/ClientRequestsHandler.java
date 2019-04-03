package ClientServerApplication.ServerApplication.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientRequestsHandler implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientRequestsHandler.class);

    private static final String START_DIRECTORY = ".";
    private static final String CLIENT_DIVIDER = "-";
    private static final String DELIMETER = ";";

    private Socket clientSocket;
    private List<String> clientList;
    private File currentDirectory;

    public ClientRequestsHandler(final Socket clientSocket, final List<String> clientList) {
        this.clientSocket = clientSocket;
        this.clientList = clientList;

        this.currentDirectory = new File(START_DIRECTORY);
    }

    @Override
    public void run() {
        System.out.println(currentDirectory.listFiles());

        String clientName = clientSocket.getInetAddress().getHostName() + CLIENT_DIVIDER + clientSocket.getPort();
        clientList.add(clientName);
        boolean quitCommandReceived = false;
        Scanner inputScanner = null;
        PrintStream printStream = null;

        try {
            inputScanner = new Scanner(clientSocket.getInputStream());
            printStream = new PrintStream(clientSocket.getOutputStream());
        }
        catch(IOException ioe) {
            LOGGER.error("ERROR!");
        }

        LOGGER.info("Socket connection established for client: {}", clientName);

        while (!quitCommandReceived) {
            String[] inputCommand = inputScanner.nextLine().split(" ");

            switch (inputCommand[0]) {
            case "ls":
                printStream.println(this.listFiles());
                break;
            case "cd":
                printStream.println(this.changeDirectory(inputCommand));
                break;
            case "quit":
                clientList.remove(clientName);
                quitCommandReceived = true;
            }
        }
    }

    private String listFiles() {
        List<File> files = Arrays.asList(currentDirectory.listFiles());

        if (!CollectionUtils.isEmpty(files)) {
            List<String> fileList = files.stream().map(file -> {
                try {
                    return file.getCanonicalFile().getName();
                } catch (IOException ioe) {
                    LOGGER.error("Error in retrieving filenames of directory.");
                    return null;
                }
            }).collect(Collectors.toList());

            return String.join(DELIMETER, fileList);
        }

        return null;
    }

    private String changeDirectory(String[] inputCommand) {
        if (inputCommand.length == 1) {
            return "Change Directory command requires 1 argument.";
        }

        File file = new File(currentDirectory + "/" + inputCommand[1]);
        if (file.exists()) {
            currentDirectory = file;
            try {
                return currentDirectory.getCanonicalFile().getName();
            }
            catch (IOException ioe) {
                LOGGER.error("Error retrieving canonical filename for directory");
                return "Error received changing directory";
            }
        }
        else {
            return "Directory requested - " + inputCommand[1] + " - does not exist";
        }
    }

}
