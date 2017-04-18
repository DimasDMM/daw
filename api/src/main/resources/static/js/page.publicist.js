$(function() {
	"use strict"

	var handlerBtn = function() {
		$('.btn-delete').click(function() {
			$("#modalDeleteAd").modal('show');
		});
	};

	handlerBtn();

});