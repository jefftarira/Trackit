var app = angular.module('alumnosApp.usuariosCtrl', []);

/*Controlador de usuarios*/


app.controller('usuariosCtrl', ['$scope','Usuarios', function($scope,Usuarios){

	$scope.activar('mUsuarios','','Usuarios','Lista');

	$scope.usuarios = Usuarios;
	$scope.usuarioSel = {};
	$scope.usuarioEd = {};
	$scope.registros = [{num : 20},{ num :50 }];
	$scope.buscar= "";

	$scope.moverA = function(pag,reg) {
		Usuarios.cargarPagina(pag,reg,$scope.buscar).then( function(){
			$scope.usuarios = Usuarios;
		});
	};

	$scope.buscarUsuario = function(){
		$scope.moverA(1,$scope.usuarios.reg_pagina);
	};

	$scope.selUsuario = function(usuario){
		angular.copy( usuario, $scope.usuarioSel);
	};

	$scope.moverA(1,20);

	/*Mostrar modal usuario en modo edicion y crear  */

	$scope.editar = function(usuario){
		$scope.usuarioEd = {};
		toastr.info('Cargando...', '',{"positionClass": "toast-bottom-center"});
		Usuarios.cargarUsuario(usuario).then(function(){
			$("#modal_usuario").modal({backdrop: 'static'});
			$scope.usuarioEd = Usuarios.usuario;
			toastr.remove();
		});
	};

	$scope.guardarUsuario = function(usuario,frmUsuario){
		Usuarios.guardarUsuario(usuario,$scope.buscar).then( function(){
			if(!Usuarios.err){

				$("#modal_usuario").modal('hide');
				$scope.usuarioEd = {};
				frmUsuario.autoValidateFormOptions.resetForm();
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
				toastr.success(Usuarios.mensaje, '');
			}
			else{
				toastr.error(Usuarios.mensaje, '');
			}

		});
	}

}]);