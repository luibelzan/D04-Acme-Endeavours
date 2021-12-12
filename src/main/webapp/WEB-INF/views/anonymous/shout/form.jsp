<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	<acme:form-textbox code="anonymous.shout.form.label.atributo1" placeholder="dd-wwdd-mmyy" path="entidad1.atributo1"/>
	<acme:form-moment code="anonymous.shout.form.label.atributo2" path="entidad1.atributo2"/>
	<acme:form-money code="anonymous.shout.form.label.atributo3" path="entidad1.atributo3"/>
	<acme:form-checkbox code="anonymous.shout.form.label.atributo4" path="entidad1.atributo4"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
  	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>