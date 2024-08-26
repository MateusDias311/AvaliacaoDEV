<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Exames</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Lista de Exames</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome do Exame</th>
                <th>Data</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="exame" items="${exames}">
                <tr>
                    <td>${exame.id}</td>
                    <td>${exame.nome}</td>
                    <td>${exame.data}</td>
                    <td>
                        <a href="editarExame.action?id=${exame.id}">Editar</a>
                        <a href="excluirExame.action?id=${exame.id}" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <a href="novoExame.jsp">Adicionar Novo Exame</a>
</body>
</html>
