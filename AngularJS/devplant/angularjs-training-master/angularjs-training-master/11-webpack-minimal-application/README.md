# 11-webpack-minimal-application

Well now, I think we might have just covered everything. So, lets go back to the application we built during the first
steps of this workshop and rebuild it with webpack. The webpack.config and package json files will remain unchanged for 
now.

### We'll start of with just one view

We'll start using only the user view, but, we will structure our application as modular as possible.

##### app.js

App.js is our application entry point, as it was up to now

```
import config from "./app.config";
import routes from "./app.routes";
import commonsModule from "./commons/commons.module";
import userModule from "./users/users.module";


export default angular.module('devplant', [ngMaterial, uirouter,  commonsModule, userModule])
    .config(config).config(routes).name;
```

Aside from the library imports, we're also import 2 of our owm **modules**
- commonsModule, which contains our DataService
- userModule, which contains our UserComponent
- we also define routes and application configurations in separate modules
  - config, for our material design theme configuration
  - routes, for defining our routes
  
#### DataService

Our data-service is pretty much the same as before.

#### Commons Module

We'll wrap this service inside its own module, the commonsModule, and instead of directly loading the service, we 
will load this instead. For now it doesn't make much sense, but having multiple services grouped together is useful.

#### User Component

We are restructuring our previous view's using components, this way, each view will have its own component.
  
The user.component.tpl.html remains unchanged. The controller is almost identical, we are just adding 
the `$inject` method in order to take care of dependency injection when running in a minified state.

The component definition is similar to previous examples, except we now explicitly alias the controller with "vm"
```
import UserTemplate from "./user.component.tpl.html";
import UserController from "./user.controller";

export default {
    $name: 'users',
    template: UserTemplate,
    controllerAs: 'vm',
    controller: UserController
};

```

#### User module

Like with the commons module, we'll bundle this component inside its own module

```
import user from "./components/user.component";
import angular from "angular";
import routes from "./users.routes";

export default angular.module('userModule', [])
    .component(user.$name, user).config(routes).name;
```

#### User routing & well, routing

Angular's built-in route-provider isn't very flexible, it doesn't offer the ability to nest views or run multiple views
at the same time. So we'll use `uiRouter` from now on. This is the defacto standard for angularJS, its even been 
incorporated int angular2/4 as the standard routing module.


```
import user from "./components/user.component";

configureState.$inject = ['$stateProvider'];
function configureState($stateProvider) {

    $stateProvider.state("default", {
        url: "/users",
        component: user.$name
    });
}

export default configureState;
```

It looks very similar to our previous route definitions, except for 1 detail, it allows you to map a component to a view.
This is not possible with angular's standard router, it would require wrapper code which simply doesn't make sense.

We are using a slightly different way of dependency inject, since we are not returing a class, but a function we
need to `$inject` in this manner. Its just another way of doing it, there are several more, but we'll stick to these 2.

Application routing, and otherwise, will remain the same, we'll fallback to users in case the URL is invalid

```
defaultRoutesConfiguration.$inject = ['$urlRouterProvider', '$locationProvider'];
function defaultRoutesConfiguration($urlRouterProvider, $locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix("*");
    $urlRouterProvider.otherwise("/users");
}

export default defaultRoutesConfiguration;
```

#### app.config.js

In app.config.js  we'll configure our customized theme, just as we did in **8-material-design**

```
themeConfiguration.$inject = ['$mdThemingProvider'];
function themeConfiguration($mdThemingProvider) {

    $mdThemingProvider.theme('default')
        .primaryPalette('deep-orange')
        .accentPalette('light-green').warnPalette('red');

}

export default themeConfiguration;

```


#### A small addition to webpack.config
We need to add just 1 thing, the webpack-dev-server needs to allow html5 push-state, for this we'll add

```
    devServer: {
        historyApiFallback: true
    },
```

to webpack.config.babel.js