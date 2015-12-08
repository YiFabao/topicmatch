FastClick.attach(document.body);
$(function(){
	var $div_li =$(".tab-b .tab_menu ul li");
	$div_li.live('click',function(){
	$(this).addClass("selected") 
	.siblings().removeClass("selected"); 
	var index =  $div_li.index(this);
	$("div.tab_box > div")   
	.eq(index).show() 
	.siblings().hide();
	})
})
//表单验证提示样式
$.testRemind.css = {
    fontSize: "16px"	
};
//二次密码验证
function checkPwd(even){
	var pwd = $(even+" .pwd"), pwdCheck = $(even+" .pwdcheck");
	pwd.bind("keyup", function() {
	    if ($.html5Validate.isEmpty(this)) {
	        pwdCheck.attr("disabled", "disabled");    
	    } else {
	        pwdCheck.removeAttr("disabled");
	    }
	});
	$(even).html5Validate(function() {
		var self = this;
		// 前后密码一致的验证
	    if (pwd.val() !== pwdCheck.val()) {
	        pwdCheck.testRemind("前后密码不一致").get(0).select();    
	    } else {
			self.submit(); 
		}
	});
}
Mobilebone.callback = function(page_in, page_out) {
	if(page_in) {
	    //遍历连接
	    $("*").each(function(){
	    	var url = $(this).attr('data-url')
	    	if(url){
	    		$(this).click(function(){
	    			window.location.href=url
	    		})
	    	}
	    })
	};
}
//阻止表单提交	
var validate_false = function(form) {
    return true;    
};
//多选按钮模拟
$(".form .cbox").click(function(){
	var cd = $(this).find(".checked")
	if(cd.is(":visible")){
		cd.hide()
	}else{
		cd.show()
	}
	return false;
})
//单选按钮模拟
$(".form .radio").click(function(){
	$(this).addClass("checked-r").siblings().removeClass("checked-r")
	return false;
})
Mobilebone.onpagefirstinto = function(pageinto) {
	// bind custom scroll events for content
	var weChatScroll = new IScroll(pageinto.querySelector(".content"), {
		tap: true
	});
};
//操作标签相关
$('.interests .tag .del').live('click',function(){
	$(this).parent('.tag').remove();
})
$('#AddTagBtn').click(function(){
	var c = $(this).prev();
	$('.login-3 .cont').append('<a href="#" class="tag">'+ c.val()+'<i class="iconfont del"></i></a>')
	c.val("");
})
//年月日联动菜单
function YearMonthDay(){
	var fo=document.getElementById("ComRegForm");
   foday=fo.day;
   MonHead=[31,28,31,30,31,30,31,31,30,31,30,31];
  
   //add options for year
   y=new Date().getFullYear();
   for(i=y;i>=(y-100);i--)
   fo.year.options.add(new Option(i,i));
   fo.year.options.value=y;//current year
  
    //add options for month
   m=new Date().getMonth();
   for(i=1;i<=12;i++)
   fo.month.options.add(new Option(i,i));
   fo.month.options.value=m+1;//current month
  
   //add options for day
   d=new Date().getDay();
   n=MonHead[m];
   if(m==1&&IsRunYear(fo.year.options.value))
   n++;
   day(n);
   fo.day.options.value=d+1;//curren day
}

   //onchange of year
function yy(str){
	var fo=document.getElementById("ComRegForm");
   monthValue=fo.month.options[fo.month.selectedIndex].value;
   if(monthValue==""){
      var foday=fo.day;
   optionClear(foday);
   return;
    }
   var n=MonHead[monthValue-1];
   if(monthValue==2&&IsRunYear(str)) n++;
   day(n);
}

   //onchange of month
function mm(ab){
	var fo=document.getElementById("ComRegForm");
  yearValue=fo.year.options[fo.year.selectedIndex].value;
 if(yearValue==""){
  optionClear(foday);
  return;
 }
 var n=MonHead[ab-1];
 if(ab==2&&IsRunYear(yearValue)) n++;
 day(n);
}
  
function day(ab){
   optionClear(foday);
   for(var i=1;i<=ab;i++)
   foday.options.add(new Option(i,i));
 }
 
function optionClear(ab){
   for(var i=ab.options.length;i>0;i--)
   ab.remove(i);
}

function  IsRunYear(year){ 
   return(0==year%4&&(year%100!=0 || year%400==0));
}