import UserController from "./user.controller";
import UserTemplate from "./user.template.html";
import "./user.css";

export default {
    $name: "userComponent",
    controller: UserController,
    template: UserTemplate,
    controllerAs: "userCtrl"
}
