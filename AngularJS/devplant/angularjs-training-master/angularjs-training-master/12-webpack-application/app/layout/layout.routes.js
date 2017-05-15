import layout from "./components/layout/layout.component";

export default ['$stateProvider', function ($stateProvider) {
    $stateProvider.state("default", {
        url: "",
        component: layout.$name
    })
}]