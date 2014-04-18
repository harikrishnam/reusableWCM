package action;

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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class PublisherAction extends ActionSupport implements SessionAware,
		ServletRequestAware {
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	String filename;
	String ret = SUCCESS;

	public String execute() throws NamingException, SQLException,
			FileNotFoundException {
		System.out.println("place1");
		try {

			File file = new File("/home/hari/DM/project/" + filename);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
			publishervalidator p= new publishervalidator();
			p.validateXMLSchema("/home/hari/DM/project/Publisher_validator.xsd","/home/hari/DM/project/" +filename);
			
           
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;

	}

	public void validate() {

		if (StringUtils.isEmpty(text)) {
			addFieldError("text", "This field cannot be empty");
		}
		if (StringUtils.isEmpty(filename)) {
			addFieldError("filename", "This field cannot be empty");
		}

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

}