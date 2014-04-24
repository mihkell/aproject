package eu.aproject.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import eu.aproject.app.model.Channel;
import eu.aproject.app.model.ISchedule;

public interface IChannelDao {

	/**
	 * Removes Schedule from channel
	 * 
	 * @param channelName
	 * @param scheduleId 
	 * @return true if Schedule was removed from channel
	 */
	boolean removeProgramFromChannel(String channelName, int scheduleId);


	/**
	 * 
	 * @param channelname
	 * @param description
	 * @return true if channel was added, false otherwise
	 */
	boolean addChannel(String channelname, String description);

	/**
	 * 
	 * 
	 * @return all channel names
	 */
	String[] getChannelNames();

	/**
	 * 
	 * @param channelName
	 * @return true if channel was removed, false otherwise 
	 */
	boolean removeChannel(String channelName);

	/**
	 * 
	 * @param programName
	 * @param length
	 * @param date
	 * @param channelname
	 * @return true if program was added, false otherwise
	 */
	boolean addProgramToChannel(String programName, int length, Date date,
			String channelname);
	/**
	 * 
	 * @param channelname
	 * @param session
	 * @return 
	 */
	Channel getChannelByName(String channelname, Session session);

	/**
	 * 
	 * @param nameChannel
	 * @param dayNumber
	 * @return
	 */
	List<ISchedule> getProgramsOfDay(String nameChannel, int dayNumber);

	/**
	 * 
	 * @param channelName
	 * @return
	 */
	List<ISchedule> getAllProgramms(String channelName);

}
