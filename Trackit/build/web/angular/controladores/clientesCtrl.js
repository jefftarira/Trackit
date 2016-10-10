var app = angular.module('facturacionApp.clientesCtrl', []);

/*Controlador del cliente*/


app.controller('clientesCtrl', ['$scope', 'Clientes', function($scope,Clientes){

	$scope.activar('mClientes','','Clientes','listado');

	$scope.clientes = Clientes;
	$scope.clienteSel = {};
	$scope.clienteEd = {};
	

	$scope.moverA = function(pag) {
		Clientes.cargarPagina(pag).then( function(){
			$scope.clientes = Clientes;
		});
	};

	$scope.selCliente = function(cliente){
		angular.copy( cliente, $scope.clienteSel);
	};

	$scope.moverA(1);

	/*Mostrar modal cliente en modo edicion y crear  */

	$scope.mostrarModal = function( clienteSel){
		$scope.clienteEd = {};
		Clientes.cargarCliente(clienteSel).then(function(){			
			$scope.clienteEd = Clientes.cliente;			
		});

		$("#modal_cliente").modal();
		

	};

}]);