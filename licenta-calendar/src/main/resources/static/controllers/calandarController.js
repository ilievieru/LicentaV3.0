/**
 * Created by P3700664 on 5/16/2017.
 */
var app = angular.module("CVS", ['ngMaterial']);
app.controller("calendarController", function ($scope, $http) {
    console.log("calendar controller working...");
    $scope.process = false;
    $scope.testCalendar = "Selected dates: ";
    var i = 0;
    var dateArray = [];
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

    $scope.clickTest = function(){
        console.log("test");
    }
    /* add datepicker*/
    $(function () {
        initDynamicTimeslots();
    });


    function initDynamicTimeslots() {
        // All required jQuery objects
        $timeslotsContainer = $('#timeslots');
        $timeslotsTemplate = $('#timeslot-input-box');
        $timeslot = $('.timeslot');
        $addTimeslot = $('.addTimeslot');
        $removeTimeslot = $('.removeTimeslot');
        $timeslotCounter = $('#timeslotCounter');

        var timeslotCounter = $timeslotsContainer.children().size();

        // Add a timeslot
        $addTimeslot.click(function (e) {
            e.preventDefault();
            var timeslotTemplate = $timeslotsTemplate.html();
            var $newTimeslot = $(timeslotTemplate.format(timeslotCounter, ""));
            $newTimeslot.find('input').datepicker();
            $timeslotsContainer.append($newTimeslot);
            updateTimeslotCounter(true);
            checkRemoveDeleteButton($newTimeslot.find($removeTimeslot.selector));
        });

        // Remove a timeslot
        $timeslotsContainer.on('click', $removeTimeslot.selector, function (e) {
            e.preventDefault();
            if (timeslotCounter > 1) {
                $(this).parent($timeslot).remove();
                updateTimeslotCounter(false);
            }

            checkRemoveDeleteButton($(this));
        });

        // Add first timeslot
        if (timeslotCounter === 0) {
            console.log("Adding first timeslot.");
            $addTimeslot.trigger('click');
        }

        // Removes the 'delete' link if there's only 1 input box
        function checkRemoveDeleteButton($deleteButton) {
            if (timeslotCounter === 1) {
                $deleteButton.remove();
            }
        }

        // Update counter after add/removing timeslot
        function updateTimeslotCounter(add) {
            timeslotCounter = add ? timeslotCounter + 1 : timeslotCounter - 1;
            $timeslotCounter.val(timeslotCounter);
        }
    }

    // Add .NET-like string.format to javascript
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, num) {
            return typeof args[num] != 'undefined' ? args[num] : match;
        });
    };
    /*---------*/
});
