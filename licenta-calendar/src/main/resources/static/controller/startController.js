app.controller("startController", function ($scope, $http) {
    console.log("start controller");
    $scope.results="Text for test";

    $scope.ready = function () {
        return true;
    }

    $scope.statProcess = function () {
        $http({
            method: 'GET',
            url: '/BordaVotingEndpoint',
            params: {input: testFirstTab}
        })
            .success(function (results) {
                console.log(results);
            })
            .error(function () {
                console.log("error");
            });
    }
});