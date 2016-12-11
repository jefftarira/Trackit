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

app.filter('mensajecorto',function(){
  return function(mensaje){
    if(mensaje){
      if (mensaje.length > 24)
        return mensaje.substr(0,25)+"...";
      else
        return mensaje;
    }
  }
})

app.filter("capitalize", function(){
  return function(text) {
    if(text != null){
      var palabras = text.split(" ");
      var nuevoTexto = "";

      for(var i=0; i<palabras.length ; i++){
        nuevoTexto += palabras[i].substring(0,1).toUpperCase() +  palabras[i].substring(1).toLowerCase() +" ";
      }
      return nuevoTexto;
    }
  }
})