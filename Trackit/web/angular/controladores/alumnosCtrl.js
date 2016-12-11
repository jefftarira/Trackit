var app = angular.module('alumnosApp.alumnosCtrl', []);

/*Controlador de usuarios*/


app.controller('alumnosCtrl', ['$scope', "Alumnos", function ($scope, Alumnos) {

        $scope.activar('mAlumnos', '', 'Alumnos', 'Lista');

        $scope.alumnos = Alumnos;
        $scope.alumnoSel = {};
        $scope.alumnoEd = {};
        $scope.usuarios = [];
        $scope.historial = [];
        $scope.registros = [{num: 20}, {num: 50}, {num: 100}];
        $scope.buscar = "";

        setInterval(function () {
            $scope.moverA($scope.alumnos.pag_actual, $scope.alumnos.reg_pagina);
        }, 5000);
        
        $scope.moverA = function (pag, reg) {
            Alumnos.cargarPagina(pag, reg, $scope.buscar).then(function () {
                $scope.alumnos = Alumnos;
            });
        };

        $scope.buscarAlumno = function () {
            $scope.moverA(1, $scope.alumnos.reg_pagina);
        };

        $scope.selAlumno = function (alumno) {
            angular.copy(alumno, $scope.alumnoSel);
        };

        $scope.moverA(1, 20);

        /*Mostrar modal usuario en modo edicion y crear*/

        $scope.verHistorial = function (alumno) {
            angular.copy(alumno, $scope.alumnoSel);
            $scope.historial = [];
            Alumnos.cargarHistorial(alumno.id).then(function () {
                $scope.historial = Alumnos.historial;
                $("#modal_historial").modal();
            });
        };

        $scope.editar = function (alumno) {
            $scope.alumnoEd = {};
            toastr.info('Cargando...', '', {"positionClass": "toast-bottom-center"});

            Alumnos.cargarAlumno(alumno).then(function () {
                $("#modal_alumno").modal({backdrop: 'static'});
                $(".select2").select2();
                $scope.alumnoEd = Alumnos.alumno;
                $scope.usuarios = Alumnos.usuarios;
                console.log($scope.alumnoEd);
                toastr.remove();
            });
        };

        $scope.guardar = function (alumno, frmAlumno) {
            Alumnos.guardar(alumno, $scope.buscar).then(function () {

                if (!Alumnos.err) {

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
                    toastr.success(Alumnos.mensaje, '');
                } else {
                    toastr.error(Alumnos.mensaje, '');
                }
            });
        }

    }]);