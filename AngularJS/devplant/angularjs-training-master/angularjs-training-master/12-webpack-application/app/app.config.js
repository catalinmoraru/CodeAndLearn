themeConfiguration.$inject = ['$mdThemingProvider'];
function themeConfiguration($mdThemingProvider) {

    $mdThemingProvider.theme('default')
        .primaryPalette('deep-orange')
        .accentPalette('light-green').warnPalette('red');

}

export default themeConfiguration;


