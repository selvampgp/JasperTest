(function(){
	
	var app = angular.module("sample",[]);
	app.controller("SampleController",function(){
		this.value=values;
		
		this.txt1="";
		
		 this.inCurr = 'EUR';
		  this.currencies = ['USD', 'EUR', 'CNY'];
		  
		this.selected='aa';
		this.selOption=['aa','bb','cc','dd'];
		
		this.add = function add() {
			this.selOption.push(this.txt1);
		  };
	});
	
	values={a:10,b:10};
})();
