package eu.aproject.app.dao;

import java.util.Date;
import java.util.List;

import eu.aproject.app.model.ISchedule;

public interface IScheduleDao {

	/**
	 * 
	 * @param type The type of programs to look for.
	 * @param startDate time periode start
	 * @param endDate time periode end 
	 * @return retunes list of ISchedules where program.getType() == type
	 */
	List<ISchedule> getSchedulesByTypeAndPeriode(String type, Date startDate,
			Date endDate);

	/**
	 * 
	 * @param program
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ISchedule> getSchedulesForProgramAndPeriod(String program,
			Date startDate, Date endDate);
	/**
	 * Removes all schedules that have this program scheduled
	 * @param programName the assosiated program name
	 */
	void removeSchedulesWith(String programName);

}
