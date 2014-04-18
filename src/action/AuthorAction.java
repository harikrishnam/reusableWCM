package action;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;
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

public class AuthorAction extends ActionSupport 
{
	
	public String execute() throws NamingException, SQLException,
			FileNotFoundException , XQException, IOException{

		
		 XQDataSource xqs = new ExistXQDataSource();
		    xqs.setProperty("serverName", "localhost");
		    xqs.setProperty("port", "8080");

		    XQConnection conn = xqs.getConnection();

		    XQPreparedExpression xqpe =
		      conn.prepareExpression("declare variable $x as xs:string external; $x");

		    xqpe.bindString(new QName("x"), "", null);

		    XQResultSequence rs = xqpe.executeQuery();
		    //schema validation
		    while(rs.next())
		      System.out.println(rs.getItemAsString(null));
		    String url = "http://localhost:8080/exist/";
		    
		    if(Desktop.isDesktopSupported()){
		        Desktop desktop = Desktop.getDesktop();
		        try {
		            desktop.browse(new URI(url));
		        } catch (IOException | URISyntaxException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		    }else{
		        Runtime runtime = Runtime.getRuntime();
		        try {
		            runtime.exec("xdg-open " + url);
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		    }
		    conn.close();

		
		
		return "success";

	}

}