package action;
import model.User;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import util.ConnectionPool;
import util.Constants;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware
{

	private String username;
	private String user_id;


	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	private String password;

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	private Map<String, Object> session;

	public Map<String, Object> getSession()
	{
		return session;
	}

	public String execute() throws NamingException, SQLException
	{
		clearFieldErrors();

		User user = (User) session.get("user");
		if (user != null)
		{
			return "logout";
		}
		else
		{
			User newUser = new User(username, password);
			if (isValidUser(newUser))
			{
				session.put("user", newUser);
				if (newUser.getUserType().equalsIgnoreCase("A"))
				{
					return "admin";
				}
				if (newUser.getUserType().equalsIgnoreCase("V"))
				{
					return "vendor";
				}
				if (newUser.getUserType().equalsIgnoreCase("AU"))
				{
					return "author";
				}
				if (newUser.getUserType().equalsIgnoreCase("P"))
				{
					return "publisher";
				}
			}
			else
			{
				return INPUT;
			}

		}
		return INPUT;

	}

	public void validate()
	{

		if (StringUtils.isEmpty(username))
		{
			addFieldError(Constants.DB_USERNAME, Constants.USER_NAME_BLANK);
		}

		if (StringUtils.isEmpty(password))
		{
			addFieldError(Constants.DB_PASSWORD, Constants.PASSWORD_BLANK);
		}
	}

	@Override
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

	private boolean isValidUser(User user)
	{

		Connection conn = ConnectionPool.getConnection();

		PreparedStatement preStmt;
		try
		{
			preStmt = conn.prepareStatement(Constants.GET_USER);

			preStmt.setString(1, user.getUsername());
			ResultSet result = preStmt.executeQuery();

			if (result.first())
			{
				if (!user.getPassword().equals(
						result.getString(Constants.DB_PASSWORD)))
				{
					addFieldError(Constants.DB_PASSWORD,
							Constants.INVALID_PASSWORD_ERROR);
					return false;
				}
				else
				{

					user.setName(result.getString("name"));
					user.setUserType(result.getString("user_type"));

					if (user.getUserType().equalsIgnoreCase("A"))
						return true;
				
				}

			}
			else
			{
				addFieldError(Constants.DB_USERNAME,
						Constants.INVALID_USER_ERROR);
				return false;
			}

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			ConnectionPool.freeConnection(conn);
		}
		return true;
	}

	}
