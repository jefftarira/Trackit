var app = angular.module('alumnosApp.usuarios',[]);

app.factory('Usuarios', ['$http', '$q', function($http, $q){

	var self = {
		'cargandoLista'    : false,
		'cargandoUsuario'	 : false,
		'err'              : false,
		'conteo'           : 0,
		'usuarios'         : [],
		'usuario'		       : [],
		'reg_pagina'			 : 20,
		'pag_actual'       : 1,
		'pag_siguiente'    : 1,
		'pag_anterior'     : 1,
		'total_paginas'    : 1,
		'paginas'          : [],

		cargarPagina : function(pag,reg,vBuscar){
			self.cargandoLista = true;

			param = {
				'pagina'  : pag,
				'max_reg' : reg,
				'buscar'  : vBuscar
			}

			var d = $q.defer();
			$http.post('service_jsp/listaUsuarios.jsp',param)
			.success(function( data ){
				self.cargandoLista  = false;
				self.err            = data.err;
				self.conteo         = data.conteo;
				self.usuarios       = data.usuarios;
				self.reg_pagina     = data.reg_pagina;
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
			$http.post('service_jsp/cargarUsuario.jsp',usuario)
			.success(function( data ){
				self.cargandoUsuario    = false;
				self.err         = data.err;
				self.usuario     = data.usuario;

				return d.resolve();

			});
			return d.promise;
		},

		guardar : function(usuario,vBuscar){

			var d = $q.defer();
			$http.post('service_jsp/guardarUsuario.jsp',usuario)
			.success(function( data ){
				self.err = data.err;
				self.mensaje = data.mensaje;
				self.usuario = data.usuario;
				if(!data.err){
					self.cargarPagina(self.pag_actual,self.reg_pagina,vBuscar);
				}
				d.resolve();
			});
			return d.promise;
		}
	};

	return self;

}])