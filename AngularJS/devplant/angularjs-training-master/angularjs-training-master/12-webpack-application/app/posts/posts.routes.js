import posts from "./components/posts.component";

configureState.$inject = ['$stateProvider'];
function configureState($stateProvider) {

    $stateProvider.state("default.posts", {
        url: "/users/:userId/posts",
        component: posts.$name,
        resolve: {
            user: resolveUser
        }
    });

    resolveUser.$inject = ['DataService','$stateParams'];
    function resolveUser(DataService, $stateParams){
        return DataService.getUserOrRedirect($stateParams.userId);
    }
}

export default configureState;