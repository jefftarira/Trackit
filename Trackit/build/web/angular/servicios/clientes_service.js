var app = angular.module('facturacionApp.clientes',[]);

app.factory('Clientes', ['$http', '$q', function($http, $q){

	var self = {
		'cargandoLista'   : false,
		'cargandoCliente' : false,
		'err'             : false,
		'conteo'          : 0,
		'clientes'        : [],
		'cliente'		  : [],
		'pag_actual'      : 1,
		'pag_siguiente'   : 1,
		'pag_anterior'    : 1,
		'total_paginas'   : 1,
		'paginas'         : [],

		cargarPagina : function(pag){

			self.cargandoLista = true;

			param = {
				'pagina' : pag,
				'max_reg' : 20
			}

			var d = $q.defer();
			$http.post('jsp/pageClientes.jsp',param)
			.success(function( data ){
				self.cargandoLista  = false;
				self.err            = data.err;        
				self.conteo         = data.conteo;      
				self.clientes       = data.clientes;     
				self.pag_actual     = data.pag_actual;  
				self.pag_siguiente  = data.pag_siguiente;
				self.pag_anterior   = data.pag_anterior;
				self.total_paginas  = data.total_paginas;
				self.paginas        = data.paginas;      

				return d.resolve();

			});

			return d.promise;

		},

		cargarCliente : function(cliente){

			self.cargandoCliente = true;

			var d = $q.defer();
			$http.post('jsp/cargarCliente.jsp',cliente)
			.success(function( data ){
				self.cargandoCliente    = false;
				self.err         = data.err; 
				self.cliente     = data.cliente;				

				return d.resolve();

			});
			return d.promise;
		}
	};

	return self;

}])