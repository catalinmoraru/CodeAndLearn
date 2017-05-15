(function () {
    'use strict';

    class UserController {

        constructor(DataService) {
            this.DataService = DataService;
            this.DataService.getUsers().then((users) => {
                this.users = users;
            });
        }

    }

    angular.module('DevPlantApp').controller("UserController", UserController);

})();