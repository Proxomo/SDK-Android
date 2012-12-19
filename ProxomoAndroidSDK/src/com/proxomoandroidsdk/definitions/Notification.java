package com.proxomoandroidsdk.definitions;

import com.proxomoandroidsdk.enums.Enums.NotificationSendMethod;
import com.proxomoandroidsdk.enums.Enums.NotificationType;

public class Notification {
	private String EMailMessage;
	private String EMailSubject;
	private String MobileMessage;
	private NotificationType NotificationType;
	private NotificationSendMethod SendMethod;
	private String PersonID;
	
	public String getPersonID() {
		return PersonID;
	}
	public void setPersonID(String personID) {
		PersonID = personID;
	}
	public String getEmailMessage() {
		return EMailMessage;
	}
	public void setEmailMessage(String emailMessage) {
		EMailMessage = emailMessage;
	}
	public String getEmailSubject() {
		return EMailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		EMailSubject = emailSubject;
	}
	public String getMobileMessage() {
		return MobileMessage;
	}
	public void setMobileMessage(String mobileMessage) {
		MobileMessage = mobileMessage;
	}
	public NotificationType getNotificationType() {
		return NotificationType;
	}
	public void setNotificationType(NotificationType notificationType) {
		NotificationType = notificationType;
	}
	public NotificationSendMethod getSendMethod() {
		return SendMethod;
	}
	public void setSendMethod(NotificationSendMethod sendMethod) {
		SendMethod = sendMethod;
	}

}
