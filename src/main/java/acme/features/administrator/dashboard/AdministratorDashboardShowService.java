/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerWorker",  
			"avegageNumberOfApplicationsPerEmployer", "ratioOfPendingApplications", 
			"ratioOfRejectedApplications", "ratioOfAcceptedApplications", "numberPublicDuty", "numberPrivateDuty", "numberFinalDuty", 
			"numberNoFinalDuty","averageDurationPeriodDuties","deviationDurationPeriodDuties","minimumDurationPeriodDuties","maximumDurationPeriodDuties",
			"averageWorkloadDuties","deviationWorkloadDuties","minimumWorkloadDuties","maximumWorkloadDuties", "ratioEntidad1Important", "ratioOfShoutsBudget0", 
			"averageCurrency1", "averageCurrency2", "deviationCurrency1", "deviationCurrency2");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		Double averageNumberOfApplicationsPerEmployer;
		Double averageNumberOfApplicationsPerWorker;
		Double averageNumberOfJobsPerEmployer;
		Double ratioOfPendingApplications;
		Double ratioOfAcceptedApplications;
		Double ratioOfRejectedApplications;
		
		final Double numberPublicDuty;
		final Double numberPrivateDuty;
		final Date now = new Date();
		final List<Duty> terminadas = new ArrayList<>();
		final Collection<Duty> duties = this.repository.findDuties();
		Double averageDurationPeriodDuties;
		Double deviationDurationPeriodDuties;
		Double minimumDurationPeriodDuties;
		Double maximumDurationPeriodDuties;
		
		
		final Double averageWorkloadDuties;
		final Double deviationWorkloadDuties;
		final Double minimumWorkloadDuties;
		final Double maximumWorkloadDuties;
		
		Double ratioEntidad1Important; 
		final Double ratioOfShoutsBudget0;
		Double averageCurrency1;
		Double averageCurrency2;
		Double deviationCurrency1;
		final Double deviationCurrency2;
		
		ratioEntidad1Important = this.repository.ratioEntidad1Important();
		ratioOfShoutsBudget0 = this.repository.ratioOfShoutsBudget0();
		
		final List<Double> averages= this.repository.averageEntidad1GroupByCurrency();
		final List<Double> deviations = this.repository.deviationEntidad1GroupByCurrency();
		
	
        if(averages.size()==2) {
        	averageCurrency1 = averages.get(0);
        	averageCurrency2 = averages.get(1);
        } else if(averages.size()<=1 && averages.size()>0) {
        	averageCurrency1 = averages.get(0);
            averageCurrency2 = 0.0;
        } else {
        	averageCurrency1 = 0.0;
            averageCurrency2 = 0.0;
        }

        if(deviations.size()==2) {
        	deviationCurrency1 = deviations.get(0);
        	deviationCurrency2 = deviations.get(1);
        } else if(deviations.size()<=1 && deviations.size()>0) {
        	deviationCurrency1 = deviations.get(0);
        	deviationCurrency2 = 0.0;
        } else {
        	deviationCurrency1 = 0.0;
        	deviationCurrency2 = 0.0;
        }

		averageNumberOfApplicationsPerEmployer = this.repository.averageNumberOfApplicationsPerEmployer();
		averageNumberOfApplicationsPerWorker = this.repository.averageNumberOfApplicationsPerWorker();
		averageNumberOfJobsPerEmployer = this.repository.averageNumberOfJobsPerEmployer();
		ratioOfPendingApplications = this.repository.ratioOfPendingApplications();
		ratioOfAcceptedApplications = this.repository.ratioOfAcceptedApplications();
		ratioOfRejectedApplications = this.repository.ratioOfRejectedApplications();
		
		numberPublicDuty = this.repository.numberPublicDuty();
		numberPrivateDuty = this.repository.numberPrivateDuty();
		averageDurationPeriodDuties=0.0;
		deviationDurationPeriodDuties= 0.0;
		maximumDurationPeriodDuties=0.0;
		averageWorkloadDuties=this.repository.averageWorkloadDuties();
		deviationWorkloadDuties=this.repository.deviationWorkloadDuties();
		minimumWorkloadDuties=this.repository.minimumWorkloadDuties();
		maximumWorkloadDuties=this.repository.maximumWorkloadDuties();
		
		for (final Duty d: duties) {
			averageDurationPeriodDuties= averageDurationPeriodDuties+ d.durationPeriodInHours();
		}
		
		
		for (final Duty d: duties) {
			//Comprueba si la tarea esta terminada
			if (now.getTime()>=d.getPeriodFinal().getTime()) {
				terminadas.add(d);
			}
		}
		for (final Duty d: duties) {
			//Esto es para calcular el maximo en los Workloads tasks
			if (d.durationPeriodInHours()>maximumDurationPeriodDuties) {
				maximumDurationPeriodDuties=1.0*d.durationPeriodInHours();
			}
		}
		//Ponemos que el minimo en el inicio sea el maximo posible, y de ahi vamos decreciendo
		minimumDurationPeriodDuties=maximumDurationPeriodDuties;
		for (final Duty d: duties) {
			//Para calcular el minimo de las workloads tasks
			if (d.durationPeriodInHours()<minimumDurationPeriodDuties) {
				minimumDurationPeriodDuties=1.0*d.durationPeriodInHours();
			}
		}
		final List<Double> workloadsLists= new ArrayList<>(); //Creamos una lista de workloads
		for (final Duty d: duties) {
			final Double workload= 1.0*d.durationPeriodInHours();
			workloadsLists.add(workload);  //Metemos los workloads en la lista
		}
		deviationDurationPeriodDuties= AdministratorDashboardShowService.calculateStandardDeviation(workloadsLists); //Usamos una funcion creada para calcular la desviacion tipica de  los workloads
		
		
		final Double noTerminadas = (double) (duties.size() - terminadas.size());
		final Double term = (double) terminadas.size();
		
		/*
		averageDurationPeriodDuties=AdministratorDashboardShowService.ponerMinutosSobre60(averageDurationPeriodDuties);
		deviationDurationPeriodDuties=AdministratorDashboardShowService.ponerMinutosSobre60(deviationDurationPeriodDuties) ;
		averageWorkloadDuties=AdministratorDashboardShowService.ponerMinutosSobre60(averageWorkloadDuties);
		deviationWorkloadDuties=AdministratorDashboardShowService.ponerMinutosSobre60(deviationWorkloadDuties);
		*/

		result = new Dashboard();
		result.setAvegageNumberOfApplicationsPerEmployer(averageNumberOfApplicationsPerEmployer);
		result.setAverageNumberOfApplicationsPerWorker(averageNumberOfApplicationsPerWorker);
		result.setAverageNumberOfJobsPerEmployer(averageNumberOfJobsPerEmployer);
		result.setRatioOfPendingApplications(ratioOfPendingApplications);
		result.setRatioOfAcceptedApplications(ratioOfAcceptedApplications);
		result.setRatioOfRejectedApplications(ratioOfRejectedApplications);
		
		result.setNumberPublicDuty(numberPublicDuty);
		result.setNumberPrivateDuty(numberPrivateDuty);
		result.setNumberFinalDuty(term);
		result.setNumberNoFinalDuty(noTerminadas);
		result.setAverageDurationPeriodDuties(averageDurationPeriodDuties);
		result.setDeviationDurationPeriodDuties(deviationDurationPeriodDuties);
		result.setMinimumDurationPeriodDuties(minimumDurationPeriodDuties);
		result.setMaximumDurationPeriodDuties(maximumDurationPeriodDuties);
		result.setAverageWorkloadDuties(averageWorkloadDuties);
		result.setDeviationWorkloadDuties(deviationWorkloadDuties);
		result.setMaximumWorkloadDuties(maximumWorkloadDuties);
		result.setMinimumWorkloadDuties(minimumWorkloadDuties);
		
		//shouts
		result.setRatioEntidad1Important(ratioEntidad1Important);
		result.setAverageCurrency1(averageCurrency1);
		result.setAverageCurrency2(averageCurrency2);
		result.setRatioOfShoutsBudget0(ratioOfShoutsBudget0);
		result.setDeviationCurrency1(deviationCurrency1);
		result.setDeviationCurrency2(deviationCurrency2);

		return result;
	}
	
	private static double ponerMinutosSobre60(final Double numero) {
		double res;
		final BigDecimal num= BigDecimal.valueOf(numero);
		final long BDhoras=num.longValue();
		final BigDecimal BDminutos= num.remainder(BigDecimal.ONE);
		
		final String parteMinutos= ""+BDminutos;
		final String parteHoras= ""+BDhoras;
		
		Double minutos= new Double(parteMinutos);
		final Double horas= new Double(parteHoras);
		minutos=minutos*(60.0/100.0);
		res=horas+minutos;
		
		
		return res;
	}
	
	private static double calculateStandardDeviation(final List<Double> lista) {
		//Sumatorio de los valores de la lista
		double sum=0.0;
		for (int i =0; i< lista.size(); i++) {
			sum=sum+lista.get(i);
		}
		//Obtiene la media
		final double media = sum / lista.size();
		
		//Calcula la desviacion estandar
		double standardDeviation = 0.0;
		for (int i = 0; i < lista.size(); i++) {
			standardDeviation += Math.pow(lista.get(i) - media, 2);

		}
		return Math.sqrt(standardDeviation/lista.size());
	}


}
