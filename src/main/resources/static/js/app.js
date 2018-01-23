'use strict';
angular.module('app', ['ngResource'])
    .controller('MovieController', function($http, $resource) {
    var vm = this;
    var Movie = $resource('api/movies/:movieId');
    vm.movie = new Movie();

    var Cities = $resource('api/cinemas/cities');
    vm.cities = new Cities();

    var Seance = $resource('api/seances/:seanceId/add');
    vm.seance = new Seance();

    var SeanceInCity = $resource('api/seances/in/:cityName');
    vm.seanceInCity = new SeanceInCity();

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
    vm.loadCities = function () {
        vm.city = Cities.get();
    }

    vm.loadData = function(id) {
        vm.details = Movie.get({movieId: id});
    }

    vm.showMovies = function (name) {
        vm.showMov = SeanceInCity.get({cityName: name});
    }

    vm.addReservation = function (id) {
        vm.det = Seance.get({seanceId: id});
    }
    vm.appName = 'Movie Manager';
    refreshData();
});