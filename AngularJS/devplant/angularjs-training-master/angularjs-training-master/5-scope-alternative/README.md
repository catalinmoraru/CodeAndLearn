# 5-scope-alternative
This is a better way of handling data, or the model of the application, so to speak.

### Explanation

The "Controller as" syntax helps us define which property we want, in case of nesting, we can always access both 
parent and child properties, this is surprisingly useful. The interpolation {{ }} is done using the controllers alias.

```
<div ng-controller="MainController as main">
    <h3>Main</h3>
    {{main.name}} {{main.message}}
    <div ng-controller="ChildController as child">
        <h3>Child</h3>
        {{child.name}} {{child.message}} <br>
        <h4>Main Properties</h4>
        {{main.name}} {{main.message}}
    </div>
</div>
```

The class syntax makes a lot of sense now! So, you'd ask, why would you alias "this" as "vm" in the vanilla sample, 
that has something to do with how "this" behaves when adding "methods", See the next example.