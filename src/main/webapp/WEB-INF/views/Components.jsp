<%@ include file="/WEB-INF/views/templates/header.jsp" %>
<html>
<body>
    <div class="container">
    <%
        Components[] all = (Components[]) request.getAttribute("all");
        Components currentComponents = (Components) request.getAttribute("currentComponents");
        Components_type[] allComponent_type = (Components_type[]) request.getAttribute("allComponent_type");
        Models[] allModels = (Models[]) request.getAttribute("allModels");
        Dimensions[] allDimension = (Dimensions[]) request.getAttribute("allDimension");
    %>
    <div class="row">
        <div class="col-md-7 tableContainer ">
            <table class="table" id="table">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Sell price</th>
                    <th>Stock</th>
                    <th>Components type</th>
                    <th>Models</th>
                    <th>Dimensions</th>
                    <th>Actions</th>
                    <th> </th>
                </tr>
                <% for (int i = 0; i < all.length; i++) { %>
                <tr>
                    <td><%= all[i].getId() %></td>
                    <td><%= all[i].getName() %></td>
                    <td><%= all[i].getSell_price() %></td>
                    <td><%= all[i].getStock() %></td>
                    <td><%= all[i].getComponent_type() != null ? all[i].getComponent_type().getName() : "" %> </td>
                    <td><%= all[i].getModels() != null ? all[i].getModels().getName() : "" %> </td>
                    <td><%= all[i].getDimension() != null ? all[i].getDimension().getInch() : "" %> </td>
                    <td>
                        <a href="/TraitComponents/<%= all[i].getId() %>" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                        <a href="/Components/delete/<%= all[i].getId() %>" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
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
            <form action="/Components" method="post">
                <input type="hidden" name="id" value="<%= currentComponents != null ? currentComponents.getId() : 0 %>">
                <% if (currentComponents != null) { %>
                    <input type="hidden" name="mode" value="u">
                <% } %>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name" class="form-control" value="<%= currentComponents != null ? currentComponents.getName() : "" %>">
                </div>
                <div class="form-group">
                    <label for="sell_price">Sell price</label>
                    <input type="number" name="sell_price" id="sell_price" class="form-control" value="<%= currentComponents != null ? currentComponents.getSell_price() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="stock">Stock</label>
                    <input type="number" name="stock" id="stock" class="form-control" value="<%= currentComponents != null ? currentComponents.getStock() : 0 %>">
                </div>
                <div class="form-group">
                    <label for="component_type">Components type</label>
                    <select name="component_type" id="component_type" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allComponent_type.length; j++) { %>
                        <option value="<%= allComponent_type[j].getId() %>" <%= currentComponents != null && currentComponents.getComponent_type() != null && currentComponents.getComponent_type().getId() == allComponent_type[j].getId() ? "selected" : "" %>><%= allComponent_type[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="models">Models</label>
                    <select name="models" id="models" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allModels.length; j++) { %>
                        <option value="<%= allModels[j].getId() %>" <%= currentComponents != null && currentComponents.getModels() != null && currentComponents.getModels().getId() == allModels[j].getId() ? "selected" : "" %>><%= allModels[j].getName() %>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="dimension">Dimensions</label>
                    <select name="dimension" id="dimension" class="form-control">
                        <option value="">-- choose --</option>
                        <% for (int j = 0; j < allDimension.length; j++) { %>
                        <option value="<%= allDimension[j].getId() %>" <%= currentComponents != null && currentComponents.getDimension() != null && currentComponents.getDimension().getId() == allDimension[j].getId() ? "selected" : "" %>><%= allDimension[j].getInch() %>
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