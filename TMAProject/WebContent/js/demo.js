$(window).load(function(){
	if (($.browser.msie) && ($.browser.version < '9.0')) {
  $('.slider')._TMS({
   show:0,
   pauseOnHover:false,
   prevBu:'.prev',
   nextBu:'.next',
   playBu:'.play',
   duration:1100,
   preset:'fade',
   pagination:true,//'.pagination',true,'<ul></ul>'
   pagNums:false,
   slideshow:7000,
   numStatus:false,
   banners:'fromLeft',// fromLeft, fromRight, fromTop, fromBottom
   waitBannerAnimation:false,
   progressBar:false
  })  
 }
 else {
  $('.slider')._TMS({
   show:0,
   pauseOnHover:false,
   prevBu:'.prev',
   nextBu:'.next',
   playBu:'.play',
   duration:1100,
   preset:'fade',
   pagination:true,//'.pagination',true,'<ul></ul>'
   pagNums:false,
   slideshow:7000,
   numStatus:false,
   banners:'fade',// fromLeft, fromRight, fromTop, fromBottom
   waitBannerAnimation:false,
   progressBar:false
  })  
 }			
 })
 
 