function checkPass(){
	let pass1 = document.getElementById('pass1').value;
	let pass2 = document.getElementById('pass2').value;
	if(pass1 != pass2){
		alert('Пароли не совпадают');
	}
}

let butt = document.querySelector('button');
if(butt) butt.addEventListener("click", checkPass);

/*Visible-hiden Menu*/
let clEl = document.getElementById('clel');
clEl.addEventListener("click", visMenu);

function visMenu(){
	let elMenu = document.getElementById('elmenu');
	elMenu.classList.toggle("menu-vis");
}
/*End Visible-hiden Menu*/