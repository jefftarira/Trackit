var app = angular.module('alumnosApp.configuracion',[]);

app.factory('Configuracion', ['$http', '$q', function($http, $q){

	var self = {

		config : {},
		cargar: function(){

			var q = $q.defer();

			$http.get('configuracion.json')
			.success(function(data){
				self.config=data;

				q.resolve();

			})
			.error(function(){
				q.reject();
				console.error("Error al cargar configuracion");
			})

			return q.promise;
		},

		usuario : function(){
			var q = $q.defer();

			$http.post('service_jsp/cargarUsuario.jsp')
			.success(function(data){
				self.config=data;
				q.resolve();
			})
			.error(function(){
				q.reject();
				console.error("Error al cargar configuracion");
			})
			return q.promise;
		},

		cerrarSesion : function(){
			$http.post('logoff.jsp')
			.success( function( respuesta){
				location.reload();
			});
		}
	};
	return self;
}])