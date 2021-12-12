<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="30%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="30%"/>
	<acme:list-column code="anonymous.shout.list.label.atributo1" path="entidad1.atributo1" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.atributo2" path="entidad1.atributo2" width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.atributo3" path="entidad1.atributo3" width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.atributo4" path="entidad1.atributo4" width="10%"/>
</acme:list>