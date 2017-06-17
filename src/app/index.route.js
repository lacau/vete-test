(function() {
  'use strict';

  angular
    .module('frontEnd')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
  }

})();
