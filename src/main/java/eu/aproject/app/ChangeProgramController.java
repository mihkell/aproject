package eu.aproject.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.aproject.app.dao.IProgramDao;

@Controller
public class ChangeProgramController implements IController{
	
	@Autowired
	IProgramDao programDao;

	
	@RequestMapping(value = "/changeprogram", method = RequestMethod.GET)
	public String changeProgram(Model model, HttpServletRequest request)
			throws IOException {
		
		setModelAttributes(model, request);

		return "changeprogram";
	}

	@RequestMapping(value = "/changeprogram", method = RequestMethod.POST)
	public String changeProgramPost(Model model, HttpServletRequest request)
			throws IOException {


		String submit = (String) request.getParameter("submit");

		if (submit != null) {
			changeProgram(submit, model, request);
		}

		setModelAttributes(model, request);

		return "changeprogram";
	}

	private void changeProgram(String submit, Model model,
			HttpServletRequest request) {
		
		if (submit.equals("add") && request.getParameter("type") != null) {
			
			boolean added = addProgram(request);
			
			model.addAttribute("added", added);

		} else if (submit.equals("remove") && request.getParameter("programname") != null) {
			
			boolean added = removeProgram(request); 
			
			model.addAttribute("added", added);
		}

	}

	private boolean removeProgram(HttpServletRequest request) {
		
		return programDao.removeProgram((String) request
				.getParameter("programname"));
	}

	private boolean addProgram(HttpServletRequest request) {
		
		return programDao.addProgram(
				(String) request.getParameter("programname"),
				(String) request.getParameter("type"));
	}

	@Override
	public void setModelAttributes(Model model, HttpServletRequest request) {
		
		model.addAttribute("types", programDao.getTypes());
		
	}

}
