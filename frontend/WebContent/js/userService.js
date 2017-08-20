/**
 * UserService
 */
myApp.service('UserService',function($http){
	var userService={};
	var BASE_URL='http://localhost:8092/task_middleware';
	userService.registerUser=function(user){
		return $http.post(BASE_URL+"/newuser",user)
	}
	userService.validateUser=function(user){
		console.log(user)
		return $http.post(BASE_URL+"/login",user);
	}
	userService.logout=function(){
		return $http.get(BASE_URL+"/logout")
	}

	return userService;
})