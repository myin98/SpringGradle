var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
  $routeProvider
	  .when("/", {
	    templateUrl : "/v3/list.html",
	    controller : "listCtrl"
	  })
	  .when("/accept/:accept", {
	    templateUrl : "/v3/list.html",
	    controller : "listCtrl"
	  })
	  .when("/new", {
	    templateUrl : "/v3/new.html",
	    controller : "newCtrl"
	  })
	  .when("/detail/:no", {
	    templateUrl : "/v3/detail.html",
	    controller : "detailCtrl"
	  });
});
app.controller("listCtrl", function ($scope, $http, $routeParams) {
	let url = "/v2/findList";
	const success = function (res) {
		if(res.status == 200) {
			$scope.list = res.data;
		}
	};
	const error = function (res) {
		console.log(res);
	};
	if($routeParams.accept != undefined) {
		url += "/" + $routeParams.accept;
	}
	$http.post(url).then(success, error);
});
app.controller("newCtrl", function ($scope, $http) {
	const success = function (res) {
		if(res.status == 200) {
			alert("작성이 성공 하였습니다.");
			location.href = "#!/detail/" + res.data.no; 
		} else {
			alert("작성이 실패 하였습니다.");
		}
	};
	const error = function (res) {
		console.log(res);
	};
	$scope.submit = function () {
		let configs = { cache: false };
		configs.params = $scope.params;
		$http.post("/v2/save", null, configs).then(success, error);
	}
});
app.controller("detailCtrl", function ($scope, $http, $routeParams) {
	let check = false;
	const success = function (res) {
		if(res.status == 200) {
			$scope.data = res.data;
			if(check) alert("수정이 성공 하였습니다.");
			check = false;
		} else {
			alert("수정이 실패 하였습니다.");
		}
	};
	const error = function (res) {
		console.log(res);
	};
	const getUrl = function (accept) {
		let url = "/v2/detail/"; 
		if($routeParams.no != undefined) {
			url += $routeParams.no;
		}
		if(accept != undefined) {
			url += "/" + accept;
		}
		return url;
	}
	$scope.submit = function () {
		check = true;
		let configs = { cache: false };
		configs.params = $scope.data;
		$http.post("/v2/edit", null, configs).then(success, error);
	}
	$scope.onClick = function (accept) {
		check = true;
		$http.post(getUrl(accept)).then(success, error);
	}
	$http.post(getUrl()).then(success, error);
});