/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Controllers.FrontEndController;
import Entities.Notification;
import Services.NotificationsCrud;
import Entities.SimpleUser;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author br4in
 */
public class RunnableDemo implements Runnable {

	private Thread t;
	public static int indicator;

	public RunnableDemo() {
		this.indicator = 1;
	}

	public void run() {
		try {
			while (indicator == 1) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						List<Notification> NewNotifications = NotificationsCrud.GetMyNewNotifications(SimpleUser.current_user.getUsername());
						for (int i = 0; i < NewNotifications.size(); i++) {
							FrontEndController.ShowNotification(NewNotifications.get(i).getText());
							NotificationsCrud.MakeNotificationSeen(NewNotifications.get(i).getId());
						}
					}
				});
				Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			//System.out.println("Thread " + threadName + " interrupted.");
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, "notif");
			t.start();
		}
	}
}
