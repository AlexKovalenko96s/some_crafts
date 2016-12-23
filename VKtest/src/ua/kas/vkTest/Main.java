package ua.kas.vkTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;

public class Main {

	private static String access_token = "e10f5aa439430959976894ff81bc128fe90df1bdfbbede4b64134b404ffa8cb6183cfc81a457d38db15cb";

	public static String getNewMessage()
			throws ClientProtocolException, IOException, NoSuchAlgorithmException, URISyntaxException {
		// формируем строку запроса
		String url = "https://api.vk.com/method/" + "messages.get" + "?out=0" + "&access_token=" + access_token;
		String line = "";
		try {
			URL url2 = new URL(url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
			line = reader.readLine();
			reader.close();

		} catch (MalformedURLException e) {
			// ...
		} catch (IOException e) {
			// ...
		}
		return line;
	}

	public static void main(String[] args) throws ClientProtocolException, NoSuchAlgorithmException, IOException,
			URISyntaxException, InterruptedException {

		String oldMessage = getNewMessage();
		String newMessage;

		int i = 0;
		for (;;) {
			// System.out.println(oldMessage);
			// Запросы на сервер можно подавать раз в 3 секунды
			Thread.sleep(3000); // ждем три секунды
			if (i == 15000) { // Если прошло 45 000 сек (Время взято с запасом,
								// токен дается на день )
				access_token = "ce67abddac71efb8a17b8e3aad3b8ce7864e1aee500086de1768f5179cfe02b15a884cbf6594b2a085aad"; // Обновляем
																														// токен
				Thread.sleep(3000); // Запросы шлем только раз в три секунды
				i = 0;
			}
			// Здесь отработка
			newMessage = getNewMessage();
			if (!newMessage.equals(oldMessage)) {
				oldMessage = newMessage;
				System.out.println("VKNotifer" + "    " + "Получено новое сообщение");
				System.out.println(oldMessage.substring(oldMessage.indexOf("uid") + 5, oldMessage.indexOf("read") - 2)
						+ "  -  " + oldMessage.substring(oldMessage.indexOf("body") + 6, oldMessage.indexOf("},{")));
			}
			i++;
		}
	}

}
