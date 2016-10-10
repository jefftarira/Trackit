var app= angular.module('alumnosApp', [
	'ngRoute','jcs-autoValidate',
	'alumnosApp.configuracion',
	'alumnosApp.dashboardCtrl',
	'alumnosApp.usuariosCtrl',
	'alumnosApp.usuarios',
	'alumnosApp.alumnosCtrl',
	'alumnosApp.alumnos'
]);

angular.module('jcs-autoValidate')
	.run([
	'defaultErrorMessageResolver',
	function (defaultErrorMessageResolver) {
		// To change the root resource file path
		defaultErrorMessageResolver.setI18nFileRootPath('angular/lib');
		defaultErrorMessageResolver.setCulture('es-co');
	}
]);

app.controller('mainCtrl', ['$scope','Configuracion',function($scope,Configuracion){
	$scope.config   = {};
	// $scope.mensajes = Mensajes.mensajes;
	// $scope.notificaciones = Notificaciones.notificaciones;
	$scope.titulo    = "";
	$scope.subtitulo ="";

	$scope.usuario = {
		nombre : "Jefferson Tarira",
		fecha_registro : "2 Agosto 2016"
	};

	$scope.cerrarSesion = function(){
		Configuracion.cerrarSesion();
	}

	Configuracion.cargar().then(function(){
		$scope.config = Configuracion.config;
	});



	/*Funciones globales*/

	$scope.activar = function(menu , submenu, titulo , subtitulo){
		$scope.titulo    = titulo;
		$scope.subtitulo = subtitulo;

		$scope.mDashboard = "";
		$scope.mAlumnos  = "";
		$scope.mUsuarios = "";

		$scope[menu] ='active';

	};

}]);


app.config(['$routeProvider',function($routeProvider){
	$routeProvider
		.when('/',{
		templateUrl: 'template/dashboard.html',
		controller : 'dashboardCtrl'

	}).when('/alumnos',{
		templateUrl: 'template/alumnos.html',
		controller : 'alumnosCtrl'

	}).when('/usuarios',{
		templateUrl: 'template/usuarios.html',
		controller : 'usuariosCtrl'

	})
		.otherwise({
		redirectTo: '/'
	})
}]);


/*Filtros*/

app.filter('quitarletra',function(){
	return function(palabra){
		if(palabra){
			if (palabra.length > 1)
				return palabra.substr(1);
			else
				return palabra;
		}
	}
})

	.filter('mensajecorto',function(){
	return function(mensaje){
		if(mensaje){
			if (mensaje.length > 20)
				return mensaje.substr(0,37)+"...";
			else
				return mensaje;
		}
	}
})