declare var angular: any;

var app = angular.module("phoneApp",[]);

app.controller("AppCtrl",function($scope){

});

app.directive("mypanel",function(){
    return {
        restrict: "E",
        transclude: true,
        template: '<div class="panel" ng-transclude>This is a panel inside the directive</div>'
    }
});


app.directive("aDirective",function(){
    return {
        restrict: "A",
        template: '<div>This is a directive</div>'
    }
});

