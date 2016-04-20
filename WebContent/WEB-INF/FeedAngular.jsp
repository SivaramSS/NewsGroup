<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div ng-controller="feedController" style="margin-top:8%;">
<div ng-repeat="article in articlelist" ng-controller="eachpostController">

	<div style="background: #ffffff; margin-left:2%; margin-right: 2%; margin-top:2%; border-radius:2%;">
    		    
    		    <div class="titleblock">
    		    	<a ng-href="{{article.url}}" target="_blank"><h3 class="title">{{article.title}}</h3></a>
    		    	<a class="timestamp" ng-href="{{article.posturl}}">{{article.uldatetime}}</a>
    		    	<hr class="hrline"/>
    		    </div>
    		    
    		    <div class="content">
    				{{article.content}}...<a class="name" ng-href="{{article.url}}" style="color:#0033aa;" target="_blank">Read more</a>
    				<br/>
    			</div>
    			<br/>
    			
    			<div>
    				<div ng-hide="article.count_likes==0" style="float:left;" class="namelabel">
    					<a href class="namelabel" data-toggle="tooltip" style="white-space:nowrap;" data-placement="right" data-original-title="{{article.likerlist}}" tooltip>{{article.count_likes}} like this post</a>
    				</div>
    		    	<div class="namelabel" style="float:right;">
    					<span style="">post by </span>
    					<a class="name" ng-href="{{article.profileurl}}">
    	 					{{article.fname}}
    					</a>
    				</div>
    			</div>
    			
    			<div class="box" style="margin-top:4%">
    				<hr class="hrline"/>
    				<div class="nameblock">
    					<a href like>Like</a>
    					<a href class="namelabel" ng-click="show();">
    					 	<span ng-hide="article.count_comments==0">{{article.count_comments}}</span> Comment<span ng-hide="article.count_comments==1 || article.count_comments==0">s</span>
    					 </a>
    					 <hr class="hrline"/>
    					 <div ng-show="article.showcomments==true">
    						<div class="commentbox">
    							
    							<div class="namelabel" style="margin-left:0px; margin-top:-1%;">
    								<a class="name" ng-href="{{comment.profileurl}}" style="float:left;">
    	 								{{user.fname}}
    								</a>
    								<input type="text" class="pastebox" style="display:inline;width:60%;margin-left:5%;margin-right:5%;" value="Write a comment" 
    								onfocus="if(this.value=='Write a comment'){this.value=''}" 
    								onblur="if(this.value==''){this.value='Write a comment'}"/>
    								<button class="button">Comment</buton>
    							</div>
    							
    							<hr class="hrline" style="margin-left:-2%"/>
    							
    							<div ng-repeat = "comment in article.commentlist">
    								<div class="namelabel" style="margin-left:0px;margin-top:-1%">
    									<a class="name" ng-href="{{comment.profileurl}}" style="float:left;">
    	 									{{comment.fname}}
    									</a>
    									<div style="display:inline;width:60%;text-align:center;margin-left:2%;margin-right:2%;">{{comment.content}}</div>
    									<span style="float:right;">
    										{{comment.cdate}}
    									</span>
    								</div>
    								<hr class="hrline" style="margin-left:-2%"/>
    							</div>
    							
    						</div>
    					</div>
    				</div>
    				
    			</div>
    </div>
   </div>
 </div>
</div>
</div>
