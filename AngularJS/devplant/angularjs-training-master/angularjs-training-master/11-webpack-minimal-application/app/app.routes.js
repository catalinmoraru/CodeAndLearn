defaultRoutesConfiguration.$inject = ['$urlRouterProvider', '$locationProvider'];
function defaultRoutesConfiguration($urlRouterProvider, $locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix("*");
    $urlRouterProvider.otherwise("/users");
}

export default defaultRoutesConfiguration;