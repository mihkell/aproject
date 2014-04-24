package eu.aproject.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
@Entity
public class Schedule implements ISchedule {
	
	@Id@GeneratedValue
	private int id;
	@ManyToOne
	private Program program;
	private long startTime;
	private long length;
	@ManyToOne
	@JoinColumn(name = "CHANNEL_ID")
	private Channel channel;
	@Transient
	private Date date;
	
	public Schedule() {}
	
	public Schedule(Program program, long startTime, long length) {
		this.program = program;
		this.startTime = startTime;
		this.length = length;
		
	}
	public Date getDate(){
		return new Date(getStartTime());
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		
		this.startTime = startTime;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


}
