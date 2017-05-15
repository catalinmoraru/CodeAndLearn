export default class HelloService {

    static get $inject() {
        return ['$q'];
    }

    static get $name() {
        return "HelloService";
    }

    constructor($q) {
        this.$q = $q;
    }

    fetchGreetings() {
        return this.$q.resolve("Hello from DevPlant");
    }

}