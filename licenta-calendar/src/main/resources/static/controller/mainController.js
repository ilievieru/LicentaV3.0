var app = angular.module("corola", ['ngMaterial']);
app.controller("homeController", function ($scope, $http) {
    console.log("homeController working...");

    $http.get("/firstResources").success(function (data) {
        $scope.backResources = data.first;
    })
    $http.get("/secondResources").success(function (data) {
        $scope.backSecondResources = data.first;
    })

    $scope.chosenFilter = "none";
    $scope.choseFilter = function (filter) {
        $scope.manual = false;
        $scope.semiAutomatic = false;
        $scope.automatic = false;
        if (filter == "Manual") {
            $scope.chosenFilter = "Manual";
            $scope.manual = true;
        }
        if (filter == "SemiAutomatic") {
            $scope.chosenFilter = "SemiAutomatic";
            $scope.semiAutomatic = true;
        }
        if (filter == "Automatic") {
            $scope.chosenFilter = "Automatic";
            $scope.automatic = true;
        }
    }

    $scope.chosenVersion = "none";
    $scope.choseVersion = function (version) {
        console.log("chose version");
        if (version == "Latest") {
            $scope.chosenVersion = "Latest";
        }
        if (version == "First") {
            $scope.chosenVersion = "First";
        }
        if (version == "Common") {
            $scope.chosenVersion = "Common";
        }
        ready();
    }

    $scope.inputType = "none";
    $scope.choseInputType = function (input) {
        $scope.file = false;
        $scope.text = false;
        if (input == "file") {
            $scope.inputType = "file";
            $scope.file = true;
        }
        if (input == "text") {
            $scope.inputType = "text";
            $scope.text = true;
        }
    }

    //about page set current
    $scope.chooseAbout = function (choose) {
        $scope.first = false;
        $scope.second = false;
        $scope.third = false;
        if (choose == "first")
            $scope.first = true;
        if (choose == "second")
            $scope.second = true;
        if (choose == "third")
            $scope.third = true;
    }

    //details page set current
    $scope.chooseDetails = function (choose) {
        $scope.author = false;
        $scope.resources = false;
        $scope.contact = false;
        if (choose == "Author")
            $scope.author = true;
        if (choose == "Resources")
            $scope.resources = true;
        if (choose == "Contact")
            $scope.contact = true;
    }

    $scope.uiFilter = function (filter) {
        $scope.ui = false;
        $scope.chart = false;
        if (filter == "ui") {
            $scope.ui = true;
            $scope.chart = false;
        }
        if (filter == "chart") {
            $scope.ui = false;
            $scope.chart = true;
        }
    }
});