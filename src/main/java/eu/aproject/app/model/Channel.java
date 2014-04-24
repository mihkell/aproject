package eu.aproject.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "CHANNEL")
@Scope("prototype")
public class Channel implements IChannel {

	@Id @GeneratedValue
	private int id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "channel", cascade = {CascadeType.ALL}, orphanRemoval=true)
	private List<Schedule> timeTable = new ArrayList<Schedule>();

	public Channel() {}

	public Channel(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String desc) {
		this.description = desc;
	}

	
	public List<Schedule> getTimeTable() {
		return timeTable;
	}

	
	public void addTimeTable(Schedule timeTable) {
		this.timeTable.add(timeTable);
	}

	@Override
	public boolean removeSchedule(Schedule schedule) {
		return timeTable.remove(schedule);
	}

}
