var app = angular.module('alumnosApp.usuarios',[]);

app.factory('Usuarios', ['$http', '$q', function($http, $q){

	var self = {
		'cargandoLista'    : false,
		'cargandoUsuario' : false,
		'err'              : false,
		'conteo'           : 0,
		'usuarios'         : [],
		'usuario'		       : [],
		'pag_actual'       : 1,
		'pag_siguiente'    : 1,
		'pag_anterior'     : 1,
		'total_paginas'    : 1,
		'paginas'          : [],

		cargarPagina : function(pag){

			self.cargandoLista = true;

			param = {
				'pagina' : pag,
				'max_reg' : 20
			}

			var d = $q.defer();
			$http.post('service_jsp/listaUsuarios.jsp',param)
			.success(function( data ){
				self.cargandoLista  = false;
				self.err            = data.err;
				self.conteo         = data.conteo;
				self.usuarios       = data.usuarios;
				self.pag_actual     = data.pag_actual;
				self.pag_siguiente  = data.pag_siguiente;
				self.pag_anterior   = data.pag_anterior;
				self.total_paginas  = data.total_paginas;
				self.paginas        = data.paginas;

				return d.resolve();

			});

			return d.promise;

		},

		cargarUsuario : function(usuario){

			self.cargandoCliente = true;

			var d = $q.defer();
			$http.post('service_jsp/cargaUsuario.jsp',usuario)
			.success(function( data ){
				self.cargandoUsuario    = false;
				self.err         = data.err; s
				self.usuario     = data.usuario;

				return d.resolve();

			});
			return d.promise;
		},

		guardarUsuario : function(usuario){

			var d = $q.defer();
			$http.post('service_jsp/guardarUsuario.jsp',usuario)
			.success(function( data ){
				self.cargandoUsuario    = false;
				self.err         				= data.err; s
				self.usuario     				= data.usuario;

				return d.resolve();

			});
			return d.promise;
		}
	};

	return self;

}])