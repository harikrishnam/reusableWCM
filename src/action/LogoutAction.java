package action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7751903212229027485L;
	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	@Override
	public String execute()  {
		System.out.println("logout called");
		if (session.get("user") != null){
			session.remove("user");
		}
		return SUCCESS;
	}

}
