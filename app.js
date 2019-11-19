var app = angular.module('myApp', []);
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.timeout = 20000;
}])
app.controller('myCtrl', function($scope, $http) {
    this.retrieve = function() {
    $http.get('http://127.0.0.1:8080/titanic/retrieve/' + $scope.passenger)
    .then(function (response) {
        console.log('inside'+ response);
        $scope.ret = response.data;
    }, function (response) {
        console.log('came here');
    });
    }

    this.delete = function(){
      $http.delete('http://127.0.0.1:8080/titanic/delete/' + $scope.passenger)
      .then(function (response) {
          console.log('inside'+ response);
          alert("Database info deleted")
      }, function (response) {
          console.log('delete error');
      });
    }

    this.add = function() {
        var message = {
            passenger: $scope.passenger,
            survived:$scope.survived,
            pclass:$scope.pclass,
            name:$scope.name,
            sex:$scope.sex,
            age:$scope.age,
            sibsp:$scope.sibsp,
            parch:$scope.parch,
            ticket:$scope.ticket,
            fare:$scope.fare,
            cabin:$scope.cabin,
            embarked:$scope.embarked
        }
        $http.post('http://127.0.0.1:8080/titanic/create', message)
            .then(function(response) {
              console.log('inside'+response);
              alert("Passenger Added")
            }, function(response) {
                console.log('error..');
            });
    }

   this.update = function(){
     $http.put('http://127.0.0.1:8080/titanic/update/' + $scope.passenger +'/'+ $scope.pclass)
     .then(function (response) {
        console.log('inside'+response);
        alert("Database Updated")
     }, function (response) {
         console.log('update error');
     });
   }

   this.retsurvive = function() {
   $http.get('http://127.0.0.1:8080/titanic/survived/' + $scope.survived)
   .then(function (response) {
       console.log('inside'+ response);
       $scope.sur = response.data;
   }, function (response) {
       console.log('survive error');
   });
 }

 this.retrievePages = function() {
 $http.get('http://127.0.0.1:8080/titanic/filtering/' + $scope.age + '/' + $scope.page + '/' + $scope.limit)
 .then(function (response) {
     console.log('inside'+ response);
     $scope.pages = response.data;
 }, function (response) {
     console.log('came here');
 });
}

 this.retrieveAgeRange = function() {
 $http.get('http://127.0.0.1:8080/titanic/range/' + $scope.ageStart + '/' + $scope.ageEnd)
 .then(function (response) {
     console.log('inside'+ response);
     $scope.ranges = response.data;
 }, function (response) {
     console.log('came here');
 });
 }

});
