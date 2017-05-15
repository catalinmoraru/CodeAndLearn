# 3-controllers-and-modules 
A primitive angularJS to illustrate $scope.
We'll be using ECMA6 - unless you have an up-to-date browser like Chrome, this won't work
index-old.html is a semi-fallback for not-so-modern browser, it still won't work in a lot of them

So, why use features that won't work across the board ? We'll get there later within this training.

### Explanation

`<html ng-app="DevPlantApp">`

ng-app directive applied to the <html> element - this tells Angular to bootstrap an application 
notice that this time we use a named app called "DevPlantApp"

`<body ng-controller="MainController">`

ng-controller directive tells angular that this element is controlled by the controller called "MainController"

```
class MainController {

        constructor($scope) {
            this.$scope = $scope;
            this.$scope.participantName = '';

            this.$scope.participants = [{name: 'Timo', age: 28},
                {name: 'Anna', age: 32},
                {name: 'Andrei', age: 25},
                {name: 'Radu', age: 35},
                {name: 'Razvan', age: 22}];
        }

    }
```

this is a es6 JavaScript class definition, its a lot better than using functions & prototype.
Especially when coming from an Object Oriented Background id would make sense to have a class model a controller, 
and its methods ( we'll get there ) used for manipulating the view

**$scope passed to the constructor is angular's way of dependency injection. this is important!** 

### Defining an Application and loading the Controller

`angular.module('DevPlantApp', []).controller("MainController", MainController);`

This piece of code will initialize an angularJS application called "DevPlantApp", the empty array [] is used to load
additional modules into this app, at this moment we won't be using any.
A module is a collection of directives, services, controllers - we'll get there soon

Notice that when registering a controller we provide 2 arguments, first one is the name - this one is used in the 
ng-controller directive, 2nd one is either a class or a function


If you'll look at index-vanilla.html you'll see that, for compatibility sake we'll define the controller as a function.

``` 
    function MainController($scope) {

        this.$scope = $scope;
        this.$scope.participantName = '';

        this.$scope.participants = [{name: 'Timo', age: 28},
            {name: 'Anna', age: 32},
            {name: 'Andrei', age: 25},
            {name: 'Radu', age: 35},
            {name: 'Razvan', age: 22}];

    }
```

### Filter

Also notice that, now, since participants are an array of objects, we can user "orderBy" on one of their properties

`orderBy:age `
