export default class UserController{

    constructor(UserService){
        this.UserService = UserService;
        this.users = [];
    }

    getUsers(){
        this.UserService.getUsers().then(
            (users) => {
                this.users = users;
            }
        );
    }
}