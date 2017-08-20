/**
 * UserController
 */

myApp.controller('userController',function(UserService,$scope,$rootScope,$location,$cookieStore)
		{
	this.message="i am in user controller.js";
	$scope.user={}
	$scope.registerUser=function(){
		UserService.registerUser($scope.user).then(
			function(response){
				$scope.message="Registration Successful. Login to continue"
					$location.path('/login')
			},function(response){
				console.log(response.data)
				console.log(response.status)
				$scope.error=response.data
				$location.path('/newUserForm')
			})
	    }
	$scope.validateUser=function(){
		UserService.validateUser($scope.user).then(
				function(response){
					console.log(response.data)
					$rootScope.currentUser=response.data
					$cookieStore.put("currentUser",response.data)
					$location.path('/home')
				},function(response){
					$scope.error=response.data
					console.log(response.status)
					$location.path('/login')
				})
	}
	$rootScope.logout=function(){
		UserService.logout().then(function(response){
			this.message="i am in user controller.js";
			$rootScope.logoutMessage="Logged out Successfully"
				delete $rootScope.currentUser
				$cookieStore.remove("currentUser")
				$location.path('/login')
		},function(response){
			$scope.error=response.data
			$location.path('/login')
		})
	}
})
