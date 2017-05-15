export default class HelloController {

    static get $inject() {
        return ['HelloService'];
    }

    constructor(HelloService) {
        this.HelloService = HelloService;
        this.message = null;
    }

    sayHello() {
        this.HelloService.fetchGreetings().then((greetings) => {
            this.message = greetings;
        });
    }
}