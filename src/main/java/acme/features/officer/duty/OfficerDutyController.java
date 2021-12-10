package acme.features.officer.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Duty;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Officer;

@Controller
@RequestMapping("officer/duty/")
public class OfficerDutyController extends AbstractController<Officer, Duty> {
	
	@Autowired
	protected OfficerDutyListService listService;
	
	@Autowired
	protected OfficerDutyShowService showService;
	
	@Autowired
	protected OfficerDutyCreateService createService;
	
	@Autowired
	protected OfficerDutyDeleteService deleteService;
	
	@Autowired
	protected OfficerDutyUpdateService updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
