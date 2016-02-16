<%@page import="com.ipartek.formacion.oscarfonticoba.Constantes"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.oscarfonticoba.pojo.Pelicula"%>
<%@page import="com.ipartek.formacion.oscarfonticoba.Constantes"%>


<!-- /.panel-heading -->
<div class="panel-body">
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table
					class="datatable table table-bordered table-hover table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Titulo</th>
							<th>Genero</th>
							<th>Duracion</th>
						</tr>
					</thead>
					<tbody>
						<%
							//recoger "atributo listado personas de la request
							ArrayList<Pelicula> lista = (ArrayList<Pelicula>) request.getAttribute("listaPeliculas");
							if (lista == null)
								lista = new ArrayList<Pelicula>();

							for (int i = 1; i <= lista.size(); i++) {
								Pelicula peli = lista.get(i - 1);
						%>
						<tr>
							<td><%=i%></td>
							<td><a href="<%=Constantes.CONTROLLER_PELICULAS%>?op=<%=Constantes.OP_DETALLE%>&id=<%=peli.getId()%>"
								title="Ir al detalle de <%=peli.getTitulo()%>"><%=peli.getTitulo()%></a></td>
							<td><%=peli.getGenero()%></td>
							<td><%=peli.getDuracion()%></td>
	
						</tr>
						<%
							} //end for
						%>
					</tbody>
				</table>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /.col-lg-4 (nested) -->
		<div class="col-lg-8">
			<div id="morris-bar-chart"></div>
		</div>
		<!-- /.col-lg-8 (nested) -->
	</div>
	<!-- /.row -->
</div>
<!-- /.panel-body -->