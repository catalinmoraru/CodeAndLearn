(function () {
    'use strict';

    angular.module('DevPlantApp', ['ngRoute']).config(function ($routeProvider, $locationProvider) {

        $locationProvider.html5Mode(true).hashPrefix("*");

        $routeProvider
            .when('/users', {
                templateUrl: 'app-vanilla/users/users.html',
                controller: 'UserController',
                controllerAs: 'vm'
            })
            .when('/users/:userId/posts', {
                templateUrl: 'app-vanilla/posts/posts.html',
                controller: 'PostsController',
                controllerAs: 'vm',
                resolve: {
                    user: function (DataService, $route, $location, $q) {
                        return DataService.getUser($route.current.params.userId).catch(function () {
                            $location.url('/users');
                            return $q.reject('Post not available');
                        });
                    }
                }
            })
            .when('/users/:userId/posts/:postId/comments', {
                templateUrl: 'app-vanilla/comments/comments.html',
                controller: 'CommentsController',
                controllerAs: 'vm',
                resolve: {
                    user: function (DataService, $route, $location, $q) {
                        return DataService.getUser($route.current.params.userId).catch(function () {
                            $location.url('/users');
                            return $q.reject('Post not available');
                        });
                    },
                    post: function (DataService, $route, $location, $q) {
                        return DataService.getPost($route.current.params.postId).catch(function () {
                            $location.url('/users');
                            return $q.reject('Post not available');
                        });
                    }
                }

            }).otherwise({redirectTo: '/users'});
    });

})();