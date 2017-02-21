$(document).ready(function () {
    $("#inp_email").each(function() {
       if($(this).val() == "")
        alert("Empty Fields!!");
    });
    $('#inp_email').addClass('validated');
});