export default class CommentsController {

    constructor(DataService) {
        this.DataService = DataService;
    }

    $onInit() {
        this.DataService.getPostComments(this.post.id).then((comments) => {
            this.comments = comments;
        });
    }

    static get $inject() {
        return ['DataService'];
    }

};