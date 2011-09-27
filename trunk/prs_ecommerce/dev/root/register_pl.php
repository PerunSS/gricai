<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#dodaj').click(function() {
                var num     = $('.clonedInput').length; // how many "duplicatable" input fields we currently have
                var newNum  = new Number(num + 1);      // the numeric ID of the new input field being added
 				
                // create the new element via clone(), and manipulate it's ID using newNum value
                var newElem = $('#input' + num).clone().attr('id', 'input' + newNum);
 
                // manipulate the name/id values of the input inside the new element
                newElem.children(':first').attr('id', 'name' + newNum).attr('name', 'name' + newNum);
 
                // insert the new element after the last "duplicatable" input field
                $('#input' + num).after(newElem);

				
                // business rule: you can only add 5 names
                /*if (newNum == 5)
                    $('#btnAdd').attr('disabled','disabled');*/
            });
 
        });
    </script>
</head>
<body>
	<form
		action="TODO register pravnog lica action"
		method="post"
		target="TODO target"
	>
		<fieldset>
			<legend>Register</legend>
			Ime firme: <input
				type="text"
				name="ime"
				id="ime"
			><br />Adresa: <input
				type="text"
				name="adresa"
				id="adresa"
			><br />Grad: <input
				type="text"
				name="grad"
				id="grad"
			><br />PIB: <input
				type="text"
				name="pib"
				id="pib"
			><br />Maticni broj: <input
				type="text"
				name="maticni_broj"
				id="maticni_broj"
			><br />Kontakt telefon: <input
				type="text"
				name="telefon"
				id="telefon"
			><br />Email: <input
				type="text"
				name="email"
				id="email"
			><div id="input1" style="margin-bottom:4px;" class="clonedInput">
        		Adresa za dostavu: <input type="text" name="adresa1" id="adresa1" />
    		</div>
    		<br /><div> <input
				type="button"
				name="dodaj"
				id="dodaj"
				value="Jos adresa"
			></div>
			<br /> <input
				type="submit"
				name="sub"
				id="sub"
				value="Register"
			>
		</fieldset>
	</form>
	
</body>
</html>
