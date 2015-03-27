<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<section class="content" >
	<div class="main">
		<div class="form login-4 acc-set">
			<form id="ComRegForm">
				<div class="item">
					<label class="dt-c" for="UserNameR">用户名&nbsp;:</label>
					<div class="dd-c lh36">&emsp;小明明</div>
				</div>
				<div class="item">
					<label for="PassWordR" class="dt-c">密&emsp;&emsp;码</label>
					<div class="dd-c">
						<input type="password" id="PassWordR" class="text-c wtb pwd" data-min="6" value="1234567890" required disabled>
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<div class="item">
					<label for="PassWordRC" class="dt-c">确认密码</label>
					<div class="dd-c">
						<input type="password" id="PassWordRC" class="text-c wtb pwdcheck" data-min="6" required disabled>
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<div class="item">
					<span class="dt-c">标签</span>
					<div class="dd-c">
						<div class="interests">
							<div class="cont mb10">
								<a href="javascript:;" class="tag">文学<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">地理<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">整治<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">语文<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">数学<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">文学<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">地理<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">整治<i class="iconfont del">&#xe601;</i></a>
								<a href="javascript:;" class="tag">语文<i class="iconfont del">&#xe601;</i></a>
							</div>
							<lable class="placeholder" style="display:none">填写或选择感兴趣的标签,以，隔开</lable>
							<div class="mb10">
								输入标签：<input type="text" class="text-c">
								<button class="btn-b w80" type="button" id="AddTagBtn">确定</button>
							</div>
							<!-- <p class="tag-list">
								<span class="mr15">您可能感兴趣的标签:</span>
								<a href="#" class="tag"><span class="add">+</span>Duang</a>
								<a href="#" class="tag"><span class="add">+</span>标签</a>
								<a href="#" class="tag"><span class="add">+</span>Duang</a>
								<a href="#" class="tag"><span class="add">+</span>标签</a>
								<a href="#" class="tag"><span class="add">+</span>Duang</a>
								<a href="#" class="tag"><span class="add">+</span>标签</a>
								<a href="#" class="tag"><span class="add">+</span>Duang</a>
								<a href="#" class="tag"><span class="add">+</span>标签</a>
							</p>
							<div class="tr"><a href="#" class="a1 mr20">换一换</a></div> -->
						</div>
					</div>
				</div>
				<div class="item">
					<label class="dt-c dtt">头像</label>
					<div class="dd-c">
						<span class="pic-area">
							<div id="imgdiv"><img id="imgShow" style="width:70px;height:70px;" src="images/delete/user-pic2.jpg"/></div>
						</span>
						<a href="javascript:up_img.click();"  class="f14 a1">
							本地上传<input type="file" id="up_img" name="myfile" style="display:none" required/>
						</a>
						<br><small style="position:relative;left:100px;top:-20px">(头像文件不大于1M)</small>
						
					</div>
				</div>
				<div class="item">
					<label for="NC" class="dt-c">昵称</label>
					<div class="dd-c">
						<input type="text" id="NC" class="text-c wtb" data-min="1">
					</div>
				</div>
				<div class="item pt14">
					<label class="dt-c">出生日期</label>
					<div class="dd-c pt5">
						<div class="d-select">
							<div class="ter">
								<label></label>
								<select name=year onchange="yy(this.value)">
								<option value=""></option>
								</select>
							</div>
						</div>
						年
						<div class="d-select">
							<div class="ter">
								<label></label>
								<select name=month onchange="mm(this.value)">
									<option value=""></option>
								</select>
							</div>
						</div>
						月
						<div class="d-select">
							<div class="ter">
								<label></label>
								<select name=day>
									<option value=""></option>
								</select>
							</div>
						</div>
						日
					</div>
				</div>
				<div class="item">
					<label for="city" class="dt-c">常驻城市</label>
					<div class="dd-c">
						<input type="text" id="city" class="text-c wtb" data-min="1" value="上海市" disabled>
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<div class="item">
					<label for="email" class="dt-c">电子邮箱</label>
					<div class="dd-c">
						<input type="email" id="email" class="text-c wtb" data-min="1">
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<!-- <div class="item mb60">
					<label for="moblie" class="dt-c">绑定手机</label>
					<div class="dd-c">
						<input type="tel" id="moblie" class="text-c wtb" data-min="1">
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div> -->
				<!-- <div class="login-1">
					<h3>第三方帐号绑定</h3>
					<ul class="quick-login">
						<li><a href="#" title="微信"><img src="images/wx.png">微信</a></li>
						<li><a href="#" title="腾讯QQ"><img src="images/qq.png">QQ</a></li>
						<li><a href="#" title="新浪微博"><img src="images/sina.png">新浪</a></li>
						<li class="justify-fix">&nbsp;</li>
					</ul>
				</div> -->
				<div class="dd-c">
					<button class="btn-d wta">确 定</button>
				</div>
			</form>
		</div>
	</div>
</section>
<!-- <script src="js/jquery-1.4.4.min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/jquery.lavalamp.min.js"></script>
<script src="js/jquery-html5Validate-min.js"></script>
<script src="js/jquery-powerSwitch-min.js"></script>
<script src="js/common.js"></script> -->
<script>
	 checkPwd("#Reg form")
	 $(function(){
		 YearMonthDay();// 加载日期选择组件
		//上传图片预览
		 new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
	 })
</script>
</html>
