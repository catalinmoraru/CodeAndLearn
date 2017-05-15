export default class UserService{

    static get $name(){
        return "UserService";
    }

    constructor($http){
        this.$http = $http;
        this.API_URL = "https://jsonplaceholder.typicode.com/users";
    }

    getUsers(){
        return this.$http.get(this.API_URL).then((response)=>{
            return response.data;
        });
    }
}