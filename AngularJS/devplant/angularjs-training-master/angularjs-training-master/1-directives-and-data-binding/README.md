# 1-directives-and-data-binding
This is a primitive angularJS to illustrate 2-way databinding

### Explanation

#### ng-app Directive
`<html ng-app>`

ng-app directive applied to the <html> element - this tells Angular to bootstrap an application 

#### ng-model Directive

`<input type="text" ng-model="name" />`

ng-model directive binds a property called name to the current scope ( in this case, root scope )

##### Data interpolation

`{{name}}`

Data Interpolation using {{ }} syntax - this will render the value of the property name from within the current scope