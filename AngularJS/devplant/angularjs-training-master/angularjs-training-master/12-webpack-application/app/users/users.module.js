import user from "./components/user.component";
import angular from "angular";
import routes from "./users.routes";

export default angular.module('userModule', [])
    .component(user.$name, user).config(routes).name;