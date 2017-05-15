# 10-webpack

Congratz, you made it this far, now things get interesting! ( And a bit complicated )
You can have a look at this article, since it basically describes how you'll feel once you are through with this step:
https://hackernoon.com/how-it-feels-to-learn-javascript-in-2016-d3a717dd577f

### Webpack

Webpack is a tool used to bundle files together. In the previous example we've seen how to deploy an application (not
necessarily angular ) using docker, and making it somewhat production ready.

Now, the problem is, that, with the previous setup, we just load all our javascript, css and html files separately
this isn't necessarily bad, since, with HTTP/2 gaining traction, its gonna be faster to load multiple files than a single bundle.
But that's another discussion.

So, for the moment we'll stick to webpack.

Webpack can do a lot of things, here are some if its main features, we'll use most of them:

- Bundle multiple files together into one big bundle
- Minify and uglify javascript
- Allow us to use all the fancy es6 syntax ( this is actually babel, but webpack enables us to use it )
- Bundling files allows us to split our code into small, concise fragments, each doing one very specific thing

#### Before we get into webpack, lets look at alternatives

Here are some that where popular over the years
- npm scripts, like the ones we used to start servers ( you'd have to write a loooot of scripts )
- grunt - javascript task runner, focuses on configuration, it comes with common pre-defined tasks
- gulp - javascript task runner, similar to grunt, focuses more on code and convention
- a lot of other no-name task runners, people came up with

**So then, why choose webpack?**
Well, it doesn't replace task runners, but it does a lot of things on its one, its configuration based, the things it 
can't do, can be done with simple* NPM scripts.

Obviously, for large applications those scripts will get more complicated, but for now, lets focus on angular.
One other advantage of webpack is that its easy to setup, it is very well documented and wide-spread, this makes finding
help easy.

#### Things we need

For webpack to work we need a bunch more devDependencies we'll get into each one as we progress this step of the workshop

```
    "babel-core": "^6.24.1",
    "babel-loader": "^7.0.0",
    "babel-plugin-transform-es2015-modules-commonjs": "^6.24.1",
    "babel-preset-es2015": "^6.24.1",
    "babel-register": "^6.24.1",
    "css-loader": "^0.28.1",
    "file-loader": "^0.11.1",
    "html-webpack-plugin": "^2.28.0",
    "raw-loader": "^0.5.1",
    "style-loader": "^0.17.0",
    "webpack": "^2.5.1",
    "webpack-core": "^0.6.9",
    "webpack-dev-server": "^2.4.5"
```
#### Additional NPM Scripts 

We now have a few more scripts, for running a development server either in production or development mode, as well as
bundling for production. This is all there is to it, webpack does the rest

```
    "dev": "node node_modules/.bin/webpack-dev-server",
    "prod": "node node_modules/.bin/webpack-dev-server -p --compress",
    "build-prod": "node node_modules/.bin/webpack -p",
```

#### Windows users need to install some packages globally

`npm install -g webpack webpack-dev-server` - Since path resolves differently on Windows

### Webpack - configuration file

Soooo, lets look at our webpack file

```

import HtmlWebpackPlugin from "html-webpack-plugin";
import path from "path";

export default {

    entry: './app/app.js',

    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "bundle.js"
    },

    plugins: [new HtmlWebpackPlugin({template: './app/index.html'})],

    module: {
        loaders: [
            {
                test: /\.js$/, loader: 'babel-loader', exclude: /node_modules/, query: {
                presets: ['es2015']
            }
            },
            {test: /\.css$/, loader: 'style-loader!css-loader'},
            {test: /\.html$/, loader: 'raw-loader', exclude: /node_modules/}
        ]
    }
};


```

Well, its gibberish at first, but, lets go through it line by line. 

First thing you notice is that we are using ecma6 import/export statements

We'll `import` an `HtmlWebpackPlugin` from `html-webpack-plugin`, the former is a node module.
Then we import path from "path", this is required to give webpack the current location on the filesystem.
next we export default an object containing the webpack definitions.

- In ecma6, with we get a new feature introduced called modules, you can export almost anything as a module
 and import it somewhere else, Think defining a class in Java and using an import statement to import said class into 
 another file
- the `default` keyword simply tells Javascript, which the default import is, when using the import statement, 
unless otherwise specified. Some applications & tools require you to have a default export, since they do not know how
 you named your export, for the default export, the name is irrelevant. **You can only have one default export per file.**

###### entry
 
Next, in our exported configuration, we start by telling webpack which our application entry point is,
in this case its a file called `app.js`, located in the `app` folder, so `./app/app.js`

`entry: './app/app.js',`

##### output

Next, we tell webpack, how the output file should be called, and where we want webpack to put it.

```
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "bundle.js"
    }
```

##### plugins

We may, or may not define some plugins, in this case we use a simple plugin which tells webpack to take our index.html
file and copy it to the output location, it will also take care of creating a script tag in this index.html, at build time,
so we don't have to care about naming our scripts, or adding imports to it.

`plugins: [new HtmlWebpackPlugin({template: './app/index.html'})]`

##### finally, we define some loaders

Loaders are used to tell webpack what to do with all the different types of files are are going to use.

```
    module: {
        loaders: [
            {
                test: /\.js$/, loader: 'babel-loader', exclude: /node_modules/, query: {
                presets: ['es2015']
            }
            },
            {test: /\.css$/, loader: 'style-loader!css-loader'},
            {test: /\.html$/, loader: 'raw-loader', exclude: /node_modules/}
        ]
    }
```

Each loader definition, contains a `test`, which is a regex, if the regex matches the file name, this loader will be used.

Next a `loader` is defined to handle this types of files. We can also exclude files, again based on a regex.

The `query` field tells is simply an object that will be appended to the loader invocation as a querystring.
Think http requests: `https://google.com?q=test`, `?q=test` is a querystring.

Loaders can be piped together using `!` this tells webpack to evaluate loaders in order, RIGHT to LEFT.
For example, for css, we first want to load css and then convert it into a `<style>` tag.

For Javascript we are going to use the babel loader. This tells webpack to invoke babel on our javascript files.

### Babel
Babel is a javascript transpiler that can turn ES6 syntax into ES5 syntax ( and more ). Its very useful if we want to
write modern code, as we've seen in previous examples, but still need to support a lot of browsers.

### Overwhelmed ? 
Well, this is it, there's not much more to it, rember the link at the top if this document? Read it!
https://hackernoon.com/how-it-feels-to-learn-javascript-in-2016-d3a717dd577f


### Using ES6 Syntax to its full potential
Since we are now using **webpack** to compile and bundle our code together, we don't care about script tags anymore.

In the previous example, we had to make sure paths are correct, and add a whole lot of files into Docker to get production read.
One small type-error and it would all go to hell.

### The application entry point - app.js

Our Javascript code needs an entry point, for webpack to know where to start, in our case this will be `app.js`

```
import "angular-material/angular-material.min.css";

import angular from "angular";
import ngMaterial from "angular-material";
import ngAria from "angular-aria";
import ngMessages from "angular-messages";
import ngAnimate from "angular-animate";
import HomeComponent from "./component/home.component";
import TextService from "./component/text.service";

export default angular.module('devplant', [ngAria, ngMessages, ngAnimate, ngMaterial])
    .component(HomeComponent.$name, HomeComponent)
    .service(TextService.$name, TextService);
```

So whats up with this? Well, we are importing a bunch of modules and then creating an angular application.

Remember, we don't have script tags in our index.html file anymore, so we got to load our dependencies this way.

Notice we can also import css.

The other new thing is the `.component` syntax we are using. Its a more natural approach to programming, and its very
close to angular2/4. 

#### Components

Starting with angularJS 1.5, we can define components, this is a way to group a HTML template together with a Controller,
Together they form a component. These are generally speaking reusable and make for great libraries.

In this example we have a very simple Component called `HomeComponent`
This is how its definition looks like:

```
import "./home.style.css";
import HomeController from "./home.controller";
import HomeTemplate from './home.component.tpl.html';

export default {
    $name: 'home',
    template: HomeTemplate,
    controller: HomeController
};

```

A component has its own css, controller, and template. We can import all those using ES6 and let babel/webpack take care
of the rest. Now, doesn't this look really clean? 


##### Component Template

The template for the HomeController is just an html file with some angular, just like we are used to from previous
examples

```
<section layout="column"  layout-align="center center" layout-wrap class="default-viewport">
    <md-button class="md-raised md-primary" ng-click="$ctrl.toggleText()">Toggle Text</md-button>
    <p ng-show="$ctrl.showText">
        {{$ctrl.text}}
    </p>
</section>
```

A component uses the "Controller as" syntax by default. Its default alias is `$ctrl`.

##### Component Controller

The controller is also similar to what we've used so far, its a class definition preceded by an  `export default`.
This means we don't care what the class is actually called, it can be omitted, but this is the right way to do it.
Always have an default export. 

```
export default class HomeController {

    // static get $inject() {
    //     return ['TextService'];
    // }

    constructor(TextService) {
        this.TextService = TextService;
        this.showText = true;
    }

    $onInit() {
        this.text = this.TextService.getText();
    }

    toggleText() {
        this.showText = !this.showText;
    }

}

```

There are 2 differences to what we are used to, first there's a commented block of code

```
    // static get $inject() {
    //     return ['TextService'];
    // }
```

Its meant for production use, its commented out for showcasing what happens with angularJS's dependency injection
when we minify and uglify the code. So, dependency injection in angularJS is done by **NAME**, we've already seen this
in previous examples when defining controllers and services. When using webpack, we minify code, here's an example

##### Original class
```
class MyClass {

    constructor(name, email) {
        this.name = name;
        this.email = email;
    }

    getNameAndEmail(){
        return 'Name: '+this.name +' Email: '+this.email;
    }
}
```

##### Minified class
```
class MyClass{constructor(i,a){this.name=i,this.email=a}getNameAndEmail(){return"Name: "+this.name+" Email: "+this.email}}
```

See, there's a problem, arguments passed to the constructor have been changed to `i` and `a` , so in our case `DataService`
wouldn't be called `DataService` any more. And that is a problem for angular, since it won't know what to inject

Try to run `npm run prod` - this will minify your application as if it where ment for production and see what happenes
with that piece of code commented out.

#### Component life-cycle

The other difference is that we are fetching data inside `$onInit()` - this is part of the angular component lifecycle
it will be called AFTER the constructor, at this point everything will be available. Other lifecycle's include:
- `$onChanges(changedObject)`
- `$onDestroy()`

#### Using the component
If you have a look at index.html you'll see that we use a component by adding a tag with its name
 
```
    <md-content>
        <md-toolbar class="md-accent">
            <div class="md-toolbar-tools">
                <h2>Webpack Application</h2>
            </div>
        </md-toolbar>
        <home></home>
    </md-content>
```

#### TextService

The service we are using is this demo is really simple, it just returns static text.


