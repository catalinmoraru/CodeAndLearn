import UserTemplate from "./user.component.tpl.html";
import UserController from "./user.controller";

export default {
    $name: 'users',
    template: UserTemplate,
    controllerAs: 'vm',
    controller: UserController
};

