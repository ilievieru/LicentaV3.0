angular.module('CVS',[]).directive("calendar", ['$rootScope', '$http', function ($rootScope, $http) {
    return {
        restrict: "E",
        templateUrl: "calendarTemplate.html",
        scope: {
            selected: "="
        },
        link: function (scope, rootScope, http) {
            console.log("calendar directive");
            scope.popup = false;
            testFirstTab = [];
            var i = 0;
            scope.moveLeft = false;
            scope.moveRight = false;
            scope.isNumber = true;
            scope.selected = _removeTime(scope.selected || moment());
            scope.month = scope.selected.clone();

            var start = scope.selected.clone();
            start.date(1);
            _removeTime(start.day(0));
            _buildMonth(scope, start, scope.month, rootScope, http);

            scope.report = false;
            scope.select = function (day) {
                scope.selectedDay = day;
                testFirstTab [i] = day.date._d;
                i++;
                scope.popup = true;
            };

            scope.next = function () {
                var next = scope.month.clone();
                _removeTime(next.month(next.month() + 1).date(1));
                scope.month.month(scope.month.month() + 1);
                _buildMonth(scope, next, scope.month);
                scope.moveLeft = false;
                scope.moveRight = true;
            };

            scope.previous = function () {
                var previous = scope.month.clone();
                _removeTime(previous.month(previous.month() - 1).date(1));
                scope.month.month(scope.month.month() - 1);
                _buildMonth(scope, previous, scope.month);
                scope.moveLeft = true;
                scope.moveRight = false;
            };
        }
    };

    function _removeTime(date) {
        return date.day(0).hour(0).minute(0).second(0).millisecond(0);
    }

    function _buildMonth(scope, start, month, rootScope, http) {
        //-----------------------
        manageRequestedDates(scope, start, month, rootScope, http);
        //-----------------------
    }

    function _buildWeek(date, month, scope, rootScope, http) {
        var days = [];
        for (var i = 0; i < 7; i++) {
            days.push({
                name: date.format("dd").substring(0, 1),
                number: date.date(),
                isCurrentMonth: date.month() === month.month(),
                isToday: date.isSame(new Date(), "day"),
                date: date
            });
            date = date.clone();
            date.add(1, "d");
        }
        return days;
    }

    function manageRequestedDates(scope, start, month, rootScope, http) {
        scope.weeks = [];
        var done = false,
            date = start.clone(),
            monthIndex = date.month(),
            count = 0;
        while (!done) {
            scope.weeks.push({days: _buildWeek(date.clone(), month, scope, rootScope, http)});
            date.add(1, "w");
            done = count++ > 2 && monthIndex !== date.month();
            monthIndex = date.month();
        }
    }

}]);

