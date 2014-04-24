package eu.aproject.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.aproject.app.model.ISchedule;

@Service(value = "scheduleDao")
public class ScheduleDao implements IScheduleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<ISchedule> getSchedulesByTypeAndPeriode(String type,
			Date startDate, Date endDate) {

		List<ISchedule> retval = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<ISchedule> list = session
				.createQuery(
						"from Schedule as schedule"+
						" where schedule.program.programType = :type and"+
						" schedule.startTime > :startTime and"+
						" schedule.startTime < :endTime")
				.setString("type", type)
				.setLong("startTime", startDate.getTime())
				.setLong("endTime", endDate.getTime())
				.list();

		if (list.size() != 0) {
			retval = list;
		}

		session.getTransaction().commit();
		session.close();

		return retval;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ISchedule> getSchedulesForProgramAndPeriod(String programName,
			Date startDate, Date endDate) {
		List<ISchedule> retval = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<ISchedule> list = session
				.createQuery(
						"from Schedule as schedule"+
						" where schedule.program.name = :programName and"+
						" schedule.startTime > :startTime and"+
						" schedule.startTime < :endTime")
				.setString("programName", programName)
				.setLong("startTime", startDate.getTime())
				.setLong("endTime", endDate.getTime())
				.list();

		if (list.size() != 0) {
			retval = list;
		}

		session.getTransaction().commit();
		session.close();

		return retval;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void removeSchedulesWith(String programName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<ISchedule> list = session
				.createQuery(
						"FROM Schedule as schedule"+
						" WHERE schedule.program.name = :programName"
						)
				.setString("programName", programName)
				.list();
		if(list.size() != 0){
			for(Object obj : list){
				session.delete(obj);
				
			}
		}
		
		session.getTransaction().commit();
		session.close();
		
	}

}
