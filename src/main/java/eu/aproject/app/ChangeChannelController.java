package eu.aproject.app;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.aproject.app.dao.IChannelDao;


@Controller
public class ChangeChannelController  implements IController{
	
	@Autowired
	IChannelDao channelDao;
	@Value("${removechannel}")
	private String removechannel;
	@Value("${submit}")
	private String submit;

	@RequestMapping(value = "/changechannel", method = RequestMethod.GET)
	public String changechannel(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		setModelAttributes(model);
		return "changechannel";
	}

	@RequestMapping(value = "/addchannel", method = RequestMethod.POST)
	public String addchannel(Model model, HttpServletRequest request) throws IOException {
		
		boolean added = false;
		
		
		if(request.getParameter("submit").equals(submit)){
			added = addChannel(request);
			
		}else if(request.getParameter("submit").equals(removechannel)){
			added = removeChannel(request);
			
		}
		
		model.addAttribute("added", added);
		
		setModelAttributes(model);
		return "changechannel";
	}

	private boolean removeChannel(HttpServletRequest request) {
		boolean added = false;
		
		String channelName = request.getParameter("channel");
		added = channelDao.removeChannel(channelName);
		
		return added;
	}

	private boolean addChannel(HttpServletRequest request) {
		
		boolean added = false;
		
		String channelName = (String) request.getParameter("channelname");
		String description = (String) request.getParameter("channeldesc");

		if(channelName != null && description != null){
			added = channelDao.addChannel(channelName, description);
		}
		return added;
	}
	
	public void setModelAttributes(Model model) {

		setModelAttributes( model, null );

	}

	public void setModelAttributes(Model model, HttpServletRequest request) {

		String[] channelNames = channelDao.getChannelNames();
		model.addAttribute("channelnames", channelNames);
		model.addAttribute("submit", submit);
		model.addAttribute("removechannel", removechannel);

	}




}
