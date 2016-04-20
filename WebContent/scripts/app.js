var sharepoint = angular.module("sharepoint", []);

var controllers = {};

controllers.feedController = function($scope,$http) {
	
		var success = function(response) {
			$scope.articlelist = response.data.articlelist;
			$scope.user = response.data.user;
		};
		
		var error = function(response) {
			console.log(response);
		};
		
		$http.post("/NewsGroup/FeedJson").then(success,error);
		
		$scope.loadFeed = function () {
			$http.post("/NewsGroup/FeedJson").then(success,error);
		};
		
};

sharepoint.directive('like',function($http){
	
	return {
		restrict : 'A',
		scope : true,
		replace : true,
		template : "<a  class='namelabel' href style='color:{{color}}'>Like</a>",
		link : function (scope,element,attribute) {
			
			element.on('click',function(){
				
				console.log(attribute.style);
				console.log(scope.$parent.article.aid);
				
				var id = scope.$parent.article.aid;
				
				//set color of Like button
				if(scope.$parent.article.liked==false) {
					element.css({'color' : '#5890ff'});
					scope.$parent.article.liked=true;
					scope.$parent.article.likerlist.push(scope.$parent.user.fname);
					console.log(scope.$parent.article.likerlist);
				}
				else {
					element.css({'color':'#6f6f6f'});
					scope.$parent.article.liked=false;
					scope.$parent.article.likerlist.splice(scope.$parent.article.likerlist.indexOf(scope.$parent.user.fname),1);
					console.log(scope.$parent.article.likerlist);
				}
				
				var success = function(response) {
					
					//get number of likes
					console.log(response.data);
					var displaycount = function(response) {
						scope.$parent.article.count_likes = response.data;
					};
					
					var error = function(response) {
						console.log(response);
					};
					
					$http({
						method : 'POST',
						url : '/NewsGroup/GetLikesCount.action',
						headers: {'Content-Type':'application/x-www-form-urlencoded'},
						data : $.param({aid : id})
					}).then(displaycount,error);
				};
				
				//send like data to server
				$http({
					method : 'POST',
					url : '/NewsGroup/LikeUnlike.action' ,
					headers: {'Content-Type': 'application/x-www-form-urlencoded'},
					data : $.param({aid : id})
				}).then(success,success);
			});
		}
	}
});

sharepoint.directive('comment',function(){
	return {
		restrict : 'A',
		scope : true,
		link : function(scope,element,attrs) {
			scope.$parent.$parent.article.showcomments = false;
			
			element.on('click',function(){
				scope.$parent.$parent.article.showcomments = true;
				console.log(element);
			});
		}
	};
});

sharepoint.directive('tooltip', function(){
    return {
        restrict: 'A',
        link: function(scope, element, attrs){
        	
        	var likerlist = scope.$parent.article.likerlist;
        	
            $(element).hover(function(){
                // on mouseenter
                $(element).tooltip('show');
            }, function(){
                // on mouseleave
                $(element).tooltip('hide');
            });
        }
    };
});

	
controllers.eachpostController = function($scope,$http) {
	
	if($scope.article.liked==true)
		$scope.color = '#5890ff';
	else
		$scope.color = '#6f6f6f';
	
	$scope.article.showcomments = false;
	
	$scope.show = function(){
		if($scope.article.showcomments==false)
			{
			 $scope.article.showcomments = true;
			 var id = $scope.article.aid;
					
					$http({
						method : 'POST',
						url : '/NewsGroup/GetCommentList.action',
						headers : {'Content-Type' : 'application/x-www-form-urlencoded'},
						data : $.param({aid : id})
					}).then(function(response){
						$scope.article.commentlist = response.data.commentlist;
						console.log(response.data);
					}).then(function(response){
						console.log(response);
					});
			}
		else
			$scope.article.showcomments = false;
		console.log("clicked");
	};
}

sharepoint.controller(controllers);

/*
var material = angular.module('material',['ngMaterial']);

material.directive('tooltip',function(){
	return {
        restrict: 'A',
        link: function(scope, element, attrs){
        	
            $(element).hover(function(){
                // on mouseenter
                $(element).tooltip('show');
            }, function(){
                // on mouseleave
                $(element).tooltip('hide');
            });
        }
    };
});
*/