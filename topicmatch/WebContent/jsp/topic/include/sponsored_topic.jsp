<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
	<section class="content">
		<div class="main">
			<div class="form spons-topic">
				<h2>发起话题</h2>
				<form>
					<div class="item">
						<label for="topic" class="dt-b">话题:</label>
						<div class="dd-b"><input type="text" id="topic" class="text-d" data-max="30" required></div>
						<span class="tip">30个字</span>
					</div>
					<div class="item detail">
						<label for="detail" class="dt-b">话题描述:</label>
						<div class="dd-b">
							<div class="cont">
								<textarea name="" id="detail" cols="100%" rows="6" class="tarea" required data-max="5000"></textarea>
							</div>
						</div>
						<span class="tip">3250/5000个字</span>
					</div>
					<div class="opear"><input type="submit" class="btn-f" value="发布"></div>
				</form>
				<div class="most-man">
					<h3 class="title">最匹配的"话题人"</h3>
					<ul class="talk-list">
						<li class="tp">
							<div class="hd">
								<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
								<div class="info">
									<p class="nc">齐天大圣</p>
									<p class="signature">即使世界末日也要和爱的人手牵手在天台看日光。</p>
									<div>
										<i class="iconfont mark">&#xe610;</i>
										<ul class="tag-lists">
											<li>90后</li>
											<li>不靠谱</li>
										</ul>
									</div>
									<ul class="fix other">
										<li>发起相关话题数<span class="num">23</span></li>
										<li>&emsp;&emsp;参与相关话题数<span class="num">23</span></li>
									</ul>
								</div>
								<a href="#" class="enter">邀请</a>
							</div>
						</li>
						<li class="tp">
							<div class="hd">
								<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
								<div class="info">
									<p class="nc">齐天大圣</p>
									<p class="signature">即使世界末日也要和爱的人手牵手在天台看日光。</p>
									<div>
										<i class="iconfont mark">&#xe610;</i>
										<ul class="tag-lists">
											<li>90后</li>
											<li>不靠谱</li>
										</ul>
									</div>
									<ul class="fix other">
										<li>发起相关话题数<span class="num">23</span></li>
										<li>&emsp;&emsp;参与相关话题数<span class="num">23</span></li>
									</ul>
								</div>
								<a href="#" class="enter">邀请</a>
							</div>
						</li>
						<li class="tp">
							<div class="hd">
								<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
								<div class="info">
									<p class="nc">齐天大圣</p>
									<p class="signature">即使世界末日也要和爱的人手牵手在天台看日光。</p>
									<div>
										<i class="iconfont mark">&#xe610;</i>
										<ul class="tag-lists">
											<li>90后</li>
											<li>不靠谱</li>
										</ul>
									</div>
									<ul class="fix other">
										<li>发起相关话题数<span class="num">23</span></li>
										<li>&emsp;&emsp;参与相关话题数<span class="num">23</span></li>
									</ul>
								</div>
								<a href="#" class="enter">邀请</a>
							</div>
						</li>
						<li class="tp">
							<div class="hd">
								<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
								<div class="info">
									<p class="nc">齐天大圣</p>
									<p class="signature">即使世界末日也要和爱的人手牵手在天台看日光。</p>
									<div>
										<i class="iconfont mark">&#xe610;</i>
										<ul class="tag-lists">
											<li>90后</li>
											<li>不靠谱</li>
										</ul>
									</div>
									<ul class="fix other">
										<li>发起相关话题数<span class="num">23</span></li>
										<li>&emsp;&emsp;参与相关话题数<span class="num">23</span></li>
									</ul>
								</div>
								<a href="#" class="enter">邀请</a>
							</div>
						</li>
						<li class="tp">
							<div class="hd">
								<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
								<div class="info">
									<p class="nc">齐天大圣</p>
									<p class="signature">即使世界末日也要和爱的人手牵手在天台看日光。</p>
									<div>
										<i class="iconfont mark">&#xe610;</i>
										<ul class="tag-lists">
											<li>90后</li>
											<li>不靠谱</li>
										</ul>
									</div>
									<ul class="fix other">
										<li>发起相关话题数<span class="num">23</span></li>
										<li>&emsp;&emsp;参与相关话题数<span class="num">23</span></li>
									</ul>
								</div>
								<a href="#" class="enter">邀请</a>
							</div>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</section>

<script>
	 $("form").html5Validate(function() {
		var self = this;
			self.submit(); 
	});
	 $(function(){
	 	$('.most-man  .talk-list .tp:even').addClass("even-b")
	 })
</script>
</html>