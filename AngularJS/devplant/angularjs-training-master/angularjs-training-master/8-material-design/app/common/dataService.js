(function () {
    'use strict';

    angular.module('DevPlantApp').factory("DataService", DataService);

    function DataService($http, $log, $q) {

        const apiUrl = "https://jsonplaceholder.typicode.com";

        const service = {
            getUsers: getUsers,
            getUserPosts: getUserPosts,
            getPostComments: getPostComments,
            getUser: getUser,
            getPost: getPost
        };


        function getUser(userId) {
            return $http.get(apiUrl + "/users/" + userId).then(getComplete)
                .catch(getFailed);
        }

        function getUsers() {
            return $http.get(apiUrl + "/users").then(getComplete)
                .catch(getFailed);
        }

        function getPost(postId) {
            return $http.get(apiUrl + "/posts/" + postId).then(getComplete)
                .catch(getFailed);
        }

        function getUserPosts(userId) {
            return $http.get(apiUrl + "/posts", {params: {userId: userId}}).then(getComplete)
                .catch(getFailed);
        }

        function getPostComments(postId) {
            return $http.get(apiUrl + "/comments", {params: {postId: postId}}).then(getComplete)
                .catch(getFailed);
        }

        function getComplete(response) {
            return response.data;
        }

        function getFailed(error) {
            $log.error('XHR Failed: ' + JSON.stringify(error));
            return $q.reject('Failed to load data!');
        }


        return service;
    }
})();