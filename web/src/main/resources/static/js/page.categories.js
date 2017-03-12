"use strict"

$(document).ready(function () {

    $("#moreResults").click(function () {

        $('#moreResults').addClass('hidden');
        $('#spinner').removeClass('hidden');

        $.ajax({
            url: "/categoria/" + categoryId + "/" + nPage
        }).done(function (data) {
            
            console.log( JSON.stringify(data) );
            
            nPage++;
            
            for(var i = 0; i< data.content.length; i++){
                
                var article = data.content[i];
                
                $('#noticias').append("<div class=\"row margin-bottom-50\"><div class=\"col-sm-4 sm-margin-bottom-20\"><img class=\"img-responsive\" src=\"/articles/1/main.jpg\" alt=\"\"></div><div class=\"col-sm-8\"><div class=\"blog-grid\"><h3><a href=\"/articulo/{{id}}\">"+article.title+"</a></h3><ul class=\"blog-grid-info\"><li>"+article.author.name+' '+ article.author.lastName+"</li><li>"+article.dateInsertStrLong+"</li><li><a href=\"#\"><i class=\"fa fa-comments\"></i></a></li></ul><p>"+article.contentShort+"</p><a class=\"r-more\" href=\"/articulo/{{id}}\">Leer m&aacute;s</a></div></div></div>");
            }
            
            // Terminar
            $('#spinner').addClass('hidden');
            if (article.isLast == false){
                $('#moreResults').removeClass('hidden');
            }
            
        });
    })
})