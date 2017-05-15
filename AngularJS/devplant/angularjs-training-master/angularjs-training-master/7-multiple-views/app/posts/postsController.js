(function () {
    'use strict';

    class PostsController {

        constructor(DataService, user) {
            this.DataService = DataService;
            this.user = user;
            this.DataService.getUserPosts(this.user.id).then((posts) => {
                this.posts = posts;
            });
        }

    }

    angular.module('DevPlantApp').controller("PostsController", PostsController);

})();