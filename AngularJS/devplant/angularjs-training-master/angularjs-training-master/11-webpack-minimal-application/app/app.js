import "./app.css";
import "angular-material/angular-material.css";
import angular from "angular";
import ngMaterial from "angular-material";
import uirouter from "@uirouter/angularjs";
// Import base modules
import config from "./app.config";
import routes from "./app.routes";
import commonsModule from "./commons/commons.module";
import userModule from "./users/users.module";


export default angular.module('devplant', [ngMaterial, uirouter,  commonsModule, userModule])
    .config(config).config(routes).name;
