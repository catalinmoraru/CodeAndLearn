defaultRoutesConfiguration.$inject = ['$urlRouterProvider', '$locationProvider'];
function defaultRoutesConfiguration($urlRouterProvider, $locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix("*");
    $urlRouterProvider.otherwise("/hello");
}

export default defaultRoutesConfiguration;