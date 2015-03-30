$(function(){
	//导航效果
	var var_a;
	if($('.nav li').find(".current").length<0) var_a = false
		else var_a = true
	$(".nav ul").lavaLamp({
		fx: "backout",
		speed:300,
		click: function(event, menuItem) {
			$(this).addClass("current").siblings().removeClass("current")
			$(this).nextAll(".back").css("display","block")
			return false;
		}
	});
	if(var_a) $('.nav li.back').show()
		else $(".nav li").removeClass("current") 
	current = $(".nav").find(".current");
	$(".nav li").hover(function(){
		current.removeClass("current");
		},function(){
			current.addClass("current")
		}
	);
	$(".nav li").click(function(){
		$(".nav li").removeClass("current");
			$(this).addClass("current")
			current = $(".nav").find(".current");
		})
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
		$(this).addClass("checked-r").siblings().removeClass("checked-r");
		var radio = $(this).find(".dn[type='radio']").attr("checked","checked");
		return false;
	})
	var radio = $(this).find(".dn[type='radio']").attr("checked","checked");
});
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
	$('.login-4 .d-select:first').nextAll(".d-select").find("label").html("");
}

function  IsRunYear(year){ 
   return(0==year%4&&(year%100!=0 || year%400==0));
}
$(".form .d-select .ter").live("change", function() {
var o;
var opt = $(this).find('option');
opt.each(function(i) {
if (opt[i].selected == true) {
o = opt[i].innerHTML;
}
})
$(this).find('label').html(o);
}).trigger('change');
//聊天框
/*$('.topic-box .toggle').click(function(){
	var title =$(this).attr("title");
	if(title=="展开"){
		$(this).attr("title","收缩");
		$(this).find("i").html("&#xe606;");
		$('.topic-box').animate({"right":0},'slow')
	}
	else{
		$(this).attr("title","展开");
		$(this).find("i").html("&#xe605;");
		$('.topic-box').animate({"right":-82},'slow')
	}
})*/
$('.topic-box .title .close').click(function(){
	$('.topic-box').animate({"bottom":-620},'300')
})
$('.mintopic-box .unfold').click(function(){
	$('.topic-box').animate({"bottom":0},'300')
})
//关闭确认框
$('.confirm-box .close').click(function(){
	$(this).parents(".confirm-box").hide()
})
//表单验证提示样式
	var css
	$.testRemind.css = {
    fontSize: "16px"	
	};
//消息弹出
$(".header .tab-list a").powerSwitch({
    classAdd: "tab-on",
    eventType: "hover"	
});
//操作标签相关
//提示函数
function Tip(even){
	alert(even)
}
$('.interests .tag .del').live('click',function(){
	var i =  $(this).parents(".interests")
	$(this).parent('.tag').remove();
	if(i.find(".cont").html() == " ")
		i.find(".placeholder").show()
})
$('#AddTagBtn').live('click',function(){
	var c = $(this).prev().find("input");
	if(c.val().replace(/(^\s*)|(\s*$)/g,"")!=""){
		$('.login-3 .cont>div').append('<a href="#" class="tag">'+ c.val()+'<i class="iconfont del">&#xe604;</i></a>')
		c.val("");
		setTimeout(function () { intScroll.refresh(); },0)
		c.attr("value","")
	}else{
		Tip("不能为空！")
		c.attr("value","")
	}
})
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
	    if (pwd.val() != pwdCheck.val()) {
	        pwdCheck.testRemind("前后密码不一致").get(0).select();    
	    } else {
			self.submit(); 
		}
	});
}