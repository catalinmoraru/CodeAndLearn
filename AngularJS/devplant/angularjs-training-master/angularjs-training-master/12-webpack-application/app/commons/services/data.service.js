export default class DataService {
    static get $name() {
        return 'DataService';
    }

    static get $inject() {
        return ['$http', '$log', '$q', '$state'];
    }

    constructor($http, $log, $q, $state) {
        this.apiUrl = "https://jsonplaceholder.typicode.com";
        this.$http = $http;
        this.$log = $log;
        this.$q = $q;
        this.$state = $state;
    }


    getUserOrRedirect(userId) {
        return this.$http.get(this.apiUrl + "/users/" + userId).then(DataService.getComplete)
            .catch((error) => {
                this.$log.error('XHR Failed, Redirecting to home page (users) :' + JSON.stringify(error));
                this.$state.go("default.users");
                return this.$q.reject('Failed to load data!');
            });
    }

    getPostOrRedirect(postId) {
        return this.$http.get(this.apiUrl + "/posts/" + postId).then(DataService.getComplete)
            .catch((error) => {
                this.$log.error('XHR Failed, Redirecting to home page (users) :' + JSON.stringify(error));
                this.$state.go("default.users");
                return this.$q.reject('Failed to load data!');
            });
    }


    getUsers() {
        return this.$http.get(this.apiUrl + "/users").then(DataService.getComplete)
            .catch((error) => {
                this.$log.error('XHR Failed:' + JSON.stringify(error));
                return this.$q.reject('Failed to load data!');
            });
    }


    getUserPosts(userId) {
        return this.$http.get(this.apiUrl + "/posts", {params: {userId: userId}}).then(DataService.getComplete)
            .catch((error) => {
                this.$log.error('XHR Failed:' + JSON.stringify(error));
                return this.$q.reject('Failed to load data!');
            });
    }


    getPostComments(postId) {
        return this.$http.get(this.apiUrl + "/comments", {params: {postId: postId}}).then(DataService.getComplete)
            .catch((error) => {
                this.$log.error('XHR Failed:' + JSON.stringify(error));
                return this.$q.reject('Failed to load data!');
            });
    }


    static getComplete(response) {
        return response.data;
    }


}