package model;

import java.io.InputStream;
import java.util.List;


public class User
{
	String username;
	String name;
	String userType;
	String password;

	public User()
	{
		
	}
	public User(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
		
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}


	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

}
