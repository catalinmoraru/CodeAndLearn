export default class PostsController {

    constructor(DataService) {
        this.DataService = DataService;
        this.posts = [];
    }


    $onInit() {
        return this.DataService.getUserPosts(this.user.id).then((posts) => {
            this.posts = posts;
        });
    }

    static get $inject() {
        return ['DataService'];
    }

}
