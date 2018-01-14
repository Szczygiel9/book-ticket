'use strict';
angular.module('app', ['ngResource'])
    .controller('MovieController', function($http, $resource) {
    var vm = this;
    var Movie = $resource('api/movies/:movieId');
    vm.movie = new Movie();
    function refreshData() {
        vm.movies = Movie.query(
            function success(data, headers) {
                console.log('Pobrano dane: ' +  data);
                console.log(headers('Content-Type'));
            },
            function error(reponse) {
                console.log(response.status); //np. 404
            });
    }

    vm.loadData = function(id) {
        vm.details = Movie.get({movieId: id});
    }

    vm.appName = 'Movie Manager';
    refreshData();
});