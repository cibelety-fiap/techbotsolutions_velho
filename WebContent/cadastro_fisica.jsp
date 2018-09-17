<!DOCTYPE html>
<html>

  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <title>SP Eventos</title>
  </head>

  <body>
    <div class="container">
      <header>
        <h1 style="float: left;">SP Eventos</h1>
        <a href="../login.html"><img style="float: right; width: 60px;" src="../img/user.png" /></a>
        <br />
        <br />
        <br />
      </header>
      <div class="topnav" id="myTopnav">
        <a href="../index.html">Home</a>
        <div class="dropdown">
          <button class="dropbtn">Cinema
						  <i class="fa fa-caret-down"></i>
						</button>
          <div class="dropdown-content">
            <a href="../filmes_em_cartaz.html">Em cartaz</a>
            <a href="../proximos_lançamentos.html">Próximos lançamentos</a>
          </div>
        </div>
        <div class="dropdown">
          <button class="dropbtn">Shows
						  <i class="fa fa-caret-down"></i>
						</button>
          <div class="dropdown-content">
            <a href="../proximos_shows.html">Próximos shows</a>
            <a href="../mais_esperados_ano.html">Mais esperados do ano</a>
          </div>
        </div>
        <div class="dropdown">
          <button class="dropbtn">Teatro
					      <i class="fa fa-caret-down"></i>
					    </button>
          <div class="dropdown-content">
            <a href="../teatro_em_cartaz.html">Em cartaz</a>
          </div>
        </div>
        <a href="../contato.html">Contato</a>
        <div class="search-container">
          <form>
            <img src="../img/search.png" alt="" id="lupa">
            <input type="search" placeholder="Pesquisa" name="search">
          </form>
        </div>
        <a href="javascript:void(0); " style="font-size:15px; " class="icon " onclick="myFunction() ">&#9776;</a>
      </div>

      <nav>
        <div class="menu_lateral">
          <button onclick="goBack()" class="menu_lateral">Voltar</button>
        </div>
      </nav>

      <article class="texto_menu_lateral">
        <br>
        <h1>Cadastro Pessoa Física</h1>
        <br>
        <form id="form1" action="CadastroPF" method="POST"> <!--alterar-->
          <label>Login (E-mail):</label>
          <br>
          <input type="text" name="email" maxlength="40">
          <br><br>
          <label>Digite o e-mail novamente:</label>
          <br>
          <input type="text" name="emailNovamente" value="" maxlength="40">
          <br><br>
          <label>Nome Completo:</label>
          <br>
          <input type="text" name="nome" maxlength="50">
          <br><br>
          <label>Telefone:</label>
          <br>
          <input id="TELEFONE" type="text" name="telefone" maxlength="14">
          <br><br>
          <label>Endereço:</label>
          <br>
          <input type="text" name="endereco" maxlength="80">
          <br><br>
          <label>CPF:</label>
          <br>
          <input id="CPF" type="text" name="cpf" maxlength="14">
          <br><br>
          <label>RG:</label>
          <br>
          <input id="RG" type="text" name="rg" maxlength="14">
          <br><br>
          <label>Data de Nascimento:</label>
          <br>
          <input id="NASCIMENTO" type="text" name="dataNascimento" maxlength="8">
          <br><br>
          <label>Senha:</label>
          <br>
          <input type="password" name="senha" maxlength="14"> <!-- alterar -->
          <br><br>
          <label>Digite a senha novamente:</label>
          <br>
          <input type="password" name="senha2" maxlength="14"> <!-- alterar -->
          <br><br>
          <h4>Gênero</h4>
          <input type="radio" name="genero" value="M"> Masculino<br><!-- alterar -->
          <input type="radio" name="genero" value="F"> Feminino<br><!-- alterar -->
          <input type="radio" name="genero" value="O"> Outro<br><!-- alterar -->
          <br>
          <h4>Declaro que estou ciente e concordo com os termos de uso
              <input type="checkbox" name="aceito" value="sim"></h4>
          <br>
          <h5>Desejo receber proximos eventos da SP-Eventos ou Parceiros
            <input type="checkbox" name="receberEmails" value="sim" /></h5><!-- alterar -->
          <br>
          <button>Enviar</button><!--  alterar -->
          <!-- <input id="enviar" type="submit" name="enviar" value="Enviar" class="tamanho_botao"> -->
          <input type="reset" name="reset" value="Reset" class="tamanho_botao">
        </form>
      </article>

      <footer>Copyright &copy; 2018 SP Eventos </footer>
    </div>
    <script>
var botao = document.querySelector("#enviar");
botao.addEventListener("click", function(event) {
  event.preventDefault();

  var form = document.querySelector("#form1");

  if (validar(form)) {
    form.submit();
  }
});

function validar(form) {
  var email = form.email.value;
  var emailx = form.emailNovamente.value;
  var senha = form.pass.value;
  var senhax = form.pass2.value;
  var aceito = form.aceito.checked;
  var nome = form.nome.value;
  var rg = form.rg.value;
  var cpf = form.cpf.value;


  if (email == "" || email.indexOf('@') == -1) {
    alert('EMAIL INVALIDO');
    form.email.focus();
    return false;
  }
  if (emailx != email) {
    alert('OS EMAILS ESTÃO DIFERENTES');
    form.emailNovamente.focus();
    return false;
  }
  if (nome == "") {
    alert('DIGITE SEU NOME');
    form.nome.focus();
    return false;
  }
  if (cpf == "") {
    alert('DIGITE SEU CPF');
    form.cpf.focus();
    return false;
  }
  if (rg == "") {
    alert('DIGITE SEU RG');
    form.rg.focus();
    return false;
  }
  if (senha == "") {
    alert('DIGITE UMA SENHA')
    form.pass.focus();
    return false;
  }
  if (senhax != senha) {
    alert('AS SENHAS ESTÃO DIFERENTES');
    form.pass2.focus();
    return false;
  }
  if (aceito == "checked") {
    alert('MARQUE QUE ACEITA OS TERMO DE USO')
    form.aceito.focus();
    return false;
  }
  return true;
}
    </script>
    <script type="text/javascript" src="../js/javascript_slides.js"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.mask.js"></script>
    <script>
$(document).ready(function() {
  var $seuCampoCpf = $("#CPF");
  $seuCampoCpf.mask('000.000.000-00', {
    reverse: true
  });
});

$(document).ready(function() {
  var $seuCampoRg = $("#RG");
  $seuCampoRg.mask('00.000.000-0', {
    reverse: true
  });
});

$(document).ready(function() {
  var $seuCampoNascimento = $("#NASCIMENTO");
  $seuCampoNascimento.mask('00/00/0000', {
    reverse: true
  });
});

$(document).ready(function() {
  var $seuCampoTelefone = $("#TELEFONE");
  $seuCampoTelefone.mask('(00)0000-0000', {
    reverse: true
  });
});
    </script>
    <script>
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}
    </script>
  </body>

</html>