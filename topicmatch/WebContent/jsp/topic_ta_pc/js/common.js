//导航效果
$(function(){
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
	if(var_a) $(".nav li").removeClass("current") 
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
	//下拉框的模拟
	$(".CRselectBox").hover(function(){
		$(this).addClass("CRselectBoxHover");
	},function(){
		$(this).removeClass("CRselectBoxHover");
	});
	$(".CRselectValue").click(function(){
		$(this).blur();
		$(this).nextAll(".CRselectBoxOptions").show();
		return false;
	});
	$(".CRselectBoxItem a").click(function(){
		$(this).blur();
		var value = $(this).attr("rel");
		var txt = $(this).text();
		$(this).parent().parent().parent().prevAll("cr_value").val(value);
		$(this).parent().parent().prevAll(".cr_txt").val(txt);
		$(this).parent().parent().prevAll(".CRselectValue").text(txt)
		$(this).parent().parent().find("li a").removeClass("selected")
		$(this).addClass("selected");
		$(this).parent().parent().hide();
		var value = $(this).attr("rel");
        var text = $(this).text();
        var type = $(this).attr("type");
        if (type == "province")
            RefreshProvinceForm(value, text, type);
        else if (type == "city") {
            var provinceName = $("#ProvinceName").val();
            var provinceID = $("#ProvinceValue").val();
            RefreshCityForm(value, text, provinceID, provinceName);
        }
		return false;
	});
	/*点击任何地方关闭层*/
	$(document).click(function(event){
		if( $(event.target).attr("class") != "CRselectBox" ){
			$(".CRselectBoxOptions").hide();
		}
	});
});
