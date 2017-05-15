import angular from "angular";
import HelloService from "./service/hello.service";
import HelloComponent from "./hello-component/hello.component";
import HelloRoutes from "./hello.routes";

export default angular.module('HelloModule', [])
    .service(HelloService.$name, HelloService)
    .component(HelloComponent.$name, HelloComponent)
    .config(HelloRoutes)
    .name;

