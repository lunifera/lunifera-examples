package org.lunifera.examples.vaaclipse.demo1.e4.data;

import com.vaadin.server.Resource;

public class Task {

	public static final String[] visibleColumns = new String[] { "name",
			"description" };

	private Type type;
	private String name;
	private String description;
	private Resource icon;

	public Task(Type type, String name, String description, Resource icon) {
		super();
		this.type = type;
		this.name = name;
		this.description = description;
		this.icon = icon;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the icon
	 */
	public Resource getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(Resource icon) {
		this.icon = icon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public static enum Type {
		ITEM, CRM;
	}

}
