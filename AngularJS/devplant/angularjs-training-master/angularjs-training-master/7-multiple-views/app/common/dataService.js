(function () {
    'use strict';

    class DataService {

        constructor($http, $log, $q) {
            this.$http = $http;
            this.$log = $log;
            this.$q = $q;
            this.API_URL = "https://jsonplaceholder.typicode.com";
        }


        getUser(userId) {
            return this.$http.get(this.API_URL + "/users/" + userId).then((response) => {
                return response.data;
            }).catch((error) => {
                return this.getFailed(error)
            });
        }

        getUsers() {
            return this.$http.get(this.API_URL + "/users").then((response) => {
                return response.data;
            }).catch((error) => {
                return this.getFailed(error)
            });
        }

        getPost(postId) {
            return this.$http.get(this.API_URL + "/posts/" + postId).then((response) => {
                return response.data;
            }).catch((error) => {
                return this.getFailed(error)
            });
        }

        getUserPosts(userId) {
            return this.$http.get(this.API_URL + "/posts", {params: {userId: userId}}).then((response) => {
                return response.data;
            }).catch((error) => {
                return this.getFailed(error)
            });
        }

        getPostComments(postId) {
            return this.$http.get(this.API_URL + "/comments", {params: {postId: postId}}).then((response) => {
                return response.data;
            }).catch((error) => {
                return this.getFailed(error)
            });
        }

        getFailed(error) {
            this.$log.error('XHR Failed: ' + JSON.stringify(error));
            return this.$q.reject('Failed to load data!');
        }


    }

    angular.module('DevPlantApp').factory("DataService", DataService);

})();