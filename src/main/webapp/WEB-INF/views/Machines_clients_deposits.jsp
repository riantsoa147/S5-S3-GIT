<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Machines_clients_deposits[] all = (Machines_clients_deposits[]) request.getAttribute("all");
        Machines_clients_deposits currentMachines_clients_deposits = (Machines_clients_deposits) request.getAttribute("currentMachines_clients_deposits");
        Dimensions[] allDimension = (Dimensions[]) request.getAttribute("allDimension");
        Models[] allModels = (Models[]) request.getAttribute("allModels");
        Clients[] allClient = (Clients[]) request.getAttribute("allClient");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table " id="table">
                <tr>
                    <th>Id</th>
                    <th>Deposit date</th>
                    <th>Dimensions</th>
                    <th>Models</th>
                    <th>Clients</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getDeposit_date() %></td>
                    <td><%= all[i].getDimension() != null ? all[i].getDimension().getInch() : "" %> </td>
                    <td><%= all[i].getModels() != null ? all[i].getModels().getName() : "" %> </td>
                    <td><%= all[i].getClient() != null ? all[i].getClient().getEmail() : "" %> </td>
                    <td>
                        <a href="/TraitMachines_clients_deposits/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Machines_clients_deposits/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Machines_clients_deposits" method="post">
                <input type="hidden" name="id" value="<%= currentMachines_clients_deposits != null ? currentMachines_clients_deposits.getId() : 0 %>">
                <% if (currentMachines_clients_deposits != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="deposit_date">Deposit date</label>
                    <input type="date" name="deposit_date" id="deposit_date" class="form-control" value="<%= currentMachines_clients_deposits != null ? currentMachines_clients_deposits.getDeposit_date() : "" %>">
                </div>
                <div class="form-group">
                    <label for="dimension">Dimensions</label>
                    <select name="dimension" id="dimension" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allDimension.length; j++) { %>
                        <option value="<%= allDimension[j].getId() %>" <%= currentMachines_clients_deposits != null && currentMachines_clients_deposits.getDimension() != null && currentMachines_clients_deposits.getDimension().getId() == allDimension[j].getId() ? "selected" : "" %>><%= allDimension[j].getInch() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="models">Models</label>
                    <select name="models" id="models" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allModels.length; j++) { %>
                        <option value="<%= allModels[j].getId() %>" <%= currentMachines_clients_deposits != null && currentMachines_clients_deposits.getModels() != null && currentMachines_clients_deposits.getModels().getId() == allModels[j].getId() ? "selected" : "" %>><%= allModels[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="client">Clients</label>
                    <select name="client" id="client" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allClient.length; j++) { %>
                        <option value="<%= allClient[j].getId() %>" <%= currentMachines_clients_deposits != null && currentMachines_clients_deposits.getClient() != null && currentMachines_clients_deposits.getClient().getId() == allClient[j].getId() ? "selected" : "" %>><%= allClient[j].getEmail() %>
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