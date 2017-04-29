package com.daw.themadridtimes.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserView {

	private long id;
	private String name;
	private String lastname;
	private String alias;
	private List<String> roles;
	private String rolesStr;


	public UserView() {
		this.roles = new ArrayList<String>();
	}
	
	public UserView(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.lastname = u.getLastname();
		
		this.setAlias(u.getAlias());
		this.setRoles(u.getRoles());
	}

	public UserView(long id, String name, String lastname, String alias, List<String> roles) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;

		this.setAlias(alias);
		this.setRoles(roles);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = (alias == null ? "" : alias);
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
		
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = roles.iterator();
		while(it.hasNext()) {
			sb.append( UserCommons.getRoleName( it.next() ) );
		
			if(it.hasNext())
				sb.append(", ");
		}
		this.rolesStr = sb.toString();
	}

	public String getRolesStr() {
		return rolesStr;
	}

	public void setRolesStr(String rolesStr) {
		this.rolesStr = rolesStr;
	}

	@Override
	public String toString() {
		return "UserView [id=" + id + ", name=" + name + ", lastname=" + lastname + ", alias=" + alias + ", roles="
				+ roles + ", rolesStr=" + rolesStr + "]";
	}

	public boolean hasRole(String role) {
		return this.getRoles().contains( role );
	}
	
	public static List<UserView> castList(List<User> l) {
		List<UserView> c = new ArrayList<UserView>();
		Iterator<User> it = l.iterator();
		
		while(it.hasNext())
			c.add( new UserView(it.next()) );
		
		return c;
	}
}
