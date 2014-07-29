/*
 * rave
 * This file defines the main module, called rave.
 *
 */

(function() {

  // The dependencies of our application
  var raveDependencies = [
    'ui.router',
    'profile',
    'ngMockE2E'
  ];

  var rave = angular.module('rave', raveDependencies);

  rave.controller('appData', ['$scope', function($scope) {
    $scope.user = window._initialData.user;
    $scope.nav = window._initialData.nav;
  }]);

  // Exposed on the window *only* for debugging purposes.
  window.rave = rave;
})();
