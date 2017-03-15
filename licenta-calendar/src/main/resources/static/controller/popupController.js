app.controller("popupController", function ($scope, $http) {
    console.log("popup controller ...");
    $scope.showPopup = false;

    $scope.click = function () {
        $scope.message = "click";
        $scope.showPopup = true;
    }
});
