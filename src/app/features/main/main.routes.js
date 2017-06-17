(function() {
  'use strict';

  angular
    .module('frontEnd')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/features/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      });
  }

})();
