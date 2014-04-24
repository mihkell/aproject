package eu.aproject.app.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.aproject.app.model.Channel;
import eu.aproject.app.model.ISchedule;
import eu.aproject.app.model.Program;
import eu.aproject.app.model.Schedule;

@Service(value = "channelDao")
public class ChannelDao implements IChannelDao{


	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IProgramDao programDao;

	@SuppressWarnings("unchecked")
	public boolean removeProgramFromChannel(String channelName, int scheduleId) {


		boolean retval = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryForName = "from Schedule as schedule"
				+ " where schedule.id = :scheduleId";

		// Should use delete in query and then executeUpdate method.
		List<Schedule> list = session.createQuery(queryForName).setMaxResults(1)
				.setLong("scheduleId", scheduleId).list();

		if (list.size() != 0) {
			session.delete(list.get(0));
			retval = true;
		}
		
		session.getTransaction().commit();
		session.close();

		return retval;


	}

	/*
	 * Remove channel
	 */
	@SuppressWarnings("unchecked")
	public boolean removeChannel(String channelName) {

		boolean retval = false;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryForName = "FROM Channel"
				+ " WHERE name = :channelname";

		List<Schedule> list = session.createQuery(queryForName).setMaxResults(1)
				.setString("channelname", channelName).list();

		if (list.size() != 0) {

			session.delete(list.get(0));
			retval = true;

		}

		session.getTransaction().commit();
		session.close();

		return retval;
	}
	@SuppressWarnings("unchecked")
	public boolean addChannel(String channelname, String description) {

		boolean retval = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryForName = "SELECT name FROM Channel "
				+ " WHERE name = :channelname";

		List<Schedule> list = session.createQuery(queryForName).setMaxResults(1)
				.setString("channelname", channelname).list();

		if (list.size() == 0) {
			Channel channel = new Channel(channelname, description);
			session.save(channel);
			retval = true;
		}
		session.getTransaction().commit();
		session.close();

		return retval;
	}

	
	public boolean addProgramToChannel(String programName, int length, Date date,
			String channelname) {
		boolean retval = false;
		boolean timeFree = false;
		

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Program program = programDao.getProgramByName(programName);
		if(program == null){
			return retval;
		}

		Channel channel = getChannelByName(channelname, session);

		if(channel != null){
			timeFree = checkIfTimePeriodIsOpenOnChannel(date.getTime(), 
					TimeUnit.MINUTES.toMillis(length), channel, session);
		}

		if(timeFree && channel != null){
			Schedule schedule = new Schedule(program, 
					date.getTime(), TimeUnit.MINUTES.toMillis(length));
			schedule.setChannel(channel);
			session.save(schedule);

			channel.addTimeTable(schedule);
			session.save(channel);

			retval = true;
		}

		session.getTransaction().commit();
		session.close();




		return retval;
	}

	@SuppressWarnings("unchecked")
	public String[] getChannelNames() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();


		List<String> list = session.createQuery(
				"select channel.name from Channel as channel").list();

		session.getTransaction().commit();
		session.close();

		String[] array = new String[list.size()];
		array = (String[]) list.toArray(array);

		return array;
	}

	@SuppressWarnings("unchecked")
	public Channel getChannelByName(String channelname, Session session ) {
		Channel channel = null;

		String queryForName = "from Channel as channel"
				+ " where channel.name = :name";

		List<Channel> list = session.createQuery(queryForName)
				.setString("name", channelname).list();
		System.out.println("in getChannelByName list.size: " + list.size() );
		if (list.size() != 0 && list.get(0).getClass() == Channel.class) {

			channel = (Channel) list.get(0);
			System.out.println("getChannelByName channel found: " + channel.getID());
		}

		return channel;
	}

	public List<ISchedule> getProgramsOfDay(String nameChannel, int dayNumber) {
		// TODO Implement this with SQL day type.
		// finds programs that are set to show in the day's time on that
		// channel
		List<ISchedule> retval = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<ISchedule> list = session.createQuery(
				"from Schedule where channel.name = :nameChannel")
				.setString("nameChannel", nameChannel)
				.list();



		session.getTransaction().commit();
		session.close();

		if(list.size() != 0){

			retval = new ArrayList<ISchedule>();
			Calendar cal = Calendar.getInstance();
			for(ISchedule schedule: list){

				cal.setTime(schedule.getDate());
				if(cal.get(Calendar.DAY_OF_WEEK) == dayNumber){
					retval.add(schedule);
				}

			}

			if(retval.size() == 0){
				return retval = null;
			}

		}

		return retval;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ISchedule> getAllProgramms(String channelName) {

		List<ISchedule> retval = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();


		List<ISchedule> list = session.createQuery(
				"from Schedule where channel.name = :channelName")
				.setString("channelName", channelName)
				.list();

		if(list.size() != 0){
			retval = list;
		}

		session.getTransaction().commit();
		session.close();

		return retval;
	}

	@SuppressWarnings("unchecked")
	private boolean checkIfTimePeriodIsOpenOnChannel(long startTime, long length,
			Channel channel, Session session) {

		String queryForTime = "select channel.timeTable from Channel as channel"+
				" where channel.name = :name";


		List<Schedule> list = session.createQuery(queryForTime)
				.setString("name", channel.getName())
				.list();

		if (list.size() != 0 ) {
			for(Schedule schedule : list){
				if(!timePeriodCheck(startTime,length, schedule)){
					System.out.println("schedule " + schedule.getStartTime());
					return false;
				}
			}
		}

		return true;
	}

	private boolean timePeriodCheck(long start, long lenght, Schedule schedule){

		boolean startsBeforAndEndsBefor = ((schedule.getStartTime()+
				schedule.getLength())<start);
		System.out.println(startsBeforAndEndsBefor  + " " +((schedule.getStartTime() + schedule.getLength())< start));
		boolean startsAfterOldEnds = (schedule.getStartTime()>start &&
				schedule.getStartTime()>=start+lenght);
		System.out.println(startsAfterOldEnds);

		return (startsBeforAndEndsBefor || startsAfterOldEnds);

	}




}
