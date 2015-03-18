$(document).ready(function() {
	var color = $('#colorPicker').val();
	$('body').css('background',color);
	$('#control').css('background-color',color);
		
    $('#colorPicker').change(function() {
		var color = $('#colorPicker').val();
		$('body').css('background',color);
		$('#control').css('background-color',color);
	});
});

$('#formUserName').change(function() {
	var name = $('#formUserName').val();
	$('#name').html(name);
});

$('#genderPicker').change(function() {
		var gender = $('#genderPicker').val();
		if(gender === 'female')
		{
			$('#userPic').attr('src','images/pFemale.png');
		}
		else $('#userPic').attr('src','images/pMale.png');
});

	
$('#themePicker').change(function() {
		var theme = $('#themePicker').val();
		if(theme === 'theme_1')
		{
			$('#banner').attr('src','images/redLaptop.png');
		}
		else
		if(theme === 'theme_2')
		{
			$('#banner').attr('src','images/blueSpheres.png');
		}
		else
		if(theme === 'theme_3')
		{
			$('#banner').attr('src','images/coffeeCup.png');
		}
		else
		if(theme === 'theme_4')
		{
			$('#banner').attr('src','images/greenBook.png');
		}
		else
		if(theme === '')
		{
			$('#banner').attr('src','images/placeholderBanner.png');
		}
		
});

$(function ()
  {
	$('#myTab').tab()
  })