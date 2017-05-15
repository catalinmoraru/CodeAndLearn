declare var angular: any;

var app = angular.module("phoneApp",[]);

app.controller("AppCtrl",function($scope){

});

app.directive("mypanel",function(){
    return {
        restrict: "E",
        transclude: true,
        templateUrl: 'include.html'
    }
});


app.directive("aDirective",function(){
    return {
        restrict: "A",
        template: '<div>This is a directive</div>'
    }
});

