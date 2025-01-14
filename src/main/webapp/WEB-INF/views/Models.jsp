<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Models[] all = (Models[]) request.getAttribute("all");
        Models currentModels = (Models) request.getAttribute("currentModels");
        Brands[] allBrand = (Brands[]) request.getAttribute("allBrand");
        Machines_type[] allMachine_type = (Machines_type[]) request.getAttribute("allMachine_type");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Brands</th>
                    <th>Machines type</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getName() %></td>
                    <td><%= all[i].getBrand() != null ? all[i].getBrand().getName() : "" %> </td>
                    <td><%= all[i].getMachine_type() != null ? all[i].getMachine_type().getName() : "" %> </td>
                    <td>
                        <a href="/TraitModels/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Models/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Models" method="post">
                <input type="hidden" name="id" value="<%= currentModels != null ? currentModels.getId() : 0 %>">
                <% if (currentModels != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" class="form-control" value="<%= currentModels != null ? currentModels.getName() : "" %>">
                </div>
                <div class="form-group">
                    <label for="brand">Brands</label>
                    <select name="brand" id="brand" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allBrand.length; j++) { %>
                        <option value="<%= allBrand[j].getId() %>" <%= currentModels != null && currentModels.getBrand() != null && currentModels.getBrand().getId() == allBrand[j].getId() ? "selected" : "" %>><%= allBrand[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="machine_type">Machines type</label>
                    <select name="machine_type" id="machine_type" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allMachine_type.length; j++) { %>
                        <option value="<%= allMachine_type[j].getId() %>" <%= currentModels != null && currentModels.getMachine_type() != null && currentModels.getMachine_type().getId() == allMachine_type[j].getId() ? "selected" : "" %>><%= allMachine_type[j].getName() %>
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