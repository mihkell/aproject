package eu.aproject.app;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.aproject.app.dao.IChannelDao;
import eu.aproject.app.model.ISchedule;

@Controller
public class ChannelController implements IController {
	
	@Value("${days}")
	private String days;
	@Autowired
	IChannelDao channelDao;
	
	@RequestMapping(value = "/channel", method = RequestMethod.GET)
	public String channelprogramlist(Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		if(request.getParameter("channel") == null){
			response.sendRedirect("home");
		}
		
		String nameChannel = request.getParameter("channel");
		String nameDay = request.getParameter("day");

		if (nameDay != null) {
			List<ISchedule> schedules = channelDao.getProgramsOfDay(
					nameChannel, Utils.getDayOfWeek(nameDay));
			
			model.addAttribute("schedules", schedules);
		}
		
		setModelAttributes(model, request);
		
		return "channel";
	}

	@Override
	public void setModelAttributes(Model model, HttpServletRequest request) {
		String[] dayArray = days.split(",");
		
		model.addAttribute("channel", request.getParameter("channel"));
		model.addAttribute("days", dayArray);
		
	}

}
