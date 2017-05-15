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
