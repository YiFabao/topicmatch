//导航效果
$(function(){
	var var_a
	if($('.nav li').find(".current").length<0) var_a = true
	$(".nav ul").lavaLamp({
		fx: "backout",
		speed:300,
		click: function(event, menuItem) {
			$(this).addClass("current").siblings().removeClass("current")
			$(this).nextAll(".back").css("display","block")
			return false;
		}
	});
	if(var_a == true)
		$(".nav li").removeClass("current")
	else $('.nav li.back').show()
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
});