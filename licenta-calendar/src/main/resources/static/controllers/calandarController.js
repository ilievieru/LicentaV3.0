/**
 * Created by P3700664 on 5/16/2017.
 */
var app = angular.module("CVS", ['ngMaterial']);
app.controller("calendarController", function ($scope, $http) {
    console.log("calendar controller working...");

    /*Show start button*/
    var makeItGreenIndex = 0;
    $scope.WinnerDiv = false;
    /*Show new event pane*/
    $scope.newEvent = false;
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
        dateArray [i] = date;
        i++;
        if ($scope.dateHistory)
            $scope.dateHistory = $scope.dateHistory + "<br> " + date;
        else
            $scope.dateHistory = date;
        $("#calendarDate").html('You clicked on date ' + $scope.dateHistory);
        $("#calendarDate").show();
        return true;
    }

    function myNavFunction(id) {
        $("#date-popover").hide();
        var nav = $("#" + id).data("navigation");
        var to = $("#" + id).data("to");
        console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }

    $scope.startVoteBorda = function (eventId) {
        $scope.WinnerDiv = true;
        $http({
            method: 'GET',
            url: '/BordaVotingEndpoint',
            params: {id: eventId}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarMyDate").html('<strong> Winner ' + results.Winner + '</strong>');
                $("#calendarMyDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.startVoteInstantRunOff = function (eventId) {
        $scope.WinnerDiv = true;
        $http({
            method: 'GET',
            url: '/InstantRunOffVotingEndpoint',
            params: {id: eventId}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarMyDate").html('<strong> Winner ' + results.Winner + '</strong>');
                $("#calendarMyDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.startVoteCondorcet = function (eventId) {
        $scope.WinnerDiv = true;
        $http({
            method: 'GET',
            url: '/CondorcetVotingEndpoint',
            params: {id: eventId}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarMyDate").html('<strong> Winner ' + results.Winner + '</strong>');
                $("#calendarMyDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.startVotePlurality = function (eventId) {
        $scope.WinnerDiv = true;
        $http({
            method: 'GET',
            url: '/PluralityVotingEndpoint',
            params: {id: eventId}
        })
            .success(function (results) {
                console.log(results);
                $("#calendarMyDate").html('<strong> Winner ' + results.Winner + '</strong>');
                $("#calendarMyDate").show();
            })
            .error(function () {
                console.log("error");
            });
    }

    $scope.inputType = "none";
    $scope.choseInputType = function (input) {
        $scope.WinnerDiv = false;
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

    $scope.startProcessing = function (eventId) {
        if ($scope.inputType == "InstantRunOff") {
            console.log("InstantRunOff");
            $scope.startVoteInstantRunOff(eventId);
        }
        if ($scope.inputType == "Borda") {
            console.log("Borda");
            $scope.startVoteBorda(eventId);
        }
        if ($scope.inputType == "Condorcet") {
            console.log("Condorcet");
            $scope.startVoteCondorcet(eventId);
        }
        if ($scope.inputType == "Plurality") {
            console.log("Plurality");
            $scope.startVotePlurality(eventId);
        }
        if ($scope.inputType == "ALL") {
            console.log("ALL");
        }
    }

    $scope.makeItGreenFunctionAllEvents = function (filter, status) {
        if (filter.green == false) {
            filter.green = true;
            document.getElementById("makeItGreen-" + filter.id).onclick = changeColorGreen(filter.id);
        } else if (filter.green == true) {
            filter.green = false;
            if (status == "Open") {
                document.getElementById("makeItGreen-" + filter.id).onclick = changeColorYellow(filter.id);
            }
            if (status == "Closed") {
                document.getElementById("makeItGreen-" + filter.id).onclick = changeColorRed(filter.id);
            }
        }

    }
    function changeColorGreen(id) {
        document.getElementById("makeItGreen-" + id).style.backgroundColor = "#dff0d8";
        return false;
    }

    function changeColorRed(id) {
        document.getElementById("makeItGreen-" + id).style.backgroundColor = "#f2dede";
        return false;
    }

    function changeColorYellow(id) {
        document.getElementById("makeItGreen-" + id).style.backgroundColor = "#fcf8e3";
        return false;
    }

    $scope.setDataForVote = function (id, data) {
        $http({
            method: 'GET',
            url: '/setDataForVote',
            params: {
                id: id,
                data: data
            }
        })
            .success(function (results) {
                console.log(results);
            })
            .error(function () {
                console.log("error");
            });
    }
    /*------ See all events ----*/
    $scope.eventType = "none";
    $scope.choseEventType = function (filter) {
        $scope.eventType = filter;
    }
    $scope.allEvents = function () {
        var ngClass = "";
        var myListData = {filters: []};
        $http({
            method: 'GET',
            url: '/allEvents'
        })
            .success(function (results) {
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
                        ngClass = "alert-danger";
                    }
                    else {
                        stringStatus = "Open";
                        ngClass = "alert-warning";

                    }
                    myListData.filters.push({
                        id: results[i].eventId,
                        description: results[i].eventDescriotion,
                        status: stringStatus,
                        name: results[i].eventName,
                        dateValueFront: datesObject,
                        userListFront: results[i].userList,
                        class: ngClass,
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
