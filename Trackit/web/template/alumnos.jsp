
<%@page import="GENERAL.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
Usuarios u;
u = (Usuarios) session.getAttribute("usuario");
%>

<ol class="breadcrumb animated fadeIn fast">
  <li><a href=""><i class="fa fa-odnoklassniki"></i> Alumnos</a></li>
</ol>

<div class="row">
<div class="box">
    <div class="box-body">
      <div class="col-md-3 margin-bottom">
        <div class="">
          <div class="btn-group">
            <button type="button" class="btn btn-default" ng-click="editar({})">Agregar alumno</button>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-default" ng-click="moverA(alumnos.pag_actual)">
              <i class="fa fa-circle-o-notch" ng-class="{'fa-spin': alumnos.cargandoLista }"></i>
            </button>
          </div>
        </div>
      </div>
      <div class="col-md-5 margin-bottom">
        <div class="btn-group">
          <div class="input-group" style="width: 100%;">
            <input type="text" name="table_search" class="form-control pull-right" placeholder="Buscar por Alumno o direccion" ng-model="buscar" ng-change="buscarAlumno()">
            <div class="input-group-btn">
              <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <ul class="pagination pagination-sm no-margin pull-right">
          <li><a ng-click="moverA(1)">«</a></li>
          <li ng-repeat="pagina in alumnos.paginas" ng-class="{'active' : alumnos.pag_actual === pagina.num } ">
            <a ng-click="moverA(pagina.num)">
              {{pagina.num}}
            </a>
          </li>
          <li><a ng-click="moverA(alumnos.total_paginas)">»</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>

<div class="row animated fadeIn fast">

  <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3" ng-repeat="alumno in alumnos.alumnos">
    <div class="box box-widget">
      <div class="box-body box-hover" ng-click="editar(alumno)">

        <h5 class="no-margin">
          <strong>{{ alumno.nombres | capitalize }} {{ alumno.apellidos | capitalize }}</strong>
        </h5>
        <p class="no-margin"><i class="fa fa-map-marker"></i><span> {{ alumno.direccion | mensajecorto | lowercase}}</span></p>
        <address class="no-margin">
          <p class="text-muted no-margin">{{alumno.u_nombre| capitalize}} {{alumno.u_apellido| capitalize}}</p>
          <!-- <small>{{ alumno.cedula }}</small> -->
          <i class="fa fa-calendar-o"></i> {{ alumno.registro | date:"MMM d, yyyy" }}
          <span class="pull-right">
            <small class="text-muted"> {{alumno.registro| date: "h:mm a"}}</small>
          </span>
        </address>
      </div>
      <div class="box-footer no-padding">
        <div class="btn-group">
          <button class="btn btn-link" ng-click="verHistorial(alumno)">
            <i class="fa fa-location-arrow margin-r-5"></i>ver ubicaciones
          </button>
        </div>

        <div class="btn-group pull-right margin-top margin-right">
          <span class="label" ng-class="{'bg-green' : alumno.estado === 'ACTIVO' , 'bg-red' : alumno.estado === 'INACTIVO' }">{{alumno.estado}}
          </span>
        </div>
      </div>
    </div>
  </div>


  <!-- 	<div class="col-xs-12">
      <div class="box">

          <div class="box-header">
              <i class="fa fa-circle-o-notch fa-spin" ng-hide="alumnos.cargandoLista == false"></i>
              <div class="overlay" ng-hide="alumnos.cargandoLista == false">
                  <i class="fa fa-refresh fa-spin"></i>
              </div>
          </div>

          <div class="box-tittle">
              Buscar
          </div>
          <div class="box-tools">

          </div>

          <div class="box-body">
              <div class="row margin">
                  <div class="col-md-7 no-padding">
                      <div class="btn-group">
                      </div>
                  </div>
                  <div class="col-md-5 no-padding pull-right">
                      <ul class="pagination pagination-sm no-margin pull-right">
                          <li><a ng-click="moverA(1)">«</a></li>
                          <li ng-repeat="pagina in alumnos.paginas" ng-class="{'active' : alumnos.pag_actual===pagina.num } ">
                              <a ng-click="moverA(pagina.num)">
                                  {{pagina.num}}
                              </a>
                          </li>
                          <li><a ng-click="moverA(alumnos.total_paginas)">»</a></li>
                      </ul>
                  </div>
              </div>
              <table class="table table-hover table-condensed">
                  <thead>
                      <tr>
                          <th></th>
                          <th>ID</th>
                          <th>Alumno</th>
                          <th>Direccion</th>
                          <th>Cuenta</th>
                          <th>Fecha Ingreso</th>
                          <th>Estado</th>
                      </tr>
                  </thead>
                  <tbody>
                      <tr ng-repeat="alumno in alumnos.alumnos">
                          <td>
                              <div class="btn-group">
                                  <a type="button" class="btn btn-link " data-toggle="dropdown">
                                      <i class="glyphicon glyphicon-option-vertical"></i>
                                  </a>
                                  <ul class="dropdown-menu" role="menu">
                                      <li><a href="" ng-click="editar(alumno)" ><i class="fa fa-pencil"></i>Editar</a></li>
                                      <li><a href="" ng-click="verHistorial(alumno)" ><i class="fa fa-map-marker"></i>Ubicaciones</a></li>
                                  </ul>
                              </div>
                          </td>
                          <td>{{ alumno.id }}</td>
                          <td>
                              <p  class="no-margin"> {{ alumno.nombres | capitalize }}
                                  <p>{{ alumno.apellidos | capitalize }}</p>
                              </p>
                          </td>
                          <td> {{ alumno.direccion | lowercase}}</td>
                          <td>
                              <p class="no-margin">{{alumno.u_nombre | capitalize}} {{alumno.u_apellido | capitalize}}</p>
                              <small class="text-muted">{{ alumno.cedula }}</small>
                          </td>
                          <td>
                              <p class="no-margin">{{ alumno.registro | date:"MMM d, yyyy" }}</p>
                              <small class="text-muted"><i class="fa fa-clock-o"></i> {{alumno.registro | date: "h:mm a"}}</small>
                          </td>
                          <td>
                              <span class="label " ng-class="{'bg-green' : alumno.estado ==='ACTIVO' , 'bg-red' : alumno.estado === 'INACTIVO' }">{{alumno.estado}}
                              </span>
                          </td>
                      </tr>
                  </tbody>
              </table>
          </div>

          <div class="box-footer clearfix">
              <ul class="pagination pagination-sm no-margin pull-right">
                  <li><a ng-click="moverA(1)">«</a></li>

                  <li ng-repeat="pagina in alumnos.paginas" ng-class="{'active' : alumnos.pag_actual===pagina.num } ">
                      <a ng-click="moverA(pagina.num)">
                          {{pagina.num}}
                      </a>
                  </li>

                  <li><a ng-click="moverA(alumnos.total_paginas)">»</a></li>
              </ul>
          </div>

      </div>

    </div> -->
  </div>

  <%
  if (u.getTipo().trim().equals("ADMINISTRADOR")) {
  %>
  <div ng-include="'template/modal_alumno.html'"></div>
  <%
} else {
%>
<div ng-include="'template/modal_alumnoST.html'"></div>
<%
}
%>
<div ng-include="'template/modal_historial.html'"></div>
