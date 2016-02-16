<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="../../includes/head.jsp"%>

<div id="page-wrapper">
	<%@include file="../../includes/mensaje.jsp"%>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Peliculas</h1>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-user fa-fw"></i> Peliculas de la Base de Datos
				<div class="pull-right">
					<div class="btn-group">
						<button type="button" class="btn btn-default btn-xs">
							<a href="<%=Constantes.CONTROLLER_PELICULAS%>?op=<%=Constantes.OP_NUEVO%>"
								title="Crear registro"> <i class="fa fa-plus fa-fw"></i>
								Añadir Pelicula
							</a>
						</button>
					</div>
				</div>
			</div>
			<%@include file="../../includes/listaPeliculas.jsp"%>

		</div>
	</div>
</div>
<!-- /#page-wrapper -->

<%@include file="../../includes/footer.jsp"%>