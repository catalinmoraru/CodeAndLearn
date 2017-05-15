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
