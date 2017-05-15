(function () {
    'use strict';

    angular.module('DevPlantApp').controller("PostsController", PostsController);

    function PostsController(DataService, user) {

        const vm = this;

        vm.user = user;
        vm.posts = [];

        onInit();

        function onInit() {
            getUserPosts(vm.user.id);
        }

        function getUserPosts(userId) {

            return DataService.getUserPosts(userId).then(function (posts) {
                vm.posts = posts;
            });
        }

    }
})();