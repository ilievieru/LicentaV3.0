/**
 * Created by P3700664 on 5/16/2017.
 */
var app = angular.module('CVS', []);
app.controller("calendarController", function ($scope) {
    console.log("calendar controller working...");
    $scope.testCalendar = "Selected dates: ";

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
        if ($scope.dateHistory)
            $scope.dateHistory = $scope.dateHistory + "</br>" + date;
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

    $scope.calendarTest = function () {
        console.log("Click");
        $scope.testCalendar = "Click";
    }
});