export default class HomeController {

    // static get $inject() {
    //     return ['TextService'];
    // }

    constructor(TextService) {
        this.TextService = TextService;
        this.showText = true;
    }

    $onInit() {
        this.text = this.TextService.getText();
    }

    toggleText() {
        this.showText = !this.showText;
    }

}
