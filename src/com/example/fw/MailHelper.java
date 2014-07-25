package com.example.fw;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class MailHelper extends HelperBase {
	
	public MailHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	//private ApplicationManager app;
	private String mailserver;

	public Msg getNewMail(String user, String password) {
		Properties properties = System.getProperties();
		Session session = Session.getDefaultInstance(properties);
		
		Store store;
		try {
			store = session.getStore("pop3");
			store.connect(mailserver, user, password);
			Folder folder = store.getDefaultFolder().getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			if (folder.getMessageCount() != 1) {
				return null;
			}
			Message message = folder.getMessage(1);
			
			message.setFlag(Flags.Flag.DELETED, true);
			Msg msg = new Msg((String) message.getContent());
			folder.close(true);
			store.close();
			return msg;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	
}
