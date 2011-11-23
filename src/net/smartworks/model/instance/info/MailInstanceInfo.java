package net.smartworks.model.instance.info;

import net.smartworks.model.community.User;
import net.smartworks.model.instance.Instance;
import net.smartworks.model.instance.WorkInstance;
import net.smartworks.model.work.Work;
import net.smartworks.model.work.WorkCategory;
import net.smartworks.util.LocalDate;

public class MailInstanceInfo extends Instance {

	private User sender;
	private LocalDate sendDate;
	private User[] receivers;
	private User[] ccReceivers;
	private int priority;
	private String data;
	private WorkInstance[] attachments;
	private boolean isRead;
	private WorkCategory mailCategory;
	private Work mailGroup;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}

	public User[] getReceivers() {
		return receivers;
	}

	public void setReceivers(User[] receivers) {
		this.receivers = receivers;
	}

	public User[] getCcReceivers() {
		return ccReceivers;
	}

	public void setCcReceivers(User[] ccReceivers) {
		this.ccReceivers = ccReceivers;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public WorkInstance[] getAttachments() {
		return attachments;
	}

	public void setAttachments(WorkInstance[] attachments) {
		this.attachments = attachments;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public WorkCategory getMailCategory() {
		return mailCategory;
	}

	public void setMailCategory(WorkCategory mailCategory) {
		this.mailCategory = mailCategory;
	}

	public Work getMailGroup() {
		return mailGroup;
	}

	public void setMailGroup(Work mailGroup) {
		this.mailGroup = mailGroup;
	}

	public MailInstanceInfo() {
		super();
	}

	public MailInstanceInfo(String id, String subject, User sender,
			LocalDate sendDate) {
		super(id, subject, Instance.TYPE_MAIL, sender, sender, sendDate);
		this.sender = sender;
		this.sendDate = sendDate;
	}

}