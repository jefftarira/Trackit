var app= angular.module('appLogin', [
	'alumnosApp.configuracion',
	'appLogin.usuarios',
	]);

/*angular.module('jcs-autoValidate')
.run([
    'defaultErrorMessageResolver',
    function (defaultErrorMessageResolver) {
        // To change the root resource file path
        defaultErrorMessageResolver.setI18nFileRootPath('angular/lib');
        defaultErrorMessageResolver.setCulture('es-co');
    }
]);*/

app.controller('loginCtrl', ['$scope','Configuracion','Usuarios',function($scope,Configuracion,Usuarios){
	$scope.config   = {};
	$scope.invalido = false;
	$scope.cargando = false;
	$scope.mensaje = "";
	$scope.btnIngresar = "Ingresar";

	Configuracion.cargar().then(function(){
		$scope.config = Configuracion.config;
	});

	$scope.ingresar =  function (datos){

		$scope.invalido = false;
		$scope.cargando = true;
		$scope.btnIngresar = "Autenticando..."

		Usuarios.login(datos).then(function(data){
		    $scope.invalido = data.err;
		    $scope.mensaje = data.mensaje;
		    $scope.cargando = false;
        $scope.btnIngresar = "Ingresar";

		    if(!data.err){
		    	location.reload();
		    }
		});
	}

}]);