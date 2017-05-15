# 12-webpack-application

This is the last sample, our application is now complete, additionally to the previous sample we added the
`Docker` configuration and the 2 other views.

### Docker

Our Dockerfile, again, is simple, it contains only familiar code

```
FROM nginx
COPY dist/ /usr/share/nginx/html
COPY docker/nginx.conf /etc/nginx/conf.d/default.conf
```

Copy dist and nginx.conf.

### Application 

The only difference to previous examples is that with the new $stateProvider and because of minification from webpack
we need to do the `resolve` part on state transitions differently.

Lets have a look at the posts.routes.js

```
import posts from "./components/posts.component";

configureState.$inject = ['$stateProvider'];
function configureState($stateProvider) {

    $stateProvider.state("default.posts", {
        url: "/users/:userId/posts",
        component: posts.$name,
        resolve: {
            user: resolveUser
        }
    });

    resolveUser.$inject = ['DataService','$stateParams'];
    function resolveUser(DataService, $stateParams){
        return DataService.getUserOrRedirect($stateParams.userId);
    }
}

export default configureState;
```

The routing part is similar to the previous example, the resolve has changed a little, 
Since we need dependency injection the function needs to be declared separately, DateService and $stateParms are then
injected.

We also changed the DataService function a little, it now handles redirection on error, this way we write less code.

```
    getUserOrRedirect(userId) {
        return this.$http.get(this.apiUrl + "/users/" + userId).then(DataService.getComplete)
            .catch((error) => {
                this.$log.error('XHR Failed, Redirecting to home page (users) :' + JSON.stringify(error));
                this.$state.go("default.users");
                return this.$q.reject('Failed to load data!');
            });
    }
```

### Resolve and Components

There's one more thing to resolve. While using components you need to tell the component that its expecting a user.

This is done in the component definition via the `bindings` property

```
import PostsTemplate from "./posts.component.tpl.html";
import PostsController from "./posts.controller";

export default {
    $name: 'posts',
    template: PostsTemplate,
    bindings: {
        user: '<'
    },
    controllerAs: 'vm',
    controller: PostsController
};

```

### Done

Yeah, for real, done