import PostsTemplate from "./posts.component.tpl.html";
import PostsController from "./posts.controller";

export default {
    $name: 'posts',
    template: PostsTemplate,
    bindings: {
        user: '<'
    },
    controllerAs: 'vm',
    controller: PostsController
};

