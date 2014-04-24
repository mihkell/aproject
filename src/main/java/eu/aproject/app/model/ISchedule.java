package eu.aproject.app.model;

import java.util.Date;

/**
 * Stores program and adds time, date and length value too them for particular 
 * channel. 
 * 
 * @author Mihkel
 *
 */
public interface ISchedule {

	/**
	 * 
	 * @return
	 */
	public Program getProgram();

	/**
	 * 
	 * @param program
	 */
	public void setProgram(Program program);

	/**
	 * 
	 * @return time in milliseconds
	 */
	public long getStartTime();

	/**
	 * 
	 * @param startTime in milliseconds
	 */
	public void setStartTime(long startTime);

	/**
	 * 
	 * @return length in milliseconds
	 */
	public long getLength();

	/**
	 * 
	 * @param length in milliseconds
	 */
	public void setLength(long length);

	/**
	 * @return the channel that this schedule is assigned to
	 */
	public Channel getChannel();

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(Channel channel);
	
	public Date getDate();
	
	/**
	 * @return the id
	 */
	public int getId();

}
