package eu.aproject.app;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.aproject.app.dao.IChannelDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	IChannelDao channelDao;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		
		model.addAttribute("channels", channelDao.getChannelNames());

		return "home";
	}

	


}
