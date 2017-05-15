import comments from "./components/comments.component";
import angular from "angular";
import routes from "./comments.routes";

export default angular.module('commentsModule', [])
    .component(comments.$name, comments).config(routes).name;