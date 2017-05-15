import layout from "./components/layout/layout.component";
import toolbar from "./components/toolbar/toolbar.component";

import angular from "angular";
import routes from "./layout.routes";

export default angular.module('layout', [])
    .component(layout.$name, layout)
    .component(toolbar.$name, toolbar)
    .config(routes).name;