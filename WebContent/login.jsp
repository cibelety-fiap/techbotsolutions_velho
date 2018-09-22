<%@ include file="imports/cabeca.jsp"%>

<body>
        <article style="float: left;">
          <form id="form1">
            <label>Login:</label>
            <br>
            <input type="text" placeholder="Nome de usu&aacute;rio" name="usuario">
            <br><br>
            <label>Senha:</label>
            <br>
            <input type="password" placeholder="Senha" name="senha">
            <br><br>
            <input type="button" onclick="Login()" class="tamanho_botao" value="Login" id="enviar">
            <input type="reset" name="reset" value="Reset" class="tamanho_botao">
          </form>
          <br>
          <p>
            Caso n&atilde;o tenha cadastro <a href="cadastro.jsp">Clique aqui</a>
          </p>
        </article>
        <article style="float:right;padding-right: 26%;">
          <p>
            Para fazer login como usu&aacute;rio:
            <br> Login: <strong>user</strong>
            <br> Senha: <strong>user</strong>
          </p>
          <br>
          <p>
            Para fazer login como administrador:
            <br> Login: <strong>admin</strong>
            <br> Senha: <strong>admin</strong>
          </p>
        </article>
 
        <%@ include file="imports/rodape.jsp"%>