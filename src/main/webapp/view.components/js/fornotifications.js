$(document).ready(function() {

    $('.my-notification').show(function(){

        // Notification
        $('.notification.notification-from-command').removeClass('bounceOutRight notification-show animated bounceInRight');
        // show notification
        $('.notification.notification-from-command').addClass('notification-show animated bounceInRight');

        $('.notification.notification-from-command').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
            setTimeout(function(){
                $('.notification.notification-from-command').addClass('animated bounceOutRight');
            }, 800);
        });


    });

    $('.btn-logoute').click(function(){ //not used

        // Notification
        $('.notification.logged-oute').removeClass('bounceOutRight notification-show animated bounceInRight');
        // show notification
        $('.notification.logged-oute').addClass('notification-show animated bounceInRight');

        $('.notification.logged-oute').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
            setTimeout(function(){
                $('.notification.logged-oute').addClass('animated bounceOutRight');
            }, 800);
        });

    });
});

function message(msj, tipo){

    //Setemos la notificacion

    //Removemos clases
    $('.notification').removeClass('notification-error');
    $('.notification').removeClass('notification-succes');
    //Limpiados el msj
    $('.notification').empty();


    //Ingresamos msj
    $('.notification').append( msj );

    // Notification
    $('.notification').removeClass('bounceOutRight notification-show animated bounceInRight');

    // show notification
    $('.notification').addClass('notification-show notification-'+tipo+' animated bounceInRight');


    $('.notification').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
        setTimeout(function(){
            $('.notification').addClass('animated bounceOutRight');
        }, 800);

    });

}/**
 * Created by Jack on 04.01.2017.
 */
