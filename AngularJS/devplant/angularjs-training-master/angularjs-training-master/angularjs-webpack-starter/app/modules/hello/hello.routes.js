import HelloComponent from "./hello-component/hello.component";

configureState.$inject = ['$stateProvider'];
function configureState($stateProvider) {

    $stateProvider.state("hello-state", {
        url: "/hello",
        component: HelloComponent.$name
    });
}

export default configureState;