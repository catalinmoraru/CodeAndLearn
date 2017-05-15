# 9-deployments

So now our app looks quite decent, adn with the knowledge gathered so far we could actually create a semi-decent application.
So now the question arises, how do we deploy this ?

### Docker to the rescue

Docker is a container platform that's used for both development and production usecases: https://www.docker.com/what-docker

**So, how does this help me?**

Well, in an traditional app you'd have your application server, or a tomcat, in which you run either both frontend and backend code
or just the frontend while the backend, like in our usecase is some REST Api.

This is counter-intuitive for development, we develop on our local machine using some mock http-server ( because its often 
very complicated to have a production like setup ) and then deploy our app, tested locally under different circumstances
onto some server where everything goes wrong. 

Its the developer paradigm of **"works on my machine"**.

So, what does docker do:

- You start from a predefined image ( or you can craft your own, but that's another story )
- You add things, I.E. your application code to that image
- You run it as a container, this means you don't need to have anything like tomcat installed on your local machine
- Containers do not interfere with your local setup
- This way you can use the same image on both localhost as well as production ( sometime minor adaptations )
- This is very similar to modern approaches to testing, where your test environment should be equal to your production environment
 
### Deploying your application

You'll need docker for this step: https://www.docker.com/get-docker

Depending on your OS this might be somewhat different.

After docker is installed, you can run:

- `npm run docker-build` - to build the image
- `npm run docker-stop` - to stop the current running instance
- `npm run docker-run` - to run the image, after build
- `npm run docker-clean` - to remove all docker containers
- `npm run docker-clean-win` - to remove all docker containers on windows


### Looking at the setup

We have a new folder called docker, this contains a Dockerfile and an nginx.conf file.
- The Docker file is used to describe how an image should be assembled
- The nginx.conf is a configuration file for nginx 


#### A look at the Docker file

- The Dockerfile basically states the following, start from an `nginx` image
- Copy our nginx.conf into the image to __/etc/nginx/conf.d/default.conf__
- Copy all our application code as well as the required libraries into the image
- Our target path is `/usr/share/nginx/html` - this is where nginx looks by default when serving content

```
FROM nginx

COPY docker/nginx.conf /etc/nginx/conf.d/default.conf

COPY index.html /usr/share/nginx/html/
COPY app/ /usr/share/nginx/html/app/

COPY node_modules/angular/angular.min.js /usr/share/nginx/html/node_modules/angular/
COPY node_modules/angular-route/angular-route.min.js /usr/share/nginx/html/node_modules/angular-route/
COPY node_modules/angular-animate/angular-animate.min.js /usr/share/nginx/html/node_modules/angular-animate/
COPY node_modules/angular-aria/angular-aria.min.js /usr/share/nginx/html/node_modules/angular-aria/
COPY node_modules/angular-messages/angular-messages.min.js /usr/share/nginx/html/node_modules/angular-messages/
COPY node_modules/angular-material/angular-material.min.js /usr/share/nginx/html/node_modules/angular-material/
COPY node_modules/angular-material/angular-material.min.css /usr/share/nginx/html/node_modules/angular-material/

```

#### What is nginx
Its an high-performance HTTP Server, its the most commonly used server for web applications that are built with just
javascript, html, and css

The nginx.conf file is used to overwrite some default behaviour, it basically allows us to run HTML5 mode in our angular
application.
