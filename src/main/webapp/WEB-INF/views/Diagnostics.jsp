<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Diagnostics[] all = (Diagnostics[]) request.getAttribute("all");
        Diagnostics currentDiagnostics = (Diagnostics) request.getAttribute("currentDiagnostics");
        Machines_clients_deposits[] allDeposit = (Machines_clients_deposits[]) request.getAttribute("allDeposit");
        Components_type[] allComponent_type = (Components_type[]) request.getAttribute("allComponent_type");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Diagnostics date</th>
                    <th>Clients</th>
                    <th>Machines</th>
                    <th class="dropdown">
                        <p class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Components <span class="caret"></span></p>
                        <ul class="dropdown-menu">
                            <li><a href="/Diagnostics">All</a></li>
                                <li role="separator" class="divider"></li>
                            <% for (int j = 0; j < allComponent_type.length; j++) { %>
                                <li><a href="/Diagnostics/<%= allComponent_type[j].getId() %>"><%= allComponent_type[j].getName() %></a></li>
                                <li role="separator" class="divider"></li>
                            <% } %>
                        </ul>
                    </th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getDiagnostics_date() %></td>
                    <td><%= all[i].getDeposit() != null ? all[i].getDeposit().getClient().getName() : "" %> </td>
                    <td><%= all[i].getDeposit() != null ? all[i].getDeposit().getModels().getName() + " " + all[i].getDeposit().getDimension().getInch() + "\"" : "" %> </td>
                    <td>
                        <% for (int j = 0; j < all[i].getComponents().length; j++) { %>
                            <p><%= all[i].getComponents()[j].getService().getName() + " - " + all[i].getComponents()[j].getComponent_type().getName() %></p>
                        <% } %>
                    </td>
                    <td>
                        <a href="/TraitDiagnostics/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Diagnostics/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                    </td>
                </tr>
                <% } %>
            </table>
            <center><nav><ul class="pagination pagination-lg"></ul></nav></center>
            <script src="/assets/myjs/pagination.js"></script>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-4">
            <form action="/Diagnostics" method="post">
                <input type="hidden" name="id" value="<%= currentDiagnostics != null ? currentDiagnostics.getId() : 0 %>">
                <% if (currentDiagnostics != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="diagnostics_date">Diagnostics date</label>
                    <input type="date" name="diagnostics_date" id="diagnostics_date" class="form-control" value="<%= currentDiagnostics != null ? currentDiagnostics.getDiagnostics_date() : "" %>">
                </div>
                <div class="form-group">
                    <label for="deposit">Machines clients deposits</label>
                    <select name="deposit" id="deposit" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allDeposit.length; j++) { %>
                        <option value="<%= allDeposit[j].getId() %>" <%= currentDiagnostics != null && currentDiagnostics.getDeposit() != null && currentDiagnostics.getDeposit().getId() == allDeposit[j].getId() ? "selected" : "" %>><%= allDeposit[j].getClient().getName() + " - " + allDeposit[j].getModels().getName() + " " + allDeposit[j].getDimension().getInch() + "\"" %>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">Valider</button>
            </form>
        </div>
</div>
    </div>

</body>
</html>