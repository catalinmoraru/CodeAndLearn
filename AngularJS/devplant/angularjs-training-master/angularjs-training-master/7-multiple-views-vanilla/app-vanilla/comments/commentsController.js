(function () {
    'use strict';

    angular.module('DevPlantApp').controller("CommentsController", CommentsController);

    function CommentsController(DataService, user, post) {

        const vm = this;

        vm.user = user;
        vm.post = post;
        vm.comments = [];

        onInit();

        function onInit() {
            getPostComments(vm.post.id);
        }

        function getPostComments(postId) {
            return DataService.getPostComments(postId).then(function (comments) {
                vm.comments = comments;
            });
        }

    }
})();