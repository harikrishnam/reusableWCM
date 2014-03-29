package action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import util.ConnectionPool;
import util.Constants;

import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class AddauthorAction extends ActionSupport implements SessionAware,
    ServletRequestAware

{

  private String username;
  private String name;  
  private String password;
 String fileUploadContentType;
  private HttpServletRequest servletRequest;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private Map<String, Object> session;

  public Map<String, Object> getSession() {
    return session;
  }

  public String execute() throws NamingException, SQLException,
      FileNotFoundException {

    String ret = ERROR;

    Connection conn = ConnectionPool.getConnection();
    ResultSet generatedKeys = null;
    PreparedStatement preStmt = null;
    try {
      String query = "insert into user(name,username,password,user_type) values(?,?,?,?)";

      preStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      preStmt.setString(1, name);
      preStmt.setString(2, username);
      preStmt.setString(3, password);
      preStmt.setString(4, "AU");

      if (preStmt.executeUpdate() > 0)
        ret = SUCCESS;
      else
        ret = ERROR;

      generatedKeys = preStmt.getGeneratedKeys();
    }
      
     finally {
      ConnectionPool.freeConnection(conn);
    }
    return ret;

  }

  public void validate() {

    if (StringUtils.isEmpty(username)) {
      addFieldError(Constants.DB_USERNAME, Constants.USER_NAME_BLANK);
    }

    if (StringUtils.isEmpty(password)) {
      addFieldError(Constants.DB_PASSWORD, Constants.PASSWORD_BLANK);
    }

    if (StringUtils.isEmpty(name)) {
      addFieldError("name", "name cannot be blank");
    }

  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;

  }

  @Override
  public void setServletRequest(HttpServletRequest req) {
    this.servletRequest = req;

  }
}