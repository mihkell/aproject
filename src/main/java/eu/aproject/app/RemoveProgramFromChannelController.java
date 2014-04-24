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
public class RemoveProgramFromChannelController implements IController {

	@Autowired
	IChannelDao channelDao;
	@Value("${removeprogram}")
	private String removeprogram;
	@Value("${submit}")
	private String submit;

	@RequestMapping(value = "/removeprogramfromchannel", 
					method = { RequestMethod.GET })
	
	public String removeProgramFromChannel(Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (request.getParameter("channel") == null ||
				request.getParameter("channel").equals("")) {
			response.sendRedirect("home");
		}

		setModelAttributes(model, request);

		return "removeprogramfromchannel";
	}

	@RequestMapping(value = "/removeprogramfromchannel", 
					method = { RequestMethod.POST })
	private String removeProgrammFromChannelPost(HttpServletRequest request,
			Model model) {

		String scheduleId = request.getParameter("scheduleId");
		boolean isRemoved = false;

		if (Utils.isPositiveInt(scheduleId)) {

			isRemoved = channelDao.removeProgramFromChannel(
					request.getParameter("channel"),
					Integer.parseInt(scheduleId));

			setModelAttributes(model, request);
		}

		model.addAttribute("added", isRemoved);

		return "removeprogramfromchannel";
	}

	public void setModelAttributes(Model model, HttpServletRequest request) {

		String channelName = request.getParameter("channel");
		List<ISchedule> schedules = channelDao.getAllProgramms(channelName);

		model.addAttribute("channel", channelName);
		model.addAttribute("schedules", schedules);
		model.addAttribute("removeprogram", removeprogram);
		model.addAttribute("submit", submit);
	}

}
