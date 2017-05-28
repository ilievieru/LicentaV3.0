var app = angular.module("LogIn", ['ngMaterial']);
app.controller("authController", function ($scope, $http) {
    console.log("authController...");
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
                window.location.replace("mainPage.html");
            })
            .error(function () {
                console.log("error");
            });
    }
});