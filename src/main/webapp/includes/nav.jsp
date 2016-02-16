<!-- /.navbar-top-links -->

<%@page import="com.ipartek.formacion.oscarfonticoba.Constantes"%>
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			
			<li><a href="<%=Constantes.VIEW_INDEX%>"><i
					class="fa fa-dashboard fa-fw"></i> Página principal</a></li>
			<li>
				<!-- vamos a llamar al controlador en lugar del jsp --> <a
				href="<%=Constantes.CONTROLLER_PELICULAS%>"><i
					class="fa fa-user fa-fw"></i> Peliculas</a>
			</li>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
</nav>
<!-- End:Navigation -->