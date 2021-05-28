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


function removeTag(e){
	let inputtedTags = document.getElementById("inputed-tags");
	inputtedTags.removeChild(e);
}


function removeTagFromForm(tag){
	let tagsForm = document.getElementById("tagsForm");
	tag += "&;";

	tagsForm.value = tagsForm.value.replace(tag, "");

}

/*End Visible-hiden Menu*/
function addTag(e){
	let inputtedTags = document.getElementById("inputed-tags");
	let tagsInput = document.getElementById("tags-input");
	let tagsForm = document.getElementById("tagsForm");

	if(inputtedTags.getElementsByTagName("li").length > 15) {
		return;
	}

	if(e.key === "Enter"){
		var textTag = tagsInput.value;

		if(textTag.length == 0){
			return;
		}

		var newLi = document.createElement('li');
		inputtedTags.appendChild(newLi);
		newLi.classList.add("tags-element");

		textTag.slice(0, Math.max(22, textTag,length));
		newLi.innerHTML = textTag;
		newLi.onclick = (function (newLi){
			return function () {
				let inputtedTags = document.getElementById("inputed-tags");
				removeTagFromForm(newLi.innerHTML);
				inputtedTags.removeChild(newLi);
			}
		}(newLi));

		console.log(newLi);
		console.log(newLi.innerHTML);

		tagsInput.value = "";
		tagsForm.value += textTag + "&;";

		console.log(tagsForm.value);
	}
}