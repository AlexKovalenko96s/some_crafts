package ua.kas.main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args)
			throws IOException, URISyntaxException, AWTException, InterruptedException, NoSuchAlgorithmException {
		// Создадим раскрывающееся меню
		PopupMenu popup = new PopupMenu();
		// Создадим элемент меню
		MenuItem exitItem = new MenuItem("Выход");
		// Добавим для него обработчик
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// Добавим пункт в меню
		popup.add(exitItem);
		SystemTray systemTray = SystemTray.getSystemTray();
		// получим картинку
		Image image = Toolkit.getDefaultToolkit().getImage("vk_icon.png");
		TrayIcon trayIcon = new TrayIcon(image, "VKNotifer", popup);
		trayIcon.setImageAutoSize(true);
		// добавим иконку в трей
		systemTray.add(trayIcon);
		trayIcon.displayMessage("VKNotifer", "Соединяемся с сервером", TrayIcon.MessageType.INFO);
		// Создадим экземпляр класса ВКапи
		VKapi vkAPI = new VKapi();
		// Получим токен
		vkAPI.setConnection();
		trayIcon.displayMessage("VKNotifer", "Соединение установлено", TrayIcon.MessageType.INFO);
		// Бескоечный цикл
		String oldMessage = vkAPI.getNewMessage();
		String newMessage;
		int i = 0;
		for (;;) {
			// Запросы на сервер можно подавать раз в 3 секунды
			Thread.sleep(3000); // ждем три секунды
			if (i == 15000) { // Если прошло 45 000 сек (Время взято с запасом,
								// токен дается на день )
				vkAPI.setConnection(); // Обновляем токен
				Thread.sleep(3000); // Запросы шлем только раз в три секунды
				i = 0;
			}
			// Здесь отработка
			newMessage = vkAPI.getNewMessage();
			if (!newMessage.equals(oldMessage)) {
				oldMessage = newMessage;
				trayIcon.displayMessage("VKNotifer", "Получено новое сообщение", TrayIcon.MessageType.INFO);
			}
			i++;
		}
	}

}
