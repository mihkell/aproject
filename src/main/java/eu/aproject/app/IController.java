package eu.aproject.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface IController {
	/**
	 * Sets default model attributes.
	 * 
	 * @param model
	 * @param request
	 */
	void setModelAttributes(Model model, HttpServletRequest request);

}
