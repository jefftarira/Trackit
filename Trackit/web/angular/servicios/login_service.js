var app = angular.module('appLogin.usuarios',[]);

app.factory('Usuarios', ['$http', '$q', function($http, $q){

	var self = {

		login : function(datos){

			var d = $q.defer();

			$http.post('service_jsp/serviceLogin.jsp',datos)
			.success(function( data ){
				d.resolve(data);
			});

			return d.promise;

		}
	};

	return self;

}])