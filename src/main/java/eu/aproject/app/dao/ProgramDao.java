package eu.aproject.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import eu.aproject.app.model.Program;

@Service(value = "programDao")
public class ProgramDao implements IProgramDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Value("${types}")
	private String types;
	@Autowired
	IScheduleDao scheduleDao;
	
	@SuppressWarnings("unchecked")
	public Program[] getAllPrograms() {
		// TODO Auto-generated method stub
		// Get programs form database
		Program[] programs = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String query = "from Program";
		
		List<Program> list = session.createQuery(query).list();

		if (list.size() != 0) {
			programs = new Program[list.size()];
			programs = list.toArray(programs);
		}
		session.getTransaction().commit();
		session.close();

		return programs;
	}
	

	@SuppressWarnings("unchecked")
	public Program getProgramByName(String programName) {

		Program program = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String queryForName = "from Program as program"
				+ " where program.name = :name";

		List<Program> list = session.createQuery(queryForName)
				.setString("name", programName).list();

		if (list.size() != 0) {

			program = (Program) list.get(0);

		}

		session.getTransaction().commit();
		session.close();

		return program;
	}

	public String[] getTypes() {
		return types.split(",");
	}

	@SuppressWarnings("unchecked")
	public boolean addProgram(String name, String type) {

		boolean retval = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String queryForName = "from Program as program"
				+ " where program.name = :name";

		
		List<Program> list = session.createQuery(queryForName).setMaxResults(1)
				.setString("name", name).list();

		if (list.size() == 0) {
			Program newProgram = new Program(name, type);
			session.save(newProgram);
			retval = true;
		}

		session.getTransaction().commit();
		session.close();

		return retval;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean removeProgram(String programName) {

		boolean retval = false;
		
		scheduleDao.removeSchedulesWith(programName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryForProgram = "from Program as Program"
				+ " where Program.name = :name";
		

		List<Program> list = session.createQuery(queryForProgram)
				.setString("name", programName).list();

		if (list.size() != 0) {

			for (Object obj : list) {
				session.delete("program", obj);

			}

			retval = true;
		}

		session.getTransaction().commit();
		session.close();

		return retval;
	}

	
}
