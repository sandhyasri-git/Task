/**
 * app.js
 */
var myApp=angular.module("myApp",['ngRoute','ngCookies'])

myApp.config(function($routeProvider){
	$routeProvider
	.when('/home',{
		templateUrl:'views/home.html'
	})
	
	.when('/register',{
		templateUrl:'views/newUserForm.html',
		controller:'userController'
	})
	.when('/login',{
		templateUrl:'views/login.html',
		controller:'userController'
	})
	.otherwise({redirectTo: '/'
	})
})
myApp.run(function($rootScope,$cookieStore){
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get("currentUser")
})