<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Maintenances_details[] all = (Maintenances_details[]) request.getAttribute("all");
        Maintenances_details currentMaintenances_details = (Maintenances_details) request.getAttribute("currentMaintenances_details");
        Components_type[] allComponent_type = (Components_type[]) request.getAttribute("allComponent_type");
        Services[] allService = (Services[]) request.getAttribute("allService");
        Components[] allComponent = (Components[]) request.getAttribute("allComponent");
        Maintenances[] allMaintenance = (Maintenances[]) request.getAttribute("allMaintenance");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Quantity</th>
                    <th>Unit price</th>
                    <th>Sell price</th>
                    <th>Components type</th>
                    <th>Services</th>
                    <th>Components</th>
                    <th>Maintenances</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getQuantity() %></td>
                    <td><%= all[i].getUnit_price() %></td>
                    <td><%= all[i].getSell_price() %></td>
                    <td><%= all[i].getComponent_type() != null ? all[i].getComponent_type().getName() : "" %> </td>
                    <td><%= all[i].getService() != null ? all[i].getService().getName() : "" %> </td>
                    <td><%= all[i].getComponent() != null ? all[i].getComponent().getName() : "" %> </td>
                    <td><%= all[i].getMaintenance() != null ? all[i].getMaintenance().getDeposit().getClient().getName() : "" %> </td>
                    <td>
                        <a href="/TraitMaintenances_details/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Maintenances_details/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Maintenances_details" method="post">
                <input type="hidden" name="id" value="<%= currentMaintenances_details != null ? currentMaintenances_details.getId() : 0 %>">
                <% if (currentMaintenances_details != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input type="number" name="quantity" id="quantity" class="form-control" value="<%= currentMaintenances_details != null ? currentMaintenances_details.getQuantity() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="unit_price">Unit price</label>
                    <input type="number" name="unit_price" id="unit_price" class="form-control" value="<%= currentMaintenances_details != null ? currentMaintenances_details.getUnit_price() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="sell_price">Sell price</label>
                    <input type="number" name="sell_price" id="sell_price" class="form-control" value="<%= currentMaintenances_details != null ? currentMaintenances_details.getSell_price() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="component_type">Components type</label>
                    <select name="component_type" id="component_type" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allComponent_type.length; j++) { %>
                        <option value="<%= allComponent_type[j].getId() %>" <%= currentMaintenances_details != null && currentMaintenances_details.getComponent_type() != null && currentMaintenances_details.getComponent_type().getId() == allComponent_type[j].getId() ? "selected" : "" %>><%= allComponent_type[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="service">Services</label>
                    <select name="service" id="service" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allService.length; j++) { %>
                        <option value="<%= allService[j].getId() %>" <%= currentMaintenances_details != null && currentMaintenances_details.getService() != null && currentMaintenances_details.getService().getId() == allService[j].getId() ? "selected" : "" %>><%= allService[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="component">Components</label>
                    <select name="component" id="component" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allComponent.length; j++) { %>
                        <option value="<%= allComponent[j].getId() %>" <%= currentMaintenances_details != null && currentMaintenances_details.getComponent() != null && currentMaintenances_details.getComponent().getId() == allComponent[j].getId() ? "selected" : "" %>><%= allComponent[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="maintenance">Maintenances</label>
                    <select name="maintenance" id="maintenance" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allMaintenance.length; j++) { %>
                        <option value="<%= allMaintenance[j].getId() %>" <%= currentMaintenances_details != null && currentMaintenances_details.getMaintenance() != null && currentMaintenances_details.getMaintenance().getId() == allMaintenance[j].getId() ? "selected" : "" %>><%= allMaintenance[j].getDeposit().getClient().getName() %>
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