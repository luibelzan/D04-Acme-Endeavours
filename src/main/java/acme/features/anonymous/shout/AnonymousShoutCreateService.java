package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.entidades1.Entidad1;
import acme.entities.shouts.Shout;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository			repository;

	//@Autowired
	//protected AdministratorSpamWordRepository	repositorySpamwords;



	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;

	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info", "entidad1.atributo1", "entidad1.atributo2", "entidad1.atributo3", "entidad1.atributo4");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;
		Entidad1 entidad;

		moment = new Date(System.currentTimeMillis() - 1);
		entidad = new Entidad1();
		
		//entidad.setDeadline(moment);
		
		result = new Shout();
		result.setMoment(moment);
		result.setEntidad1(entidad);

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
        assert entity != null;
        assert errors != null;

        final List<Configuration> sp = this.repository.findManySpamWord();
        final List<Configuration> lsp = new ArrayList<>();
        lsp.addAll(sp);
        final Date now = new Date();
        
        if(entity.getEntidad1().getAtributo2()==null) {
        	errors.state(request, false, "entidad1.atributo2", "anonymous.message.form.error.atributo22");
        } else if(entity.getEntidad1().getAtributo2()!=null && this.sumarDias(entity.getEntidad1().getAtributo2(), -7).before(now)) {
        	errors.state(request, false, "entidad1.atributo2", "anonymous.message.form.error.atributo2");
        } 
        	
  
        
      /*  
        if(this.sumarDias(entity.getRegard().getDeadline(), -7).before(now)) {
        	errors.state(request, false, "regard.deadline", "anonymous.message.form.error.deadline");
        }
        */
        
        for (int i = 0; i < lsp.size(); i++) {
            if(lsp.get(i).isSpam(entity.getText())){
                errors.state(request, false, "text", "anonymous.message.form.error.spam");
            }
            if(lsp.get(i).isSpam(entity.getAuthor())) {
            	 errors.state(request, false, "author", "anonymous.message.form.error.spam.author");
            }
        }
        
        final String ahora = LocalDate.now().toString();
        if(!entity.getEntidad1().getAtributo1().equals("")) {
            final String ins = entity.getEntidad1().getAtributo1();
            final String[] trozos = ins.split("-");
            	
            final String dia = trozos[0].trim();
            final String mes = trozos[2].trim().substring(0, 2);
            final String año = trozos[2].trim().substring(2);
            final String fecha = "20" + año + "-" + mes + "-" + dia;
            if(!fecha.equals(ahora)) {
            	errors.state(request, false, "entidad1.atributo1", "anonymous.message.form.error.spam.atributo1");
            }
            
            final Collection<Entidad1> entidades1 = this.repository.findManyEntidad1();
            for(final Entidad1 r:entidades1) {
            	if(ins.equals(r.getAtributo1())) {
            		errors.state(request, false, "entidad1.atributo1", "anonymous.message.form.error.spam.atributo1");
            	}
            }
        }

	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		Entidad1 entidad;
		
		entidad = entity.getEntidad1();
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		//entidad.setDeadline(moment);
		this.repository.save(entity.getEntidad1());
		this.repository.save(entity);
	}
	
	public Date sumarDias(final Date fecha, final int dias) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return calendar.getTime();
	}

}