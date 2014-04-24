package eu.aproject.app.model;

/**
 * 
 * 
 * @author Mihkel
 *
 */
public interface IProgram {
	/**
	 * 
	 * @return the programs id which is autogenerated by hibernate
	 */
	int getID();

	/**
	 * 
	 * @return The name of the progam
	 */
	String getName();

	/**
	 * 
	 * @param name sets the name of the program
	 */
	void setName(String name);

	/**
	 * 
	 * @return program type
	 */
	String getProgramType();

	/**
	 * 
	 * @param type of the program
	 */
	void setProgramType(String type);



}