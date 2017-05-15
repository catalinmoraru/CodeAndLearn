declare var angular: any;

var app = angular.module("app",['ngRoute']);


app.config(function ($routeProvider:any){
    $routeProvider.when('/',
        {
            templateUrl:'app.html',
            controller:'AppCtrl'
        }
    )
})

app.controller("AppCtrl",function($scope:any){
    $scope.model = {
        message: 'This is my app!!!'
    }
});

