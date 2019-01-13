var symbols = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!#$%^&*()_+=-/.,';][}{\":?><|\\";
var shift = 10;

function RegisterUser()
{
	if(document.registerForm.password.value == document.registerForm.confirm.value && document.registerForm.password.value !== "" && document.registerForm.password.value.length > 1)
	{
		document.registerForm.password.value = CaezarShift(document.registerForm.password.value);
		document.registerForm.confirm.value = document.registerForm.password.value;
		
		document.registerForm.submit();
	}
	
	else
	{
		document.registerForm.password.value = "";
		document.registerForm.confirm.value = "";
		alert("Check password! The password and confirmation are different or they contains less than 2 symbols!");
	}
}

function LoginUser()
{
	if(document.loginForm.password.value !== "" && document.loginForm.password.value.length > 1)
	{
		document.loginForm.password.value = CaezarShift(document.loginForm.password.value);
		
		document.loginForm.submit();
	}
	
	else
	{
		document.loginForm.password.value = "";
		alert("Enter the password! It's empty or contains less than 2 symbols!");
	}
}

function CaezarShift(stringForEncoding)
{
	var result = "";
	
	for(var i = 0; i < stringForEncoding.length; i++)
	{
		var indexOfChar = i;
		
		var currentChar = stringForEncoding.charAt(indexOfChar);
		
		var indexInSymbols = symbols.indexOf(currentChar);
		
		var shiftIndex = indexInSymbols + shift;
		
		if(shiftIndex >= symbols.length)
		{
			shiftIndex = shiftIndex - symbols.length;
		}
		
		var newChar = symbols.charAt(shiftIndex);
		
		result = result.concat(newChar);
	}
	
	return result;
}
