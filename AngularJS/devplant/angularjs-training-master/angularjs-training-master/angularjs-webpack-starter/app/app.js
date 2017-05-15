//Material CSS
import "angular-material/angular-material.css";
//Angular Core
import angular from "angular";
import ngAnimate from "angular-animate";
import ngAria from "angular-aria";
import ngMessages from "angular-messages";
//Angular Material
import ngMaterial from "angular-material";
//UI Router
import uiRouter from "@uirouter/angularjs";
//Our Components
import HelloModule from "./modules/hello/hello.module";
import DefaultRoutesConfiguration from "./app.routes";

export default angular.module('DevPlantStarter', [ngMaterial, ngAnimate, ngAria, ngMessages, uiRouter,HelloModule])
    .config(DefaultRoutesConfiguration);

