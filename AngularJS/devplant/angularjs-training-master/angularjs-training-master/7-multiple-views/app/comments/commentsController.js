(function () {
    'use strict';

    class CommentsController {

        constructor(DataService, user, post) {
            this.DataService = DataService;
            this.user = user;
            this.post = post;
            this.DataService.getPostComments(this.post.id).then((comments) => {
                this.comments = comments;
            });
        }
    }

    angular.module('DevPlantApp').controller("CommentsController", CommentsController);

})();