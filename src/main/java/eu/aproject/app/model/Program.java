package eu.aproject.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "PROGRAM")
@Scope("prototype")
public class Program implements IProgram{

	@Id @GeneratedValue
	private int id;
	private String name;
	@Column(name = "PROGRAM_TYPE")
	private String programType;

	public Program(){}

	public Program(String name, String type) {
		this.name = name;
		setProgramType(type);
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
	public String getProgramType() {
		// TODO Auto-generated method stub
		return this.programType;
	}

	@Override
	public void setProgramType(String type) {
		this.programType = type;

	}

}
