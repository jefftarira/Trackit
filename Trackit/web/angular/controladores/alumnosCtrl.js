var app = angular.module('alumnosApp.alumnosCtrl', []);

/*Controlador de usuarios*/


app.controller('alumnosCtrl', ['$scope',"Alumnos", function($scope,Alumnos){

	$scope.activar('mAlumnos','','Alumnos','Lista');

	$scope.alumnos = Alumnos;
	$scope.alumnoSel = {};
	$scope.alumnoEd = {};
	$scope.usuarios = [];
	$scope.historial = [];
	$scope.buscar= "";

	$scope.moverA = function(pag) {
		Alumnos.cargarPagina(pag,$scope.buscar).then( function(){
			$scope.alumnos = Alumnos;
		});
	};

	$scope.buscarAlumno = function(){
		$scope.moverA(1);
	};

	$scope.selAlumno = function(alumno){
		angular.copy( alumno, $scope.alumnoSel);
	};

	$scope.moverA(1);

	/*Mostrar modal usuario en modo edicion y crear*/

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

	$scope.guardar = function(alumno,frmAlumno){
		console.log(alumno);
		Alumnos.guardar(alumno,$scope.buscar).then( function(){
			$("#modal_alumno").modal('hide');
			$scope.alumnoEd = {};
			frmAlumno.autoValidateFormOptions.resetForm();
			toastr.options = {
				"closeButton": true,
				"debug": false,
				"newestOnTop": true,
				"progressBar": true,
				"positionClass": "toast-bottom-left",
				"preventDuplicates": false,
				"onclick": null,
				"showDuration": "300",
				"hideDuration": "1000",
				"timeOut": "2000",
				"extendedTimeOut": "1000",
				"showEasing": "swing",
				"hideEasing": "linear",
				"showMethod": "fadeIn",
				"hideMethod": "fadeOut"
			};
			toastr.success('Se guardo correctamente', '');
		});
	}


}]);