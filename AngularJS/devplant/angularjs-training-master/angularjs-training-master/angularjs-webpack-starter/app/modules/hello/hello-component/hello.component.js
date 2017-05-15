import "./hello.component.css";
import HelloController from "./hello.controller";
import HelloTemplate from "./hello.template.html";

export default {
    $name: "hello",
    controller: HelloController,
    template: HelloTemplate,
    controllerAs: "helloCtrl"
}
