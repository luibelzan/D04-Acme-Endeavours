<%--
- menu.jsp
-
- Copyright (c) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.all-jobs" action="/anonymous/job/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.list-shouts-recent" action="/anonymous/shout/list-recent"/>
			<acme:menu-suboption code="master.menu.anonymous.create-shout" action="/anonymous/shout/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.list-duty" action="/anonymous/duty/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.money-exchage" action="/authenticated/money-exchange/perform"/>
			<acme:menu-suboption code="master.menu.authenticated.list-public-finished-duty" action="/authenticated/duty/list-public-finished"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.officer" access="hasRole('Officer')">
			<acme:menu-suboption code="master.menu.officer.list-duties" action="/officer/duty/list"/>
			<acme:menu-suboption code="master.menu.officer.create-duties" action="/officer/duty/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/master/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/master/populate-sample"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.spamWordsList" action="/administrator/configuration/list"/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.create-job" action="/employer/job/create"/>
			<acme:menu-suboption code="master.menu.employer.all-jobs" action="/employer/job/list-all"/>
			<acme:menu-suboption code="master.menu.employer.my-jobs" action="/employer/job/list-mine"/>			
			<acme:menu-suboption code="master.menu.employer.my-applications" action="/employer/application/list"/>			
		</acme:menu-option>

		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.all-jobs" action="/worker/job/list-all"/>	
			<acme:menu-suboption code="master.menu.worker.my-applications" action="/worker/application/list"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-officer" action="/authenticated/officer/create" access="!hasRole('Officer')"/>
			<acme:menu-suboption code="master.menu.user-account.officer" action="/authenticated/officer/update" access="hasRole('Officer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

