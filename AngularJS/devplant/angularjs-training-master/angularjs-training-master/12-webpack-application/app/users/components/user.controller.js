export default class UserController {

    constructor(DataService) {
        this.DataService = DataService;
        this.users = [];

    }

    $onInit() {
        this.DataService.getUsers().then((users) => {
            this.users = users;
        });
    }

    static get $inject() {
        return ['DataService'];
    }

}
