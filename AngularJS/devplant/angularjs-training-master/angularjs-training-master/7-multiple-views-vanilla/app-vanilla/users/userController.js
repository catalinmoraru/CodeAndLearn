(function () {
    'use strict';

    angular.module('DevPlantApp').controller("UserController", UserController);

    function UserController(DataService) {

        const vm = this;

        vm.users = [];

        onInit();

        function onInit() {
            getUsers();
        }

        function getUsers() {
            return DataService.getUsers().then(function (users) {
                vm.users = users;
            });
        }

    }
})();