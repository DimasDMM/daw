$(function() {
	"use strict"

	var handlerBtn = function() {
		$('.btn-delete').click(function() {
			$("#modalDeleteArticle").modal('show');
		});
	};

	handlerBtn();

});