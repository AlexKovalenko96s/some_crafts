package ua.kas.main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FileReader implements Reader {

	final static Logger LOGGER = Logger.getLogger(FileReader.class);

	@Override
	public String read() {
		PropertyConfigurator.configure(ClassLoader.getSystemResource("res/log4j.properties"));

		String message = "";

		try {
			URI url = ClassLoader.getSystemResource("res/Message.txt").toURI();
			LOGGER.info("File URL have been gotten");

			byte[] fileBytes = Files.readAllBytes(Paths.get(url));
			LOGGER.info("Byte array from file have been gotten");

			message = new String(fileBytes);
			LOGGER.info("Message from file have been gotten");

		} catch (URISyntaxException | IOException e) {
			LOGGER.error(e);
		}
		return message;
	}

}
