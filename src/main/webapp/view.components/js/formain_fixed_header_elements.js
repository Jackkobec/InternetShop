jQuery(document).ready(function (){
    var navOffset = jQuery(".header").offset().top;

    $(".header").wrap('<div class="header-placeholder"></div>');

    $(".header-placeholder").height($(".header").outerHeight());

    $(".header").wrapInner('<div class="header-inner"></div>');
    $(".header-inner").wrapInner('<div class="header-inner-most"></div>');
    jQuery(window).scroll(function (){
        var scrollPos = jQuery(window).scrollTop();

        if(scrollPos >= navOffset){
            jQuery('.header').addClass('fixed');
        }else{
            jQuery('.header').removeClass('fixed');
        }
    });

});/**
 * Created by Jack on 06.01.2017.
 */
