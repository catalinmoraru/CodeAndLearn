# 10-webpack-deployments-docker

Now, since we made it this far, why not have a look at how things work in docker?

This example contains exactly the same code as the previous one, but we added the docker setup.

The docker commands are almost the same as in example **9-deployments** 

`"docker-build": "node node_modules/.bin/webpack -p && docker build -t devplant/webpack-basics -f docker/Dockerfile .",`
This is a new command that chains webpack together with docker, first run webpack, then build a docker image.

#### Dockerfile got a lot cleaner

Our new Dockerfile only contains the following statements:

```
FROM nginx
COPY dist/ /usr/share/nginx/html
```

Webpack puts production-ready cody into the `dist` folder - we just take that and put it into nginx's root!