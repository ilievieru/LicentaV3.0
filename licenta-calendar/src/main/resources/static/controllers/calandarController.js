/**
 * Created by P3700664 on 5/16/2017.
 */
var app = angular.module("CVS", ['ngMaterial']);
app.controller("calendarController", function ($scope, $http) {
    console.log("calendar controller working...");

    $scope.userName = document.getElementById("userName").value;
    console.log($scope.userName);
    var makeItGreenIndex = 0;
    /*This var is to set up green color on vote. See all events */
    $scope.process = false;
    /*Show start button*/
    $scope.newEvent = false;
    /*Show new event pane*/

    /*Show new event pane*/
    $scope.displayNewEvent = function () {
        $scope.newEvent = true;
    }

    $scope.testCalendar = "Selected dates: ";
    $scope.user = "user de test";
    var i = 0;
    /*Index for dataArray*/
    var dateArray = [];
    /*Holds dates clicked on calendar*/
    $(document).ready(function () {
        $("#date-popover").popover({html: true, trigger: "manual"});
        $("#date-popover").hide();
        $("#date-popover").click(function (e) {
            $(this).hide();
        });

        $("#my-calendar").zabuto_calendar({
            action: function () {
                return myDateFunction(this.id, false);
            },
            action_nav: function () {
                return myNavFunction(this.id);
            },
            ajax: {
                url: "show_data.php?action=1",
                modal: true
            },
            legend: [
                {type: "text", label: "Special event", badge: "00"},
                {type: "block", label: "Regular event"}
            ]
        });
    });

    function myDateFunction(id, fromModal) {
        $("#date-popover").hide();
        if (fromModal) {
            $("#" + id + "_modal").modal("hide");
        }
        var date = $("#" + id).data("date");
        var hasEvent = $("#" + id).data("hasEvent");
        if (hasEvent && !fromModal) {
            return false;
        }
        $("#date-popover-content").html('You clicked on date ' + date);
        $("#date-popover").show();
        $scope.process = true;
        dateArray [i] = date;
        i++;
        if ($scope.dateHistory)
            $scope.dateHistory = $scope.dateHistory + "<br> " + date;
        else
            $scope.dateHistory = date;
        $("#calendarDate").html('You clicked on date ' + $scope.dateHistory);
        $("#calendarDate").show();
        $("#calendarMyDate").html('You clicked on date ' + $scope.dateHistory);
        $("#calendarMyDate").show();
        return true;
    }

    function myNavFunction(id) {
        $("#date-popover").hide();
        var nav = $("#" + id).data("navigation");
        var to = $("#" + id).data("to");
        console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }

    $scope.startVoteBorda = function () {
        $http({
            method: 'GET',
            url: '/BordaVotingEndpoint',
            params: {input: dateArray}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarDate").html('Winner ' + results.Winner);
                $("#calendarDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.startVoteInstantRunOff = function () {
        $http({
            method: 'GET',
            url: '/InstantRunOffVotingEndpoint',
            params: {input: dateArray}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarDate").html('Winner ' + results.Winner);
                $("#calendarDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.startVoteCondorcet = function () {
        $http({
            method: 'GET',
            url: '/CondorcetVotingEndpoint',
            params: {input: dateArray}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarDate").html('Winner ' + results.Winner);
                $("#calendarDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.startVotePlurality = function () {
        $http({
            method: 'GET',
            url: '/PluralityVotingEndpoint',
            params: {input: dateArray}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarDate").html('Winner ' + results.Winner);
                $("#calendarDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.inputType = "none";
    $scope.choseInputType = function (input) {
        $scope.InstantRunOff = false;
        $scope.Borda = false;
        $scope.Condorcet = false;
        $scope.Plurality = false;
        $scope.ALL = false;
        if (input == "InstantRunOff") {
            $scope.inputType = "InstantRunOff";
            $scope.InstantRunOff = true;
        }
        if (input == "Borda") {
            $scope.inputType = "Borda";
            $scope.Borda = true;
        }
        if (input == "Condorcet") {
            $scope.inputType = "Condorcet";
            $scope.Condorcet = true;
        }
        if (input == "Plurality") {
            $scope.inputType = "Plurality";
            $scope.Plurality = true;
        }
        if (input == "ALL") {
            $scope.inputType = "ALL";
            $scope.ALL = true;
        }
    }

    $scope.startProcessing = function () {
        if ($scope.inputType == "InstantRunOff") {
            console.log("InstantRunOff");
            $scope.startVoteInstantRunOff();
        }
        if ($scope.inputType == "Borda") {
            console.log("Borda");
            $scope.startVoteBorda();
        }
        if ($scope.inputType == "Condorcet") {
            console.log("Condorcet");
            $scope.startVoteCondorcet();
        }
        if ($scope.inputType == "Plurality") {
            console.log("Plurality");
            $scope.startVotePlurality();
        }
        if ($scope.inputType == "ALL") {
            console.log("ALL");
        }
    }

    $scope.clickTest = function (filter) {
        console.log(filter.green);
        if (filter.green == false) {
            filter.green = true;
            document.getElementById("makeItGreen-" + filter.id).onclick = changeColorGreen(filter.id);
        } else if (filter.green == true) {
            filter.green = false;
            document.getElementById("makeItGreen-" + filter.id).onclick = changeColorBlue(filter.id);

        }
    }
    function changeColorGreen(id) {
        console.log("green");
        document.getElementById("makeItGreen-" + id).style.backgroundColor = "#7FFFD4";
        return false;
    }

    function changeColorBlue(id) {
        document.getElementById("makeItGreen-" + id).style.backgroundColor = "#d9edf7";
        return false;
    }

    /*------ See all events ----*/
    $scope.eventType = "none";
    $scope.choseEventType = function (filter) {
        $scope.eventType = filter;
    }
    $scope.allEvents = function () {
        var myListData = {filters: []};
        $http({
            method: 'GET',
            url: '/allEvents'
        })
            .success(function (results) {
                console.log(results);
                for (var i = 0; i < results.length; i++) {
                    var datesObject = [];
                    for (var j = 0; j < results[i].dates.length; j++) {
                        makeItGreenIndex = makeItGreenIndex + 1;
                        datesObject.push({
                            id: makeItGreenIndex,
                            date: results[i].dates[j],
                            green: false
                        });
                    }
                    var stringStatus = "";
                    if (results[i].status == 0) {
                        stringStatus = "Closed";
                    }
                    else {
                        stringStatus = "Open";
                    }
                    myListData.filters.push({
                        id: results[i].eventId,
                        description: results[i].eventDescriotion,
                        status: stringStatus,
                        name: results[i].eventName,
                        dateValueFront: datesObject,
                        userListFront: results[i].userList,
                        display: false
                    })
                }
                $scope.myList = myListData;
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.activateFilter = function (filter) {
        filter.display = true;
    }

    $scope.minimize = function (filter) {
        if (filter.display == true)
            filter.display = false;
    }
});
