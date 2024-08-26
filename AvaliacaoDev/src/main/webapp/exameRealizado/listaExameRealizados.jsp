<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title><s:text name="label.titulo.pagina.consulta"/></title>
    <link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">
    <div class="container mt-5">
        <div class="row mb-3">
            <div class="col-sm">
                <s:form action="filtrarExameRealizados" method="post" cssClass="form-inline">
                    <div class="input-group">
                        <span class="input-group-text"><s:text name="label.buscar.por"/></span>
                        <s:select cssClass="form-select" name="filtrar.opcoesCombo"
                                  list="listaOpcoesCombo" headerKey="" headerValue="Escolha..."
                                  listKey="%{codigo}" listValue="%{descricao}" />
                        <s:textfield cssClass="form-control" name="filtrar.valorBusca" />
                        <button type="submit" class="btn btn-primary"><s:text name="label.pesquisar"/></button>
                    </div>
                </s:form>
            </div>
        </div>

        <div class="row">
            <div class="col-sm">
                <table class="table table-striped table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th><s:text name="label.id"/></th>
                            <th><s:text name="label.nomeExame"/></th>
                            <th><s:text name="label.data"/></th>
                            <th class="text-end"><s:text name="label.acao"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="examesRealizados" var="exame">
                            <tr>
                                <td><s:property value="#exame.id"/></td>
                                <td><s:property value="#exame.nome"/></td>
                                <td><s:property value="#exame.data"/></td>
                                <td class="text-end">
                                    <s:url action="editarExameRealizado" var="editarUrl">
                                        <s:param name="exameRealizadoVo.rowid" value="#exame.id" />
                                    </s:url>
                                    <a href="${editarUrl}" class="btn btn-warning text-white">
                                        <s:text name="label.editar"/>
                                    </a>
                                    <s:url action="excluirExameRealizado" var="excluirUrl">
                                        <s:param name="exameRealizadoVo.rowid" value="#exame.id" />
                                    </s:url>
                                    <a href="${excluirUrl}" class="btn btn-danger"
                                       onclick="return confirm('<s:text name="label.confirmar.exclusao"/>');">
                                        <s:text name="label.excluir"/>
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row">
            <div class="col-sm">
                <s:url action="novoExameRealizado" var="novoExameUrl"/>
                <a href="${novoExameUrl}" class="btn btn-success">
                    <s:text name="label.novoExameRealizado"/>
                </a>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
