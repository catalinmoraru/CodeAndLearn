class TestController {

    constructor(PromisesService) {
        this.PromisesService = PromisesService;
    }

    postAndUsers() {
        this.PromisesService.postAndUsers().then((response) => {
                console.log('Response is an array, containing an array of users and an array of posts', response);
            }
        );
    }

    postAndUsersFailed() {
        this.PromisesService.postAndUsersFailed().catch((error) => {
            console.log('Controller Error', error);
        });
    }

    customOk() {
        const okPromise = this.PromisesService.customOk();
        console.log('this will print before $3');
        okPromise.then((ok) => {
            console.log("this completed after 5 seconds", ok);
        });
        console.log('this will also print before $3');
    }

    customFail() {
        const failPromise = this.PromisesService.customFail();
        console.log('this will print before $3');
        failPromise.catch((fail) => {
            console.log("this will failed after 5 seconds", fail);
        });
        console.log('this will also print before $3');
    }

    simpleValue() {
        try {
            this.PromisesService.getSimpleValue().then(() => {
                console.log('this will never print');
            }).catch(() => {
                console.log("neither will this, its not a promise, its a value");
            });
        } catch (exception) {
            console.log("we will end up here, the .then will throw an exception, you can't invoke then on a strinc");
        }
    }

    turnValueToPromise() {
        this.PromisesService.turnValueToPromise().then((promiseValue) => {
            console.log("Value turned to promise", promiseValue);
            return "test";
        }).then((data) => {
            console.log('this will work, then returns are wrapped into promises, you can keep chaining it up', data);
            return data + " 1 ";
        }).then((data) => {
            console.log('still going strong', data);
            return data + " 2 ";
        }).then((data) => {
            console.log('then end', data);
        });
    }

}
