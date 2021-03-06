$(document).ready(function(){
    
    $.validator.addMethod("checkUserName", 
        function(value, element) {
            var result = false;
            $.ajax({
                type:"POST",
                async: false,
                url: 'includes/checkUserName.php',
                data: "username="+ value,
                success: function(msg) {
                    result = (msg === "TRUE") ? false : true;
                }
            });
            return result;
        }, 
        "Username Already Exists."
    );
    
    $.validator.addMethod("loginRegex", function(value, element) {
        return this.optional(element) || /^[a-z0-9\-\s]+$/i.test(value);
    }, "Username must contain only letters, numbers, or dashes.");
    
    $.validator.addMethod("nowhitespace", function(value, element) {
     return this.optional(element) || /^\S+$/i.test(value);
    }, "  No white space please");

    //Validate sign up Form
    $('#signUpForm').validate({
        rules:{
            firstname:{
                required:true,
            },
            lastname:{
                required:true,
            },
            licence:{
                required:true,
            },
            email:{
                required:true,
                email:true
            },
            password1:{
                required:true,
                rangelength:[8,16],
                nowhitespace:true
            },
            confirm_password:{
                equalTo:'#password1'
            },
            username:{
                required:true,
                rangelength:[5,20],
                loginRegex:true,
                nowhitespace:true,
                checkUserName:true
            },
            phone:{
                required:true,
                phoneUS:true
            },
            uploadFile:{
                required:true
            }
        }
    });
    
    //validate account change form
    $('#changeAccountForm').validate({
        rules:{
            email:{
                email:true
            },
            phone:{
                phoneUS:true
            }
        }
    });
    
    //validate add Listing form
    $('#addListingForm').validate({
        rules:{
            street:{
                required:true
            },
            city:{
                required:true
            },
            state:{
                required:true,
                rangelength:[2,2]
            },
            zipCode:{
                required:true
            },
            beds:{
                required:true
            },
            baths:{
                required:true
            },
            size:{
                required:true
            },
            price:{
                required:true
            },
            description:{
                required:true
            },
            uploadFile:{
                required:true
            }
        },
        messages:{
            state:{
                rangelength:"Enter 2 characters for the state"
            }
        }
        
    });
    
    //Function that controls Log in
    $("#submitLogIn").click(function(){

        username=$("#username").val();
        password=$("#password").val();

        $.ajax({
            type: "POST",
            url: "includes/checklogin.php",
            data: "username="+username+"&password="+password,
            success: function(html){
                if(html==='true'){
                    location.reload();
                }
                else
                {
                    $("#message").html("</br><b>Wrong username or password</b>");
                }
            },
            beforeSend:function(){
                $("#message").html("</br><b>Loading...<b>");
            }
        });
        return false;
    });
    
    //Makes search result divs selectable
    $(".listingRows").click(function(){
        window.location=$(this).find("a").attr("href"); 
        return false;
    });
    
    $( document ).tooltip();
    
    $('.listImageToBeDeleted').click(function(){
        var $this = $(this);
        var id = $this.attr('id');
        var checkbox = $('#checkbox'+id);
        if ($this.hasClass('selectedImage')) {
            $this.removeClass('selectedImage');
            checkbox.prop("checked", !checkbox.prop("checked"));;
        } else {
            $this.removeClass('selectedImage');
            $this.addClass('selectedImage');
            checkbox.prop("checked", !checkbox.prop("checked"));;
        }
    });

});
