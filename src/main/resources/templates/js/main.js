function checkPass(){
	let pass1 = document.getElementById('pass1').value;
	let pass2 = document.getElementById('pass2').value;
	if(pass1 != pass2){
		 alert('Пароли не совпадают');
		 }
}

let butt = document.querySelector('button');
butt.addEventListener("click", checkPass);