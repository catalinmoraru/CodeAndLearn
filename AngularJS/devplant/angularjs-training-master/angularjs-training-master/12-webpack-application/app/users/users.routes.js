import user from "./components/user.component";

configureState.$inject = ['$stateProvider'];
function configureState($stateProvider) {

    $stateProvider.state("default.users", {
        url: "/users",
        component: user.$name
    });
}

export default configureState;