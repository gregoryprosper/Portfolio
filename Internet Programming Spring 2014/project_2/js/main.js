$(function() {
  $('#make').change(function(event)
  {
	  
	$('#model').load('makeAction.php',{make: $(event.target).val()},
	function() { $('[value=""]',event.target).remove();});
	
	
	$('#model').prop('disabled',false);
	
  });
	  
});

$(function() {
  $('#model').change(function(event)
  {
	  
	$('#color').load('colorAction.php',{color: $(event.target).val()},
	function() { $('[value=""]',event.target).remove();});
	
	
	$('#color').prop('disabled',false);
	
  });
	  
});

$(function() {
  $('#color').change(function(event)
  {
	  
	$('#options_Div').load('optionAction.php',{option: $(event.target).val()},
	function() { $('[value=""]',event.target).remove();});
	
	
	$('#options').prop('disabled',false);
	$('#submit').css('display','block');
	
  });
	  
});

function validate(form){
	fail = validateFirstName(form.firstName.value)
	fail += validateLastName(form.lastName.value)
	fail += validateEmail(form.email.value)
	if (fail == "") return true
	else {alert(fail); return false}
}

function validateFirstName(field){
	if(field == "") return "No First Name was entered.\n"
	return ""
}

function validateLastName(field){
	if(field == "") return "No Last Name was entered.\n"
	return ""
}

function validateEmail(field){
	if (field == "") return "No Email was entered.\n"
	else if (!((field.indexOf(".") > 0) && (field.indexOf("@") > 0)) || /[^a-zA-Z0-9.@_-]/.test(field))
	return "The Email address is invalid.\n"
	return ""
}




