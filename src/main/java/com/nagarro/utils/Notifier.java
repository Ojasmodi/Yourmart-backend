package com.nagarro.utils;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.models.Admin;
import com.nagarro.models.Product;
import com.nagarro.services.AdminService;
import com.nagarro.services.ProductService;

@Service
public class Notifier {

	@Autowired
	ProductService productService;

	@Autowired
	AdminService adminService;

	@Autowired
	private SendMail sendMail;

	public void setTimerForSendingNotifications() throws Exception {
		System.out.println("heelllo");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("right dfgdf time");
				LocalTime now = LocalTime.now();
				LocalTime initialLimit = LocalTime.parse("00:00:00");
				LocalTime finalLimit = LocalTime.parse("23:59:59");
				Boolean isLate = now.isAfter(initialLimit);
				Boolean isBefore = now.isBefore(finalLimit);
				if (isLate && isBefore) {
					System.out.println("right time");
					try {
						List<Product> products = productService
								.findProductsWithStatusNEWorREVIEWSinceMoreThanFiveDays();
						if (products.size() > 0) {
							List<Admin> admins = adminService.findAllAdmins();
							if (admins.size() > 0) {
								Iterator it = admins.iterator();
								while (it.hasNext()) {
									Admin admin = (Admin) it.next();
									String adminEmail = admin.getEmail();
									sendMail.sendMailToAdmin(products, adminEmail);
								}
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		Timer timer = new Timer();
		// long delay = Constants.DELAY_IN_READING_FILES;
		// long intervalPeriod = Constants.INTERVAL_FOR_READING_CSV_FILES;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, 0, 24 * 60000); // in milliseconds // in 24 hours it will run once.
	}

}
