package acme.entities.entidades1;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.shouts.Shout;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entidad1 extends DomainEntity{
	
protected static final long	serialVersionUID	= 1L;
	
	@Pattern(regexp = "\\d{2}-\\w{2}\\d{2}-\\d{2}\\d{2}", message = "Invalid format")
	@NotNull
    protected String atributo1;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	protected Date atributo2;
	
	@Valid
    @NotNull
	protected Money atributo3;
	
	protected Boolean atributo4;
	
	@Valid
	@OneToOne(mappedBy = "entidad1")
	protected Shout shout;

}
