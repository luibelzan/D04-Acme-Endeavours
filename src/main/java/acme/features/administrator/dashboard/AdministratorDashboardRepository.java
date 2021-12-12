/*
 * AdministratorDashboardRepository.java
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

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select avg(select count(a) from Application a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.PENDING")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.ACCEPTED")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.REJECTED")
	Double ratioOfRejectedApplications();
	
	
	
	@Query("select d from Duty d")
	Collection<Duty> findDuties();
	
	@Query("select 1.0 * count(d) from Duty d  where d.isPublic= 1")
	Double numberPublicDuty();
	
	@Query("select 1.0 * count(d) from Duty d  where d.isPublic = 0")
	Double numberPrivateDuty();
	
	@Query("select avg(d.workloadInHours) from Duty d")
	Double averageWorkloadDuties();
	
	@Query("select stddev(d.workloadInHours) from Duty d")
	Double deviationWorkloadDuties();
	
	@Query("select min(d.workloadInHours) from Duty d")
	Double minimumWorkloadDuties();
	
	@Query("select max(d.workloadInHours) from Duty d")
	Double maximumWorkloadDuties();
	
	//Shouts
	@Query("select s from Shout s")
	Collection<Shout> findShouts();
			
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.entidad1.atributo4 = true")
	Double ratioEntidad1Important();
						
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.entidad1.atributo3.amount = 0.0")
	Double ratioOfShoutsBudget0();
			
	@Query("select avg(e.atributo3.amount) from Entidad1 e group by e.atributo3.currency")
	List<Double> averageEntidad1GroupByCurrency();
						
	@Query("select stddev(e.atributo3.amount) from Entidad1 e group by e.atributo3.currency")
	List<Double> deviationEntidad1GroupByCurrency();

}
