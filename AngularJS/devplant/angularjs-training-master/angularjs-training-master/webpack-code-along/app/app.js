import "angular-material/angular-material.css";

import angular from "angular";
import ngMaterial from "angular-material";
import ngAnimate from "angular-animate";
import ngAria from "angular-aria";
import ngMessages from "angular-messages";

import UserComp from "./users/user.component";
import UserServie from "./users/user.service";


export default angular.module('CodeAlong', [ngMaterial, ngAnimate, ngAria, ngMessages]).
    component(UserComp.$name,UserComp)
    .service(UserServie.$name,UserServie)
