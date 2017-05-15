var app = angular.module("app", ['ngRoute']);
app.config(function ($routeProvider) {
    // how to call  :  url#/a/b
    $routeProvider
        .when('/:message/:message2', {
        templateUrl: 'app.html',
        controller: 'AppCtrl'
    })
        .when('/pizza', {
        template: '<div>Yumm!</div>',
    })
        .when('/pizzas/:crust/:toppings', {
        redirectTo: function (routeParams, path, search) {
            console.log(routeParams);
            console.log(path);
            console.log(search);
            return '/' + routeParams.crust;
        }
    })
        .when('/deep', {
        template: 'Deep dish'
    })
        .otherwise({
        // template: 'this state/page does not exist'
        redirectTo: '/'
    });
});
app.controller("AppCtrl", function ($scope, $routeParams) {
    $scope.model = {
        message: $routeParams.message + ' ' + $routeParams.message2
    };
});
//# sourceMappingURL=app.js.map