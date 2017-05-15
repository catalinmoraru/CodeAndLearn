import post from "./components/posts.component";
import angular from "angular";
import routes from "./posts.routes";

export default angular.module('postsModule', [])
    .component(post.$name, post).config(routes).name;