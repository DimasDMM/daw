var CommentForm = function () {

    return {
        
        //Comment Form
        initCommentForm: function () {
    	    // Validation
	        $("#sky-form4").validate({
	            // Rules for form validation
	            rules:
	            {
	                name:
	                {
	                    required: true
	                },
	                email:
	                {
	                    required: true,
	                    email: true
	                },
	                url:
	                {
	                    url: true
	                },
	                comment:
	                {
	                    required: true
	                },
	                captcha:
	                {
	                    required: true,
	                    remote: 'assets/plugins/sky-forms/version-2.0.1/captcha/process.php'
	                }
	            },
	                                
	            // Messages for form validation
	            messages:
	            {
	                name:
	                {
	                    required: 'Introduce tu nombre'
	                },
	                email:
	                {
	                    required: 'Introduce tu correo electronico',
	                    email: 'Introduce un correo electronico valido'
	                },
	                url:
	                {
	                    email: 'Introduce una URL valida'
	                },
	                comment:
	                {
	                    required: 'Por favor, escribe tu comentario'
	                },
	                captcha:
	                {
	                    required: 'Por favor, introduce los caracteres',
	                    remote: 'Es necesario que los caracteres coincidan con el Captcha'
	                }
	            },
	                                
	            // Ajax form submition                  
	            submitHandler: function(form)
	            {
	                $(form).ajaxSubmit(
	                {
	                    beforeSend: function()
	                    {
	                        $('#sky-form4 button[type="submit"]').attr('disabled', true);
	                    },
	                    success: function()
	                    {
	                        $("#sky-form4").addClass('submited');
	                    }
	                });
	            },
	            
	            // Do not change code below
	            errorPlacement: function(error, element)
	            {
	                error.insertAfter(element.parent());
	            }
	        });
        }

    };
    
}();