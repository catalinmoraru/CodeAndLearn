# 6-services-and-http
Lets have a look at services, and how to access data from an API

### Explanation

Lets first have a look at the vanilla implementation

```
    angular.module('DevPlantApp', []).controller("SearchController", SearchController).factory("DataService", DataService);
```

This registers a service, same as a controller, using a name and a function. We are using factory, because in 
vanilla javascript factory's map better to functions ( since a factory is expected too return an object containing functions)
Services and Factory's are the same thing, they are both singletons and both are used to process or fetch data.
the only difference is how they are implemented.

lets have a look at the vanilla implementation

```
    function DataService($http, $log, $q) {

        const apiUrl = "https://jsonplaceholder.typicode.com";

        const service = {
            getUsers: getUsers
        };

        function getUsers() {
            return $http.get(apiUrl + "/users").then(function (response) {
                return response.data;
            }).catch(function getFailed(error) {
                $log.error('XHR Failed for getUsers:' + JSON.stringify(error));
                return $q.reject("Sorry!");
            });
        }

        return service;
    }
```
We are injecting `$http, $log, $q` into the function:
- `$http` is a angular core service used for http requests. This is similar to jquery's `$.ajax`
- `$log` is used for logging
- `$q` is used to handle promises

We are defining an API_URL using const - this is just a public API that provides dummy data. 

Check it out at: https://jsonplaceholder.typicode.com

Next we are defining our service, this is an Object that will be returned by this function. Its members are usually
functions, these functions perform data processing and fetching, so you separate business logic from controllers.
Services can the be injected into the controller just as `$scope`

The service exposes a function `getUsers()`. This function makes a HTTP get request to our API and returns a promise.

### Promises

A promise is an object, that will eventually contain data, similar to a Future in Java. 

A promise can be:

- fulfilled - The action relating to the promise succeeded
- rejected - The action relating to the promise failed
- pending - Hasn't fulfilled or rejected yet
- settled - Has fulfilled or rejected

Back to our service. On the promise we are invoking "then" - this will execute when the promise is fulfilled, further we
are invoking "catch" - this will execute in case the promise is "rejected", for example, the API is not available and the
$http.get failed because of it.

Methods "catch" and "then" should always return, either data, or, in the case of "catch" another promise.
We can use the "catch" invocation to return default data, or in our case to return a new promise, which simply reads
"sorry". `$q` is used in angular to manage promises.


### Back to the Controller

```
    function SearchController(DataService) {
        const vm = this;
        vm.users = [];
        vm.getUsers = getUsers;

        function getUsers() {
            return DataService.getUsers().then(function (users) {

                console.log("this context is different, so that's why we user vm: ",this == vm);

                return vm.users = users;

            }).then(function (data) {

                console.log('Obtained following data:', data);

            }).catch(function (error) {
                showError(error);
            })
        }

        function showError(error) {
            alert('Failed to load data: ' + error);
        }

    }
```

The controller has the DataService we defined injected into it, it will use this service to get users.

the getUsers function is attached to the controller and will be invoked when the button is clicked.

This also introduces a new directive `ng-click` - this directive binds to a function invocation and will call said 
function on click.
Notice that we are chaining promises together, first we fetch users, then we log them using console.log. This 
makes code natural to read, especially we chaining multiple HTTP request.

Also notice that console.log(...,this ==vm) - this is because of how javascript handles 'this'. 
As expected, it always refers to the current object 

### Try it out

try to change the API URL from the service and see how error-handling works.

# A modern approach

lets have a look at the es6 version

```
    class DataService {

        constructor($http, $log, $q) {
            this.$http = $http;
            this.$log = $log;
            this.$q = $q;
            this.API_URL = "https://jsonplaceholder.typicode.com";
        }

        getUsers() {
            return this.$http.get(this.API_URL + "/users").then((response) => {
                return response.data;
            }).catch((error) => {
                this.$log.error('XHR Failed for getUsers:' + JSON.stringify(error));
                return this.$q.reject("Sorry!");
            });
        }

    }
```

We have a class for the DataService, this is a lot cleaner than defining a return object. We are also using "arrow functions"
these are special functions that share "this" with their "parent". Think lambda's in Java. 
This also makes the code a lot more concise as we are not declaring inner functions.

```
   class SearchController {

        constructor(DataService) {
            this.users = [];
            this.DataService = DataService;
        }

        getUsers() {
            this.DataService.getUsers().then((users) => {
                return this.users = users;
            }).then((users) => {
                console.log('Obtained following data:', users);
            }).catch(function (error) {
                alert('Failed to load data: ' + error);
            })
        }

    }
```

Same goes for the SearchController, we are defining a defining a single method "getUsers", this is also called via
'ng-click'. Injection, just as before is done via the constructor.