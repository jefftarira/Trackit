var app = angular.module('alumnosApp.dashboardCtrl', []);

/*Controlador del cliente*/


app.controller('dashboardCtrl', ['$scope','Alumnos', function($scope,Alumnos){

	$scope.activar('mDashboard','','Dashboard','Pagina principal');

	$scope.alumnos = Alumnos;
	$scope.alumnoSel = {};
	$scope.alumnoEd = {};
	$scope.usuarios = [];
	$scope.historial = [];
	$scope.buscar= "";

	$scope.cargar = function() {
		Alumnos.cargarLista().then( function(){
			$scope.alumnos = Alumnos;
		});
	};

	$scope.selAlumno = function(alumno){
		angular.copy( alumno, $scope.alumnoSel);
	};

	$scope.cargar();

	$scope.verHistorial = function(alumno){
		angular.copy( alumno, $scope.alumnoSel);
		$scope.historial= [];
		Alumnos.cargarHistorial(alumno.id).then( function(){
			$scope.historial = Alumnos.historial;
			$("#modal_historial").modal();
		});
	};

	$scope.editar = function(alumno){
		$scope.alumnoEd = {};
		toastr.info('Cargando...', '',{"positionClass": "toast-bottom-center"});

		Alumnos.cargarAlumno(alumno).then( function(){
			$("#modal_alumno").modal({backdrop: 'static'});
			$(".select2").select2();
			$scope.alumnoEd = Alumnos.alumno;
			$scope.usuarios = Alumnos.usuarios;

			toastr.remove();
		});
	};


}])