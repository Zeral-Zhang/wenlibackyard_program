<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <s:if test="#session.snsUserInfo==null">
	<c:redirect url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7d5b5ab70d0ae347&redirect_uri=http%3A%2F%2Fwenlibackyard.ngrok.natapp.cn%2Fwenlibackyard%2Fvalidate_User&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"></c:redirect>
</s:if>
 --%>
 <s:if test="#session.userInfo==null">
 	<c:redirect url="${request.servletContext.contextPath}/login.jsp"></c:redirect>
 </s:if>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>main</title>
		<meta name="description" content="" />
		<meta name="author" content="ZeralZhang" />
		<!-- Include meta tag to ensure proper rendering and touch zooming -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico" />
		<link rel="apple-touch-icon" href="/apple-touch-icon.png" />
		<!-- //for-mobile-apps -->
		<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="css/jquery.mobile-1.4.5.min.css" rel="stylesheet" type="text/css" media="all" />
		<!-- pignose css -->
		<link href="css/pignose.layerslider.css" rel="stylesheet" type="text/css" media="all" />
		<!-- //pignose css -->
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- js -->
		<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
		<!-- //js -->
		<script type="text/javascript" src="js/jquery.mobile-1.4.5.min.js"></script>
		<!-- for bootstrap working -->
		<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
		<!-- //for bootstrap working -->
		<script src="js/jquery.easing.min.js"></script>
	</head>
	<body>
		<div data-role="page" data-theme="a" id="productlist" data-title="最新列表">

			<div data-role="main" class="ui-content">
				<!-- //banner-top -->
				<!-- banner -->
				<div class="banner-grid">
					<div id="visual">
						<div class="slide-visual">
							<!-- Slide Image Area (1000 x 424) -->
							<ul class="slide-group">
								<li><img class="img-responsive" src="images/ba1.jpg" alt="Dummy Image" />
								</li>
								<li><img class="img-responsive" src="images/ba2.jpg" alt="Dummy Image" />
								</li>
								<li><img class="img-responsive" src="images/ba3.jpg" alt="Dummy Image" />
								</li>
							</ul>

							<!-- Slide Description Image Area (316 x 328) -->
							<div class="script-wrap">
								<ul class="script-group">
									<li>
										<div class="inner-script"><img class="img-responsive" src="images/baa1.jpg" alt="Dummy Image" />
										</div>
									</li>
									<li>
										<div class="inner-script"><img class="img-responsive" src="images/baa2.jpg" alt="Dummy Image" />
										</div>
									</li>
									<li>
										<div class="inner-script"><img class="img-responsive" src="images/baa3.jpg" alt="Dummy Image" />
										</div>
									</li>
								</ul>
								<div class="slide-controller">
									<a data-ajax="false" href="#" class="btn-prev"><img src="images/btn_prev.png" alt="Prev Slide" /></a>
									<a data-ajax="false" href="#" class="btn-play"><img src="images/btn_play.png" alt="Start Slide" /></a>
									<a data-ajax="false" href="#" class="btn-pause"><img src="images/btn_pause.png" alt="Pause Slide" /></a>
									<a data-ajax="false" href="#" class="btn-next"><img src="images/btn_next.png" alt="Next Slide" /></a>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					<script type="text/javascript" src="./js/pignose.layerslider.js"></script>
					<script type="text/javascript">
						//<![CDATA[
						$(window).load(function() {
							$('#visual').pignoseLayerSlider({
								play : '.btn-play',
								pause : '.btn-pause',
								next : '.btn-next',
								prev : '.btn-prev'
							});
						});
						//]]>
					</script>

				</div>
				<!-- //banner -->
				<!-- product-nav -->

				<div class="product-easy">
					<div class="container">

						<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
						<script type="text/javascript">
							$(document).ready(function() {
								$('#horizontalTab').easyResponsiveTabs({
									type : 'default', //Types: default, vertical, accordion
									width : 'auto', //auto or any width like 600px
									fit : true // 100% fit in a container
								});
							});

						</script>
						<div class="sap_tabs">
							<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
								<ul class="resp-tabs-list">
									<li class="resp-tab-item" aria-controls="tab_item-0" role="tab">
										<span>Latest Designs</span>
									</li>
									<li class="resp-tab-item" aria-controls="tab_item-1" role="tab">
										<span>Special Offers</span>
									</li>
									<li class="resp-tab-item" aria-controls="tab_item-2" role="tab">
										<span>Collections</span>
									</li>
								</ul>
								<div class="resp-tabs-container">
									<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a1.png" alt="" class="pro-image-front">
													<img src="images/a1.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a href="single.html" data-ajax="false" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Air Tshirt Black</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a8.png" alt="" class="pro-image-front">
													<img src="images/a8.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">1+1 Offer</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Next Blue Blazer</a></h4>
													<div class="info-product-price">
														<span class="item_price">$99.99</span>
														<del>$109.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a3.png" alt="" class="pro-image-front">
													<img src="images/a3.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Air Tshirt Black </a></h4>
													<div class="info-product-price">
														<span class="item_price">$119.99</span>
														<del>$120.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a4.png" alt="" class="pro-image-front">
													<img src="images/a4.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Maroon Puma Tshirt</a></h4>
													<div class="info-product-price">
														<span class="item_price">$79.99</span>
														<del>$120.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a5.png" alt="" class="pro-image-front">
													<img src="images/a5.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">Combo Pack</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Multicoloured TShirts</a></h4>
													<div class="info-product-price">
														<span class="item_price">$129.99</span>
														<del>$150.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a6.png" alt="" class="pro-image-front">
													<img src="images/a6.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Air Tshirt Black </a></h4>
													<div class="info-product-price">
														<span class="item_price">$129.99</span>
														<del>$150.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a7.png" alt="" class="pro-image-front">
													<img src="images/a7.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Dresses</a></h4>
													<div class="info-product-price">
														<span class="item_price">$129.99</span>
														<del>$150.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/a2.png" alt="" class="pro-image-front">
													<img src="images/a2.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Wedding Blazers</a></h4>
													<div class="info-product-price">
														<span class="item_price">$129.99</span>
														<del>$150.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/g1.png" alt="" class="pro-image-front">
													<img src="images/g1.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Dresses</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/g2.png" alt="" class="pro-image-front">
													<img src="images/g2.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html"> Shirts</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/g3.png" alt="" class="pro-image-front">
													<img src="images/g3.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Shirts</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/mw2.png" alt="" class="pro-image-front">
													<img src="images/mw2.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">T shirts</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/w1.png" alt="" class="pro-image-front">
													<img src="images/w1.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Wedges</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/w2.png" alt="" class="pro-image-front">
													<img src="images/w2.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Sandals</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/mw1.png" alt="" class="pro-image-front">
													<img src="images/mw1.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Casual Shoes</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/mw3.png" alt="" class="pro-image-front">
													<img src="images/mw3.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Sport Shoes</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/ep2.png" alt="" class="pro-image-front">
													<img src="images/ep2.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Watches</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/ep3.png" alt="" class="pro-image-front">
													<img src="images/ep3.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Watches</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>

										<div class="clearfix"></div>
									</div>
									<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/g1.png" alt="" class="pro-image-front">
													<img src="images/g1.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Dresses</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/g2.png" alt="" class="pro-image-front">
													<img src="images/g2.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html"> Shirts</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/g3.png" alt="" class="pro-image-front">
													<img src="images/g3.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Shirts</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/mw2.png" alt="" class="pro-image-front">
													<img src="images/mw2.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">T shirts</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/w4.png" alt="" class="pro-image-front">
													<img src="images/w4.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Air Tshirt Black Domyos</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="col-md-3 product-men yes-marg">
											<div class="men-pro-item simpleCart_shelfItem">
												<div class="men-thumb-item">
													<img src="images/w3.png" alt="" class="pro-image-front">
													<img src="images/w3.png" alt="" class="pro-image-back">
													<div class="men-cart-pro">
														<div class="inner-men-cart-pro">
															<a data-ajax="false" href="single.html" class="link-product-add-cart">Quick View</a>
														</div>
													</div>
													<span class="product-new-top">New</span>

												</div>
												<div class="item-info-product ">
													<h4><a data-ajax="false" href="single.html">Hand Bags</a></h4>
													<div class="info-product-price">
														<span class="item_price">$45.99</span>
														<del>$69.71</del>
													</div>
													<a data-ajax="false" href="#" class="item_add single-item hvr-outline-out button2">Add to cart</a>
												</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- //product-nav -->
			</div><!-- /content -->
			<div data-role="footer" data-position="fixed" data-id="myfooter" data-theme="a">
				<div data-role="navbar">
					<ul>
						<li>
							<a  href="list.jsp"  data-transition="flip" data-icon="bars">最新列表</a>
						</li>
						<li>
							<a  href="find.html"  data-transition="flip" data-icon="search">发现</a>
						</li>
						<li>
							<a  href="shopcar.html"  data-transition="flip" data-icon="shop">购物车</a>
						</li>
						<li>
							<a href="userinfo.jsp"  data-transition="flip" data-icon="user">个人</a>
						</li>
					</ul>
				</div><!-- /navbar -->
			</div><!-- /footer -->
		</div><!-- /page -->

	</body>
</html>
