<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="container">
        <h1>📚 • API para Biblioteca</h1>
        <p>Este projeto consiste em uma API para gerenciamento de biblioteca, permitindo operações CRUD para clientes, livros, empréstimos e exemplares, utilizando JPA para interação com o banco de dados.</p>

<br><br><br>

<h2>🛠️ • Tecnologias Utilizadas</h2>
        <ul>
            <li><strong>Java</strong> (JDK 21 LTS)</li>
            <li><strong>Spring Boot</strong> (Framework)</li>
            <li><strong>JPA/Hibernate</strong> (Mapeamento Objeto-Relacional)</li>
            <li><strong>MySQL</strong> (Banco de Dados)</li>
            <li><strong>Maven</strong> (Gerenciador de Dependências)</li>
            <li><strong>IntelliJ IDEA</strong> (IDE)</li>
        </ul>

<br><br><br>

<h2>⚙️ • Configuração do Projeto</h2>
        <ol>
            <li>Verifique se o <strong>IntelliJ IDEA</strong> está instalado.</li>
            <li>Certifique-se de que o <strong>Maven</strong> está configurado.</li>
            <li>Atualize o arquivo <code>application.yaml</code> na pasta <code>resources</code> com suas credenciais de acesso ao banco de dados.</li>
            <li>Crie o banco de dados <code>biblioteca</code> no MySQL.</li>
        </ol>

<br><br><br>

<h2>🚀 • Iniciar a API</h2>
        <p>Para iniciar a API, execute a classe principal do projeto. As rotas para gerenciamento de clientes, livros, empréstimos e exemplares estarão disponíveis conforme definido nos controllers.</p>

<br><br><br>

<h2>🔍 • Endpoints e Operações CRUD</h2>
        <p>Abaixo estão os exemplos de uso dos endpoints para realizar operações CRUD no recurso <strong>Clientes</strong>. Utilize o <strong>Postman</strong> para enviar as requisições no formato <strong>JSON</strong> conforme descrito:</p>
        
<ul>
            <li><code>/clientes</code> - Gerencia informações dos clientes</li>
        </ul>

<br>

<h4>1. Listar Todos os Clientes</h4>
        <pre>GET http://localhost:8080/clientes</pre>

<br>

<h4>2. Consultar Cliente por ID</h4>
        <pre>GET http://localhost:8080/clientes/{id}</pre>

<br>

<h4>3. Criar Novo Cliente</h4>
        <pre>
POST http://localhost:8080/clientes
Content-Type: application/json
{
    "nome": "João Silva",                  // String (máx. 255 caracteres)
    "cpf": "123.456.789-00",               // String no formato ###.###.###-##
    "telefone": "+55 (11) 91234-5678",     // String no formato +## (##) #####-####
    "email": "joao.silva@example.com",     // String (máx. 255 caracteres, único)
    "apto": true                           // Boolean
}
</pre>

<br>

<h4>4. Atualizar Cliente</h4>
        <pre>
PUT http://localhost:8080/clientes/{id}
Content-Type: application/json
{
    "nome": "João Silva Atualizado",
    "cpf": "123.456.789-00",
    "telefone": "+55 (11) 98888-8888",
    "email": "joao.silva.atualizado@example.com",
    "apto": false
}
</pre>

<br>

<h4>5. Deletar Cliente</h4>
        <pre>DELETE http://localhost:8080/clientes/{id}</pre>

<br><br><br>

<h2>📜 • Licença</h2>
        <p>Este projeto é licenciado sob a <strong>Licença MIT</strong>.</p>

<br><br><br>

<h2>📬 • Contato</h2>
        <p>Para dúvidas, sugestões ou feedback, entre em contato pelo email: 
            <a href="mailto:scythe.github@gmail.com">scythe.github@gmail.com</a>
        </p>
    </div>
</body>
</html>
