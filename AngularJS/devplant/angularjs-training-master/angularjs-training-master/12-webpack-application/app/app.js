import "./app.css";
import "angular-material/angular-material.css";
import angular from "angular";
import ngMaterial from "angular-material";
import uirouter from "@uirouter/angularjs";
// Import base modules
import config from "./app.config";
import routes from "./app.routes";
import layout from "./layout/layout.module";
import userModule from "./users/users.module";
import postsModule from "./posts/posts.module";
import commentsModule from "./comments/comments.module";
import commonsModule from "./commons/commons.module";


export default angular.module('devplant', [ngMaterial, uirouter, layout, commonsModule, userModule, postsModule, commentsModule])
    .config(config).config(routes).name;
