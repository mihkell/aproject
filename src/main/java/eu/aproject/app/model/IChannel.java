package eu.aproject.app.model;

import java.util.List;


/**
 * 
 * 
 * @author Mihkel
 *
 */
public interface IChannel {
	
	int getID();
	
	/**
	 * 
	 * @return Channel name.
	 */
	String getName();
	
	/**
	 * 
	 * @param name
	 */
	void setName(String name);
	
	/**
	 * 
	 * @return Channel description
	 */
	String getDescription();
	
	/**
	 * 
	 * @param desc Channel description to bet set.
	 */
	void setDescription(String desc);
	
	/**
	 * 
	 * @return the List of Schedule items.
	 */
	public List<Schedule> getTimeTable();
	/**
	 * 
	 * @param Schedule to be added to Channel.
	 */
	public void addTimeTable(Schedule timeTable);
	
	/**
	 * 
	 * @param schedule
	 * @return returns true if removed.
	 */
	public boolean removeSchedule(Schedule schedule);
	

}
