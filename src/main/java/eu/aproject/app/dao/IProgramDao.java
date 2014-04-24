package eu.aproject.app.dao;

import eu.aproject.app.model.Program;

public interface IProgramDao {

	/**
	 * 
	 * @return returns all programs
	 */
	Program[] getAllPrograms();

	/**
	 * 
	 * @param programName
	 * @return returns the program with the given name
	 */
	Program getProgramByName(String programName);

	/**
	 * 
	 * @return names of program types
	 */
	String[] getTypes();

	/**
	 * 
	 * @param name
	 * @param type
	 * @return true if program was added, false otherwise
	 */
	boolean addProgram(String name, String type);

	/**
	 * 
	 * @param programName
	 * @return if program is removed returns true
	 */
	boolean removeProgram(String programName);
	

}
