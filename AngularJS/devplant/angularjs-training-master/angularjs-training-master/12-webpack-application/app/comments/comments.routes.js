import comments from "./components/comments.component";

configureState.$inject = ['$stateProvider'];
function configureState($stateProvider) {

    $stateProvider.state("default.comments", {
        url: "/users/:userId/posts/:postId/comments",
        component: comments.$name,
        resolve: {
            user: resolveUser,
            post: resolvePost
        }
    });

    resolveUser.$inject = ['DataService', '$stateParams'];
    function resolveUser(DataService, $stateParams) {
        return DataService.getUserOrRedirect($stateParams.userId);
    }

    resolvePost.$inject = ['DataService', '$stateParams'];
    function resolvePost(DataService, $stateParams) {
        return DataService.getPostOrRedirect($stateParams.postId);
    }

}

export default configureState;