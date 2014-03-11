$(document).ready(function() { 
	$('ul.sf-menu').superfish({ 
		delay:       600,                            // one second delay on mouseout 
		animation:   {opacity:'show',height:'show'},  // fade-in and slide-down animation 
		speed:       'normal',                          // faster animation speed 
		autoArrows:  false,                           // disable generation of arrow mark-up 
		dropShadows: false                            // disable drop shadows 
	});
	/*************** tooltips  ****************/
	/* $(".list-services a.tooltips").easyTooltip(); */
}); 

$(window).load(function() { 
	$(".jCarouselLite").jCarouselLite({
		  btnNext: ".next1",
		  btnPrev: ".prev1",		  
		  speed: 700,		  
		  vertical: false,
		  circular: false,
		  visible: 4,
		  start: 0,
		  scroll: 1
	})
});