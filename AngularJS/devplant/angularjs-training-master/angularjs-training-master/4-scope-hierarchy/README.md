# 4-scope-hierarchy

Now it gets confusing, right?
This example illustrates how angular looks up properties, it'll go trough the scope hierarchy till it finds a match

### Explanation

We are using a hierarchical structure of HTML elements.

```
<div>
    <h3>From Root Scope</h3>
    {{name}}<br>{{message}}
    <div ng-controller="MainController">
        <h3>From main Controller</h3>
        {{name}}<br>{{message}}
        <div ng-controller="ChildController">
            <h3>From child Controller</h3>
            {{name}}<br>{{message}}
        </div>
    </div>
</div>
```

The top div has no controller, which means it'll look for the 2 properties "name" and "message"
in the rootScope.

The second div has a controller called "MainController" this means that it will first look in the scope of this
controller, if nothing is found it'll look in the rootScope

The third div has a controller called "ChildController" this means that it will first look in the scope of this
controller, if nothing is found it'll look in the scope of "MainController", if this fails it'll look for the property 
on rootScope 

If you'll look at index-vanilla.html you'll see that, the "vanilla" way of defining controllers is by using functions.
this will work in any browser* 

``` 
    function ChildController($scope) {

        $scope.message = "My message is better than yours";
    }

    function MainController($scope) {

        $scope.name = "I'm Main, but i'll inherit a message from root";
    }
```

_- browser*  - Internet Explorer is not a browser, its actually a tool used to download a browser._