/*var app = angular.module("LogIn", ['ngMaterial']);*/
app.controller("authController", function ($scope, $http) {
    console.log("authController...");
    $scope.login = false;
    $scope.beforeLogin = true;
    $scope.auth = function () {
        $scope.userName = document.getElementById("userName").value;
        $scope.password = document.getElementById("password").value;
        $http({
            method: 'GET',
            url: '/Auth',
            params: {
                userName: $scope.userName,
                password: $scope.password
            }
        })
            .success(function (results) {
                console.log(results);
                if (results.Success == true) {
                    window.location.replace("mainPage.html");
                }
                else {
                    $scope.login = true;
                    $scope.beforeLogin = false;
                }
            })
            .error(function () {
                console.log("error");
                $scope.login = true;
                $scope.beforeLogin = false;
            });
    }
});