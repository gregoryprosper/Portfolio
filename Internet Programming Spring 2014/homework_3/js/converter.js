var reportIF = function (inches, feet) {
	var first;
	var second;
	if(inches == 1)
	{
		first = "inch";
	}
	else first = "inches";
	if(feet == 1)
	{
		second = "foot";
	}
	else second = "feet";
    document.getElementById("result").innerHTML =
        inches + " " + first + " = " + feet + " " + second;
};

var reportIY = function (inches, yards) {
	var first;
	var second;
	if(inches == 1)
	{
		first = "inch";
	}
	else first = "inches";
	if(yards == 1)
	{
		second = "yard";
	}
	else second = "yards";
    document.getElementById("result").innerHTML =
        inches + " " + first + " = " + yards + " " + second;
};

var reportFY = function (feet, yards) {
	var first;
	var second;
	if(feet == 1)
	{
		first = "foot";
	}
	else first = "feet";
	if(yards == 1)
	{
		second = "yard";
	}
	else second = "yards";
    document.getElementById("result").innerHTML =
        feet + " " + first + " = " + yards + " " + second;
};

var reportFI = function (feet, inches) {
	var first;
	var second;
	if(feet == 1)
	{
		first = "foot";
	}
	else first = "feet";
	if(inches == 1)
	{
		second = "inch";
	}
	else second = "inches";
    document.getElementById("result").innerHTML =
        feet + " " + first + " = " + inches + " " + second;
};

var reportYI = function (yards, inches) {
	var first;
	var second;
	if(yards == 1)
	{
		first = "yard";
	}
	else first = "yards";
	if(inches == 1)
	{
		second = "inch";
	}
	else second = "inches";
    document.getElementById("result").innerHTML =
        yards + " " + first + " = " + inches + " " + second;
};

var reportYF = function (yards, feet) {
	var first;
	var second;
	if(yards == 1)
	{
		first = "yard";
	}
	else first = "yards";
	if(feet == 1)
	{
		second = "foot";
	}
	else second = "feet";
    document.getElementById("result").innerHTML =
        yards + " " + first + " = " + feet + " " + second;
};


document.getElementById("i_to_f").onclick = function () {
    var i = document.getElementById("length").value;
    reportIF(i,i / 12);
};

document.getElementById("i_to_y").onclick = function () {
    var i = document.getElementById("length").value;
    reportIY(i,i/36);
};

document.getElementById("f_to_i").onclick = function () {
    var i = document.getElementById("length").value;
    reportFI(i,i*12);
};

document.getElementById("f_to_y").onclick = function () {
    var i = document.getElementById("length").value;
    reportFY(i,i/3);
};

document.getElementById("y_to_i").onclick = function () {
    var i = document.getElementById("length").value;
    reportYI(i,i*36);
};

document.getElementById("y_to_f").onclick = function () {
    var i = document.getElementById("length").value;
    reportYF(i,i*3);
};