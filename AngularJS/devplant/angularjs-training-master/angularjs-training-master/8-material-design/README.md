# 8-material-design

Up to this point, our app does some pretty nice things, it can route, fetch data from a server and display it.
But it looks like crap. So lets change that.

### Material Design and Angular

Material Design is a UI/UX paradigm developed by google. It basically tells you how web & mobile apps should look and feel.
AngularJS adopted this design philosophy withing the Angular Material Design Library: https://material.angularjs.org/latest/

For the spec defined by google, have a look at: https://material.io/guidelines/

For Agnular, its a predefined set of Directives & Services as well as CSS which help us build quite pretty apps with little effort 
and no previous design or UX experience.

Angular Material is nice, because it provides a lot of well-documented samples and examples for everything you might need,
check out their demo's!

##### Adding material design

Material Design depends on some other angular core modules, aria, messages and animate. These together can be installed
either using `npm install --save angular-animate angular-aria angular-messages angular-material`

This project is already setup with these dependencies and the only thing that we had to change is the HTML. That's the beauty of it.

Our index file changed a little to accommodate the new dependencies

```
<script src="node_modules/angular/angular.min.js"></script>
<script src="node_modules/angular-route/angular-route.min.js"></script>
<script src="node_modules/angular-animate/angular-animate.min.js"></script>
<script src="node_modules/angular-aria/angular-aria.min.js"></script>
<script src="node_modules/angular-messages/angular-messages.min.js"></script>
<script src="node_modules/angular-material/angular-material.min.js"></script>

```

We also change the <section> and <div> tags we used for structuring with angular-material directives

```
<md-content>
    <md-toolbar class="md-accent">
        <div class="md-toolbar-tools">
            <h2>Sample Data Application</h2>
        </div>
    </md-toolbar>
    <md-content ng-view class="view-container"></md-content>
</md-content>
```

the `md-content` directive is used to structure views, its a container for other elements.
the `md-toolbar` directive will provide a toolbar outside our `ng-view` - this will be shared across all views

### The User, Posts and Comments views


```
<md-card class="md-padding">
    <md-list>
        <md-subheader>All Users</md-subheader>
        <md-list-item class="md-3-line" ng-repeat="user in vm.users">
            <div class="md-list-item-text" layout="column">
                <h3>{{user.name}}</h3>
                <h4>{{user.email}}</h4>
                <p>{{user.address.street}}, {{user.address.suite}}, {{user.address.city}}, {{user.address.zipcode}}</p>
            </div>
            <md-button ng-href="/users/{{user.id}}/posts">Posts</md-button>
        </md-list-item>
    </md-list>
</md-card>
```

We're using a card (https://material.io/guidelines/components/cards.html) to structure our content.
Inside we use an md-list to show one user at a time with the same `ng-repeat` we had before.
`layout=column` tells angular-material to show the nested elements one beneath the other, try to change it to "row"

Finally we changed the anchor-tag to an `md-button`.

The other HTML files are similar to this one, except for the fact that we display different data

### app.js

Since we want to customize the look a little, we'll use angular-material's built-in themeProvider, to change the theme

```
    $mdThemingProvider.theme('default')
        .primaryPalette('deep-orange')
        .accentPalette('light-green').warnPalette('red');
```

More about theming can be found here: https://material.angularjs.org/latest/Theming/01_introduction