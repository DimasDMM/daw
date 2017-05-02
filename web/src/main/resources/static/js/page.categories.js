$(function () {
	"use strict";
	
	var page = 2;
	
    $("#more-results").click(function () {

        $('#more-results').addClass('hidden');
        $('#spinner').removeClass('hidden');

        $.ajax({
            url: "/api/articulos/" + categoryId + "?page=" + page +"&view=true"
        }).done(function (data) {

            page++;
            
            for(var i = 0; i < data.content.length; i++){
                
                var article = data.content[i];
                
                $('#category_news').append(
                		"<div class=\"row margin-bottom-50\">"+
                			"<div class=\"col-sm-4 sm-margin-bottom-20\">"+
                				"<img class=\"img-responsive\" src=\"/imagen/articulo/"+article.id+"\" alt=\"\">"+
                			"</div>" +
                			"<div class=\"col-sm-8\">" +
                				"<div class=\"blog-grid\">" +
		                			"<h3><a href=\"/articulo/{{id}}\">"+article.title+"</a></h3>" +
		        					"<ul class=\"blog-grid-info\">" +
		        						"<li>"+article.author.name+' '+ article.author.lastname+"</li>" +
		    							"<li>"+article.dateInsertStrLong+"</li>" +
		    							"<li><a href=\"/articulo/{{id}}\"><i class=\"fa fa-comments\"></i></a></li>" +
									"</ul>" +
									"<p>"+article.contentShort+"</p>" +
									"<a class=\"r-more\" href=\"/articulo/{{id}}\">Leer m&aacute;s</a>" +
								"</div>" +
							"</div>" +
						"</div>");
            }
            
            // Terminar
            $('#spinner').addClass('hidden');
            if(!data.isLast) {
                $('#more-results').removeClass('hidden');
            }
            
        }).error(function (jqXHR, textStatus, errorThrown) {
            $('#spinner').addClass('hidden');
            $('#more-results').removeClass('hidden');
        });
    });
    
});