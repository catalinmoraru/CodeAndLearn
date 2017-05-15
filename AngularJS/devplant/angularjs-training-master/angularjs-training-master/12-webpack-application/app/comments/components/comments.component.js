import CommentsTemplate from "./comments.component.tpl.html";
import CommentsController from "./comments.controller";

export default {
    $name: 'comments',
    template: CommentsTemplate,
    bindings: {
        user: '<',
        post: '<'
    },
    controllerAs: 'vm',
    controller: CommentsController
};





