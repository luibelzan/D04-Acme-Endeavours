/*
 * Dashboard.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;
	Double						avegageNumberOfApplicationsPerEmployer;
	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;

	
	Double                      numberPublicDuty;
	Double                      numberPrivateDuty;
	Double						numberFinalDuty;
	Double						numberNoFinalDuty;
	Double						averageDurationPeriodDuties;
	Double						deviationDurationPeriodDuties;
	Double						minimumDurationPeriodDuties;
	Double						maximumDurationPeriodDuties;
	Double						averageWorkloadDuties;
	Double						deviationWorkloadDuties;
	Double						minimumWorkloadDuties;
	Double						maximumWorkloadDuties;
	
	
	Double 						ratioEntidad1Important;
	Double						ratioOfShoutsBudget0;
	Double						averageCurrency1;
	Double 						averageCurrency2;
	Double 						deviationCurrency1;
	Double						deviationCurrency2;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
