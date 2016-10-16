var app = angular.module('alumnosApp.alumnos',[]);

app.factory('Alumnos', ['$http', '$q', function($http, $q){

	var self = {
		'cargandoLista'    : false,
		'cargandoAlumno'   : false,
		'err'              : false,
		'conteo'           : 0,
		'alumnos'          : [],
		'alumno'		       : [],
		'usuarios'				 : [],
		'historial'				 : [],
		'pag_actual'       : 1,
		'pag_siguiente'    : 1,
		'pag_anterior'     : 1,
		'total_paginas'    : 1,
		'paginas'          : [],

		cargarPagina : function(pag,vBuscar){
			console.log(vBuscar);
			self.cargandoLista = true;
			param = {
				'pagina' : pag,
				'max_reg' : 20,
				'buscar' : vBuscar
			}
			var d = $q.defer();
			$http.post('service_jsp/listaAlumnos.jsp',param)
			.success(function( data ){
				self.cargandoLista  = false;
				self.err            = data.err;
				self.conteo         = data.conteo;
				self.alumnos        = data.alumnos;
				self.pag_actual     = data.pag_actual;
				self.pag_siguiente  = data.pag_siguiente;
				self.pag_anterior   = data.pag_anterior;
				self.total_paginas  = data.total_paginas;
				self.paginas        = data.paginas;
				d.resolve();
			});
			return d.promise;
		},

		cargarHistorial : function(idAlumno){
			param = {
				'id' : idAlumno
			}
			var d = $q.defer();
			$http.post('service_jsp/listaHistorial.jsp',param)
			.success(function( data ){
				self.err            = data.err;
				self.historial      = data.historial;
				d.resolve();
			});
			return d.promise;
		},

		cargarAlumno : function(alumno){

			self.cargandoAlumno = true;

			var d = $q.defer();
			$http.post('service_jsp/cargarAlumno.jsp',alumno)
			.success(function( data ){
				self.cargandoAlumno   = false;
				self.err              = data.err;
				self.alumno           = data.alumno;
				self.usuarios         = data.usuarios;
				d.resolve();
			});
			return d.promise;
		},

		guardar : function(alumno,vBuscar){

			var d = $q.defer();
			$http.post('service_jsp/guardarAlumnos.jsp',alumno)
			.success(function( data ){
				self.cargarPagina(self.pag_actual,vBuscar);
				d.resolve();
			});
			return d.promise;
		}
	};
	return self;

}])