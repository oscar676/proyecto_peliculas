
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page
	import="com.ipartek.formacion.oscarfonticoba.Constantes"%>
<%@page import="com.ipartek.formacion.oscarfonticoba.pojo.Pelicula"%>

<%@include file="../../includes/head.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<%
				//recoger "atributo persona de la request
				Pelicula peli = (Pelicula) request.getAttribute("pelicula");
				boolean isNew = (peli.getId() == -1);
			%>
			<h1 class="page-header"><%=peli.getTitulo()%></h1>
		</div>

		<!-- Formularios -->
		<form method="post" action="back/peliculas">

			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"
					style="margin: 10px 0;">ID</label>
				<div class="col-sm-10">
					<label for="id" class="col-sm-2 control-label"
						style="margin: 10px 0;"><%=peli.getId()%></label>
				</div>
			</div>

			<div class="form-group">
				<label for="titulo" class="col-sm-2 control-label"
					style="margin: 10px 0;">Titulo</label>
				<div class="col-sm-10">
					<input type="text" name="titulo" placeholder="Escribe el titulo"
						value="<%=peli.getTitulo()%>" required
						style="margin: 10px 0;"
						size="60" autofocus>
				</div>
			</div>

			<div class="form-group">
				<label for="genero" class="col-sm-2 control-label"
					style="margin: 10px 0;">Genero</label>
				<div class="col-sm-10">
					<input type="text" name="genero" placeholder="Escribe el genero"
						value="<%=peli.getGenero()%>" size="60" required style="margin: 10px 0;">
				</div>
			</div>

			<div class="form-group">
				<label for="duracion" class="col-sm-2 control-label"
					style="margin: 10px 0;">Duracion</label>
				<div class="col-sm-10">
					<input type="text" name="duracion" placeholder="Escribe la duracion en minutos"
						value="<%=peli.getDuracion()%>" size="60" required style="margin: 10px 0;">
				</div>
			</div>

	
			
						
			<input type="hidden" name="id" value="<%=peli.getId()%>"> <input
				type="hidden" name="titulo" value="<%=peli.getTitulo()%>"> <input
				type="hidden" name="genero" value="<%=peli.getGenero()%>"> <input
				type="hidden" name="duracion" value="<%=peli.getDuracion()%>"> 
				 <input
				type="hidden" name="op"
				value="<%=Constantes.OP_MODIFICAR%>">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<%
						if (isNew) {
					%>
					<button type="submit" class="btn btn-primary"
						style="margin: 10px 0;">Crear</button>
					<%
						} else {
					%>
					<button type="submit" class="btn btn-primary"
						style="margin: 10px 0;">Modificar</button>
					<button type="button" class="btn btn-danger" data-toggle="modal"
						data-target="#eliminar">Eliminar</button>
					<%
						}
					%>
				</div>
			</div>
		</form>
		<!-- Pop Up de eliminación-->
		<div class="modal fade" id="eliminar" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Cerrar</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Eliminación de
							pelicula</h4>
					</div>
					<div id="nuevaAventura" class="modal-body">
						<form method="post" action="back/peliculas">
							<div class="form-group">
								<input type="hidden" name="op"
									value="<%=Constantes.OP_ELIMINAR%>"> <input
									type="hidden" name="id" value="<%=peli.getId()%>"> <label
									for="aviso">¿Está seguro que desea eliminar la pelicula?
									Esta acción no se puede deshacer.</label>
								<button type="submit" class="btn btn-danger" id="eliminacion"
									style="margin: 10px 0;">Eliminar</button>
								<button type="button" class="btn btn-success"
									data-dismiss="modal" style="margin: 10px 0;">Cerrar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- fin Pop Up de eliminación-->
	</div>
	<!-- END <div class="row"> -->
</div>
<!-- END <div id="page-wrapper"> -->

<%@include file="../../includes/footer.jsp"%>