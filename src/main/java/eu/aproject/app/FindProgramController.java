package eu.aproject.app;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.aproject.app.dao.IProgramDao;
import eu.aproject.app.dao.IScheduleDao;
import eu.aproject.app.model.ISchedule;

@Controller
public class FindProgramController implements IController{

	@Autowired
	IScheduleDao scheduleDao;
	@Autowired
	IProgramDao programDao;
	@Value("${intdays}")
	private String intdays;
	@Value("${types}")
	private String types;
	@Value("${intmonths}")
	private String intmonths;
	@Value("${hours}")
	private String hours;
	@Value("${year}")
	private int year;

	private String findByType = "Find by type";

	private String findByProgram = "Find by program";

	@RequestMapping(value = "/findprogram", method = RequestMethod.GET)
	public String findProgram(Model model, HttpServletRequest request) 
							throws IOException {

		setModelAttributes(model, request);

		return "findprogram";
	}

	@RequestMapping(value = "/findprogram", method = RequestMethod.POST)
	public String findProgramPost(Model model, HttpServletRequest request){

		setModelAttributes(model);

		Date startDate = getStartDate(request);
		Date endDate = getEndDate(request);

		if(endDate == null || startDate == null){
			model.addAttribute("found", true );
			return "findprogram";
		}


		List<ISchedule> schedules = null;
		String submit = request.getParameter("submit");

		if(submit.equals(findByType)){
			String type = request.getParameter("type");
			
			if(type != null){
				schedules = scheduleDao.getSchedulesByTypeAndPeriode(type, startDate, endDate);
				
			}
		}else if (submit.equals(findByProgram)){
			String program = request.getParameter("program");
			schedules = scheduleDao.getSchedulesForProgramAndPeriod(program, startDate, endDate);
		}

		model.addAttribute("schedules", schedules );

		return "findprogram";
	}

	public void setModelAttributes(Model model) {

		setModelAttributes( model, null );

	}

	@Override
	public void setModelAttributes(Model model, HttpServletRequest request) {
		
		model.addAttribute("types", types.split(","));
		model.addAttribute("hours", hours.split(","));
		model.addAttribute("days", intdays.split(","));
		model.addAttribute("months", intmonths.split(","));
		model.addAttribute("findbytype", findByType);
		model.addAttribute("findbyprogram", findByProgram);
		model.addAttribute("programnamess", programDao.getAllPrograms());

	}

	public Date getStartDate(HttpServletRequest request){

		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String hours = request.getParameter("hours");

		return getThisYearDate(hours, day, month);

	}

	public Date getEndDate(HttpServletRequest request) {

		String endday = request.getParameter("endday");
		String endmonth = request.getParameter("endmonth");
		String endhours = request.getParameter("endhours");

		return getThisYearDate(endhours, endday, endmonth);

	}

	public Date getThisYearDate(String hours, String day, String month){

		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		String[] dateString = {day, month, hours};

		if(!Utils.isPositiveInts(dateString)){
			return null;
		}
		
		try {
			calendar.set(year, Integer.parseInt(month)-1,
					Integer.parseInt(day), Integer.parseInt(hours),
					0);
			
			return calendar.getTime();
			
		} catch (Exception e) {
			return null;
		}
		

	}


}
