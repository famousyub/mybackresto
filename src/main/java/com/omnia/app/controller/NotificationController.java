package com.omnia.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.omnia.app.entity.Notifications;
import com.omnia.app.service.SsePushNotificationService;

@RestController
@RequestMapping("/api/notify")
@CrossOrigin(origins = "*")
public class NotificationController {
	
	 @Autowired
	    private SimpMessagingTemplate template;

	    private Notifications notifications = new Notifications(0);

	    @GetMapping("/notify")
	    public String getNotification() {

	        notifications.increment();

	        template.convertAndSend("/topic/notification", notifications);

	        return " new recipe add to menu  item   ";

}
	    
	    @Autowired
		SsePushNotificationService service;

		final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

		@GetMapping("/notification")
		public ResponseEntity<SseEmitter> doNotify() throws InterruptedException, IOException {
			final SseEmitter emitter = new SseEmitter();
			service.addEmitter(emitter);
			service.doNotify();
			emitter.onCompletion(() -> service.removeEmitter(emitter));
			emitter.onTimeout(() -> service.removeEmitter(emitter));
			return new ResponseEntity<>(emitter, HttpStatus.OK);
		}
}