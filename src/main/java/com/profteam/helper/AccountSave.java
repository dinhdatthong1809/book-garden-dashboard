package com.profteam.helper;

import com.profteam.model.Admin;
import com.profteam.model.User;

import java.util.Date;

public class AccountSave 
{
	private static Admin admin = new Admin(105, "haogd", "123", "Lý Tiểu Long", "lytieulong@gmail.com", "01682439314", null, true, 0, true, new Date());
	private static User user = new User(100, "haond", "123", "Nguyễn Văn Hao", new Date(), "teonv@gmail.com", "0623457413", false, true, new Date());
	public static void setAdmin(Admin admin) 
	{
		AccountSave.admin = admin;
	}
	
	public static Admin getAdmin()
	{
		return admin;
	}
	
	public static void removeAdmin()
	{
		admin = null;
	}
	
	public static void setUser(User user)
	{
		AccountSave.user = user;
	}
	
	public static User getUser()
	{
		return user;
	}
	
	public static void removeUser()
	{
		user = null;
	}
	
}
