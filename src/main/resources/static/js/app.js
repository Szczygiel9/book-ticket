'use strict';

angular.module('app', ['ngRoute', 'ngResource'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/list', {
                templateUrl: 'partials/list.html',
                controller: 'ListController',
                controllerAs: 'listCtrl'
            })
            .when('/details/:id', {
                templateUrl: 'partials/details.html',
                controller: 'DetailsController',
                controllerAs: 'detailsCtrl'
            })
            .when('/new', {
                templateUrl: 'partials/new.html',
                controller: 'NewController',
                controllerAs: 'newCtrl'
            })
            .otherwise({
                redirectTo: '/list'
            });
    })
    .constant('MOVIE_ENDPOINT', '/api/movies/:id')
    .factory('Movie', function($resource, MOVIE_ENDPOINT) {
        return $resource(MOVIE_ENDPOINT);
    })
    .service('Movies', function(Movie) {
        this.getAll = function() {
            return Movie.query();
        }
        this.get = function(index) {
            return Movie.get({id: index});
        }
        this.add = function(movie) {
            movie.$save();
        }
    })
    .controller('ListController', function(Movies) {
        var vm = this;
        vm.movies = Movies.getAll();
    })
    .controller('DetailsController', function($routeParams, Movies) {
        var vm = this;
        var movieIndex = $routeParams.id;
        vm.movie = Movies.get(movieIndex);
    })
    .controller('NewController', function(Movies, Movie) {
        var vm = this;
        vm.movie = new Movie();
        vm.saveMovie = function() {
            Movies.add(vm.movie);
            vm.movie = new Movies();
        }
    });