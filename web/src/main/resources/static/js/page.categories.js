"use strict"

$(document).ready(function () {

    $("#moreResults").click(function () {

        $('#noticias').html("<img src='/img/spinner.gif'/>");

        $.ajax({
            url: "/themadridnews/categoria/" + categoryId + "/" + nPage
        }).done(function (data) {
            
            npage++;
            
            for(var i = 0; i<10; i++){
                
                var article = page<Article>.findOne();
            }
            
            $('#noticias').append("<div class=\"row margin-bottom-50\"><div class=\"col-sm-4 sm-margin-bottom-20\"><img class=\"img-responsive\" src=\"/articles/1/main.jpg\" alt=\"\"></div><div class=\"col-sm-8\"><div class=\"blog-grid\"><h3><a href=\"/articulo/{{id}}\">"+article.title+"</a></h3><ul class=\"blog-grid-info\"><li>"+article.name+ article.lastName+"</li><li>"+article.dateInsert+"</li><li><a href=\"#\"><i class=\"fa fa-comments\"></i></a></li></ul><p>"+article.content+"</p><a class=\"r-more\" href=\"/articulo/{{id}}\">Leer m&aacute;s</a></div></div></div>");
            
        });
    })
})