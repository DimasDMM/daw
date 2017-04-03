$(function () {
	"use strict";
	
	var nPage = 2;

    $("#more-results").click(function () {

        $('#more-results').addClass('hidden');
        $('#spinner').removeClass('hidden');

        $.ajax({
            url: "/api/buscar?search="+ encodeURIComponent(search) +"&page="+nPage
        }).done(function (data) {
            
            nPage++;
            
            for(var i = 0; i < data.content.length; i++) {
                
                var article = data.content[i];
                
                $('#search_result').append(
                		"<div class=\"row margin-bottom-50\">" +
	                		"<div class=\"col-sm-4 sm-margin-bottom-20\">" +
	                			"<img class=\"img-responsive\" src=\"/articles/1/main.jpg\" alt=\"\">" +
	                		"</div>" +
	                		"<div class=\"col-sm-8\">" +
		                		"<div class=\"blog-grid\">" +
			                		"<h3><a href=\"/articulo/{{id}}\">"+article.title+"</a></h3>" +
			        				"<ul class=\"blog-grid-info\">" +
				        				"<li>"+article.author.name+' '+ article.author.lastName+"</li>" +
										"<li>"+article.dateInsertStrLong+"</li>" +
										"<li><a href=\"/articulo/{{id}}\"><i class=\"fa fa-comments\"></i> "+ article.nComments +"</a></li>" +
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
    })
})