class PromisesService {

    constructor($q, $http, $timeout) {
        this.$q = $q;
        this.$http = $http;
        this.$timeout = $timeout;

        this.validUserApiUrl = "https://jsonplaceholder.typicode.com/users";
        this.validPostsApiUrl = "https://jsonplaceholder.typicode.com/posts";
        this.invalidApiUrl = "https://jsonplaceholder.typicode.com/invalid";

    }

    users() {
        return this.$http.get(this.validUserApiUrl).then((response) => {
            console.log('got a valid user response');
            return response.data;
        });
    }

    posts() {
        return this.$http.get(this.validPostsApiUrl).then((response) => {
            console.log('got a valid posts response');
            return response.data;
        });
    }

    invalid() {
        return this.$http.get(this.invalidApiUrl).catch((error) => {
            console.log('Error happend since URL is not valid', JSON.stringify(error));
            return this.$q.reject('Error happend!');
        });
    }

    postAndUsers() {
        return this.$q.all([this.users(), this.posts()]);
    }

    postAndUsersFailed() {
        return this.$q.all([this.users(), this.posts(), this.invalid()]);
    }

    customOk() {
        //custom promise
        const deferred = this.$q.defer();
        // simulate a long running task
        console.log('$1 = this will print first');
        this.$timeout(function () {
            console.log('$3 = this will print after $2');
            deferred.resolve('Task Completed');
        }, 5000);
        console.log('$2 = this will after $1');
        return deferred.promise;
    }

    customFail() {
        //custom promise
        const deferred = this.$q.defer();
        // simulate a long running task
        console.log('$1 = this will print first');
        this.$timeout(function () {
            console.log('$3 = this will print after $2');
            deferred.reject('Task Completed');
        }, 5000);
        console.log('$2 = this will after $1');
        return deferred.promise;
    }

    getSimpleValue() {
        return "This is just a string";
    }

    turnValueToPromise() {
        return this.$q.when(this.getSimpleValue());
    }


}