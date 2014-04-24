package eu.aproject.app;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.aproject.app.dao.IChannelDao;
import eu.aproject.app.dao.IProgramDao;
import eu.aproject.app.model.Program;

@Controller
public class AddProgramToChannelController implements IController{

	@Autowired
	IChannelDao channelDao;
	@Autowired
	IProgramDao programDao;
	@Value("${addchange}")
	private String addchange;
	@Value("${intdays}")
	private String intdays;
	@Value("${hours}")
	private String hours;
	@Value("${intmonths}")
	private String intmonths;
	@Value("${submit}")
	private String submit;
	@Value("${minutes}")
	private String minutes;
	@Value("${year}")
	private int year;
	
	private Program[] programs;
	/**
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addprogramtochannel", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String addprogramtochannel(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (request.getParameter("channel") == null) {
			response.sendRedirect("home");
		}
		
		if (request.getMethod().equals("POST")) {
			postMethodMade(model, request);
		}
		
		setModelAttributes(model, request);
		return "addprogramtochannel";
	}

	/**
	 * Renders the post method made to add program
	 * 
	 * @param model
	 * @param request
	 */
	private void postMethodMade(Model model, HttpServletRequest request) {

		if(request.getParameter(submit).equals(addchange)){
			Calendar calendar = Calendar.getInstance();
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String hourOfDay = request.getParameter("hours");
			String minutes = request.getParameter("minutes");
			String length = request.getParameter("length");

			String[] dateArray = { month, day, hourOfDay, minutes, length };

			if (!Utils.isPositiveInts(dateArray)) {
				model.addAttribute("added", false);
				return;
			}
			
			try{
				calendar.set(year, Integer.parseInt(month)-1,
					Integer.parseInt(day), Integer.parseInt(hourOfDay),
					Integer.parseInt(minutes));
			
			addProgramToChannel(calendar.getTime(), Integer.parseInt(length),
								request, model);
			}catch(IllegalArgumentException e ){
				
				model.addAttribute("added", false);
			}
		}
	}


	private void addProgramToChannel(Date date, int length,
			HttpServletRequest request, Model model) {

		boolean added = channelDao.addProgramToChannel(
				request.getParameter("programname"), length, date,
				request.getParameter("channel"));

		model.addAttribute("added", added);

	}
	
	public void setModelAttributes(Model model, HttpServletRequest request) {
		programs = programDao.getAllPrograms();
		

		model.addAttribute("hours", hours.split(","));
		model.addAttribute("days", intdays.split(","));
		model.addAttribute("submit", submit);
		model.addAttribute("minutes", minutes.split(","));
		model.addAttribute("programs", programs);
		model.addAttribute("addchange", addchange);
		model.addAttribute("intmonths", intmonths.split(","));
		model.addAttribute("channel", request.getParameter("channel"));
		
		
		
	}



}
