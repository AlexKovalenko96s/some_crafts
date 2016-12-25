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

	private static String access_token = "0474deffb23115dd2660cf27656b52b1f149b78cae84e3d0131e6dd5c1363189c3dbda443fdce5bc1e99b";

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
			System.out.println(oldMessage);
			// Запросы на сервер можно подавать раз в 3 секунды
			Thread.sleep(3000); // ждем три секунды
			if (i == 15000) { // Если прошло 45 000 сек (Время взято с запасом,
								// токен дается на день )
				access_token = "bda0a59dcac157e7e8697f139be326cb3656d4323491729062dba3062ccbe06e03acc51533c7c449d3791"; // Обновляем
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
